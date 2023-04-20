package com.example.fxznova;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {
    private JPanel panel;
    private JPanel PanelPanelu;

    private JPanel ButtonPanel;

    public Game(int rows,int cols,int hgap,int vgap){
        JFrame frame=new JFrame("Set JButton background color");//todo Jframe nefunguje idk
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        PanelPanelu = new JPanel();
        PanelPanelu.setPreferredSize(new Dimension(500, 600));

        panel=new JPanel(new GridLayout(rows, cols, hgap, vgap));
        panel.setPreferredSize(new Dimension(500, 500));
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                JButton btn=new JButton(new EraseAction(i , j));
                btn.setBorder(BorderFactory.createLineBorder(Color.black));
                panel.add(btn);
            }
        }

        ButtonPanel = new JPanel();
        ButtonPanel.setPreferredSize(new Dimension(500, 100));

        JButton b=new JButton("Ulozit nastavení");
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //tf.setText("Welcome to Javatpoint.");
                System.out.println("ulozeno!");
                //Game g = new Game(20,20, 0,0);
            }
        });

        ButtonPanel.add(b);

        PanelPanelu.add(panel);
        PanelPanelu.add(ButtonPanel);

        //add(panel);
        add(PanelPanelu);
        pack();
        setVisible(true);

    }
}
