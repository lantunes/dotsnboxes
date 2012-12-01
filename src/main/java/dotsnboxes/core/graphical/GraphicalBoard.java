package dotsnboxes.core.graphical;

import dotsnboxes.core.Board;
import dotsnboxes.core.Line;

/**
 * A GraphicalBoard is a Board that can be rendered graphically. That is, it
 * works with the x and y coordinates of the Lines and Dots to render itself
 * spatially in a sense that is intelligible to human players.
 * 
 * @author luis
 */
public class GraphicalBoard extends Board {

    private Dot[] dots;

    GraphicalBoardSettings settings = new GraphicalBoardSettings();

    public GraphicalBoard(int rows, int columns) {

        this(rows, columns, null);
    }

    public GraphicalBoard(int rows, int columns, GraphicalBoardSettings settings) {

        if (settings != null) {
            this.settings = settings;
        }
        init(rows, columns);
        createDots();
    }

    private void createDots() {

        Dot[] dots = new Dot[rows + (columns * rows) + columns + 1];
        int k = 0;
        int x = settings.dotStartX();
        int y = settings.dotStartY();
        for (int i = 0; i < columns + 1; i++) {

            for (int j = 0; j < rows + 1; j++) {
                dots[k++] = new Dot(x, y, settings.dotWidth());
                y += settings.dotSeparatorLength();
            }
            x += settings.dotSeparatorLength();
            y = settings.dotStartY();
        }
        this.dots = dots;
    }

    /*
     * TODO abstract this and the parent createLines() into a common method
     */
    @Override
    protected void createLines() {

        Line[] lines = new GraphicalLine[(2 * columns * rows) + columns + rows];
        int lineIndex = 0;

        int boxCounter = 0;
        int x = settings.dotStartX() + settings.dotWidth();
        int y = settings.dotStartY() + settings.lineOffset();
        boolean isTop = true;
        boolean isFirstRow = true;

        /* horizontal lines */
        /* for every row + 1 */
        for (int i = 0; i < rows + 1; i++) {

            /* for every column */
            for (int j = 0; j < columns; j++) {

                Line line = new GraphicalLine(x, y, settings.lineWidth(), settings.lineHeight());
                line.addBox(boxes[boxCounter]);
                if (isTop) {
                    boxes[boxCounter].setTopLine(line);
                } else {
                    boxes[boxCounter].setBottomLine(line);
                    /* if there is a next row */
                    if (i + 1 < rows + 1) {
                        line.addBox(boxes[boxCounter + columns]);
                        boxes[boxCounter + columns].setTopLine(line);
                    }
                }
                boxCounter++;
                lines[lineIndex++] = line;
                x += settings.dotSeparatorLength();
            }
            boxCounter = isTop ? boxCounter - columns : boxCounter;
            if (isFirstRow) {
                isTop = false;
                isFirstRow = false;
            }
            y += settings.dotSeparatorLength();
            x = settings.dotStartX() + settings.dotWidth();
        }

        boxCounter = 0;
        x = settings.dotStartX() + settings.lineOffset();
        y = settings.dotStartY() + settings.dotWidth();

        /* vertical lines */
        /* for every row */
        for (int i = 0; i < rows; i++) {

            /* for every column + 1 */
            for (int j = 0; j < columns + 1; j++) {

                Line line = new GraphicalLine(x, y, settings.lineHeight(), settings.lineWidth());
                /* if there is a previous vertical line */
                if (j > 0) {
                    boxes[boxCounter - 1].setRightLine(line);
                    line.addBox(boxes[boxCounter - 1]);
                }
                /* if there is a next column */
                if (j + 1 < columns + 1) {
                    boxes[boxCounter].setLeftLine(line);
                    line.addBox(boxes[boxCounter]);
                    boxCounter++;
                }
                lines[lineIndex++] = line;
                x += settings.dotSeparatorLength();
            }
            y += settings.dotSeparatorLength();
            x = settings.dotStartX() + settings.lineOffset();
        }

        this.lines = lines;
    }

    public Dot[] getDots() {

        return dots;
    }

    public int getDotWidth() {

        return settings.dotWidth();
    }
}