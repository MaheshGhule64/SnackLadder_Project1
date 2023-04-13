package com.example.snackladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnackLadder extends Application {

    public static final int tileSize=40, Width=10, Height=10;
    static boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;
    private static Dice dice = new Dice();

    Player playerOne, playerTwo;
    private Pane creatContent(){
        Pane root = new Pane();

        root.setPrefSize(Width*tileSize, Height*tileSize +  100);

        for(int i=0; i<Height; i++){
            for(int j=0; j<Width; j++){
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }

        Image img = new Image("C:\\Users\\shahaji\\IdeaProjects\\SnackLadder\\src\\main\\img.png");

        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(Height*tileSize);
        board.setFitWidth(Width*tileSize);

        Button playerOneButton = new Button(" ");
        Button playerTwoButton = new Button(" ");
        Button startButton = new Button("Start Botton");

        playerOneButton.setTranslateX(20);
        playerOneButton.setTranslateY(Height * tileSize + 60);
        playerOneButton.setDisable(true);

        playerTwoButton.setTranslateX(330);
        playerTwoButton.setTranslateY(Height * tileSize + 60);
        playerTwoButton.setDisable(true);

        startButton.setTranslateX(165);
        startButton.setTranslateY(Height * tileSize + 60);

        Label playerOneLabel = new Label("Your Turn");
        Label playerTwoLabel = new Label("Your Turn");
        Label diceLabel = new Label("Start The Game");

        playerOneLabel.setTranslateX(20);
        playerOneLabel.setTranslateY(Height * tileSize + 35);

        playerTwoLabel.setTranslateX(320);
        playerTwoLabel.setTranslateY(Height * tileSize + 35);

        diceLabel.setTranslateX(160);
        diceLabel.setTranslateY(Height * tileSize + 35);

        playerOne = new Player(tileSize, Color.BLACK, "Mahesh");
        playerTwo = new Player(tileSize-5, Color.WHITE, "Gaurav");

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " +  diceValue);
                        playerOne.movePlayer(diceValue);

                        // winner
                        if(playerOne.isWinner()){
                            diceLabel.setText("Congratulations! " + playerOne.getName());

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            playerOneButton.setDisable(true);
                            playerTwoButton.setDisable(true);
                            gameStarted = false;

                            playerOne.startingPos();

                        }
                        else{
                            // Enable Player Two
                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerOneLabel.setText(" ");

                            // Disable player One
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerTwoLabel.setText(playerTwo.getName() + "'s Turn");
                        }
                    }
                }
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " +  diceValue);
                        playerTwo.movePlayer(diceValue);

                        // winner
                        if(playerTwo.isWinner()){
                            diceLabel.setText("Congratulations! " + playerTwo.getName());

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            playerOneButton.setDisable(true);
                            playerTwoButton.setDisable(true);
                            gameStarted = false;

                            playerTwo.startingPos();
                        }
                        else{
                            // Enable player Two
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText(playerOne.getName() + "'s Turn");

                            // Disable player One
                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText(" ");
                        }
                    }
                }
            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                diceLabel.setText("Lets Play!");
                startButton.setDisable(true);
                playerOneTurn = true;
                playerOneLabel.setText(playerOne.getName() + "'s Turn");
                playerOneButton.setDisable(false);
                playerOne.startingPos();

                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);
                playerTwo.startingPos();

            }
        });

        root.getChildren().addAll((Node) board, playerOneButton, playerTwoButton, startButton, playerOneLabel,
                playerTwoLabel, diceLabel, playerOne.getCoin(), playerTwo.getCoin());

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(creatContent());
        stage.setTitle("Snack & Ladder !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}