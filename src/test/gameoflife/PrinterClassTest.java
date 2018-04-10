package gameoflife;

import main.World;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PrinterClassTest {

    @Test
    public void testPrinterOutputsTheWorldGrid() {
        MockPrinterClass printer = new MockPrinterClass();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        World world = new World(new boolean[][]{{false, true}, {true, false}});

        String expectedResult = "0 1 \n1 0 \n\n";
        printer.print(world);

        Assert.assertEquals(expectedResult, outContent.toString());
    }
}
