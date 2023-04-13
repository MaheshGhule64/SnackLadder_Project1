package com.example.snackladder;

import java.util.ArrayList;


 public class Board {

     class Pair{
        int x;
        int y;

        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    ArrayList<Pair> posCoordinate;
     ArrayList<Integer> snackLadderPos;
    public Board(){

        posCoordinate = new ArrayList<>();
        snackLadderPos = new ArrayList<>();
        getSnackLadder();
        makeCoordinate();
    }

    private void makeCoordinate(){
        posCoordinate.add(new Pair(0,0));
        for(int i=1; i<=SnackLadder.Height; i++){
            for(int j=1; j<=SnackLadder.Width; j++){
                int x = 0;
                int y = 0;
                if(i%2!=0){
                    x = (j*SnackLadder.tileSize) -20;
                }
                else{
                    x = (SnackLadder.tileSize*10) - ((j * SnackLadder.tileSize) - 20);
                }
                y = (SnackLadder.tileSize * SnackLadder.Height) - ((SnackLadder.tileSize * i)-20);
                posCoordinate.add(new Pair(x, y));
            }
        }
    }

    public void getSnackLadder(){
        for(int i=0; i<=100; i++){
            snackLadderPos.add(i);
        }

        snackLadderPos.set(4, 25);
        snackLadderPos.set(13, 46);
        snackLadderPos.set(27, 5);
        snackLadderPos.set(33, 49);
        snackLadderPos.set(40, 3);
        snackLadderPos.set(42, 63);
        snackLadderPos.set(43, 18);
        snackLadderPos.set(50, 69);
        snackLadderPos.set(54, 31);
        snackLadderPos.set(62, 81);
        snackLadderPos.set(66, 45);
        snackLadderPos.set(74, 92);
        snackLadderPos.set(76, 58);
        snackLadderPos.set(89, 53);
        snackLadderPos.set(99, 41);
    }

    public int getNewPos(int pos){
        if(pos>0 && pos<=100){
            return snackLadderPos.get(pos);
        }
        return -1;
    }

     public int getXCoordinate(int pos){
         if(pos>=1 && pos<=100){
             Pair p =posCoordinate.get(pos);
             return p.x;
         }
         return -1;
     }

     public int getYCoordinate(int pos){
         if(pos>=1 && pos<=100){
             Pair p = posCoordinate.get(pos);
             return p.y;
         }
         return -1;
     }

//    public static void main(String[] args){
//
//        Board board = new Board();
//        int i = 0;
//        for(Pair e : board.posCoordinate){
//            int x = e.x;
//            int y = e.y;
//            System.out.println(i + " " + "X : " + x + " " + "Y : " + y);
//            i++;
//        }
//    }
}
