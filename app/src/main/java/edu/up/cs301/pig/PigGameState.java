package edu.up.cs301.pig;

import java.util.ArrayList;

import edu.up.cs301.game.infoMsg.GameInfo;

public class PigGameState extends GameInfo {
    private int playerId;
    private int player1Score;
    private int player0Score;
    private int runningTotal;
    private int die;



    public PigGameState(){

        die = 1;
        playerId = 0;
        runningTotal = 0;
        player1Score= 0;
        player0Score=0;

    }

    //Create a deep copy.
    public PigGameState(PigGameState original){
        this.player0Score = original.player0Score;
        this.player1Score = original.player1Score;
        this.playerId = original.playerId;
        this.runningTotal = original.runningTotal;
        this.die = original.die;
    }

    public int getPlayerId() {
        return playerId;
    }


    public int getPlayer1Score(){
        return player1Score;
    }

    public int getPlayer0Score(){
        return player0Score;
    }

    public int getRunningTotal(){
        return runningTotal;
    }

    public int getDie() {
        return die;
    }

    public void setPlayerId(int playerId){
        this.playerId = playerId;
    }

    public void setPlayer0Score(int player0Score) {
        this.player0Score = player0Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public void setRunningTotal(int runningTotal) {
        this.runningTotal = runningTotal;
    }

    public void setDie(int die) {
        this.die = die;
    }
}
