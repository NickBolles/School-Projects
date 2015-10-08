/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.io.*;
import java.util.*;

/**
 *
 * @author Nicholas
 */
public class Gamestate {
    private int currentPlayerIndex=0, numPlayers, numOfTurns=0;
    public Player players[];
    private boolean successfull, hasSaveGame=false;
    private InputStream gameStateFile;

    Gamestate(InputStream resourceAsStream) {
        this.gameStateFile=resourceAsStream;
        numPlayers=0;
    }
    public void setCurrentPlayerIndex(int playerTurn){
        currentPlayerIndex=playerTurn;
    }
    public int getCurrentPlayerIndex(){
        return currentPlayerIndex;
    }
    public void setNumPlayers(int numPlayers){
        this.numPlayers=numPlayers;      
    }
    public int getNumPlayers(){
        return numPlayers;
        
    }
    public void addPlayers(Player[] players){
            this.players=players;
    }
    
   public void nextPlayer(){
       currentPlayerIndex++;
       if (currentPlayerIndex>=players.length){
           currentPlayerIndex=0;
           numOfTurns++;
       }
       
   }
   public void resetStats(){
             currentPlayerIndex=0; numOfTurns=0;
             successfull=false;
       }
   public int getNumOfTurns(){
       return numOfTurns;
   }
   public boolean save()throws IOException{
       //TODO save to file in persistant format
       try{
            FileWriter fileWriter=new FileWriter( "gamestate.txt");
            fileWriter.write(currentPlayerIndex+"\r\n");
            fileWriter.write(numPlayers+"\r\n");
            fileWriter.write(numOfTurns+"\r\n");
            for (int i=0; i<players.length;i++){
                fileWriter.write(players[i].getName()+"\t");
                fileWriter.write(players[currentPlayerIndex].getTurnTotal()+"\t");
                fileWriter.write(players[i].getCumScore()+"\t");
                fileWriter.write(players[i].getLowScore()+"\t");
                fileWriter.write(players[i].getHighScore()+"\t");
                fileWriter.write(players[i].getWin()+"\t");
                if (players[i].getForceRollAgain()==true){
                    fileWriter.write('1'+ "\r\n");
                }
                else{
                    fileWriter.write('0'+ "\r\n");
                }
            }
            
            fileWriter.close();
            successfull=true;
       }catch(IOException e){
           System.out.println("Error Saving File");
       }

       
       return successfull;
   }
   public boolean load() throws IOException{
      boolean success=false;
      FileReader file;
      Scanner scanner= new Scanner(file=new FileReader("gamestate.txt"));

      try{ 
        File f=new File("gamestate.txt");
            while (scanner.hasNext()){
                currentPlayerIndex=scanner.nextInt();
                numPlayers=scanner.nextInt();
                numOfTurns=scanner.nextInt();
                players=new Player [numPlayers];

                for (int i=0; i<players.length;i++){
                    players[i].setName(scanner.next());
                    players[currentPlayerIndex].setTurnTotal(scanner.nextInt());
                    players[i].setCumScore(scanner.nextInt());
                    players[i].setLowScore(scanner.nextInt());
                    players[i].setHighScore(scanner.nextInt());
                    players[i].setWin(scanner.nextInt());
                    players[i].setForceRollAgain(scanner.nextBoolean());
                }
            
            }
            scanner.close();
            file.close();
            success=f.delete();
      }catch(FileNotFoundException e){
          System.out.println("----------------------------------------------------------------------");
          System.out.println("NO save File Exists.");
          System.out.println("----------------------------------------------------------------------");
          success=false;
          file.close();

      }catch(InputMismatchException d){
          System.out.println("----------------------------------------------------------------------");
          System.out.println("Error: Input Mismatch, Most Likely a Currupt save");
          System.out.println("----------------------------------------------------------------------");
          success=false;
          file.close();

      }
       return success;
   }
   public boolean checkSaveGame(){
       boolean success=false;
       try{       
        Properties prop= new Properties();
        prop.load(new InputStreamReader(gameStateFile));
        if (prop.isEmpty()==true){
           success=false;
       }
        else{
           success=true;
        }
      }catch(FileNotFoundException e){
          success=false;
          System.out.println("error: File Not Found");
      }catch(IOException e){
          System.out.println("IOException");
          success=false;
      }
       System.out.println("the file is there? :" + success);
       return success;
   }
   public boolean deleteSaveGame(){
       File f=new File ("gamestate.txt");
       boolean success = f.delete();
       return success;
   }
}
    

