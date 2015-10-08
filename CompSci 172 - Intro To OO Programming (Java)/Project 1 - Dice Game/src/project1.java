
import java.util.*;


public class project1 {

    public static void main(String[] args) 
        throws java.io.IOException{
        
        int cumscore =0, cumscore1=0, cumscore2=0, turnscore = 0, roll1 =0, roll2 =0, rolltotal=0, result;
        int turn1 =0, turn2 = 0,i = 0,odd =0;
        int name1wins =0,name1high = 0, name1low=0, name2wins =0,name2high = 0, name2low=0;
        String name1, name2, player= " ", watcher = " ";
        boolean quit = false, exit = false, rollagain = false;
        char nxtrnd, nxttrn;
        String name[]=new String[2];
        
        String asciiArt[] = new String[6];
        asciiArt[0] = "  -----\n |     |\n |  o  |\n |     |\n  ----- Oh no! It's a pig!";
        asciiArt[1] = "  -----\n |   o |\n |     |\n | o   |\n  ----- It's a 2!";
        asciiArt[2] = "  -----\n |   o |\n |  o  |\n | o   |\n  ----- It's a 3!";
        asciiArt[3] = "  -----\n | o o |\n |     |\n | o o |\n  ----- It's a 4!";
        asciiArt[4] = "  -----\n | o o |\n |  o  |\n | o o |\n  ----- It's a 5!";
        asciiArt[5] = "  -----\n | o o |\n | o o |\n | o o |\n  ----- It's a 6!";

        
                
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.println("     ____");
        System.out.println("    /\\' .\\    _____");
        System.out.println("   /: \\___\\  / .  /\\    Two-Dice ");
        System.out.println("   \\' / . / /____/..\\    Pig Jeopardy ");
        System.out.println("    \\/___/  \\'  '\\  /   Dice Game");
        System.out.println("             \\'__'\\/     by Nick");
        System.out.println("======================================================================");
        System.out.println("Enter Player 1's Name: ");
        name1 = scanner.nextLine();
        System.out.println("Enter Player 2's Name: ");
        name2 = scanner.nextLine();
        

        while (exit == false ){
            menu: {
            quit=false;
            System.out.println("----------------------------------------------------------------------");
            if (name1wins>0 | name2wins>0){
                System.out.println("Do you want to play another round of Two-Dice Pig?");
            }
            else {
                System.out.println("Do you want to play a round of Two-Dice Pig?");
            }
            System.out.println("Y) Play");
            System.out.println("X) Exit");
            System.out.println("H) How To Play");
            System.out.println("----------------------------------------------------------------------");
            nxtrnd = (char) System.in.read(); System.in.read();
            
           if (nxtrnd == 'x' || nxtrnd =='X'){
                System.out.println("QUITTING Game...");
                exit = true;
                System.out.println("======================================================================");
                System.out.println("==========================End Of Game Report==========================");
                System.out.println(name1 + "'s Statistics");
                System.out.println("wins:          " + name1wins);
                System.out.println("Lowest Score:  " + name1low);
                System.out.println("Highest Score: " + name1high);
                System.out.println("");
                System.out.println(name2 + "'s Statistics");
                System.out.println("wins:          " + name2wins);
                System.out.println("Lowest Score:  " + name2low);
                System.out.println("Highest Score: " + name2high);
                System.out.println("======================================================================");
                System.out.println("");
                System.out.println("Thanks For Playing!!!!");
                
                
            }
           //How to play menu
           else if(nxtrnd =='h'|| nxtrnd =='H'){
               System.out.println("======================================================================");
               System.out.println("          Heres how to play Two-Dice Pig!");
               System.out.println("======================================================================");
               System.out.println("The Objective....  Be the first player to 50 points");
               System.out.println("----------------------------------------------------------------------");

               System.out.println("When you are asked if you want to play or not enter 'Y'");
               System.out.println("Now it is the first players turn. The player has three choices \n1) roll, by entering 'r'\n2) hold, by entering 'h'\n3) quit to the menu, by entering 'q' \n\n **NOTE: Quitting will forfiet the current game to the other player");
               System.out.println("----------------------------------------------------------------------");
               System.out.println("Press Enter To Continue or 'q' to exit to Menu");
               if (System.in.read()=='q'){
                   System.in.read();
                   break menu;
               }
               System.out.println("Certain combinations of numbers have consequences");
               System.out.println("The first combination is a 1 and any other number, like this:");
               System.out.println("  -----\n |     |\n |  o  |\n |     |\n  ----- Oh no! It's a pig!");
               System.out.println("  -----\n | o o |\n | o o |\n | o o |\n  ----- It's a 6!");
               System.out.println("This combination ends your turn, but you get to keep your points!!");
               System.out.println("Press Enter To Continue or 'q' to exit to Menu");
               if (System.in.read()=='q'){
                   System.in.read();
                   break menu;
               }
               System.out.println("The second combination is rolling double 1's, like this:");
               System.out.println("  -----\n |     |\n |  o  |\n |     |\n  ----- Oh no! It's a pig!");
               System.out.println("  -----\n |     |\n |  o  |\n |     |\n  ----- Oh no! It's a pig!");
               System.out.println("This combination ends your turn AND resets your CUMULATIVE SCORE to 0");
               System.out.println("Press Enter To Continue or 'q' to exit to Menu");
               if (System.in.read()=='q'){
                   System.in.read();
                   break menu;
               }
               System.out.println("The third combination is rolling doubles of anything \n\texcept 1's , like this:");
               System.out.println("  -----\n |   o |\n |  o  |\n | o   |\n  ----- It's a 3!");  
               System.out.println("  -----\n |   o |\n |  o  |\n | o   |\n  ----- It's a 3!");
               System.out.println("When you roll this combination you are required to roll \n\tagain. You also keep all of your points.");
               System.out.println("Press Enter To Continue or 'q' to exit to Menu");
               if (System.in.read()=='q'){
                   System.in.read();
                   break menu;
               }
               System.out.println("At the end of the game, when you quit the program, some statistics will be displayed.");
               System.out.println("These statistics are displayed:\nNumber of Wins\nLowest Score\nHighest Score\nThese statistics are kept for each player");
               System.out.println("If you are getting an 'You entered an invalid entry', \ntry pressing enter before entering your next choice.");
               System.out.println("Press Enter To Go To Main Menu");
               System.in.read();
           }
            //this is the game code
            else if (nxtrnd == 'y' || nxtrnd =='Y'){
                System.out.println("----------------------------------------------------------------------");
               cumscore1=0;cumscore2=0;cumscore=0;rolltotal=0;
                //loop for the turn
                do {
                    for (i=0; i<10000; i++){
                        if (quit==true){break;}
                        
                        //resetting data for new turn
                        roll1 = 0; roll2 = 0;rolltotal = 0; odd=0;

                        
                        //figure out whos turn it is
                        if (i>0){odd=i%2;}
                        
                        if (odd == 0){
                            player = name1;
                            watcher = name2;
                            cumscore=cumscore1;
                        }
                        else {
                            player = name2;
                            watcher = name1;
                            cumscore=cumscore2;
                        }
                        //Figure out whos turn it is
                        
                        
                        //asking player what they would like to do

                        
                        
                        if (rollagain==true){
                            nxttrn= 'r';
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println(player + " is required to roll again.");
                            System.out.println("Press ENTER to roll...");
                            System.in.read();
                            
                        }
                        else{
                            
                            System.out.println(player + "'s turn...");
                            System.out.println("r) Roll");
                            System.out.println("h) Hold");
                            System.out.println("q) Quit");
                            System.out.println("----------------------------------------------------------------------");                            
                            nxttrn = (char) System.in.read();System.in.read();}
                        
                        
                        
                            
                        
                            switch(nxttrn){                           
                                case 'q':
                                    
                                    //Need to enter in the round scores
                                    //Need to assign low, high and wins
                                    System.out.println("======================================================================");
                                    System.out.println("**********************************************************************");
                                    System.out.println("======================================================================");
                                    System.out.println("End Of Round. " + player + " FORFIETS");
                                    System.out.println(watcher + " wins by default");
                                    if (odd==0) {
                                       name2wins++;
                                    }
                                    if (odd==1) {
                                       name1wins++;
                                    }
                                    
                                    //calculating min and max for player 1
                                   
                                    if (odd == 0){
                                        turn1++;
                                        if (turn1 ==1){
                                            name1high=turnscore;
                                            name1low=turnscore;
                                        }
                                        else{
                                            if(turnscore>name1high){
                                                name1high=turnscore;
                                            }
                                            if(turnscore<name1low){
                                                name1low=turnscore;
                                            }   
                                            
                                        }
                                    }
                                    
                                    
                                    //calculating min and max for player 2
                                    else if (i==1 || odd ==1) {
                                        turn2++;
                                        if (turn2 == 1){
                                            name2high=turnscore;
                                            name2low=turnscore;
                                        }
                                        else{
                                            if(turnscore>name2high){
                                                name2high=turnscore;
                                            }
                                            if(turnscore<name2low){
                                                name2low=turnscore;
                                            }
                                            
                                        }
                                    }
                                    turnscore = 0;
                                    rollagain =false;
                                    
                                    
                                    quit=true;
                                    break;
                                case 'h':
                                    System.out.println("----------------------------------------------------------------------");
                                    cumscore=cumscore+turnscore;
                                    if (odd == 0){
                                        cumscore1=cumscore;
                                     }
                                    else {
                                        cumscore2=cumscore;
                                    }                                    
                                    System.out.println("Holding Dice...");
                                    System.out.println(player + " scored " + turnscore + " points this turn.");
                                    System.out.println(player + " has a CUMULATIVE SCORE of: " + (cumscore));
                                    
                                    System.out.println("======================================================================");
                                    
                                    
                                    //calculating min and max for player 1
                                    if (odd == 0){
                                        turn1++;
                                        if (turn1 ==1){
                                            name1high=turnscore;
                                            name1low=turnscore;
                                        }
                                        else{
                                            if(turnscore>name1high){
                                                name1high=turnscore;
                                            }
                                            if(turnscore<name1low){
                                                name1low=turnscore;
                                            }   
                                            
                                        }
                                    }
                                    
                                    
                                    //calculating min and max for player 2
                                    else if (i==1 || odd ==1) {
                                        turn2++;
                                        if (turn2 ==1){
                                            name2high=turnscore;
                                            name2low=turnscore;
                                        }
                                        else{
                                            if(turnscore>name2high){
                                                name2high=turnscore;
                                            }
                                            if(turnscore<name2low){
                                                name2low=turnscore;
                                            }
                                            
                                        }
                                    }
                                    
                                    turnscore = 0;
                                    rollagain =false;
                                    rolltotal=0;
                                    
                                    
                                    break;
                                case 'r':
                                    System.out.println("----------------------------------------------------------------------");
                                    System.out.println("rolling...");
                                    roll1 = (int) (Math.random()*6);
                                    roll2 = (int) (Math.random()*6);
                                    System.out.println(asciiArt[roll1]);
                                    System.out.println(asciiArt[roll2]);                                

                                    rolltotal=roll1+roll2+2;


                                           
                                                                        
                                    
                                    
                                    
                                    //special cicrumstances
                                    //special cicrumstances
                                    
                                    if (roll1==0 | roll2==0){
                                        //if both are 1
                                        if (roll1==0 & roll2==0){
                                            result='2';
                                        }
                                        //if only one is 1
                                        else{
                                            result='1';
                                        }
                                    }
                                    else if(roll1==roll2){
                                        //if the names are the same and neither are 1
                                        result='3';
                                    }
                                    else{
                                        result='5';
                                    }
                                                                    

                                        
                                        
                                    
                                    
                                    //special cicrumstances
                                    //special cicrumstances

                                    //turn results
                                    
                                    switch (result){
                                        case '1'://rolling a single 1
                                            rollagain=false;
                                            
                                            rolltotal=0;
                                            
                                            cumscore=cumscore+turnscore;
                                             if (odd == 0){
                                                 cumscore1=cumscore;
                                              }
                                             else if (i==1 || odd ==1) {
                                                 cumscore2=cumscore;
                                             }
                                            System.out.println("You rolled a pig. Ending Turn...");
                                            System.out.println("----------------------------------------------------------------------");
                                            System.out.println(player + " scored " + turnscore + " points this turn.");     
                                            System.out.println(player + "'s CUMULATIVE SCORE is: " + cumscore);
                                            if (odd == 0){
                                                turn1++;
                                                if (turn1 ==1){
                                                    name1high=turnscore;
                                                    name1low=turnscore;
                                                }
                                                else{
                                                    if(turnscore>name1high){
                                                        name1high=turnscore;
                                                    }
                                                    if(turnscore<name1low){
                                                        name1low=turnscore;
                                                    }   

                                                }
                                            }


                                            //calculating min and max for player 2
                                            else if (i==1 || odd ==1) {
                                                turn2++;
                                                if (turn2 ==1){
                                                    name2high=turnscore;
                                                    name2low=turnscore;
                                                }
                                                else{
                                                    if(turnscore>name2high){
                                                        name2high=turnscore;
                                                    }
                                                    if(turnscore<name2low){
                                                        name2low=turnscore;
                                                    }

                                                }
                                            }
                                            System.out.println("======================================================================");
                                            turnscore=0;
                                            break;
                                        case '2'://rolling two 1's
                                            
                                            rollagain=false;
                                            //calculating min and max for player 2
                                            if (odd == 0){
                                                turn1++;
                                                if (turn1 ==1){
                                                    name1high=turnscore;
                                                    name1low=turnscore;
                                                }
                                                else{
                                                    if(turnscore>name1high){
                                                        name1high=turnscore;
                                                    }
                                                    if(turnscore<name1low){
                                                        name1low=turnscore;
                                                    }   

                                                }
                                            }


                                            //calculating min and max for player 2
                                            else if (i==1 || odd ==1) {
                                                turn2++;
                                                if (turn2 ==1){
                                                    name2high=turnscore;
                                                    name2low=turnscore;
                                                }
                                                else{
                                                    if(turnscore>name2high){
                                                        name2high=turnscore;
                                                    }
                                                    if(turnscore<name2low){
                                                        name2low=turnscore;
                                                    }

                                                }
                                            }
                                            
                                            
                                             if (odd == 0){
                                                 cumscore1=cumscore;
                                              }
                                             else if (i==1 || odd ==1) {
                                                 cumscore2=cumscore;
                                             }
                                             cumscore =0; turnscore=0;
                                            rolltotal=0;                                            
                                            System.out.println(player + "'s CUMULATIVE SCORE has been reset to 0");
                                            System.out.println("Turn ending...");
                                            System.out.println("======================================================================");
                                           break;                                            
                                        case '3'://rolling doubles
                                            --i;
                                            rollagain=true;
                                            turnscore=turnscore+rolltotal;
 
                                            System.out.println("----------------------------------------------------------------------");
                                            //checking to see if the player has won
                                            if ((cumscore+turnscore)>=50){
                                                    System.out.println("======================================================================");
                                                    System.out.println("**********************************************************************");
                                                    System.out.println("======================================================================");
                                                    System.out.println(player + "'s score is: "+ (cumscore+turnscore));
                                                    System.out.println(player + " wins!!");
                                                    if (odd==0) {
                                                       name1wins++;
                                                       System.out.println(player + " has won " + name1wins + " times.");
                                                       //calculating min and max for player 1 if they are over 50
                                                       turn1++;
                                                       if (turn1 ==1){
                                                           name1high=turnscore;
                                                           name1low=turnscore;
                                                       }
                                                       else{
                                                           if(turnscore>name1high){
                                                                name1high=turnscore;
                                                           }
                                                           if(turnscore<name1low){
                                                               name1low=turnscore;
                                                           }   

                                                       }
                                                   }
                                                   if (odd==1) {
                                                       name2wins++;                                                       
                                                       System.out.println(player + " has won " + name2wins + " times.");
                                                       //calculating min and max for player 2 if they are over 50
                                                       turn2++;
                                                       if (turn2 ==1){
                                                               name2high=turnscore;
                                                               name2low=turnscore;
                                                       }
                                                       else{
                                                               if(turnscore>name2high){
                                                                   name2high=turnscore;
                                                               }
                                                               if(turnscore<name2low){
                                                                   name2low=turnscore;
                                                               }
                                                       }
                                                       
                                                    }
                                                    turnscore=0;
                                                    rollagain =false;
                                                    
                                                    quit=true;
                                               }
                                             else{
                                                System.out.println(player + "'s TURN TOTAL so far is: " + turnscore);
                                                System.out.println(player + "'s Tentative CUMULATIVE SCORE is: " + (cumscore+turnscore));
                                             }
                                            break;           
                                             
                                            
                                        default://no special circumstances
                                            --i;
                                            rollagain=false;
                                            turnscore=turnscore+rolltotal;
                                                if ((cumscore+turnscore)>=50){
                                                    System.out.println("======================================================================");
                                                    System.out.println("**********************************************************************");
                                                    System.out.println("======================================================================");
                                                    System.out.println(player + "'s score is: "+ (cumscore+turnscore));
                                                    System.out.println(player + " wins!!");
                                                    if (odd==0) {
                                                       name1wins++;

                                                       System.out.println(player + " has won " + name1wins + " times.");
                                                       //calculating min and max for player 1 if they are over 50
                                                       turn1++;
                                                       if (turn1 ==1){
                                                           name1high=turnscore;
                                                           name1low=turnscore;
                                                       }
                                                       else{
                                                           if(turnscore>name1high){
                                                                name1high=turnscore;
                                                           }
                                                           if(turnscore<name1low){
                                                               name1low=turnscore;
                                                           }   

                                                       }
                                                   }
                                                   if (odd==1) {
                                                       name2wins++;                                                       
                                                       System.out.println(player + " has won " + name2wins + " times.");
                                                       //calculating min and max for player 2 if they are over 50
                                                       turn2++;
                                                       if (turn2 ==1){
                                                               name2high=turnscore;
                                                               name2low=turnscore;
                                                       }
                                                       else{
                                                               if(turnscore>name2high){
                                                                   name2high=turnscore;
                                                               }
                                                               if(turnscore<name2low){
                                                                   name2low=turnscore;
                                                               }
                                                       }
                                                       
                                                    }
                                                    turnscore=0;
                                                    rollagain =false;
                                                    
                                                    quit=true;
                                               }
                                               else{
                                                   
                                                   System.out.println("----------------------------------------------------------------------");
                                                   System.out.println(player + "'s turn total is: " + turnscore);
                                                   System.out.println(player + "'s Tentative cumilative score is: " + (cumscore+turnscore));
                                                   System.out.println("----------------------------------------------------------------------");
                                               }
                                            
                                            break;    
                                    }
                                    
                                    //turn results
                                    
                                    
                                    
                                    
                                    
                                    break;
                                    
                                default:
                                    System.out.println("Error: Incorrect input. Try Again");
                                    System.out.println("***You may need to hit enter once before entering your choice***");


                                    --i;
                                    break;
                                
                            }
                        
                    }
                }while (quit ==false);
                        
            }
            else { 
                System.out.println("You entered an invalid entry");
                System.out.println("***You may need to hit enter once before entering your choice***");
                  
                    }
            
            
            
            
                       
        
          }//Main while exit==false    
        }//Menu
              
        
        
        
    }//Main Class
}//package


