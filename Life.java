package com.example.fxznova;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;


public class Life {
    private final int rows;
    private final int cols;
    private int[][] grid;
    private Random random = new Random();
    private final GraphicsContext graphics;

    private static final int width = 500;
    private static final int height = 500;
    private static final int cellSize = 10;

    public Life(int rows, int cols, GraphicsContext graphics) {
        this.rows = rows;
        this.cols = cols;
        this.graphics = graphics;
        grid = new int[rows][cols];
    }


    public void init() {
        EraseAction e = new EraseAction(0,0);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //grid[i][j] = random.nextInt(2);//vykomentovat
                //importneme pomocí getteru 2D pole z tridy FlowLayout/Nastavovače
                e = Game.grid_arr[i][j];
                if(e.alive == true ){
                    grid[i][j] = 1;
                }
                else if(e.alive == false){
                    grid[i][j] = 0;
                }

            }
        }
        draw();
    }

    //btw funkce opravena - bylo tam inverzne prohozeny kresleni - kreslilo spatne - prohodil jsem i a j
    //problem --> vyresen ez gg
    private void draw() {
        // clear graphics
        graphics.setFill(Color.LAVENDER);
        graphics.fillRect(0, 0, width, height);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
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
        /*
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    // first rect will end up becoming the border
                    graphics.setFill(Color.gray(0.5, 0.5));
                    graphics.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    graphics.setFill(Color.PURPLE);
                    graphics.fillRect((i * cellSize) + 1, (j * cellSize) + 1, cellSize - 2, cellSize - 2);
                }else {
                    graphics.setFill(Color.gray(0.5, 0.5));
                    graphics.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    graphics.setFill(Color.LAVENDER);
                    graphics.fillRect((i * cellSize) + 1, (j * cellSize) + 1, cellSize - 2, cellSize - 2);
                }
            }
        }
         */



    }

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


    private int countAliveNeighbors(int i, int j) {
        int sum = 0;
        int iStart = i == 0 ? 0 : -1;
        int iEndInclusive = i == grid.length - 1 ? 0 : 1;
        int jStart = j == 0 ? 0 : -1;
        int jEndInclusive = j == grid[0].length - 1 ? 0 : 1;

        for (int k = iStart; k <= iEndInclusive; k++) {
            for (int l = jStart; l <= jEndInclusive; l++) {
                sum += grid[i + k][l + j];
            }
        }

        sum -= grid[i][j];

        return sum;
    }

    /*
    //count neigh funkce z myho og kodu - bude zde místo aktualni funkce countAliveNeighbors
    private static int countAliveNeighbors(Cell c, Cell maze[][]) {
        int sum = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int col = (c.x + i + SLOUPCE) % SLOUPCE;
                int row = (c.y + j + SLOUPCE) % SLOUPCE;
                sum += maze[col][row].stav;
            }
        }
        int u = c.x;
        int z = c.y;
        sum = sum - maze[u][z].stav;
        return sum;
    }

     */
}