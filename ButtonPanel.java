package com.example.fxznova;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JFrame {
    private JPanel panel;
    private JPanel PanelPanelu;

    private JPanel ButtonPanel;

    public static ButtonSettings[][] grid_arr = new ButtonSettings[20][20];


    public ButtonPanel(int rows, int cols, int hgap, int vgap){
        JFrame frame=new JFrame("Pocatecni konfigurace");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        PanelPanelu = new JPanel();
        PanelPanelu.setPreferredSize(new Dimension(500, 600));

        panel=new JPanel(new GridLayout(rows, cols, hgap, vgap));
        panel.setPreferredSize(new Dimension(500, 500));
        //ulozime si buttony do pole aby se mohla ulozit jejich hodnota a poslat dal
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                grid_arr[i][j] = new ButtonSettings(i, j);
            }
        }

        //pridavame buttony do panelu
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                JButton btn=new JButton(grid_arr[i][j]);
                btn.setBorder(BorderFactory.createLineBorder(Color.black));//ohranicime kazdy button cernou carou
                panel.add(btn);
            }
        }

        //panel pro tlacitko pro ulozeni konfigurace
        // - ve skutecnost neni potreba(?) - nic neuklada
        ButtonPanel = new JPanel();
        ButtonPanel.setPreferredSize(new Dimension(500, 100));
        JButton b=new JButton("Ulozit nastavení");
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("ulozeno!");
                //System.out.println(grid_arr[0][1].alive);
            }
        });

        //pridame panely do JFrame
        ButtonPanel.add(b);
        PanelPanelu.add(panel);
        PanelPanelu.add(ButtonPanel);
        add(PanelPanelu);
        pack();
        setVisible(true);
    }
}
