package gameoflife;

import gameoflife.NextGeneration;
import main.World;
import org.junit.Assert;
import org.junit.Test;

public class NextGenerationClassTest {

    @Test
    public void givenSquareWorldGComputesNextGenerationSuccessfully() {
        boolean[][] arr = {{false,true},{true, false}};
        World world = new World(arr);
        World nextWorld = NextGeneration.getNextWorld(world);
        Assert.assertFalse(nextWorld.getWorldGrid()[0][0].isAlive());
        Assert.assertFalse(nextWorld.getWorldGrid()[0][1].isAlive());
        Assert.assertFalse(nextWorld.getWorldGrid()[1][0].isAlive());
        Assert.assertFalse(nextWorld.getWorldGrid()[1][1].isAlive());
    }

    @Test
    public void givenAllDeadWorldGivesDeadWorld() {
        boolean[][] arr = {{false,false},{false, false}};
        World world = new World(arr);
        World nextWorld = NextGeneration.getNextWorld(world);
        Assert.assertFalse(nextWorld.getWorldGrid()[0][0].isAlive());
        Assert.assertFalse(nextWorld.getWorldGrid()[0][1].isAlive());
        Assert.assertFalse(nextWorld.getWorldGrid()[1][0].isAlive());
        Assert.assertFalse(nextWorld.getWorldGrid()[1][1].isAlive());
    }

    @Test
    public void givenAllAliveCellsReturnAllLiveCells() {
        boolean[][] arr = {{true,true},{true, true}};
        World world = new World(arr);
        World nextWorld = NextGeneration.getNextWorld(world);
        Assert.assertTrue(nextWorld.getWorldGrid()[0][0].isAlive());
        Assert.assertTrue(nextWorld.getWorldGrid()[0][1].isAlive());
        Assert.assertTrue(nextWorld.getWorldGrid()[1][0].isAlive());
        Assert.assertTrue(nextWorld.getWorldGrid()[1][1].isAlive());
    }

    @Test
    public void givenNonSquareWorldComputeNextGenerationSuccessfully() {
        boolean[][] arr = {{true,false,true,false},{false,true, true,true}};
        World world = new World(arr);
        World nextWorld = NextGeneration.getNextWorld(world);
        Assert.assertTrue(nextWorld.getWorldGrid()[0][0].isAlive());
        Assert.assertFalse(nextWorld.getWorldGrid()[0][1].isAlive());
        Assert.assertTrue(nextWorld.getWorldGrid()[0][2].isAlive());
        Assert.assertFalse(nextWorld.getWorldGrid()[0][3].isAlive());
        Assert.assertTrue(nextWorld.getWorldGrid()[1][0].isAlive());
        Assert.assertTrue(nextWorld.getWorldGrid()[1][1].isAlive());
        Assert.assertTrue(nextWorld.getWorldGrid()[1][2].isAlive());
        Assert.assertTrue(nextWorld.getWorldGrid()[1][3].isAlive());
    }

}
