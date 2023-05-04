package com.example.fxznova;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.example.fxznova.MainMenu.COLS;
import static com.example.fxznova.MainMenu.ROWS;

public class ButtonPanel extends JFrame {
    private JPanel panel;
    private JPanel PanelPanelu;


    public static ButtonSettings[][] grid_arr = new ButtonSettings[ROWS][COLS];


    public ButtonPanel(int rows, int cols, int hgap, int vgap){
        JFrame frame=new JFrame("Pocatecni konfigurace");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        PanelPanelu = new JPanel();
        PanelPanelu.setPreferredSize(new Dimension(800, 810));//500, 600

        panel=new JPanel(new GridLayout(rows, cols, hgap, vgap));
        panel.setPreferredSize(new Dimension(800, 800));//500, 500
        //ulozime si buttony do pole aby se mohla ulozit jejich hodnota a poslat dal
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
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


        //pridame panely do JFrame
        PanelPanelu.add(panel);
        add(PanelPanelu);
        pack();
        setVisible(true);
    }
}
