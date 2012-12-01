package dotsnboxes.core;

import java.util.Set;

/**
 * A Game instance keeps track of the internal state of a game. It assigns
 * selected lines to the players, and keeps track of which player is active.
 * 
 * @author luis
 */
public class Game {

    public static final int FIRST_PLAYER_WON = 1;
    public static final int SECOND_PLAYER_WON = 2;
    public static final int TIE = 3;

    private Player[] players = new Player[] { new Player(), new Player() };

    private int activePlayer = 0;

    public Game() {
    }

    public void reset() {

        getFirstPlayer().resetBoxesCompleted();
        getFirstPlayer().resetLines();
        getSecondPlayer().resetBoxesCompleted();
        getSecondPlayer().resetLines();
        activePlayer = 0;
    }

    public int complete() {

        if (getFirstPlayer().getBoxesCompleted().size() > getSecondPlayer().getBoxesCompleted().size()) {

            getFirstPlayer().gameWon();
            return FIRST_PLAYER_WON;

        } else if (getSecondPlayer().getBoxesCompleted().size() > getFirstPlayer().getBoxesCompleted().size()) {

            getSecondPlayer().gameWon();
            return SECOND_PLAYER_WON;
        }
        return TIE;
    }

    public boolean selectLine(Line line) {

        if (line != null && !getFirstPlayer().getLines().contains(line) && !getSecondPlayer().getLines().contains(line)) {

            Set<Box> boxesCompleted = line.getBoxesCompleted();
            getActivePlayer().addBoxesCompleted(boxesCompleted);
            getActivePlayer().getLines().add(line);
            nextTurn(boxesCompleted.size() > 0);

            return true;
        }
        return false;
    }

    public Player[] getPlayers() {

        return players;
    }

    public Player getFirstPlayer() {

        return players[0];
    }

    public byte getFirstPlayerNextMove(byte[] currentBoardConfiguration) {

        return players[0].nextMove(currentBoardConfiguration);
    }

    public void setFirstPlayerStrategy(Strategy strategy) {

        players[0].setStrategy(strategy);
    }

    public Player getSecondPlayer() {

        return players[1];
    }

    public byte getSecondPlayerNextMove(byte[] currentBoardConfiguration) {

        return players[1].nextMove(currentBoardConfiguration);
    }

    public void setSecondPlayerStrategy(Strategy strategy) {

        players[1].setStrategy(strategy);
    }

    public Player getActivePlayer() {

        return players[activePlayer];
    }

    public int getFirstPlayerScore() {

        return players[0].getBoxesCompleted().size();
    }

    public int getFirstPlayerGamesWon() {

        return players[0].getGamesWon();
    }

    public int getSecondPlayerScore() {

        return players[1].getBoxesCompleted().size();
    }

    public int getSecondPlayerGamesWon() {

        return players[1].getGamesWon();
    }

    public boolean isFirstPlayerActive() {

        return activePlayer == 0;
    }

    public boolean isSecondPlayerActive() {

        return activePlayer == 1;
    }

    private void nextTurn(boolean goAgain) {

        if (!goAgain) {
            activePlayer = activePlayer == 0 ? 1 : 0;
        }
    }
}