package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InfoCenter {
    private StackPane pane;
    private Label message;
    private Button startGameButton;

    //Constructor
    public InfoCenter()
    {
        pane = new StackPane();
        pane.setMinSize(Constants.app_width,Constants.app_height);
        pane.setTranslateX((Constants.app_width/2.0));//In X-Direction
        pane.setTranslateY((Constants.app_height/6.0));//In Y-Direction

        //MESSAGE
        message = new Label("Tic Tac Toe"); // heading
        message.setMinSize(Constants.app_width,Constants.app_height);  // Setting size of heading, i.e full size of page
        message.setFont(Font.font(24));
        message.setTextFill(Color.WHITE);
        message.setAlignment(Pos.CENTER);
        message.setTranslateY(-20); // As label is slightly above the center.
        pane.getChildren().add(message); // Getting and adding the message to layout

        //BUTTON
        startGameButton = new Button("Start New Game");
        startGameButton.setMinSize(135,30);
        startGameButton.setTranslateY(20); // 20 pixels below midway point
        pane.getChildren().add(startGameButton); // Adding button to layout
    }

    public StackPane getStackPane()
    {
        return pane;// Getting StackPane because its marked private. Label & Button we get already in the constructor.
    }

    public void updateMessage(String message)
    {
        this.message.setText(message); // To update heading after clicking button
    }

    public void showStartButton()
    {
        startGameButton.setVisible(true);//To show start button when game restarts
    }

    public void hideStartButton()
    {
        startGameButton.setVisible(false);//To un-hide after clicking on it
    }

    public void setStartGameButtonOnAction(EventHandler <ActionEvent> onAction)
    {
        startGameButton.setOnAction(onAction);
    }



}
