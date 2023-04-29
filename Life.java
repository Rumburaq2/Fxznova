package com.example.fxznova;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;


public class Life {
    private final int rows;
    private final int cols;
    private Cell[][] grid;
    private Random random = new Random();
    private final GraphicsContext graphics;

    private static final int width = 500;
    private static final int height = 500;
    private static final int cellSize = 10;

    private Cell[][] next = new Cell[20][20];

    public Life(int rows, int cols, GraphicsContext graphics) {
        this.rows = rows;
        this.cols = cols;
        this.graphics = graphics;
        grid = new Cell[rows][cols];
    }


    public void init() {
        EraseAction e = new EraseAction(0,0);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //grid[i][j] = random.nextInt(2);//vykomentovat
                //importneme pomocí getteru 2D pole z tridy FlowLayout/Nastavovače
                e = Game.grid_arr[i][j];
                if(e.alive == true ){
                    //grid[i][j] = 1;
                    grid[i][j] = new Cell(i, j, 1);
                }
                else if(e.alive == false){
                    //grid[i][j] = 0;
                    grid[i][j] = new Cell(i, j, 0);
                }

            }
        }
        //nastavyme vsechny bunky v next na 0
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                next[i][j] = new Cell(i,j, 0);
            }
        }

        draw();
    }

    private void draw() {
        // clear graphics
        graphics.setFill(Color.LAVENDER);
        graphics.fillRect(0, 0, width, height);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].stav == 1) {
                    // first rect will end up becoming the border
                    graphics.setFill(Color.gray(0.5, 0.5));
                    graphics.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                    graphics.setFill(Color.PURPLE);
                    graphics.fillRect((j * cellSize) + 1, (i * cellSize) + 1, cellSize - 2, cellSize - 2);
                }else {
                    graphics.setFill(Color.gray(0.5, 0.5));
                    graphics.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                    graphics.setFill(Color.LAVENDER);
                    graphics.fillRect((j * cellSize) + 1, (i * cellSize) + 1, cellSize - 2, cellSize - 2);
                }
            }
        }
    }

    /*
    public void tick() {
        int[][] next = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int neighbors = countAliveNeighbors(i, j);

                if (neighbors == 3) {
                    next[i][j] = 1;
                }else if (neighbors < 2 || neighbors > 3) {
                    next[i][j] = 0;
                }else {
                    next[i][j] = grid[i][j];
                }
            }
        }
        grid = next;
        draw();
    }
     */


    public void tick() {
        //pro kazdou bunkku
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell c = grid[i][j];
                int neighbors = countAliveNeighbors(c, grid);

                //definujeme pravidla - game of life
                if (c.stav == 0 && neighbors == 3) {
                    Cell x = new Cell(i, j, 1);
                    next[i][j] = x;
                }
                else if (c.stav == 1) {
                    if (neighbors < 2) {
                        Cell x = new Cell(i, j, 0);
                        next[i][j] = x;
                    }
                    else if (neighbors > 3) {
                        Cell x = new Cell(i, j, 0);
                        next[i][j] = x;
                    }
                    else {//zustane stejny stav
                        //Cell x = new Cell(i, j, 1);
                        next[i][j] = grid[i][j];
                    }
                }
            }
        }

        //prekopirujeme a vykreslime vysledek
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = next[i][j];
            }
        }
        draw();
    }

    private int countAliveNeighbors(Cell c, Cell maze[][]) {
        int sum = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int col = (c.x + i + 20) % 20;
                int row = (c.y + j + 20) % 20;
                sum += maze[col][row].stav;
            }
        }
        int u = c.x;
        int z = c.y;
        sum = sum - maze[u][z].stav;
        return sum;
    }
}