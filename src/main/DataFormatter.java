package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataFormatter {
    private static final String QUIT = "q";
    private static final String EMPTY_STRING = "";
    private static final int MIN_WORLD_SIZE = 2;

    public static String[] splitStringWhereSpaces (String input) {
        return input.split("\\s+");
    }

    public static boolean[][] storeValueIntoBooleanArray(List<String> input, int worldColumns) {
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

    // VALIDITY
    private static boolean isValidInput (String input, int worldColumns) {
        return input.split("\\s+").length == worldColumns;
    }

    public static List<String> getListOfUserInputs(Scanner scanner, List<String> inputStrings, String input, int worldColumns) {
        while(!input.equals(QUIT) && worldColumns >= MIN_WORLD_SIZE) {
            checkValidity(inputStrings, input, worldColumns);

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

    private static void checkValidity(List<String> inputStrings, String input, int worldcolumns) {
        if (isValidInput(input, worldcolumns)) {
            inputStrings.add(input);
        } else {
            System.out.print("Invalid entry. Try again..\n");
        }
    }
}
