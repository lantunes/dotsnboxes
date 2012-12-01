package dotsnboxes.core.graphical;

import dotsnboxes.core.Line;

/**
 * A GraphicalLine simply adds spatial characteristics to a Line. Namely, the x
 * and y starting coordinates of the Line, and its width and height, are stored
 * here.
 * 
 * @author luis
 */
public class GraphicalLine extends Line {

    private int x;
    private int y;
    private int height;
    private int width;

    public GraphicalLine(int x, int y, int width, int height) {

        setX(x);
        setY(y);
        setHeight(height);
        setWidth(width);
    }

    public int getX() {

        return x;
    }

    public void setX(int x) {

        this.x = x;
    }

    public int getY() {

        return y;
    }

    public void setY(int y) {

        this.y = y;
    }

    public int getHeight() {

        return height;
    }

    public void setHeight(int height) {

        this.height = height;
    }

    public int getWidth() {

        return width;
    }

    public void setWidth(int width) {

        this.width = width;
    }

    @Override
    public int hashCode() {

        int result = 17;
        result = 37 * result + x;
        result = 37 * result + y;
        return result;
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof GraphicalLine))
            return false;

        if (o == this)
            return true;

        GraphicalLine line = (GraphicalLine) o;
        return line.x == x && line.y == y;
    }
}