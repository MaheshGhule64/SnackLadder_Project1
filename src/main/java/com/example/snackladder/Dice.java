package com.example.snackladder;

public class Dice {
    public int getRolledDiceValue(){
        return (int) (Math.random()*6+1);
    }
}
