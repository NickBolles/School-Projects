package com.nickbolles;

import java.util.Scanner;

public class Main {

    public static Player player1 = new Player("X",1);
    public static Player player2 = new Player("O",2);
    public static Player nextPlayer = player2;

    public static boolean PlayVsAI = true;

    public static void main(String[] args) {

        GameBoard board = new GameBoard();

        boolean quit = false;
        while(!quit){
            Player currentPlayer = player1;
            board.reset();
            do{
                System.out.println(board);
                getPlayerInput(board, currentPlayer);

                Player temp = nextPlayer;
                nextPlayer = currentPlayer;
                currentPlayer = temp;
            }while(board.getCurrentState() == GameBoard.PLAYING);
            System.out.println(board.getCurrentState()+ "! Congrats!");
            System.out.println(board);
            System.out.println("Play another game? Enter n or N to exit");
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine().toLowerCase();
            if (text.equals("n")) {
                quit = true;
            }
        }
    }

    public static Scanner in = new Scanner(System.in); // the input Scanner
    public static int[] getPlayerInput(GameBoard board, Player currentPlayer){
        boolean valid = false;
        if (currentPlayer == player2 && PlayVsAI){
            AI.choosePlay(board, currentPlayer, nextPlayer);
        }else{
            while(!valid){
                System.out.println("Player '" + currentPlayer.getName() + "', enter your move in the form 'row[1-" +  board.getSize() + "] col[1-" +  board.getSize() + "]' : ");
                int row = in.nextInt()-1;
                int col = in.nextInt()-1;
                //Make sure that the row and col are both in the range, and that the cell is empty
                if (row >=0 && row < board.getSize() && col >=0 &&  row < board.getSize()){
                    if(board.getCell(row,col) != 0) { //If the cell is not empty show a message saying that someone has already moved there
                        if (board.getCell(row, col) == currentPlayer.getId()) {
                            System.out.println("You have already moved there, silly!");
                        } else {
                            System.out.println("Can't Move there! '" + nextPlayer.getName() + "' Has already moved there!");
                        }
                    }else{
                        valid = true;
                        board.setCell(currentPlayer.getId(), row,col);
                        return new int[]{row,col};
                    }
                }else{//Otherwise inform the player of an error and re ask
                    System.out.println("Please Enter a number between 1 and 3 for both the column and the row");
                }
            }
        }
        return new int[]{-1,-1};
    }
}
