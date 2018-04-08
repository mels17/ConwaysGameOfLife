package test;

import main.Printer;
import main.World;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PrinterClassTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    public void testPrinterOutputsTheWorldGrid() {
        Printer printer = new Printer();
        World world = new World(new boolean[][]{{false, true}, {true, false}});

        String expectedResult = "0 1 \n1 0 \n\n\n";
        printer.printWorld(world);
        Assert.assertEquals(expectedResult, outContent.toString());
    }
}
