package dotsnboxes.core;

import java.util.HashSet;
import java.util.Set;

/**
 * A Player instance keeps track of the internal state of a player during a
 * gaming session. It tracks which lines belong to the player, how many boxes
 * the player has completed, and how many games the player has won over the
 * course of the session.
 * 
 * @author luis
 */
public class Player {

    private Set<Line> lines = new HashSet<Line>();
    private Set<Box> boxesCompleted = new HashSet<Box>();
    private int gamesWon = 0;
    private Strategy strategy = null; // null for a human player

    public Player() {
    }

    public Player(Strategy strategy) {

        this.strategy = strategy;
    }

    public Set<Line> getLines() {

        return lines;
    }

    public void setLines(Set<Line> lines) {

        this.lines = lines;
    }

    public Strategy getStrategy() {

        return strategy;
    }

    public void setStrategy(Strategy strategy) {

        this.strategy = strategy;
    }

    public byte nextMove(byte[] currentBoardConfiguration) {

        if (strategy == null) {
            throw new IllegalStateException("This player has no strategy configured.");
        }
        return strategy.evaluateNextLine(currentBoardConfiguration);
    }

    public Set<Box> getBoxesCompleted() {

        return boxesCompleted;
    }

    public void addBoxesCompleted(Set<Box> boxesCompleted) {

        this.boxesCompleted.addAll(boxesCompleted);
    }

    public void resetLines() {

        lines = new HashSet<Line>();
    }

    public void resetBoxesCompleted() {

        boxesCompleted = new HashSet<Box>();
    }

    public int getGamesWon() {

        return gamesWon;
    }

    public void gameWon() {

        gamesWon++;
    }
}