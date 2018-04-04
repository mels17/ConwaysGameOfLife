package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Neighbour {
    private static List<Cell> getAllPossibleNeighbours(Cell centreCell) {
        List<Cell> allPossibleNeighbours = new ArrayList<Cell>();

        int centreCellX = centreCell.getX();
        int centreCellY = centreCell.getY();

        // NORTH
        allPossibleNeighbours.add(new Cell(centreCellX, centreCellY - 1));
        //  NORTH-EAST
        allPossibleNeighbours.add(new Cell(centreCellX + 1, centreCellY - 1));
        // EAST
        allPossibleNeighbours.add(new Cell(centreCellX + 1, centreCellY));
        // SOUTH-EAST
        allPossibleNeighbours.add(new Cell(centreCellX + 1, centreCellY + 1));
        //SOUTH
        allPossibleNeighbours.add(new Cell(centreCellX, centreCellY + 1));
        // SOUTH-WEST
        allPossibleNeighbours.add(new Cell(centreCellX - 1, centreCellY + 1));
        // WEST
        allPossibleNeighbours.add(new Cell(centreCellX - 1, centreCellY));
        // NORTH-WEST
        allPossibleNeighbours.add(new Cell(centreCellX - 1, centreCellY - 1));

        return allPossibleNeighbours;
    }

    private static HashSet<Cell> getActualSurroundingCells(Cell centreCell, Cell[][] grid) {
        Set<Cell> cellNeighbours = new HashSet<Cell>();
        int worldEndX = grid.length - 1;
        int worldEndY = grid[0].length - 1;
        int x;
        int y;
        List<Cell> allSurroundingCells = getAllPossibleNeighbours(centreCell);

        for (Cell cell: allSurroundingCells) {
            x = getValidCoordinate(worldEndX, cell.getX());

            y = getValidCoordinate(worldEndY, cell.getY());

            cellNeighbours.add(grid[x][y]);
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
