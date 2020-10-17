package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    private PigGameState newGamePiggyState;
    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        newGamePiggyState = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if(newGamePiggyState.getPlayerId() == playerIdx){
            return true;
        }
        return false;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof PigHoldAction){
            int playerTurns = newGamePiggyState.getPlayerId();
            if(playerTurns == 1){
                newGamePiggyState.setPlayer1Score(newGamePiggyState.getRunningTotal() + newGamePiggyState.getPlayer1Score());
                newGamePiggyState.setRunningTotal(0);
                newGamePiggyState.setPlayerId(0);
                return true;
            }else{
                newGamePiggyState.setPlayer0Score(newGamePiggyState.getRunningTotal() + newGamePiggyState.getPlayer0Score());
                newGamePiggyState.setRunningTotal(0);
                newGamePiggyState.setPlayerId(1);
                return true;
            }
        }else if(action instanceof PigRollAction){

            int min = 1;
            int max = 6;
            int dieNumber = (int)(Math.random() * (max - min + 1) + min);
            newGamePiggyState.setDie(dieNumber);

            if(newGamePiggyState.getDie() == 1){
                newGamePiggyState.setRunningTotal(0);
                if(newGamePiggyState.getPlayerId() == 1){
                    newGamePiggyState.setPlayerId(0);
                }else{
                    newGamePiggyState.setPlayerId(1);
                }
                return true;
            }else{
                newGamePiggyState.setRunningTotal(newGamePiggyState.getRunningTotal() + newGamePiggyState.getDie());
                return true;
            }
        }

        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PigGameState copy = this.newGamePiggyState;
        p.sendInfo(copy);

    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        if(newGamePiggyState.getPlayer1Score() >= 50){
            System.out.println("Player " + newGamePiggyState.getPlayerId() + "won!!\n" + "The score was: " + newGamePiggyState.getPlayer1Score() );

        }else if(newGamePiggyState.getPlayer0Score() >= 50){
            System.out.println("Player " + newGamePiggyState.getPlayerId() + "won!!\n" + "The score was: " + newGamePiggyState.getPlayer0Score() );
        }
        return null;
    }

}// class PigLocalGame
