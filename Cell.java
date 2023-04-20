package com.example.fxznova;

public class Cell {
    int x;
    int y;
    int stav = 0;//stav 0 - mrtva, stav 1 - ziva, reprezentovano cislem pro ucely scitani

    public Cell(int x, int y, int stav) {
        this.x = x;
        this.y = y;
        this.stav = stav;
    }
}
