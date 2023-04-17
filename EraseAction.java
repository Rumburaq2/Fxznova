package com.example.fxznova;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EraseAction extends AbstractAction {
    int x;
    int y;

    boolean alive = false;
    int pocitadlo = 0;//kolikrat bylo tlacitko kliknuto
    EraseAction(int i, int j) {
        super("");
        this.x = i;
        this.y = j;
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
            }
            else if(pocitadlo % 2 != 2){
                ((JButton) arg0.getSource()).setBackground(new Color(238, 238, 238));
                ((JButton) arg0.getSource()).setContentAreaFilled(true);
                ((JButton) arg0.getSource()).setOpaque(true);
                alive = false;
            }

            System.out.println(this.x + " " + this.y + " " + this.alive);
            pocitadlo++;
        }
    }
}
