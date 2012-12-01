package dotsnboxes.core;

/**
 * A Board represents the physical game board. It is comprised of a collection
 * of Lines and Boxes.
 * 
 * @author luis
 */
public class Board {

    protected Line[] lines;
    protected Box[] boxes;
    protected int rows;
    protected int columns;

    public Board() {
    }

    public Board(int rows, int columns) {

        init(rows, columns);
    }

    protected void init(int rows, int columns) {

        this.rows = rows;
        this.columns = columns;
        createBoxes();
        createLines();
    }

    @SuppressWarnings("unchecked")
    public <T extends Line> T[] getLines() {

        return (T[]) lines;
    }

    public Box[] getBoxes() {

        return boxes;
    }

    public int getRows() {

        return rows;
    }

    public int getColumns() {

        return columns;
    }

    public boolean allLinesSelected() {

        for (Line line : getLines()) {
            if (!line.isSelected()) {
                return false;
            }
        }
        return true;
    }

    public byte[] getConfiguration() {

        byte[] configuration = new byte[lines.length];
        for (int i = 0; i < lines.length; i++) {
            configuration[i] = (byte) ((lines[i].isSelected()) ? 1 : 0);
        }
        return configuration;
    }

    protected void createLines() {

        Line[] lines = new Line[(2 * columns * rows) + columns + rows];
        int lineIndex = 0;

        int boxCounter = 0;
        boolean isTop = true;
        boolean isFirstRow = true;

        /* horizontal lines */
        /* for every row + 1 */
        for (int i = 0; i < rows + 1; i++) {

            /* for every column */
            for (int j = 0; j < columns; j++) {

                Line line = new Line();
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
            }
            boxCounter = isTop ? boxCounter - columns : boxCounter;
            if (isFirstRow) {
                isTop = false;
                isFirstRow = false;
            }
        }

        boxCounter = 0;

        /* vertical lines */
        /* for every row */
        for (int i = 0; i < rows; i++) {

            /* for every column + 1 */
            for (int j = 0; j < columns + 1; j++) {

                Line line = new Line();
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
            }
        }

        this.lines = lines;
    }

    protected void createBoxes() {

        Box[] boxes = new Box[rows * columns];
        for (int i = 0; i < (rows * columns); i++) {
            boxes[i] = new Box();
        }
        this.boxes = boxes;
    }
}