package test;

import main.Cell;
import main.World;
import org.junit.Assert;
import org.junit.Test;

public class IntegratedTest {

    private int worldSize = 3;

    public Cell[][] getAllDeadCells() {
        Cell[][] cells = new Cell[worldSize][worldSize];
        for(int i = 0; i < worldSize; i++) {
            for (int j = 0; j < worldSize; j++) {
                cells[i][j] = new Cell(i, j, Cell.DEAD);
            }
        }
        return cells;
    }

    public Cell[][] getAllLiveCells() {
        Cell[][] cells = new Cell[worldSize][worldSize];
        for(int i = 0; i < worldSize; i++) {
            for (int j = 0; j < worldSize; j++) {
                cells[i][j] = new Cell(i, j, Cell.ALIVE);
            }
        }
        return cells;
    }

    @Test
    public void worldIsEmptyAtTheStart() {
        World world = new World(getAllDeadCells());
        Assert.assertEquals(9, world.getNoOfDeadCells());
    }

    @Test
    public void livingCellAddedToWorldSuccessfully() {
        World world = new World(getAllDeadCells());
        world.addLivingCellAt(1,1);
        Assert.assertEquals(1, world.getNoOfLivingCells());
        Assert.assertEquals(8, world.getNoOfDeadCells());
    }

    @Test
    public void deadCellAddedToWorldSuccessfully(){
        World world = new World(getAllLiveCells());
        world.addDeadCellAt(1,1);
        Assert.assertEquals(1, world.getNoOfDeadCells());
    }
}
