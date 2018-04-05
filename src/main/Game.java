package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    // Get user's input - could be 0 and 1's

    private static int worldLength;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a 2-d array of zeros and ones with spaces:\n");
        String input = scanner.nextLine();
        worldLength = input.length();
        List<String> inputStrings = new ArrayList<>();

        while(!input.equals("q") && worldLength >= 3) {
            if (isValidInput(input)) {
                inputStrings.add(input);
            } else {
                System.out.print("Invalid entry");
                input = "";
            }
            input = scanner.nextLine();
            if(input.equals("q") && inputStrings.size() < 2) {
                input = "";
                System.out.print("World length should be atleast two in both dimensions.");
                worldLength = 0;
                inputStrings = new ArrayList<String>();
            }
        }
        run(storeValueIntoBooleanArray(inputStrings));
    }

    public static void run(boolean[][] booleanGrid) {
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

    private static void printWorld(World world) {
        System.out.print(World.getStringRepresentationOfWorld(world));
        System.out.print("\n\n");
    }

    private static boolean[][] storeValueIntoBooleanArray(List<String> input) {
        boolean[][] worldGrid = new boolean[input.size()][worldLength];
        int count = 0;
        for (String in: input) {
            worldGrid[count] = convertStringToArrayOfBooleans(in);
            count++;
        }
        return worldGrid;
    }

    private static boolean[] convertStringToArrayOfBooleans(String input) {
        String[] splitString = input.split("\\s+");
        boolean[] booleanArray = new boolean[splitString.length];
        for (int i = 0; i < splitString.length; i++) {
            booleanArray[i] = splitString[i].equals("0") ? false : true;
        }
        return booleanArray;
    }

    private static boolean isValidInput(String input) {
        return input.length() == worldLength;
    }
}

