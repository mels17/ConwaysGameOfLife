package main;


public class World {
    private Cell[][] worldGrid;

    private static final String DEAD_CELL_REP = "0";

    private static final String LIVE_CELL_REP = "1";
    // Constructors
    public World(boolean[][] booleanGrid) {
        this.worldGrid = initializeCellGridGivenBooleanArray(booleanGrid);
    }

    public World(Cell[][] worldGrid) {
        this.worldGrid = worldGrid;
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
                if (worldGrid[row][column].isAlive()) {
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

    public void addLivingCellAt(int rowNumber, int columnNumber) {
        // Get cell from the world grid and make it living cell.
        worldGrid[rowNumber][columnNumber].setState(Cell.ALIVE);
    }

    public Cell getCellAt(int rowNumber, int columnNumber) {
        return worldGrid[rowNumber][columnNumber];
    }

    public boolean worldIsDead() {
        Cell[][] worldGrid = this.getWorldGrid();
        int noOfDeadCells = 0;
        for (int row = 0; row < worldGrid.length; row++) {
            for(int column = 0; column < worldGrid[row].length; column++) {
                if(!worldGrid[row][column].isAlive()) {
                    noOfDeadCells++;
                }
            }
        }
        return noOfDeadCells == (worldGrid.length) * (worldGrid[0].length);
    }

    public int getNoOfRows() {
        return this.worldGrid.length;
    }

    public int getNoOfColumns() {
        return this.worldGrid[0].length;
    }
}
