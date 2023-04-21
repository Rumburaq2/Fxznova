package com.example.fxznova;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class GameOfLife extends Application {

    private static final int width = 500;//muzeme vypocitat vychozi hodnotu podle poctu bunek
    private static final int height = 500;
    private static final int cellSize = 10;

    EraseAction[][] grid_ar = new EraseAction[20][20];

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, width, height + 100);
        final Canvas canvas = new Canvas(width, height);

        FlowLayoutExample f = new FlowLayoutExample();
        int[] secondArray = f.getNumArray();
        System.out.println(secondArray.toString());
        System.out.println(secondArray[2]);

        Button reset = new Button("Reset");
        Button step = new Button("Step");
        Button run = new Button("Run");
        Button stop = new Button("Stop");

        root.getChildren().addAll(canvas, new HBox(10, reset, step, run, stop));
        primaryStage.setScene(scene);
        primaryStage.show();

        //int rows = (int) Math.floor(height / cellSize);
        ///int cols = (int) Math.floor(width / cellSize);
        int rows = 20;
        int cols = 20;

        GraphicsContext graphics = canvas.getGraphicsContext2D();
        Life life = new Life(rows, cols, graphics);
        life.init();

        AnimationTimer runAnimation = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                // only update once every second
                if ((now - lastUpdate) >= TimeUnit.MILLISECONDS.toNanos(200)) {
                    life.tick();
                    lastUpdate = now;
                }
            }
        };


        //Game h = new Game();
        //grid_ar = FlowLayoutExample.g

        EraseAction e = new EraseAction(0,0);
       // e = Game.grid_arr[0][1];
        //System.out.println(e.alive);

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                e = Game.grid_arr[i][j];
                if(e.alive){
                    System.out.print(e.x);
                    System.out.print(", ");
                    System.out.println(e.y);
                }


            }
        }



        /*

        EraseAction C = new EraseAction(0,0);
        grid_ar = C.getArray();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if(grid_ar[i][j].stav == 1) {
                    System.out.println(grid_ar[i][j].stav);
                }
            }
        }

         */

        reset.setOnAction(l -> life.init());
        run.setOnAction(l -> runAnimation.start());
        step.setOnAction(l -> life.tick());
        stop.setOnAction(l -> runAnimation.stop());
    }
}