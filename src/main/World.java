package main;


public class World {
    private Cell[][] worldGrid;
    private int noOfLivingCells;
    private int noOfDeadCells;

    private static final String DEAD_CELL_REP = "0";

    private static final String LIVE_CELL_REP = "1";
    // Constructors
    public World(boolean[][] booleanGrid) {
        this.worldGrid = initializeCellGridGivenBooleanArray(booleanGrid);
        setNoOfLivingAndDead();
    }

    public World(Cell[][] worldGrid) {
        this.worldGrid = worldGrid;
        setNoOfLivingAndDead();
    }

    private Cell[][] initializeCellGridGivenBooleanArray(boolean[][] booleanGrid) {
        Cell[][] cellGrid = new Cell[booleanGrid.length][booleanGrid[0].length];
        for (int row = 0; row < booleanGrid.length; row++) {
            for (int column = 0; column < booleanGrid[row].length; column++) {
                cellGrid[row][column] = new Cell(row, column, booleanGrid[row][column]);
            }
        }
        return cellGrid;
    }

    public static String[][] getStringRepresentationOfWorld(World world) {
        Cell[][] worldGrid = world.getWorldGrid();
        String[][] worldString = new String[worldGrid.length][worldGrid[0].length];
        for(int row = 0; row < worldGrid.length; row++) {
            for (int column = 0; column < worldGrid[row].length; column++) {
                if (worldGrid[row][column].getState()) {
                    worldString[row][column] = LIVE_CELL_REP;
                } else {
                    worldString[row][column] = DEAD_CELL_REP;
                }
            }
        }
        return worldString;
    }

    public Cell[][] getWorldGrid() {
        return worldGrid;
    }

    private void setNoOfLivingAndDead() {
        noOfLivingCells = 0;
        noOfDeadCells = 0;
        for(int row = 0; row < worldGrid.length; row++) {
            for (int column = 0;  column < worldGrid[row].length; column++) {
                int result = (worldGrid[row][column].getState()) ? noOfLivingCells++ : noOfDeadCells++;
            }
        }
    }

    public void addLivingCellAt(int rowNumber, int columnNumber) {
        // Get cell from the world grid and make it living cell.
        worldGrid[rowNumber][columnNumber].setState(Cell.ALIVE);
        setNoOfLivingAndDead();
    }

    public void addDeadCellAt(int rowNumber, int columnNumber) {
        // Get cell from the world grid and make it living cell.
        worldGrid[rowNumber][columnNumber].setState(Cell.DEAD);
        setNoOfLivingAndDead();
    }

    public int getNoOfLivingCells() {
        return noOfLivingCells;
    }

    public int getNoOfDeadCells() {
        return noOfDeadCells;
    }

    public Cell getCellAt(int rowNumber, int columnNumber) {
        return worldGrid[rowNumber][columnNumber];
    }

//    public boolean deadWorld() {
//        return this.noOfDeadCells == (worldGrid.length * worldGrid[0].length);
//    }

    public boolean worldIsDead() {
        Cell[][] worldGrid = this.getWorldGrid();
        int noOfDeadCells = 0;
        for (int row = 0; row < worldGrid.length; row++) {
            for(int column = 0; column < worldGrid[row].length; column++) {
                if(!worldGrid[row][column].getState()) {
                    noOfDeadCells++;
                }
            }
        }
        return noOfDeadCells == (worldGrid.length) * (worldGrid[0].length);
    }
}
