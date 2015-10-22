package com.nickbolles;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nicholas on 10/21/2015.
 */
public class GameBoard {
    public static final String PLAYING = "Playing";
    public static final String DRAW = "Draw";
    public static final String X_WON = "X Won";
    public static final String O_WON = "O Won";

    private int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
    private String[] PLAYERS = {" ", "X", "O"};
    private String currentState = PLAYING;

    GameBoard(){
        super();
    }
    GameBoard(int[][] newBoard){
        super();
        this.board = newBoard;
    }
    public int getSize(){
        return board.length;
    }
    public void setCell(int val, int row, int col){
        if (getCell(row, col) == 0){
            this.board[row][col] = val;
            GameBoard.updateGamestate(this);
        }
    }
    public int getCell(int row,int col){
        return this.board[row][col];
    }
    public void setCurrentState(String currentState){
        if (currentState.equals(PLAYING )|| currentState.equals(DRAW) || currentState.equals(X_WON) || currentState.equals(O_WON)){
            this.currentState = currentState;
        }
    }
    public String getCurrentState(){
        return this.currentState;
    }
    public ArrayList<ArrayList<Integer>> getEmptyCells(){
        ArrayList<ArrayList<Integer>> empty = new ArrayList<ArrayList<Integer>>();
        for (int r=0;r<this.board.length;r++){
            for (int c=0;c<this.board.length;c++){
                if (board[r][c] == 0){
                    ArrayList<Integer> coords = new ArrayList<Integer>();
                    coords.add(r);
                    coords.add(c);
                    empty.add(coords);
                }
            }
        }
        return empty;
    }
    public GameBoard createCopy(){
        return new GameBoard(copy2d(this.board));
    }
    public void reset(){
        for (int i = 0; i<board.length;i++){
            for (int c=0;c<board[i].length;c++){
                board[i][c] = 0;
            }
        }
        this.currentState = PLAYING;
    }
    public static int[][] copy2d(int[][] oldArray){
        int[][] newArray = new int[oldArray.length][];
        for(int i = 0; i < oldArray.length; i++)
        {
            newArray[i] = new int[oldArray[i].length];
            for (int j = 0; j < oldArray[i].length; j++)
            {
                newArray[i][j] = oldArray[i][j];
            }
        }
        return newArray;
    }
    public static void updateGamestate(GameBoard board){
        for (int i =0;i<2;i++){
            int curPlayer = i+1;
            String winState = PLAYING;
            if (curPlayer == 1){
                winState = X_WON;
            }
            if (curPlayer == 2){
                winState = O_WON;
            }
            if (         // 3-in-the-diagonal
                    board.getCell(0,0) == curPlayer
                    && board.getCell(1,1) == curPlayer
                    && board.getCell(2,2) == curPlayer
                    || board.getCell(0,2) == curPlayer
                    && board.getCell(1,1) == curPlayer
                    && board.getCell(2,0) == curPlayer){
                //The curPlayer has won
                board.setCurrentState(winState);
            }else{
                for (int r=0;r<board.getSize();r++){
                    for (int c=0;c<board.getSize();c++){
                        if  (board.getCell(r,c)!=curPlayer){
                            break;
                        }
                        if (c==board.getSize()-1){
                            //The player has won
                            board.setCurrentState(winState);
                            return;
                        }
                    }
                    for (int c=0;c<board.getSize();c++){
                        if  (board.getCell(c,r)!=curPlayer){
                            break;
                        }
                        if (c==board.getSize()-1){
                            //The player has won
                            board.setCurrentState(winState);
                            return;
                        }
                    }
                }
            }
        }
        //If a winning state has not been set and there are no cells empty set state to DRAW
        if (board.getEmptyCells().size() == 0 && board.getCurrentState() == PLAYING){
            board.setCurrentState(DRAW);
        }
    }
    @Override
    public String toString(){
        String lineBreak = "\r\n";
        String rowBreak = "-------------" + lineBreak;
        String ColBreak = "|";
        String str = rowBreak;
        for (int i = 0; i<board.length;i++){
            for (int c=0;c<board[i].length;c++){
                str += ColBreak + " " + PLAYERS[board[i][c]] + " ";
            }
            str += ColBreak + lineBreak + rowBreak;
        }
        return str;
    }
}
