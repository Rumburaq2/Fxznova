package com.example.fxznova;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EraseAction extends AbstractAction {
    int x;
    int y;

    boolean alive = false;
    int pocitadlo = 0;//kolikrat bylo tlacitko kliknuto

    Cell[][] grid_arr = new Cell[20][20];



    EraseAction(int i, int j) {
        super("");
        this.x = i;
        this.y = j;
        grid_arr[i][j] = new Cell(i, j, 0);

        /*
        for (int k = 0; k < 20; k++) {
            for (int l = 0; l < 20; l++) {
                grid_arr[k][l] = new Cell(i, j, 0);
            }
        }

         */
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        //System.out.println("Do " + arg0.getActionCommand());

        if (arg0.getSource() instanceof JButton) {
            if(pocitadlo % 2 == 0) {
                ((JButton) arg0.getSource()).setBackground(Color.BLUE);
                ((JButton) arg0.getSource()).setContentAreaFilled(true);
                ((JButton) arg0.getSource()).setOpaque(true);
                alive = true;
                grid_arr[this.x][this.y].stav = 1;
            }
            else if(pocitadlo % 2 != 2){
                ((JButton) arg0.getSource()).setBackground(new Color(238, 238, 238));
                ((JButton) arg0.getSource()).setContentAreaFilled(true);
                ((JButton) arg0.getSource()).setOpaque(true);
                alive = false;
                grid_arr[this.x][this.y].stav = 0;
            }

            System.out.println(this.x + " " + this.y + " " + this.alive);
            pocitadlo++;


        }
    }

    public Cell[][] getArray() {
        return grid_arr.clone();
    }

    public Cell[][] getGrid_arr() {
        return grid_arr;
    }
}