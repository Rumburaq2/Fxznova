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
                //musíme array otocit protoze mi to rekl Zeus
                rotateMatrix(Game.grid_arr);
                new GameOfLife().main(null);//zavola javafx pro zobrazeni
            }
        });

        jb2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("rucni nastaveni");
                //musíme array otocit protoze mi to rekl Zeus
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

    public static void setAllDead(Cell[][] pole){

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                 pole[i][j].stav = 0;

            }
        }
    }

    //https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
    private static void rotateMatrix(EraseAction[][] mat)
    {
        int N = 20;
        //EraseAction e = new EraseAction(0,0);
        // Consider all squares one by one
        for (int x = 0; x < N / 2; x++) {
            // Consider elements in group
            // of 4 in current square
            for (int y = x; y < N - x - 1; y++) {
                // Store current cell in
                // temp variable
                EraseAction e = new EraseAction(0,0);
                e = mat[x][y];
                x = e.x;
                y = e.y;

                // Move values from right to top
                mat[x][y] = mat[y][N - 1 - x];

                // Move values from bottom to right
                mat[y][N - 1 - x]
                        = mat[N - 1 - x][N - 1 - y];

                // Move values from left to bottom
                mat[N - 1 - x][N - 1 - y]
                        = mat[N - 1 - y][x];

                // Assign temp to left
                mat[N - 1 - y][x] = e;
            }
        }
    }
}