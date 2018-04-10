package gameoflife;

import main.Cell;
import main.World;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        Assert.assertEquals(false, actualCellGrid[0][0].isAlive());
        Assert.assertEquals(true, actualCellGrid[0][1].isAlive());
        Assert.assertEquals(true, actualCellGrid[1][0].isAlive());
        Assert.assertEquals(false, actualCellGrid[1][1].isAlive());
    }

    @Test
    public void givenListOfStringAndNumberOfWorldColumnsInitializeCellGridSuccessfully() {
        List<String> userInput = new ArrayList<String>();
        userInput.add("1 0\n");
        userInput.add("0 0\n");

        World world = new World(userInput, 2);
        Cell[][] worldGrid = world.getWorldGrid();

        Assert.assertTrue(worldGrid[0][0].isAlive());
        Assert.assertFalse(worldGrid[0][1].isAlive());
        Assert.assertFalse(worldGrid[1][1].isAlive());
        Assert.assertFalse(worldGrid[1][0].isAlive());
    }

    @Test
    public void computesStringRepresentationOfTheWorldSuccessfully() {
        String actualResult = World.getStringRepresentationOfWorld(world);
        String expectedResult = "0 1 \n1 0 \n";
        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    public void givenTheWorldDimensionsInitializeWorldToAllDead() {
        World world = new World(2, 2);
        Cell[][] cellGrid = world.getWorldGrid();
        Assert.assertFalse(cellGrid[0][0].isAlive());
        Assert.assertFalse(cellGrid[0][1].isAlive());
        Assert.assertFalse(cellGrid[1][0].isAlive());
        Assert.assertFalse(cellGrid[1][1].isAlive());
    }

    @Test
    public void addsLivingCellToWorldSuccessfully() {
        world.addLivingCellAt(0,0);
        Cell[][] actualCellGrid = world.getWorldGrid();
        Assert.assertTrue(actualCellGrid[0][0].isAlive());
    }

    @Test
    public void getsTheCellSuccessfully() {
        Cell cell = world.getCellAt(0,1);
        Assert.assertTrue(cell.isAlive());
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
