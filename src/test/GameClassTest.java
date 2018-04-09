package test;

import main.Game;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


public class GameClassTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private String userPrompt = "Enter a 2-d array of zeros and ones with spaces:\n";
    private String gameOverMessage = "Game Over.\n";
    private MockReaderClass reader;
    @Before
    public void setUpMockReaderClassObject() {
        reader = new MockReaderClass();
    }
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    public void whenUserEntersCorrectInputReturnsNextWorld() {
        List<String> userInput = new ArrayList<String>();
        userInput.add("1 0\n");
        userInput.add("0 1\n");
        userInput.add("q");

        String nextWorldString = "0 0 \n0 0 \n\n";
        String expectedResult = userPrompt + nextWorldString;

        reader.setReturnValue(userInput);
        Game.enterGame(reader, new MockPrinterClass());
        Assert.assertEquals(expectedResult, outContent.toString());
    }

    @Test
    public void gameStopsWhenLetter_q_IsEntered() {
        List<String> userInput = new ArrayList<String>();
        userInput.add("q");

        reader.setReturnValue(userInput);

        Game.enterGame(reader, new MockPrinterClass());

        Assert.assertEquals(userPrompt+"Game Over.\n", outContent.toString());
    }
    @Test
    public void printsUserPromptAgainWhenTheUserInputLengthIsLessThan2() {
        List<String> userInput = new ArrayList<String>();
        userInput.add("1\n");
        userInput.add("q");

        reader.setReturnValue(userInput);

        Game.enterGame(reader, new MockPrinterClass());

        Assert.assertEquals(userPrompt+userPrompt+gameOverMessage, outContent.toString());
    }

    @Test
    public void printsErrorMessageWhenTheUserInputHasInvalidLength() {
        List<String> userInput = new ArrayList<String>();
        userInput.add("1 0\n");
        userInput.add("1\n");
        userInput.add("1 0\n");
        userInput.add("0 1\n");
        userInput.add("q");

        reader.setReturnValue(userInput);

        Game.enterGame(reader, new MockPrinterClass());

        String expectedResult = userPrompt + "Invalid entry. Try again..\n" + "1 1 \n1 1 \n1 1 \n\n" + "0 0 \n0 0 \n0 0 \n\n";
        Assert.assertEquals(expectedResult, outContent.toString());
    }

    @Test
    public void printsErrorMessageWhenTheUserEntersLessThan2LinesOfInputs() {
        List<String> userInput = new ArrayList<String>();
        userInput.add("1 0\n");
        userInput.add("q");

        reader.setReturnValue(userInput);

        Game.enterGame(reader, new MockPrinterClass());

        String expectedResult = userPrompt + "World length should be atleast two in both dimensions.";

        Assert.assertEquals(expectedResult, outContent.toString());
    }
}
