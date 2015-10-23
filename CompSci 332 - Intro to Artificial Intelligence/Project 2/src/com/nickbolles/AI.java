package com.nickbolles;

import java.util.ArrayList;

/**
 * Created by Nicholas on 10/21/2015.
 */
public class AI {
    /**
     * Choose a play for the specified player on the specified GameBoard. The nextPlayer is used in
     * minmax to determine min part calls minmax, but gets the actual move from the state it returns.
     * @param board
     * @param player
     * @param nextPlayer
     */
    public static void choosePlay(GameBoard board, Player player, Player nextPlayer){
        GameState toState = minmax(new GameState(board), player, nextPlayer, true, 0);
        int[] move = toState.getMove();
        board.setCell(player.getId(), move[0], move[1]);
    }

    /**
     * Calculate the minmax for the state specified and all possible children states
     * @param curState
     * @param player
     * @param nextPlayer
     * @param max
     * @param depth
     * @return  returns the child state that results in the best play, or the state passed in if it is a winning state
     */
    private static GameState minmax(GameState curState, Player player, Player nextPlayer, boolean max, int depth){
        GameBoard board = curState.getBoard();
        //Check to see if a player has won
        if (board.getCurrentState() != GameBoard.PLAYING){
            //Get the value of the winner x=-1 O=1 Draw=0
            int score = getWinnerVal(board);
            //add the score to this state and return the state
            curState.addScore(score);
            return curState;
        }

        //Get all the empty cells in this board
        ArrayList<int[]> emptyCells = board.getEmptyCells();
        //if there no empty cells theres no child states so skip
        if (emptyCells.size() >0){
            //Create a list of all of the possible "child" moves
            ArrayList<GameState> children = new ArrayList<GameState>();
            //For each possible move create a child state, simulate a move and then call minmax on it
            for (int[] move: emptyCells){
                GameBoard newBoard = board.createCopy();
                //Set the child states cell
                newBoard.setCell(player.getId(), move[0], move[1]);
                GameState state = new GameState(newBoard);
                //Save the move on the state for access later
                state.setMove(move[0], move[1]);
                //Recurse to score all child states
                minmax(state, nextPlayer, player, !max, ++depth);
                children.add(state);
            }

            //Find the min, or the max, depending on what the value of the variable max is
            GameState bestState = children.get(0);
            int bestScore = bestState.getScore();
            for (int i = 1; i < children.size(); i++) {
                if ((max && children.get(i).getScore() >= bestScore ) || (!max && children.get(i).getScore() <=bestScore)){
                    bestState = children.get(i);
                    bestScore = bestState.getScore();
                }
            }
            //Add the score that we just found to the current states score
            curState.addScore(bestState.getScore());
            //return the best child state
            return bestState;
        }
        return curState;
    }
    /**
     * Utility function to get the value of the winner on the specified game board
     * @param board
     * @return
     */
    public static int getWinnerVal(GameBoard board){
        String state = board.getCurrentState();
        if (state == GameBoard.X_WON){
            return -1;
        }else if(state == GameBoard.O_WON){
            return 1;
        }else{
            return 0;
        }
    }
}