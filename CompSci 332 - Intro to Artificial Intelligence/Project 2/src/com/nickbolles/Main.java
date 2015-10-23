package com.nickbolles;

import java.util.Scanner;

public class Main {
    /**
     * Create the players
     */
    public static Player player1 = new Player("X",1);
    public static Player player2 = new Player("O",2);
    /**
     * Keep track of the current player and to go next
     */
    public static Player currentPlayer = player1;
    public static Player nextPlayer = player2;

    /**
     * Whether to use AI or not
     */
    public static boolean PlayVsAI = true;


    public static void main(String[] args) {
        //Loop to allow starting another game after this one finishes
        boolean quit = false;
        while(!quit){
            //First we need to create the GameBoard
            GameBoard board = new GameBoard();
            //Now ask if you want to play with the AI
            gameTypePrompt();
            //Set the first player, X always goes first
            currentPlayer = player1;
            nextPlayer = player2;
            System.out.println("=======================================");
            //While the board state is PLAYING loop
            do{
                //Print the GameBoard so the player can decide their move
                System.out.println(board);
                //Get the players move
                getPlayerInput(board, currentPlayer);
                //Swap currentPlayer and nextPlayer
                Player temp = nextPlayer;
                nextPlayer = currentPlayer;
                currentPlayer = temp;
            }while(board.getCurrentState() == GameBoard.PLAYING);
            System.out.println("=======================================");
            //The Game is over, print a finishing message
            System.out.println(board.getCurrentState()+ "! Congrats!");
            System.out.println(board);
            //Ask if the user would like to play another game
            System.out.println("=======================================");
            System.out.println("Play another game? Enter n or N to exit");
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine().toLowerCase();
            //If the text is n quit, otherwise play another game
            if (text.equals("n")) {
                quit = true;
            }
        }
    }
    public static Scanner in = new Scanner(System.in);

    /**
     * Function to prompt the user for game type (AI or 2 player)
     */
    public static void gameTypePrompt(){
        System.out.println("Would you like to play vs the AI? (enter 'n' for 2 player): ");
        String entry = in.next();
        PlayVsAI = !entry.toLowerCase().equals("n");
    }

    /**
     * Function that prompts the player for their move and checks its validity. If it is valid it sets the cell
     * @param board
     * @param currentPlayer
     * @return
     */
    public static int[] getPlayerInput(GameBoard board, Player currentPlayer){
        //If the game mode is PlayVsAI and this is player 2 ("O") have the AI agent choose a play
        if (currentPlayer == player2 && PlayVsAI){
            AI.choosePlay(board, currentPlayer, nextPlayer);
        }else{
            boolean valid = false;
            while(!valid){
                System.out.println("Player '" + currentPlayer.getName() + "', enter your move in the form 'row[1-" +
                        board.getSize() + "] col[1-" +  board.getSize() + "]' : ");
                int row = promptForInt()-1;
                int col = promptForInt()-1;
                //Make sure that the row and col are both in the range, and that the cell is empty
                if (row >=0 && row < board.getSize() && col >=0 &&  row < board.getSize()){
                    if(board.getCell(row,col) != 0) { //If the cell is not empty show a message saying that someone has already moved there
                        if (board.getCell(row, col) == currentPlayer.getId()) {
                            System.out.println("You have already moved there, silly!");
                        } else {
                            System.out.println("Can't Move there! '" + nextPlayer.getName() + "' Has already moved there!");
                        }
                    }else{
                        //Only now do we have a valid entry, set valid to true
                        valid = true;
                        //Set the cell on the board to the current players ID
                        board.setCell(currentPlayer.getId(), row,col);
                        //return the move
                        return new int[]{row,col};
                    }
                }else{//Otherwise inform the player of an error and re ask
                    System.out.println("Please Enter a number between 1 and 3 for both the column and the row");
                }
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * Utility function to prompt for an Int, if the user enters something else it will tell the user to "Only use Numbers" and ask again
     * @return
     */
    public static int promptForInt(){
        int num=0;
        while(true){ //Loop to make sure input is valid
            try{
                num = in.nextInt();
                break;
                //catch the error if the input isn't an Int
            }catch(java.util.InputMismatchException e){
                //provide an error message to the user and DONT
                //modify inputValid so that they have a chance to start again
                System.out.println("Please Enter Only Numbers, Try Again: ");
            }
        }
        return num;
    }
}
