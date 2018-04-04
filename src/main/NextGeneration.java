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

    public static World getNextWorld(World world) {
        World nextWorld = new World(new Cell[world.getWorldGrid().length][world.getWorldGrid().length == 0 ? 0 : world.getWorldGrid()[0].length]);

        for (int x = 0; x < world.getWorldGrid().length; x++) {
            for (int y = 0; y < world.getWorldGrid()[x].length; y++) {
                nextWorld.getCellAt(x, y).setState(computeNextGenForCell(world.getCellAt(x, y),
                        Neighbour.getLivingNeighbours(world.getCellAt(x, y), world.getWorldGrid())));
            }
        }
        return nextWorld;
    }
}