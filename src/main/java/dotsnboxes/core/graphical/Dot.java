package dotsnboxes.core.graphical;

/**
 * A Dot is a graphical representation of a dot on the physical board. It is
 * used to help render the board.
 * 
 * @author luis
 */
public class Dot {

    private int x;
    private int y;
    private int width;

    public Dot(int x, int y, int width) {

        setX(x);
        setY(y);
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

    public int getWidth() {

        return width;
    }

    public void setWidth(int width) {

        this.width = width;
    }
}