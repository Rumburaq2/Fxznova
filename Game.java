package com.example.fxznova;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    private JPanel panel;

    public Game(int rows,int cols,int hgap,int vgap){
        JFrame frame=new JFrame("Set JButton background color");//todo Jframe nefunguje idk
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel=new JPanel(new GridLayout(rows, cols, hgap, vgap));
        panel.setPreferredSize(new Dimension(500, 500));
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                JButton btn=new JButton(new EraseAction(i , j));
                btn.setBorder(BorderFactory.createLineBorder(Color.black));
                panel.add(btn);
            }
        }

        add(panel);
        pack();
        setVisible(true);
    }
}
