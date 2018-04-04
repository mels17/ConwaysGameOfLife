package main;

public class Cell {
    public static final boolean ALIVE = true;
    public static final boolean DEAD = false;

    private int xCoordinate;
    private int yCoordinate;

    private boolean state;

    public Cell(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Cell(int x, int y, boolean state) {
        this.xCoordinate = x;
        this.yCoordinate = y;

        this.state = state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return state;
    }

    public int getX() {
        return xCoordinate;
    }

    public int getY() {
        return yCoordinate;
    }
}
