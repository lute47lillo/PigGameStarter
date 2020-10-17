package edu.up.cs301.pig;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        PigGameState statePCP = (PigGameState) info;
        if(statePCP.getPlayerId() != this.playerNum){
            return;
        }

        int min = 0;
        int max = 1;
        int selectAction = (int)(Math.random() * (max - min + 1) + min);

        if (selectAction == 1) {
            PigHoldAction newHold = new PigHoldAction(this);
            game.sendAction(newHold);
        } else if (selectAction == 0) {
            PigRollAction newRoll = new PigRollAction(this);
            game.sendAction(newRoll);

        }

    }//receiveInfo

}
