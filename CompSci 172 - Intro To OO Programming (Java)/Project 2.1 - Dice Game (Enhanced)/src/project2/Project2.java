/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;
import java.util.*;
import java.io.*;
import static project2.RollResult.ONE_PIG;
import static project2.RollResult.TWO_PIG;
/**
 *
 * @author Nicholas
 */
public class Project2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws java.io.IOException{

        
        while (quitProgram!=true){
            //moved variable inside loop so that they will reset correclty when starting a new game
            
            
            Project2UI test=new Project2UI();
            test.jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
            });//checking to see if there is a gamestate saved  
            
            //asks if user wants to resume saved game if possible
            if (hasSaveGame!=false){
                System.out.println("r) Resume Saved Game");
                System.out.println("d) Delete Saved Game");
            }
            
            System.out.println("n) New Game");
            System.out.println("x) Exit");
            System.out.println("----------------------------------------------------------------------");
            choice = scanner.next();
            
            //If user chooses to load saved game, loads data, and switches decision to new game, else just switches decision to new game
            breakOutOfGame: {
                switch(choice){
                    case "D":
                    case "d":
                        if (state.deleteSaveGame()==true){
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("Save Deleted Successfully");
                            System.out.println("----------------------------------------------------------------------");

                        }
                        else{
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("Delete Save FAILED: File May Be In Use");
                            System.out.println("----------------------------------------------------------------------");
                        }
                        break;
                    case "R":
                    case "r":                    

                        if (hasSaveGame!=false){
                            loadedSaveGame=true;
                            if (state.load()==true){
                                players=state.players;
                                System.out.println("----------------------------------------------------------------------");
                                System.out.println("Save Loaded Successfully");
                                System.out.println("----------------------------------------------------------------------");
                            }
                            
                        }
                        else{
                                System.out.println("----------------------------------------------------------------------");
                                System.out.println("ERROR: No Save Game is Available. Please Try Again.");
                                System.out.println("----------------------------------------------------------------------");

                                break breakOutOfGame;
                            }
                    case "N" :
                    case "n" :
                        //Creates new Gamestate file and asks for number of players and creates array
                        //If a saved game was loaded it skips this
                        if (loadedSaveGame!=true & firstGame!=false){
                            //getting and checking to see if number of entered players is valid
                            validInput=false;
                                do {
                                    try{
                                        Scanner numPlayer=new Scanner(System.in);
                                        System.out.println("----------------------------------------------------------------------");
                                        System.out.println("Please enter the number of players:");
                                        tempNumPlayer=numPlayer.nextInt();

                                            if (tempNumPlayer>1 & tempNumPlayer<=5){
                                                validInput=true;

                                                state.setNumPlayers(tempNumPlayer);
                                                System.out.println("----------------------------------------------------------------------");

                                            }
                                            else{
                                                System.out.println("----------------------------------------------------------------------");
                                                System.out.println("Please enter a number between 2 and 5");
                                                validInput=false;
                                            }
                                    }catch (InputMismatchException e){
                                        System.out.println("----------------------------------------------------------------------");
                                        System.out.println("ERROR: Please enter only numbers");
                                        validInput=false;
                                    }
                                    
                                }while (validInput!=true);

                            //create the array of players to the correct size
                            players=new Player[state.getNumPlayers()];
                            //Set players names
                            for (int i=0; i<state.getNumPlayers(); i++){
                                System.out.println("Please enter Player " + (i+1) + "'s name: ");
                                players[i] = new Player (scanner.next());
                            }
                        state.addPlayers(players);
                        }

                        else{              
                        //RESETTING STATS FOR NEW GAME
                        if (firstGame!=false){
                            for (int i=0;i<players.length;i++){
                                players[i].resetStats();
                            }
                            state.resetStats();
                        }
                        }

                //Game loop, untill quit is true

                        while(quit==false){
                            //Updates playerturn to new state

                            playerTurn=state.getCurrentPlayerIndex();
                            System.out.println("======================================================================");                
                            System.out.println(players[playerTurn].getName() + "'s turn...");
                            System.out.println("======================================================================");                
                            System.out.println("r) Roll");
                            //doesnt print if the player is forced to roll
                            if (players[playerTurn].getForceRollAgain()!=true){
                                System.out.println("h) Hold");
                                System.out.println("q) Quit");
                            }
                            else {
                                System.out.println("You Are required to roll again");
                            }
                            //getting players choice
                            System.out.println("----------------------------------------------------------------------");                            

                            //forcing player to roll 
                            if (players[playerTurn].getForceRollAgain()!=false){
                                //scanner is here to wait for user input
                                rollAgainChoice=scanner.next();
                                rollAgainChoice="r";
                            }
                            else {
                                rollAgainChoice = scanner.next();
                            }
                            
                            
                            
                            switch(rollAgainChoice){
                                case "Q":
                                case "q":
                                    System.out.println("======================================================================");
                                    System.out.println("**********************************************************************");
                                    System.out.println("======================================================================");
                                    System.out.println("would you like to save your game? y/n: ");
                                    score=players[playerTurn].getTurnTotal();
                                    highScore=players[playerTurn].getHighScore();
                                    lowScore=players[playerTurn].getLowScore();
                                    //checking if its the first turn, if it is setting turntotal to highscore/lowscore
                                    if (state.getNumOfTurns() <=1){
                                            players[playerTurn].setHighScore(score);
                                            players[playerTurn].setLowScore(score);
                                        }
                                    //if it is not first turn compare turn total to high/low and change it accordingly
                                    else{
                                            if(score>highScore){
                                                players[playerTurn].setHighScore(score);
                                            }
                                            if(score<lowScore){
                                                players[playerTurn].setLowScore(score);
                                            }   

                                    }
                                    //set cumulative score to include points scored this turn
                                    players[playerTurn].setCumScore(score + players[playerTurn].getCumScore());


                                    switch(scanner.next()){
                                        case "y":
                                        case "Y":
                                            if ((state.save())==true){
                                                System.out.println("Game Saved Successfully!");
                                            }
                                            else{
                                                System.out.println("Save Failed");
                                            }
                                            break;
                                        case "n":
                                        case "N":
                                             //printout end of turn stats
                                            System.out.println(players[playerTurn].getName() + " scored " + players[playerTurn].getTurnTotal() + " points this turn.");
                                            System.out.println(players[playerTurn].getName() + " has a CUMULATIVE SCORE of: "+players[playerTurn].getCumScore());
                                            System.out.println("End Of Round. " + players[playerTurn].getName() + " FORFIETS");
                                            players[playerTurn].setWinForfiet();
                                            System.out.println("----------------------------------------------------------------------");

                                            if (state.deleteSaveGame()==false){
                                                System.out.println("Error clearing save");
                                            }
                                            break;
                                    }


                                    //reset stats and player turn
                                    for (int i=0;i<players.length;i++){
                                        players[i].resetStats();
                                    }
                                    score=0; highScore=0; lowScore=0;
                                    players[playerTurn].setTurnTotal(score);
                                    firstGame=false;

                                    quit=true;

                                    break;
                                case "h":
                                case "H":
                                    System.out.println("----------------------------------------------------------------------");

                                    System.out.println("Holding Dice...");
                                    //getting varriables for highscore/lowscore comparisons and setting
                                    score=players[playerTurn].getTurnTotal();
                                    highScore=players[playerTurn].getHighScore();
                                    lowScore=players[playerTurn].getLowScore();
                                    //checking if its the first turn, if it is setting turntotal to highscore/lowscore
                                    if (state.getNumOfTurns() <=1){
                                            players[playerTurn].setHighScore(score);
                                            players[playerTurn].setLowScore(score);
                                        }
                                    //if it is not first turn compare turn total to high/low and change it accordingly
                                    else{
                                            if(score>highScore){
                                                players[playerTurn].setHighScore(score);
                                            }
                                            if(score<lowScore){
                                                players[playerTurn].setLowScore(score);
                                            }   

                                    }
                                    //set cumulative score to include points scored this turn
                                    players[playerTurn].setCumScore(score + players[playerTurn].getCumScore());

                                    //printout end of turn stats
                                    System.out.println(players[playerTurn].getName() + " scored " + players[playerTurn].getTurnTotal() + " points this turn.");
                                    System.out.println(players[playerTurn].getName() + " has a CUMULATIVE SCORE of: "+players[playerTurn].getCumScore());
                                    //preparing for next turn
                                    score=0; highScore=0; lowScore=0;
                                    players[playerTurn].setTurnTotal(score);
                                    //procede to next player in array
                                    state.nextPlayer();
                                    break;
                                case "r":
                                case "R":
                                    System.out.println("----------------------------------------------------------------------");                            
                            //Rolling Dice and getting ENUM Result)
                                    players[playerTurn].roll();


                                    switch(players[playerTurn].getRollResult()){
                                        case TWO_PIG:

                                            System.out.println("======================================================================");
                                            System.out.println(players[playerTurn].getName() + "'s CUMULATIVE SCORE has been reset to 0");
                                            System.out.println("Turn ending...");
                                            state.nextPlayer();

                                            break;
                                        case ONE_PIG:
                                            System.out.println("======================================================================");
                                            System.out.println(players[playerTurn].getName() + "'s TURN TOTAL has been reset to 0");
                                            System.out.println(players[playerTurn].getName() + "'s CUMULATIVE SCORE is: " + players[playerTurn].getCumScore());
                                            System.out.println("Turn ending...");
                                            state.nextPlayer();
                                        break;                                  
                                        default:
                                            System.out.println("\n" + players[playerTurn].getName() + "'s TURN TOTAL is: " + players[playerTurn].getTurnTotal());
                                            System.out.println(players[playerTurn].getName() + "'s Tentative CUMULATIVE SCORE is: " + (players[playerTurn].getCumScore() + players[playerTurn].getTurnTotal()));


                                            //Check to see if current player has won on this turn
                                            if ((players[playerTurn].getCumScore() + players[playerTurn].getTurnTotal())>=50){
                                                System.out.println("======================================================================");
                                                System.out.println("**********************************************************************");
                                                System.out.println("======================================================================"); 
                                                System.out.println(players[playerTurn].getName() + " WINS!!!");
                                                players[playerTurn].incWin();
                                                System.out.println(players[playerTurn].getName() + " Has won " + players[playerTurn].getWin() + " Times!");
                                                for (int i=0;i<players.length;i++){
                                                        players[i].resetStats();
                                                    }
                                                firstGame=false;
                                                quit=true;
                                            }
                                            break;

                                        }

                                    break;
                                default:
                                    System.out.println("Invalid Entry, Please Try Again");
                                    break;
                            }

                        }//exiting single game
                        break;


                    case "x":
                    case "X":
                    //Prints out exit game message and stats for each player. 
                    //If no stats have been created it catchs the error and prints no stats
                        System.out.println("********************************************************************");
                        System.out.println("Exiting Game");
                        System.out.println("********************************************************************");
                        try{
                                for (int i=0;i< players.length;i++){
                                System.out.println("----------------------------------------------------------------------");
                                System.out.println(players[i].getName() + "'s STATS...");
                                System.out.println(players[i].getName() + " WON " + players[i].getWin() + " Times");
                                System.out.println(players[i].getName() + "'s HIGH SCORE was: " +players[i].getHighScore());
                                System.out.println(players[i].getName() + "'s LOW SCORE was:  " +players[i].getLowScore());
                            }
                        }catch(NullPointerException e){
                            System.out.println("No Stats To Display");
                        }
                        System.out.println("======================================================================");
                        System.out.println("*******************Thank You For Playing Pig Dice!*******************");
                        System.out.println("======================================================================");
                        quitProgram=true;
                        break;
                    default:
                        System.out.println("Invalid Entry, Please Try Again");

                        break;
                    }
            }
        }
    }
}





