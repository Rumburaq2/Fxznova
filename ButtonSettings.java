package com.example.fxznova;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ButtonSettings extends AbstractAction {
    int x;
    int y;

    boolean alive = false;

    ButtonSettings(int i, int j) {
        super("");
        this.x = i;
        this.y = j;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        //System.out.println("Do " + arg0.getActionCommand());

        if (arg0.getSource() instanceof JButton) {
            if(alive == false) {//na button se kliknulo po prve
                ((JButton) arg0.getSource()).setBackground(Color.BLUE);//nastavime na modro
                ((JButton) arg0.getSource()).setContentAreaFilled(true);
                ((JButton) arg0.getSource()).setOpaque(true);
                alive = true;

            }
            else if(alive == true){//na button se kliknulo po druhe
                ((JButton) arg0.getSource()).setBackground(new Color(238, 238, 238));//nastavime na sedivo
                ((JButton) arg0.getSource()).setContentAreaFilled(true);
                ((JButton) arg0.getSource()).setOpaque(true);
                alive = false;
            }
            System.out.println(this.x + " " + this.y + " " + this.alive);
        }
    }
}