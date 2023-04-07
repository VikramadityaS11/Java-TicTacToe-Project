package com.example.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class TileBoard {
    private StackPane pane;
    private InfoCenter infoCenter;
    private HelloApplication game;
    private Tile[][] tiles = new Tile[3][3]; //CONTAINS ALL TILES
    private char playerTurn = 'X';
    private boolean isEndOfGame = false;
    private Line winningLine;
    public HelloApplication h1;

    public TileBoard(InfoCenter infoCenter)
    {
        this.infoCenter = infoCenter;
        pane = new StackPane();
        pane.setMinSize(Constants.app_width,Constants.app_height);
        pane.setTranslateX(Constants.app_width/2.0);
        pane.setTranslateY(Constants.app_height/6.0 + Constants.app_height/2.0);//Added because tile board should be after Heading.
        addAllTiles();
        //WINNING LINE
        winningLine = new Line();
        winningLine.setStroke(Color.RED);
        pane.getChildren().add(winningLine);
    }

    private void addAllTiles()
    {
        for(int row = 0; row < 3;row++)
        {
            for(int col=0;col<3;col++)
            {
                Tile tile = new Tile();
                tile.getStackPane().setTranslateX((col*100)-100);
                tile.getStackPane().setTranslateY((row*100)-100);
                pane.getChildren().add(tile.getStackPane());
                tiles[row][col] = tile;
            }
        }
    }

    public void startNewGame()
    {
        isEndOfGame = false;
        playerTurn = 'X';
        for(int row = 0; row < 3;row++)
        {
            for(int col=0;col<3;col++)
            {
                tiles[row][col].setValue("");
            }
        }
        winningLine.setVisible(false);// Not showing line during game.

    }

    public void changePlayerTurn()
    {
        if(playerTurn == 'X')
        {
            playerTurn = 'O';
        }
        else
        {
            playerTurn = 'X';
        }
        infoCenter.updateMessage("Player " + playerTurn + " 's turn");
    }
    public void checkForWinner()
    {
        checkRowsForWinner();
        checkColsForWinner();
        checkTopLeftToBottomRightForWinner();
        checkTopRightToBottomLeftForWinner();
        checkForTie();
    }

    private void checkRowsForWinner()
    {
        if(!isEndOfGame)
        {
            for(int row = 0;row < 3; row++)
            {
                if(tiles[row][0].getValue().equals(tiles[row][1].getValue()) && tiles[row][0].getValue().equals(tiles[row][2].getValue())
                        && !tiles[row][0].getValue().isEmpty())
                {
                    String winner = tiles[row][0].getValue();
                    endGame(winner,new WinningTiles(tiles[row][0],tiles[row][1],tiles[row][2]));
                    return;
                }
            }
        }
    }



    private void checkColsForWinner()
    {
        if(!isEndOfGame)
        {
            for(int col = 0;col < 3; col++)
            {
                if(tiles[0][col].getValue().equals(tiles[1][col].getValue()) && tiles[0][col].getValue().equals(tiles[2][col].getValue())
                        && !tiles[0][col].getValue().isEmpty())
                {
                    String winner = tiles[0][col].getValue();
                    endGame(winner,new WinningTiles(tiles[0][col],tiles[1][col],tiles[2][col]));
                    return;
                }
            }
        }

    }

    private void checkTopLeftToBottomRightForWinner()
    {
        if(!isEndOfGame)
        {
            if(tiles[0][0].getValue().equals(tiles[1][1].getValue()) && tiles[0][0].getValue().equals(tiles[2][2].getValue())
                    && !tiles[0][0].getValue().isEmpty())
            {
                String winner = tiles[0][0].getValue();
                endGame(winner,new WinningTiles(tiles[0][0],tiles[1][1],tiles[2][2]));
            }
            return;
        }
    }

    private void checkTopRightToBottomLeftForWinner()
    {
        if(!isEndOfGame)
        {
            if(tiles[0][2].getValue().equals(tiles[1][1].getValue()) && tiles[0][2].getValue().equals(tiles[2][0].getValue())
                    && !tiles[0][2].getValue().isEmpty())
            {
                String winner = tiles[0][2].getValue();
                endGame(winner,new WinningTiles(tiles[0][2],tiles[1][1],tiles[2][0]));
                return;
            }
        }
    }

    private void checkForTie()
    {
        if(!isEndOfGame)
        {
            for(int row = 0;row<3;row++)
            {
                for(int col=0;col<3;col++)
                {
                    if(tiles[row][col].getValue().isEmpty())
                    {
                        return; //As all tiles aren't filled yet
                    }
                }
            }
            isEndOfGame=true;
            infoCenter.updateMessage("Tie...");
            infoCenter.showStartButton();
            return;
        }
    }
    private void endGame(String winner,WinningTiles winningTiles) {
        isEndOfGame = true;
        drawWinningLine(winningTiles);
        infoCenter.showStartButton();
        infoCenter.updateMessage("Player "+ winner + " wins");
    }

    private void drawWinningLine(WinningTiles winningTiles)
    {
        winningLine.setStartX(winningTiles.start.getStackPane().getTranslateX());
        winningLine.setStartY(winningTiles.start.getStackPane().getTranslateY());
        winningLine.setEndX(winningTiles.end.getStackPane().getTranslateX());
        winningLine.setEndY(winningTiles.end.getStackPane().getTranslateY());
        winningLine.setTranslateX(winningTiles.middle.getStackPane().getTranslateX());
        winningLine.setTranslateY(winningTiles.middle.getStackPane().getTranslateY());
        winningLine.setVisible(true);

    }

    private class WinningTiles
    {
        Tile start;
        Tile middle;
        Tile end;
        public WinningTiles(Tile start, Tile middle, Tile end)
        {
            this.start = start;
            this.middle = middle;
            this.end = end;
        }
    }

    public StackPane getStackPane()
    {
        return pane;
    }
    public String getPlayerTurn()
    {
        return String.valueOf(playerTurn);
    }

    //TILES

    private class Tile
    {
        private StackPane pane;
        private Label label;
        public Tile()
        {
            pane = new StackPane();
            pane.setMinSize(100,100);
            Rectangle border = new Rectangle(); // For tile border

            border.setHeight(100);
            border.setWidth(100);
            border.setFill(Color.TRANSPARENT);
            border.setStroke(Color.YELLOW); //Border Colour
            pane.getChildren().add(border);
            //X AND Y LABEL
            label = new Label();
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(24));
            label.setTextFill(Color.WHITE);
            pane.getChildren().add(label);
            //MOUSE CLICK EVENT
            pane.setOnMouseClicked(event -> {
                if(label.getText().isEmpty() && !isEndOfGame)
                {
                    label.setText(getPlayerTurn());
                    changePlayerTurn();
                    checkForWinner();
                }
            });
        }
        //Getting private members
        public StackPane getStackPane()
        {
            return pane;
        }
        public String getValue()
        {
            return label.getText();
        }
        //Setting label value
        public void setValue(String value)
        {
            label.setText(value);
        }

    }


}
