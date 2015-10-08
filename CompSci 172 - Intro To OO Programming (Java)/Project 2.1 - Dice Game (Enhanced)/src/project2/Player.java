/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

/**
 *
 * @author Nicholas
 */
public class Player {
    private String name;
    private int roll1, roll2;        
    Die d1=new Die();
    Die d2=new Die();
    private int turnTotal, cumScore, lowScore, highScore, temp, numWins;
    public boolean forceRollAgain=false, changePlayer=false;
    private RollResult rollResult;

    
    
    Player(String tempName) {
        this.name=tempName;
        lowScore=0;
        highScore=0;
    }
    public void setName(String tempName){
        this.name=tempName;
    }
    public String getName(){
        return this.name;
    }
    public int getTurnTotal(){
        return turnTotal;
    }
    public void setTurnTotal(int score){
        turnTotal=score;
    }
    public void setCumScore(int score){
        cumScore=score;
    }
    public int getCumScore(){
        return cumScore;
    }
    public void setLowScore(int score){
        lowScore=score;
    }
    public int getLowScore(){
        return lowScore;
    }    
    public void setHighScore(int turnTotal){
        highScore=turnTotal;
    }
    public int getHighScore(){
        return highScore;
    }

    public void setWin(int wins){
        this.numWins=wins;
    }
    public void incWin(){
        numWins++;
    }
    public void setWinForfiet(){
        //for when the player forfiets they get -1 win
        ++numWins;
    }
    public int getWin(){
        return numWins;
    }
    public boolean getForceRollAgain(){
        return forceRollAgain;
    }
    public void setForceRollAgain(boolean scanner){
        this.forceRollAgain=scanner;
    }
    public void resetStats(){
    roll1=0; roll2=0;        
    turnTotal = 0; cumScore =0; temp=0;
    forceRollAgain=false; changePlayer=false;
    
    }
    public void roll(){
        forceRollAgain=false;
        roll1=d1.roll();
        roll2=d2.roll();
        d1.display();
        d2.display();
        if (roll1==1 & roll2==1 ){
            rollResult=RollResult.TWO_PIG;
            turnTotal=0;
            cumScore=0;
            


        }
        if (roll1==roll2 && roll1!=1){
            rollResult=RollResult.SAME;
            turnTotal= turnTotal + roll1 + roll2;
            forceRollAgain=true;
        }
        else if(roll1==1 | roll2==1){
            rollResult=RollResult.ONE_PIG;
            turnTotal=0;

            }
        else {
            rollResult=RollResult.DIFF;
            turnTotal= turnTotal + roll1 + roll2;
            
        }
    }
    public RollResult getRollResult(){
        return rollResult;
    }


   
}




/*
 * 
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
 * 
 */