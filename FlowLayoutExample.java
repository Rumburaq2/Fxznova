package com.example.fxznova;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlowLayoutExample {

    public static void main(String[] args) {

        // Create and set up a frame window
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define new buttons
        JButton jb1 = new JButton("Spustit simulaci");
        JButton jb2 = new JButton("Konfigurace počátečního stavu");
        JButton jb3 = new JButton("Button 3");

        jb1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Spustit simulaci");
                new GameOfLife().main(null);//zavola javafx pro zobrazeni
            }
        });

        jb2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("rucni nastaveni");
                Game g = new Game(20,20, 0,0);
            }
        });

        // Define the panel to hold the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(jb1);
        panel.add(jb2);
        panel.add(jb3);

        // Set the window to be visible as the default to be false
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}