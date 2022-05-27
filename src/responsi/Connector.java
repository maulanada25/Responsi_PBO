/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responsi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 *
 * @author MYWINDOWS
 */
public class Connector {
    String DBurl = "jdbc:mysql://localhost/movie_db";
    String DBid = "root";
    String BDpw = "";
    
    String data[] = new String[2];
    Connection conn;
    Statement statement;
    public Connector(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(DBurl, DBurl, DBurl);
            System.out.println("Connection Success");
        } catch (Exception e) {
            System.out.println("Connection Failed "+ e.getMessage());
        }
    }
}
