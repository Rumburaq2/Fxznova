package com.example.fxznova;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    private JPanel panel;

    //private JPanel panel2;

    public Game(int rows,int cols,int hgap,int vgap){
        JFrame frame=new JFrame("Set JButton background color");//todo Jframe nefunguje idk
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel=new JPanel(new GridLayout(rows, cols, hgap, vgap));
        panel.setPreferredSize(new Dimension(500, 500));
        //Border border = BorderFactory.createTitledBorder("simulace ig");
        //panel.setBorder(border);
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                JButton btn=new JButton(new EraseAction(i , j));
                btn.setBorder(BorderFactory.createLineBorder(Color.black));
                panel.add(btn);
            }
        }
        /*
        panel2=new JPanel(new GridLayout(1, 1));
        JButton b=new JButton("Zastavit simulaci");
        b.setBounds(50,100,95,30);
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //tf.setText("Welcome to Javatpoint.");
                System.out.println("Zasvaveno");
                //Game g = new Game(20,20, 0,0);
            }
        });
         */
        add(panel);
        // frame.add(panel2);
        //panel2.add(b);

        pack();
        setVisible(true);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//todo taky nefunguje
    }
}
