package com.example.fxznova;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    static int ROWS = 60;//velikosti pole
    static int COLS = 60;

    /*
    //max velikost:
    // ROWS = 90;
    // COLS = 168;
    // GOL WIDTH = 1700;
    // GOL HEIGHT = 900;
    // hazi bufferoverflow ale i tak funguje

    // max velikost:
    // ROWS = 90;
    // COLS = 100;
    // GOL WIDTH = 1000;
    // GOL HEIGHT = 900;
    // NEhazi bufferoverflow; funguje ale je to tam dive cerny

    // max velikost:
    // ROWS = 80;
    // COLS = 80;
    // GOL WIDTH = 800;
    // GOL HEIGHT = 800;
    // NEhazi bufferoverflow; funguje ale je to tam dive cerny
     */

    public static boolean nastaveno = false;

    public static void main(String[] args) {

        // vytvorime Frame
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Conwayova hra života");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Definujeme Buttons
        JButton jb1 = new JButton("Spustit simulaci");
        JButton jb2 = new JButton("Konfigurace počátečního stavu");
        JButton jb3 = new JButton("Button 3");

        jb1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //spustí simulaci
                new GameOfLife().main(null);//zavola javafx pro zobrazeni
            }
        });

        jb2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //spusti rucni konfiguraci pocatecniho stavu
                nastaveno = true;
                ButtonPanel g = new ButtonPanel(ROWS,COLS, 0,0);
            }
        });

        // pridame buttony do panelu
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(jb1);
        panel.add(jb2);
        panel.add(jb3);

        // zobrazime frame
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}