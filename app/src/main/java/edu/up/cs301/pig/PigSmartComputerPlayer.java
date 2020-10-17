
package edu.up.cs301.pig;



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
    public class PigSmartComputerPlayer extends GameComputerPlayer {
        private int track = 0;
        /**
         * ctor does nothing extra
         */
        public PigSmartComputerPlayer(String name) {
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

            int selectAction = 0;

            int p1CurrenScore = statePCP.getPlayer1Score();
            int pPCcurrenScore = statePCP.getPlayer0Score();
            int totalCurrenScore = statePCP.getRunningTotal();

            //If Pc has less points and human can win in three rolls or less, always roll.
            if(pPCcurrenScore < p1CurrenScore && ((50 - p1CurrenScore) <= 18)){

                if(pPCcurrenScore < p1CurrenScore){ //Until you don't have more points than the human -> roll
                    selectAction = 0;
                }

                if(pPCcurrenScore >=44){ //It can win with one roll -> hold.
                    selectAction = 1;
                }
            }
            if(totalCurrenScore >= 15){ //If you roll for 25 points -> hold
                selectAction = 1;

            }
            if(pPCcurrenScore > p1CurrenScore && pPCcurrenScore < 35){ //If PC is winning and has less than 35 ->roll
                selectAction = 0;
            }

            if(track == 3){
                selectAction = 1;
            }

            if (selectAction == 1) {
                PigHoldAction newHold = new PigHoldAction(this);
                track = 0;
                game.sendAction(newHold);
            } else if (selectAction == 0) {
                PigRollAction newRoll = new PigRollAction(this);
                track += 1;
                game.sendAction(newRoll);

            }

        }//receiveInfo

    }


