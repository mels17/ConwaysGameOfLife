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

    private static final String QUIT = "q";
    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final int MIN_WORLD_SIZE = 2;

    // Get user's input - could be 0 and 1's

    private static int worldColumns;

    private static String[] splitStringWhereSpaces (String input) {
        return input.split("\\s+");
    }

    public static void run (boolean[][] booleanGrid) {
        World world = new World(booleanGrid);
        while(!world.worldIsDead()) {
            world = NextGeneration.getNextWorld(world);
            printWorld(world);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printWorld (World world) {
        String[][] stringRep = World.getStringRepresentationOfWorld(world);
        for(int row = 0; row < stringRep.length; row++) {
            for(int column = 0; column < stringRep[row].length; column++) {
                System.out.print(stringRep[row][column] + SPACE);
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }

    private static boolean[][] storeValueIntoBooleanArray (List<String> input) {
        boolean[][] worldGrid = new boolean[input.size()][worldColumns];

        for(int row = 0; row < input.size(); row++) {
            boolean[] array = convertStringToArrayOfBooleans(input.get(row));
            for(int column = 0; column < worldColumns; column++) {
                worldGrid[row][column] = array[column];
            }
        }
        return worldGrid;
    }

    private static boolean[] convertStringToArrayOfBooleans (String input) {
        String[] splitString = splitStringWhereSpaces(input);
        boolean[] booleanArray = new boolean[splitString.length];
        for (int i = 0; i < splitString.length; i++) {
            booleanArray[i] = splitString[i].equals("0") ? Cell.DEAD : Cell.ALIVE;
        }
        return booleanArray;
    }

    private static boolean isValidInput (String input) {
        return input.split("\\s+").length == worldColumns;
    }

    private static List<String> getListOfUserInputs(Scanner scanner, List<String> inputStrings, String input) {
        while(!input.equals(QUIT) && worldColumns >= MIN_WORLD_SIZE) {
            checkValidity(inputStrings, input);

            input = scanner.nextLine();

            if(input.equals(QUIT) && inputStrings.size() < MIN_WORLD_SIZE) {
                input = EMPTY_STRING;
                System.out.print("World length should be atleast two in both dimensions.");
                worldColumns = 0;
                inputStrings = new ArrayList<String>();
            }
        }
        return inputStrings;
    }

    private static void checkValidity(List<String> inputStrings, String input) {
        if (isValidInput(input)) {
            inputStrings.add(input);
        } else {
            System.out.print("Invalid entry. Try again..\n");
        }
    }

    /**
     * Entry Point of the game.
     * @param args
     */
    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<String> inputStrings = new ArrayList<>();

        System.out.print("Enter a 2-d array of zeros and ones with spaces:\n");
        String input = scanner.nextLine();
        worldColumns = splitStringWhereSpaces(input).length;

        run(storeValueIntoBooleanArray(getListOfUserInputs(scanner, inputStrings, input)));
    }


}

