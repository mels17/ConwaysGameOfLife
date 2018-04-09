package main;

public class NextGeneration {
    // Determine if a cell is live or dead in the next generation.
    // It also gives the next world.

    private static boolean computeNextGenForCell(Cell cell, int noOfLivingNeighbours) {
        return cell.isAlive() ? aliveCellGetNextState(noOfLivingNeighbours) : deadCellGetNextState(noOfLivingNeighbours);
    }

    private static boolean deadCellGetNextState(int noOfLivingNeighbours) {
        return noOfLivingNeighbours == 3;
    }

    private static boolean aliveCellGetNextState(int noOfLivingNeighbours) {
        return noOfLivingNeighbours == 2 || noOfLivingNeighbours == 3;
    }

    private static Cell[][] populateWithDeadCells(World world) {
        Cell[][] cells = new Cell[world.getWorldGrid().length][world.getWorldGrid()[0].length];

        for(int row = 0; row < world.getNoOfRows(); row++) {
            for (int column = 0; column < world.getNoOfColumns(); column++) {
                cells[row][column] = new Cell(row, column, Cell.DEAD);
            }
        }
        return cells;
    }

    public static World getNextWorld(World world) {
        World nextWorld = new World(populateWithDeadCells(world));

        for (int row = 0; row < world.getNoOfRows(); row++) {
            for (int column = 0; column < world.getNoOfColumns(); column++) {
                nextWorld.getCellAt(row, column).setState(computeNextGenForCell(world.getCellAt(row, column),
                        Neighbour.getLivingNeighbours(world.getCellAt(row, column), world.getWorldGrid())));
            }
        }
        return nextWorld;
    }
}


//interface SAMPrinter {
//
//    void print(String input);
//
//}
//
//class Game {
//
//    public void start(SAMPrinter samPrinter) {
//        samPrinter.print("Hi there");
//    }
//
//}
//
//
//class Application {
//
//    public void main() {
//        new Game().start(input -> Printer.printWorld(input));
//
//    }
//
//}