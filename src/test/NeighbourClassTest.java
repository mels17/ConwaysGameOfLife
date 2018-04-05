package test;

import main.Cell;
import main.Neighbour;
import org.junit.Assert;
import org.junit.Test;

public class NeighbourClassTest {

    public Cell[][] getAllSameStateCells(int rows, int columns, boolean val) {
        Cell[][] cells = new Cell[rows][columns];
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell(i, j, val);
            }
        }
        return cells;
    }

    @Test
    public void get0LivingNeigboursGivenCellAndDeadWorld() {
        Cell[][] cellGrid = getAllSameStateCells(2, 4, false);
        Assert.assertEquals(0, Neighbour.getLivingNeighbours(cellGrid[1][2], cellGrid));
    }

    @Test
    public void get2LivingNeigboursGivenCellAndDeadWorld() {
        Cell[][] cellGrid = getAllSameStateCells(2, 4, false);
        cellGrid[0][1].setState(true);
        cellGrid[0][2].setState(true);
        Assert.assertEquals(2, Neighbour.getLivingNeighbours(cellGrid[1][1], cellGrid));
    }

    @Test
    public void get7LivingNeigboursGivenLiveCellAndLiveWorld() {
        Cell[][] cellGrid = getAllSameStateCells(3, 3, true);
        Assert.assertEquals(8, Neighbour.getLivingNeighbours(cellGrid[1][1], cellGrid));
    }
}
