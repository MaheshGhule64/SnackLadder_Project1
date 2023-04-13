package com.example.snackladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
   private Circle coin;
   private int position;
   private String name;

    Board gameBoard = new Board();
    Player(int tileSize,Color coinColor, String name){
;        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        position = 0;
        movePlayer(1);
        this.name = name;
    }

    public void movePlayer(int diceValue){
        if(position+diceValue<=100){
            position = position + diceValue;

            TranslateTransition secondMove = null, firstMove =  translateAnimate(diceValue);

            int newPos = gameBoard.getNewPos(position);
            if(newPos!=position && newPos!=-1){
                position = newPos;
                secondMove = translateAnimate(6);
            }
            if(secondMove==null){
                firstMove.play();
            }
            else{
                SequentialTransition sequentialTransition = new SequentialTransition(firstMove, new PauseTransition(Duration.millis(1000)), secondMove);
            }


 //           position = position + diceValue;
//            int x = gameBoard.getXCoordinate(position);
//            int y = gameBoard.getYCoordinate(position);
//            coin.setTranslateX(x);
//            coin.setTranslateY(y);
        }
    }

    private TranslateTransition translateAnimate(int diceValue){
        TranslateTransition animate = new TranslateTransition(Duration.millis(300*diceValue), coin);
        animate.setToX(gameBoard.getXCoordinate(position));
        animate.setToY(gameBoard.getYCoordinate(position));
        animate.setAutoReverse(false);
        return animate;

    }

    public boolean isWinner(){
        if(position==100) return true;
        return false;
    }

    public void startingPos(){
        position = 1;
        movePlayer(0);
    }

    public Circle getCoin() {
        return coin;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
