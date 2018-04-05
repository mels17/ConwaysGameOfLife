package main;

public class NextGeneration {
    // Determine if a cell is live or dead in the next generation.
    // It also gives the next world.

    private static boolean computeNextGenForCell(Cell cell, int noOfLivingNeighbours) {
        return cell.getState() ? aliveCellGetNextState(noOfLivingNeighbours) : deadCellGetNextState(noOfLivingNeighbours);
    }

    private static boolean deadCellGetNextState(int noOfLivingNeighbours) {
        return noOfLivingNeighbours == 3;
    }

    private static boolean aliveCellGetNextState(int noOfLivingNeighbours) {
        return noOfLivingNeighbours == 2 || noOfLivingNeighbours == 3;
    }

    private static Cell[][] populateWithDeadCells(World world) {
        Cell[][] cells = new Cell[world.getWorldGrid().length][world.getWorldGrid()[0].length];
        for(int row = 0; row < world.getWorldGrid().length; row++) {
            for (int column = 0; column < world.getWorldGrid()[row].length; column++) {
                cells[row][column] = new Cell(row, column, Cell.DEAD);
            }
        }
        return cells;
    }

    public static World getNextWorld(World world) {
        World nextWorld = new World(populateWithDeadCells(world));

        for (int row = 0; row < world.getWorldGrid().length; row++) {
            for (int column = 0; column < world.getWorldGrid()[row].length; column++) {
                nextWorld.getCellAt(row, column).setState(computeNextGenForCell(world.getCellAt(row, column),
                        Neighbour.getLivingNeighbours(world.getCellAt(row, column), world.getWorldGrid())));
            }
        }
        return nextWorld;
    }
}