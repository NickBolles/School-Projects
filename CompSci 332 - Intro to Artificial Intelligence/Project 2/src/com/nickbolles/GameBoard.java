package com.nickbolles;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nicholas on 10/21/2015.
 */
public class GameBoard {
    /**
     * Constants for the game state (should probly be enums)
     */
    public static final String PLAYING = "Playing";
    public static final String DRAW = "Draw";
    public static final String X_WON = "X Won";
    public static final String O_WON = "O Won";

    /**
     * Private variables
     */
    private int[][] board = {{0,0,0},{0,0,0},{0,0,0}}; //The board is a 3x3 board of ints
    private String[] PLAYERS = {" ", "X", "O"}; //Ideally this would be dynamically created on instantiation of the GameBoard
    private String currentState = PLAYING; //initial state is PLAYING

    /**
     * Default Constructor with default (blank) initial board
     */
    GameBoard(){
        super();
    }

    /**
     * Constructor to initialize the game board to the specified board
     * @param newBoard
     */
    GameBoard(int[][] newBoard){
        super();
        this.board = newBoard;
    }

    /**
     * Get the size of the GameBoard (3)
     * @return
     */
    public int getSize(){
        return board.length;
    }

    /**
     * Set the cell at the specified row and column to the specified value
     * (0, 1 or 2, corresponding to the player in the PLAYERS private variable)
     * @param val
     * @param row
     * @param col
     */
    public void setCell(int val, int row, int col){
        if (getCell(row, col) == 0){
            this.board[row][col] = val;
            GameBoard.updateGamestate(this);
        }
    }

    /**
     * get the cell value at the specified row and column
     * @param row
     * @param col
     * @return
     */
    public int getCell(int row,int col){
        return this.board[row][col];
    }

    /**
     * Sets the current state of the GameBoard, should be equal to PLAYING,DRAW, X_WON, or O_WON
     * @param currentState
     */
    public void setCurrentState(String currentState){
        if (currentState.equals(PLAYING )|| currentState.equals(DRAW) || currentState.equals(X_WON) || currentState.equals(O_WON)){
            this.currentState = currentState;
        }
    }

    /**
     * Gets the current state of the GameBoard
     * @return
     */
    public String getCurrentState(){
        return this.currentState;
    }

    /**
     * Gets an ArrayList of [x,y] pairs empty cells in the GameBoard
     * @return
     */
    public ArrayList<int[]> getEmptyCells(){
        ArrayList<int[]> empty = new ArrayList<int[]>();
        for (int r=0;r<this.board.length;r++){
            for (int c=0;c<this.board.length;c++){
                if (board[r][c] == 0){
                    empty.add(new int[]{r,c});
                }
            }
        }
        return empty;
    }

    /**
     * Create a copy of the gameboard, used by the AI function
     * @return
     */
    public GameBoard createCopy(){
        return new GameBoard(copy2d(this.board));
    }

    /**
     * Function to reset the board back to empty
     */
    public void reset(){
        for (int i = 0; i<board.length;i++){
            for (int c=0;c<board[i].length;c++){
                board[i][c] = 0;
            }
        }
        this.currentState = PLAYING;
    }

    /**
     * Update the GameBoard state. This will check to see if there has been a winner
     * @param board
     */
    public static void updateGamestate(GameBoard board){
        //Check all possible states for both players
        for (int i =0;i<2;i++){
            //Set the curPlayer to the corresponding values in the board
            int curPlayer = i+1;
            //Set the winState if this player wins
            String winState = PLAYING;
            if (curPlayer == 1){
                winState = X_WON;
            }
            if (curPlayer == 2){
                winState = O_WON;
            }
            //First check for diagonal
            if (    //Diagonal from top left to bottom right
                    board.getCell(0,0) == curPlayer
                    && board.getCell(1,1) == curPlayer
                    && board.getCell(2,2) == curPlayer
                    //Diagonal from bottom left to top right
                    || board.getCell(0,2) == curPlayer
                    && board.getCell(1,1) == curPlayer
                    && board.getCell(2,0) == curPlayer){
                //The curPlayer has won
                board.setCurrentState(winState);
            }else{
                //No diagonal win, so loop through the rows and columns and check for a winner
                for (int r=0;r<board.getSize();r++){
                    //loop through the columns checking to see if there is a win horiz, if there is one not matching break
                    for (int c=0;c<board.getSize();c++){
                        if  (board.getCell(r,c)!=curPlayer){
                            break;
                        }
                        //if we reach the last column this player has won
                        if (c==board.getSize()-1){
                            //The player has won
                            board.setCurrentState(winState);
                            return;
                        }
                    }
                    //loop through the columns, but flip the values so that we are checking for a vertical win
                    //This works because the board will always be a square
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
        //Important to check that the state is still PLAYING because there could be 0 cells empty,
        // but someone could have just won
        if (board.getEmptyCells().size() == 0 && board.getCurrentState() == PLAYING){
            board.setCurrentState(DRAW);
        }
    }

    /**
     * Utility function to copy a 2d array
     * @param oldArray
     * @return
     */
    public static int[][] copy2d(int[][] oldArray){
        int[][] newArray = new int[oldArray.length][];
        for(int i = 0; i < oldArray.length; i++){
            newArray[i] = new int[oldArray[i].length];
            for (int j = 0; j < oldArray[i].length; j++){
                newArray[i][j] = oldArray[i][j];
            }
        }
        return newArray;
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
