package test;

import main.Cell;
import main.Neighbour;
import main.World;
import org.junit.Assert;
import org.junit.Test;

public class IntegratedTest {
    public Cell[][] getAllDeadCells(int worldSizeX, int worldSizeY) {
        Cell[][] cells = new Cell[worldSizeX][worldSizeY];
        for(int i = 0; i < worldSizeX; i++) {
            for (int j = 0; j < worldSizeY; j++) {
                cells[i][j] = new Cell(i, j, Cell.DEAD);
            }
        }
        return cells;
    }

    public Cell[][] getAllLiveCells(int worldSizeX, int worldSizeY) {
        Cell[][] cells = new Cell[worldSizeX][worldSizeY];
        for(int i = 0; i < worldSizeX; i++) {
            for (int j = 0; j < worldSizeY; j++) {
                cells[i][j] = new Cell(i, j, Cell.ALIVE);
            }
        }
        return cells;
    }

    @Test
    public void squareWorldIsInitializedSuccessfully() {
        World world = new World(getAllDeadCells(3, 3));
        Assert.assertEquals(9, world.getNoOfDeadCells());
    }

    @Test
    public void nonSquareWorldInitializedSuccessfully() {
        World world = new World(getAllDeadCells(4, 3));
        Assert.assertEquals(12, world.getNoOfDeadCells());
    }

    @Test
    public void livingCellAddedToWorldSuccessfully() {
        World world = new World(getAllDeadCells(3, 3));
        world.addLivingCellAt(1,1);
        Assert.assertEquals(1, world.getNoOfLivingCells());
        Assert.assertEquals(8, world.getNoOfDeadCells());
    }

    @Test
    public void deadCellAddedToWorldSuccessfully(){
        World world = new World(getAllLiveCells(3,3));
        world.addDeadCellAt(1,1);
        Assert.assertEquals(1, world.getNoOfDeadCells());
        Assert.assertEquals(8, world.getNoOfLivingCells());
    }

    @Test
    public void givenSquareWorldWithCellSurroundedBy0LivingReturns0Living() {
        World world = new World(getAllDeadCells(3, 3));
        world.addLivingCellAt(1,1);
        Assert.assertEquals(0, Neighbour.getLivingNeighbours(world.getCellAt(1,1), world.getWorldGrid()));
    }

    @Test
    public void givenSquareWorldWithCellSurroundedBy2LivingReturns2Living() {
        World world = new World(getAllDeadCells(3, 3));
        world.addLivingCellAt(0, 0);
        world.addLivingCellAt(2, 2);
        Assert.assertEquals(2, Neighbour.getLivingNeighbours(world.getCellAt(1, 1), world.getWorldGrid()));
    }

    /***
     * This is how the world looks for the next four corner test cases.
     * L D L
     * L D L
     * D D D
     * where L - Live and D - Dead
     */
    @Test
    public void givenSquareWorldGet3LivingForTopLeftCorner() {
        World world = new World(getAllDeadCells(3, 3));
        world.addLivingCellAt(0, 0);
        world.addLivingCellAt(0, 1);
        world.addLivingCellAt(2, 0);
        world.addLivingCellAt(2, 1);
        Assert.assertEquals(3, Neighbour.getLivingNeighbours(world.getCellAt(0, 0), world.getWorldGrid()));

    }

    @Test
    public void givenSquareWorldGet3LivingForTopRightCorner() {
        World world = new World(getAllDeadCells(3, 3));
        world.addLivingCellAt(0, 0);
        world.addLivingCellAt(0, 1);
        world.addLivingCellAt(2, 0);
        world.addLivingCellAt(2, 1);
        Assert.assertEquals(3, Neighbour.getLivingNeighbours(world.getCellAt(0, 0), world.getWorldGrid()));

    }

    @Test
    public void givenSquareWorldGet3LivingForBottomLeftCorner() {
        World world = new World(getAllDeadCells(3, 3));
        world.addLivingCellAt(0, 0);
        world.addLivingCellAt(0, 1);
        world.addLivingCellAt(2, 0);
        world.addLivingCellAt(2, 1);
        Assert.assertEquals(3, Neighbour.getLivingNeighbours(world.getCellAt(0, 0), world.getWorldGrid()));

    }@Test
    public void givenSquareWorldGet3LivingForBottomRightCorner() {
        World world = new World(getAllDeadCells(3, 3));
        world.addLivingCellAt(0, 0);
        world.addLivingCellAt(0, 1);
        world.addLivingCellAt(2, 0);
        world.addLivingCellAt(2, 1);
        Assert.assertEquals(3, Neighbour.getLivingNeighbours(world.getCellAt(0, 0), world.getWorldGrid()));

    }

    @Test
    public void givenRectangularWorldWithCellSurroundedBy0LivingReturns0Living() {
        World world = new World(getAllDeadCells(2, 3));
        world.addLivingCellAt(1,1);
        Assert.assertEquals(0, Neighbour.getLivingNeighbours(world.getCellAt(1,1), world.getWorldGrid()));
    }

    @Test
    public void givenRectangularWorldWithCellSurroundedByAllLivingCells() {
        World world = new World(getAllLiveCells(2, 3));
        Assert.assertEquals(5, Neighbour.getLivingNeighbours(world.getCellAt(0, 0), world.getWorldGrid()));
    }

    /**
     * The world grid looks like this for the following test:
     * D D D D
     * A D A A
     * A D A D
     * A D A A
     * D D D D
     * D D D D
     */
    @Test
    public void given6x4WorldWithEdgeCellSurroundedByLiveCells() {
        World world = new World(getAllDeadCells(4, 6));
        world.addLivingCellAt(0, 1);
        world.addLivingCellAt(2, 1);
        world.addLivingCellAt(3, 1);
        world.addLivingCellAt(0, 2);
        world.addLivingCellAt(2, 2);
        world.addLivingCellAt(0, 3);
        world.addLivingCellAt(2, 3);
        world.addLivingCellAt(3, 3);
        Assert.assertEquals(8, Neighbour.getLivingNeighbours(world.getCellAt(3, 2), world.getWorldGrid()));
        Assert.assertEquals(0, Neighbour.getLivingNeighbours(world.getCellAt(3, 5), world.getWorldGrid()));
    }



}
