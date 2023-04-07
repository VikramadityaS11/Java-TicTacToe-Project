package com.example.tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.image.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HelloApplication extends Application {

    private InfoCenter infoCenter;

    private TileBoard tileBoard;

    @Override
    public void start(Stage primaryStage) throws IOException {

        // Create the heading text
        Text headingText = new Text("Welcome to TicTacToe");
        headingText.setUnderline(true);
        headingText.setFont(Font.font("Helvetica", FontWeight.BOLD, 43));
        headingText.setFill(Color.YELLOW);
        headingText.setStroke(Color.BLACK);

        // Create the Start Game button
        Button startGameButton = new Button("Start Game >");
        startGameButton.setMinSize(100,40);
        startGameButton.setFont(Font.font("Arial",FontWeight.BOLD,15));
        startGameButton.setStyle("-fx-background-color: Beige;");
        startGameButton.setOnAction(e -> {
            // Switch to the game scene when the button is clicked

            goToNextScene(primaryStage);
        });

        // Create a VBox to hold the heading text and the Start Game button
        VBox vbox = new VBox(20, headingText, startGameButton);
        vbox.setAlignment(Pos.CENTER);

        // Create a StackPane to hold the background image and the VBox
        StackPane stackPane = new StackPane(vbox);
        //stackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
        FileInputStream imp = new FileInputStream("/Users/vikramadityasharma/Desktop/TicTacToe/src/main/java/com/example/tictactoe/xoxo.jpg");
        Image image = new Image(imp);
        BackgroundImage bg = new BackgroundImage(image,null,null,null,null);
        stackPane.setBackground(new Background(bg));
        Scene scene = new Scene(stackPane, 600, 500);
        // Set the scene on the primary stage
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();




    }




        public void goToNextScene(Stage stage)
        {
            stage.setTitle("TicTacToe");
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        Scene scene = new Scene(root, Constants.app_width, Constants.app_height);
        initLayout(root);
        stage.setResizable(true);
       /* File file = new File("/src/main/java/com/example/tictactoe/xoxo.jpg");
        Image image = new Image(file.toURI().toString());
        BackgroundImage bg = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(bg));*/

        stage.setScene(scene);
        stage.show();
    }

    private void initInfoCenter(BorderPane root) {
        infoCenter = new InfoCenter();
        root.getChildren().add(infoCenter.getStackPane()); //To add stack pane to page
        infoCenter.setStartGameButtonOnAction(startNewGame());//Passing event.
    }

    private void initLayout(BorderPane root) {
        initInfoCenter(root);

        initTileBoard(root);
    }


    private void initTileBoard(BorderPane root)
    {
        tileBoard = new TileBoard(infoCenter);
        root.getChildren().add(tileBoard.getStackPane());

    }

    private EventHandler<ActionEvent> startNewGame() //Syntax for event handling.
    {
        return new EventHandler<ActionEvent>() {
            @Override //Overriden method "handle".
            public void handle(ActionEvent e) {
                infoCenter.hideStartButton();
                infoCenter.updateMessage("Player X's Turn");
                tileBoard.startNewGame();
            }
        };
    }


    public static void main(String[] args) {
        launch(args);
    }
}