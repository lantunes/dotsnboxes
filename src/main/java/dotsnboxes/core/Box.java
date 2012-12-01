package dotsnboxes.core;

/**
 * A Box instance represents a box on the board. On the board, a box is simply a
 * collection of the four Lines that enclose it.
 * 
 * @author luis
 */
public class Box {

    private Line topLine;
    private Line leftLine;
    private Line rightLine;
    private Line bottomLine;

    public Line getTopLine() {

        return topLine;
    }

    public void setTopLine(Line topLine) {

        this.topLine = topLine;
    }

    public Line getLeftLine() {

        return leftLine;
    }

    public void setLeftLine(Line leftLine) {

        this.leftLine = leftLine;
    }

    public Line getRightLine() {

        return rightLine;
    }

    public void setRightLine(Line rightLine) {

        this.rightLine = rightLine;
    }

    public Line getBottomLine() {

        return bottomLine;
    }

    public void setBottomLine(Line bottomLine) {

        this.bottomLine = bottomLine;
    }

    public boolean isComplete() {

        return topLine != null && topLine.isSelected() && leftLine != null && leftLine.isSelected()
                && rightLine != null && rightLine.isSelected() && bottomLine != null && bottomLine.isSelected();
    }
}