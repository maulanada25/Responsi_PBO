/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responsi;

import java.awt.event.*;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
import javax.swing.*;
//import javax.swing.JTable;

/**
 *
 * @author MYWINDOWS
 */
public class Controller {
    Model mod = new Model();
    MovieView movieView = new MovieView();
    public String data;
    public Controller(Model mod, MovieView movieView){
        this.mod = mod;
        this.movieView = movieView;
        
        if(mod.getNumData()!=0){
            String dataMovie[][] = mod.readMovie();
            movieView.table.setModel((new JTable(dataMovie, movieView.columnName)).getModel());
        }else{
            JOptionPane.showMessageDialog(null, "Data does not exist");
        }
        
        movieView.btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String title = movieView.getJudul();
                double plot = Double.parseDouble(movieView.getAlur());
                double chara = Double.parseDouble(movieView.getPenokohan());
                double act = Double.parseDouble(movieView.getAkting());
                double score = (Alur+Penokohan+Akting)/3;
                modelContact.insertData(title, plot, chara, act, score);
         
                String dataMovie[][] = modelContact.readContact();
                movieView.tabel.setModel((new JTable(dataMovie, movieView.namaKolom)).getModel());
            }
        });
    }
}
