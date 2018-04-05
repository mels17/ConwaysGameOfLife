package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Neighbour {
    private static List<Cell> getAllPossibleNeighbours(Cell centreCell) {
        List<Cell> allPossibleNeighbours = new ArrayList<Cell>();

        int centreCellRowNumber = centreCell.getRowNumber();
        int centreCellColumnNumber = centreCell.getColumnNumber();

        // NORTH
        allPossibleNeighbours.add(new Cell(centreCellRowNumber - 1, centreCellColumnNumber));
        //  NORTH-EAST
        allPossibleNeighbours.add(new Cell(centreCellRowNumber - 1, centreCellColumnNumber + 1));
        // EAST
        allPossibleNeighbours.add(new Cell(centreCellRowNumber, centreCellColumnNumber + 1));
        // SOUTH-EAST
        allPossibleNeighbours.add(new Cell(centreCellRowNumber + 1, centreCellColumnNumber + 1));
        //SOUTH
        allPossibleNeighbours.add(new Cell(centreCellRowNumber + 1, centreCellColumnNumber));
        // SOUTH-WEST
        allPossibleNeighbours.add(new Cell(centreCellRowNumber + 1, centreCellColumnNumber - 1));
        // WEST
        allPossibleNeighbours.add(new Cell(centreCellRowNumber, centreCellColumnNumber - 1));
        // NORTH-WEST
        allPossibleNeighbours.add(new Cell(centreCellRowNumber - 1, centreCellColumnNumber - 1));

        return allPossibleNeighbours;
    }

    private static HashSet<Cell> getActualSurroundingCells(Cell centreCell, Cell[][] grid) {
        Set<Cell> cellNeighbours = new HashSet<Cell>();
        int worldEndRows = grid.length - 1;
        int worldEndColumns = grid[0].length - 1;
        int row;
        int column;
        List<Cell> allSurroundingCells = getAllPossibleNeighbours(centreCell);

        for (Cell cell: allSurroundingCells) {
            row = getValidCoordinate(worldEndRows, cell.getRowNumber());

            column = getValidCoordinate(worldEndColumns, cell.getColumnNumber());

            cellNeighbours.add(grid[row][column]);
        }
        return (HashSet<Cell>)cellNeighbours;
    }


    private static int getValidCoordinate(int worldEnd, int coordinate) {
        int newCoordinate;
        if(coordinate > worldEnd) {
            newCoordinate = 0;
        } else if(coordinate < 0) {
            newCoordinate = worldEnd;
        } else {
            newCoordinate = coordinate;
        }
        return newCoordinate;
    }

    public static int getLivingNeighbours(Cell centreCell, Cell[][] worldGrid) {
        int livingNeighbours = 0;
        Set<Cell> neighbours = getActualSurroundingCells(centreCell, worldGrid);

        for (Cell cell: neighbours) {
            if(cell.getState()) {
                livingNeighbours++;
            }
        }
        return livingNeighbours;
    }
}
