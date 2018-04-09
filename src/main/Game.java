package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Example:
 * 0 1 0 1 0
   0 0 0 1 0
   0 1 1 1 0
   0 1 1 1 0
 */
public class Game {

    private static final int THREAD_SLEEP_SECONDS = 500;

    // Get user's input - could be 0 and 1's

    private static void run (boolean[][] booleanGrid, Printer printer) {
        World world = new World(booleanGrid);
        while(!world.worldIsDead()) {
            world = NextGeneration.getNextWorld(world);
            printer.printWorld(world);
            try {
                Thread.sleep(THREAD_SLEEP_SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Entry Point of the game.
     * @param reader - Read input object
     * @param printer - Object of the printer class
     */
    public static void enterGame (Reader reader, Printer printer) {
        List<String> inputStrings = new ArrayList<>();

        System.out.print("Enter a 2-d array of zeros and ones with spaces:\n");
        String input = reader.readInput();
        int worldColumns = DataFormatter.splitStringWhereSpaces(input).length;

        run(DataFormatter.storeValueIntoBooleanArray(DataFormatter.getListOfUserInputs(reader, inputStrings,
                input, worldColumns), worldColumns), printer);
    }


}

