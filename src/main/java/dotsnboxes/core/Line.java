package dotsnboxes.core;

import java.util.HashSet;
import java.util.Set;

/**
 * A Line instance represents a physical line on the board, and tracks the
 * internal state of that line during the game. Namely, it keeps track of the
 * Boxes which are associated with it, and whether or not it has been selected
 * by a player.
 * 
 * @author luis
 */
public class Line {

    protected boolean selected = false;

    /* a Line should have at least one box but no more than two */
    protected Set<Box> boxes = new HashSet<Box>();

    public Line() {
    }

    public boolean isSelected() {

        return selected;
    }

    public void setSelected(boolean selected) {

        this.selected = selected;
    }

    public int getBoxCount() {

        return boxes.size();
    }

    public boolean containsBox(Box box) {

        return boxes.contains(box);
    }

    public void addBox(Box box) {

        if (boxes.size() == 2) {
            throw new IllegalStateException("Line is already associated to two boxes.");
        }
        boxes.add(box);
    }

    public Set<Box> getBoxesCompleted() {

        Set<Box> completed = new HashSet<Box>();
        for (Box box : boxes) {
            if (box.isComplete()) {
                completed.add(box);
            }
        }
        return completed;
    }
}