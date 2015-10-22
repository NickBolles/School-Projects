package com.nickbolles;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Nicholas on 10/21/2015.
 */
public class AI {
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
    public static void choosePlay(GameBoard board, Player player, Player nextPlayer){
        //Get all the empty cells in this board
        ArrayList<ArrayList<Integer>> emptyCells = board.getEmptyCells();
        if (emptyCells.size()>0){
            //Create a list of all of the possible "child" moves
            ArrayList<GameState> children = new ArrayList<GameState>();
            for (ArrayList<Integer> move: emptyCells){
                GameBoard newBoard = board.createCopy();
                newBoard.setCell(player.getId(), move.get(0), move.get(1));
                GameState state = new GameState(newBoard);
                state.setMove(move.get(0), move.get(1));
                state.addScore(minmax(state, nextPlayer, player, false, 0));
//                System.out.println(state);
                children.add(state);
            }
            int maxScore = children.get(0).getScore();
            int maxIdx = 0;
            for (int i = 1; i < children.size(); i++) {
                if (children.get(i).getScore() > maxScore) {
                    maxScore = children.get(i).getScore();
                    maxIdx = i;
                }
            }
            System.out.print(children);
            int[] move = children.get(maxIdx).getMove();
            board.setCell(player.getId(),move[0],move[1]);
        }
        return;
    }
    public static int minmax(GameState curState, Player player, Player nextPlayer, boolean max, int depth){
        GameBoard board = curState.getBoard();
        if (board.getCurrentState() != GameBoard.PLAYING){
//            int factor = 1;
//            //If we arent getting the max, we are getting the min, so inverse the winner val
//            if (!max){
//                factor = -1;
//            }
            int score = getWinnerVal(board);
            curState.addScore(score);
            return score;//*factor;
        }
        //Get all the empty cells in this board
        ArrayList<ArrayList<Integer>> emptyCells = board.getEmptyCells();
        if (emptyCells.size() >0){
            //Create a list of all of the possible "child" moves
            ArrayList<GameState> children = new ArrayList<GameState>();
            for (ArrayList<Integer> move: emptyCells){
                GameBoard newBoard = board.createCopy();
                newBoard.setCell(player.getId(), move.get(0), move.get(1));
                GameState state = new GameState(newBoard);
                state.setMove(move.get(0), move.get(1));
                int result = minmax(state, nextPlayer, player, !max, ++depth);
//                System.out.println(state);
                children.add(state);

            }
            int compScore = children.get(0).getScore();
            int compIdx = 0;
            for (int i = 1; i < children.size(); i++) {
                if ((max && children.get(i).getScore() > compScore ) || (!max && children.get(i).getScore() <compScore)){
                    compScore = children.get(i).getScore();
                    compIdx = i;
                }
            }
            curState.addScore(children.get(compIdx).getScore(), depth);
        }
//        System.out.println(curState);
        return curState.getScore()-depth;
    }

    public static class Score{
        private int score = 0;
        private int depth = 0;
        Score(int score, int depth){
            this.depth = depth;
            this.score = score;
        }
    }
    /**
     * Created by Nicholas on 10/21/2015.
     */
    public static class GameState {
        private int score = 0;
        private GameBoard board;
        private int[] move={-1,-1};
        private ArrayList<Score> scores= new ArrayList<Score>();

        GameState(GameBoard board) {
            this.board = board;
        }
        public GameBoard getBoard(){
            return board;
        }
        public void addScore(int score) {
            this.score += score;
        }
        public void addScore(int score, int depth) {
            scores.add(new Score(score, depth));
            this.score += score;
        }

        @Override
        public String toString() {
            return "GameState{" +
                    "score=" + score +
                    ", board=" + board +
                    '}';
        }

        /**
         * Gets score.
         *
         * @return Value of score.
         */
        public int getScore() {
            return score;
        }

        /**
         * Sets new move
         */
        public void setMove(int r, int c) {
            this.move = new int[]{r,c};
        }

        /**
         * Gets move.
         *
         * @return Value of move.
         */
        public int[] getMove() {
            return move;
        }
    }
}
//    public static Board Pick(Vector<Board> boards, int play) {
//        int MAXMIN = Integer.MAX_VALUE;
//        Board ret = null;
//        if (play == X) {
//            MAXMIN = Integer.MIN_VALUE;
//            for (Board i : boards) {
//                if (i.value > MAXMIN) {
//                    ret = i;
//                    MAXMIN = ret.value;
//                }
//            }
//        } else {
//            for (Board i : boards) {
//                if (i.value < MAXMIN) {
//                    ret = i;
//                    MAXMIN = ret.value;
//                }
//            }
//        }
//        return ret;
//    }
//
//    public static Board com(Board root, int play) {
//        if (PlayerWon(root.board) != DRAW) {
//            root.value += PlayerWon(root.board);
//            return root;
//        }
//        if (Tie(root)) {
//            return root;
//        }
//        Vector<Board> child = new Vector<Board>();
//        for (int i = 0; i < GRID_SIZE; i++) {
//            if (root.board[i] == 0) {
//                int[] add = root.board.clone();
//                add[i] = play;
//                child.add(new Board(add));
//                com(child.lastElement(), -play);
//            }
//        }