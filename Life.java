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

    //private Cell[][] next;
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
        //next = grid;
        //nastavyme vsechny bunky v next na 0
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                next[i][j] = new Cell(i,j, 0);
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
        //Cell[][] next = new Cell[rows][cols];//pole pro dalsi generaci

        //next = grid;//mby fix tohle - next na zacatku nema zadnou hodnotu takze jsem
        // to vyresil tak ze se na zacatku skopiruje z grid. pak se to ale dela pokazdy zbytecne protoze to je
        // i na konci
        //pro kazdou bunku
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell c = grid[i][j];
                int neighbors = countAliveNeighbors(c, grid);

                /*
                if (c.stav == 0 && neighbors == 3) {
                    next[i][j].stav = 1;
                }
                else if (c.stav == 1) {
                    if (neighbors < 2) {
                        next[i][j].stav = 0;
                    }
                    else if (neighbors > 3) {
                        next[i][j].stav = 0;
                    }
                    else {//zustane stejny stav
                        next[i][j].stav = 1;
                    }
                }

                 */

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
        //grid = next;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = next[i][j];
            }
        }
        draw();
    }


    /*
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

     */


    //count neigh funkce z myho og kodu - bude zde místo aktualni funkce countAliveNeighbors
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