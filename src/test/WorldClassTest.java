package test;

import main.Cell;
import main.World;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorldClassTest {

    World world;
    @Before
    public void initializeWorldObject() {
        world = new World(new boolean[][]{
                {false,true},
                {true,false}
        });
    }

    @Test
    public void givenBoolean2DArrayReturns2DCellArray() {
        Cell[][] actualCellGrid = world.getWorldGrid();
        Assert.assertEquals(false, actualCellGrid[0][0].getState());
        Assert.assertEquals(true, actualCellGrid[0][1].getState());
        Assert.assertEquals(true, actualCellGrid[1][0].getState());
        Assert.assertEquals(false, actualCellGrid[1][1].getState());
    }

    @Test
    public void computesStringRepresentationOfTheWorldSuccessfully() {
        String[][] actualResult = World.getStringRepresentationOfWorld(world);
        String[][] expectedResult = new String[][]{
                {"0", "1"},
                {"1", "0"}
        };
        assertThat(actualResult, equalTo(expectedResult));
    }



    @Test
    public void addsLivingCellToWorldSuccessfully() {
        world.addLivingCellAt(0,0);
        Cell[][] actualCellGrid = world.getWorldGrid();
        Assert.assertTrue(actualCellGrid[0][0].getState());
    }

    @Test
    public void getsTheCellSuccessfully() {
        Cell cell = world.getCellAt(0,1);
        Assert.assertTrue(cell.getState());
        Assert.assertEquals(0, cell.getRowNumber());
        Assert.assertEquals(1, cell.getColumnNumber());
    }

    @Test
    public void returnTrueIfWorldIsAllDeadCells() {
        world = new World(new boolean[][] {
                {false, false},
                {false, false}
        });
        Assert.assertTrue(world.worldIsDead());
    }

    @Test
    public void returnFalseIfOneLiveCellInWorld() {
        world = new World(new boolean[][] {
                {false, true},
                {false, false}
        });
        Assert.assertFalse(world.worldIsDead());
    }

    @Test
    public void returnFalseIfAllCellsAreLive() {
        world = new World(new boolean[][] {
                {true, true},
                {true, true}
        });
        Assert.assertFalse(world.worldIsDead());
    }

}
