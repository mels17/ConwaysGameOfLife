package gameoflife;

import gameoflife.IPrinter;
import main.World;

public class MockPrinterClass implements IPrinter{
    public void print(World world) {
        System.out.println(World.getStringRepresentationOfWorld(world));
    }
}
