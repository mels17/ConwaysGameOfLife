package main;


public class World {
    private Cell[][] worldGrid;
    private int noOfLivingCells;
    private int noOfDeadCells;

    public World(Cell[][] worldGrid) {
        this.worldGrid = worldGrid;
        setNoOfLivingAndDead();
    }

    private void setNoOfLivingAndDead() {
        noOfLivingCells = 0;
        noOfDeadCells = 0;
        for(int i = 0; i < worldGrid.length; i++) {
            for (int j = 0; j < worldGrid[i].length; j++) {
                int result = (worldGrid[i][j].getState()) ? noOfLivingCells++ : noOfDeadCells++;
            }
        }
    }

    public void addLivingCellAt(int x, int y) {
        // Get cell from the world grid and make it living cell.
        worldGrid[x][y].setState(Cell.ALIVE);
        setNoOfLivingAndDead();
    }

    public void addDeadCellAt(int x, int y) {
        // Get cell from the world grid and make it living cell.
        worldGrid[x][y].setState(Cell.DEAD);
        setNoOfLivingAndDead();
    }

    public int getNoOfLivingCells() {
        return noOfLivingCells;
    }

    public int getNoOfDeadCells() {
        return noOfDeadCells;
    }
}
