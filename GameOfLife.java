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

import static com.example.fxznova.MainMenu.COLS;
import static com.example.fxznova.MainMenu.ROWS;

public class GameOfLife extends Application {

    //muzeme vypocitat vychozi hodnotu podle poctu bunek
    private static final int width = 600;//500
    private static final int height = 600;//500
    private static final int cellSize = 10;


    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, width, height + 100);
        final Canvas canvas = new Canvas(width, height);


        Button reset = new Button("Reset");
        Button step = new Button("Step");
        Button run = new Button("Run");
        Button stop = new Button("Stop");

        root.getChildren().addAll(canvas, new HBox(10, reset, step, run, stop));
        primaryStage.setScene(scene);
        primaryStage.show();


        int rows = ROWS;
        int cols = COLS;

        GraphicsContext graphics = canvas.getGraphicsContext2D();
        Life life = new Life(rows, cols, graphics);
        life.init();

        AnimationTimer runAnimation = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if ((now - lastUpdate) >= TimeUnit.MILLISECONDS.toNanos(150)) {
                    life.tick();
                    lastUpdate = now;
                }
            }
        };


        reset.setOnAction(l -> life.init());
        run.setOnAction(l -> runAnimation.start());
        step.setOnAction(l -> life.tick());
        stop.setOnAction(l -> runAnimation.stop());
    }
}