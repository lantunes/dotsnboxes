package dotsnboxes.core.graphical;

/**
 * A class that simply stores the spatial defaults for a GraphicalBoard. It
 * allows GraphicalBoards to be easily changed in terms of their graphical
 * appearance.
 * 
 * @author luis
 */
public class GraphicalBoardSettings {

    private int dotStartX = 15;
    private int dotStartY = 15;
    private int dotWidth = 15;
    private int dotSeparatorLength = 45;
    private int lineOffset = 3;
    private int lineWidth = 30;
    private int lineHeight = 7;

    public GraphicalBoardSettings() {
    }

    public GraphicalBoardSettings(int dotStartX, int dotStartY, int dotWidth, int dotSeparatorLength, int lineOffset,
            int lineWidth, int lineHeight) {

        this.dotStartX = dotStartX;
        this.dotStartY = dotStartY;
        this.dotWidth = dotWidth;
        this.dotSeparatorLength = dotSeparatorLength;
        this.lineOffset = lineOffset;
        this.lineWidth = lineWidth;
        this.lineHeight = lineHeight;
    }

    public int dotStartX() {

        return dotStartX;
    }

    public int dotStartY() {

        return dotStartY;
    }

    public int dotWidth() {

        return dotWidth;
    }

    public int dotSeparatorLength() {

        return dotSeparatorLength;
    }

    public int lineOffset() {

        return lineOffset;
    }

    public int lineWidth() {

        return lineWidth;
    }

    public int lineHeight() {

        return lineHeight;
    }
}