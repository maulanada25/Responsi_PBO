/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responsi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

/**
 *
 * @author MYWINDOWS
 */
public class Model {
//    String name, plot, chara, act;
    
    static final String JDBC_DRIVER = "com.mysql.cj.jdbcDriver";
    static final String DB_URL = "jdbc:mysql://localhost/movie_db";
    static final String USER = "root";
    static final String PASS = "";
    
    Connection conn;
    Statement state;
    
    public Model(){
        try {
            Class.forName(JDBC_DRIVER);
            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Success");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Failed bro");
        }
    }
    
    public String[][] readMovie(){
        try {
            int totData = 0;
            
            String data[][] = new String[getNumData()][5]; 
            
            String query = "SELECT * FROM movie"; 
            ResultSet resultSet = state.executeQuery(query);
            while (resultSet.next()){
                data[totData][0] = resultSet.getString("title");
                data[totData][1] = String.valueOf(resultSet.getDouble("plot"));                
                data[totData][2] = String.valueOf(resultSet.getDouble("character"));
                data[totData][3] = String.valueOf(resultSet.getDouble("acting"));
                data[totData][4] = String.valueOf(resultSet.getDouble("score"));
                totData++;
            }
            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
        
    }
    
    public void insertData(String title, double plot, double chara, double act, double score){
        int totData = 0;
//        double score;
        try {
           String query = "SELECT * FROM movie WHERE title='" + title+"'"; 
           System.out.println(title + " " + plot + " " + chara + " " + act);
           ResultSet resultSet = state.executeQuery(query);
           
           while (resultSet.next()){ 
                totData++;
            }
//            score = (plot+chara+act)/3;
            if (totData==0) {
                query = "INSERT INTO movie(title,plot,character,acting,score) VALUES('"+title +"','"+plot+"','"+chara+"','"+act+"','"+score+"')";
           
                state = (Statement) conn.createStatement();
                state.executeUpdate(query);
                System.out.println("Success");
                JOptionPane.showMessageDialog(null, "Success");
            }
            else {
                JOptionPane.showMessageDialog(null, "Data already existed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());   
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void updateData(String title, double plot, double chara, double act, double score){
        int totData=0;
        try {
            String query = "SELECT * FROM movie WHERE title='" + title +"'"; 
           ResultSet resultSet = state.executeQuery(query);
           
           while (resultSet.next()){ 
                totData++;
            }
           
             if (totData==1) {
                query = "UPDATE movie SET plot='" + plot + "', character='" + chara + "', score='"+ score+"' WHERE title='" + title+"'"; 
                state = (Statement) conn.createStatement();
                state.executeUpdate(query); //execute querynya
                System.out.println("Success");
                JOptionPane.showMessageDialog(null, "Data Updated");
             }
             else {
                 JOptionPane.showMessageDialog(null, "Data does not exist");
             }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());   
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void deleteData(String title){
        try{
            String query = "DELETE FROM movie WHERE title = '"+title+"'";
            state = conn.createStatement();
            state.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Deleted");
            
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getNumData() {
        int totData=0;
        try {
            state = conn.createStatement();
            String query = "SELECT * FROM movie";
            ResultSet resultSet = state.executeQuery(query);
            while (resultSet.next()){ 
                totData++;
            }
            return totData;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }

}
