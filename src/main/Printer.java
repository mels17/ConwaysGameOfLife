package main;

public class Printer {

    private static final String SPACE = " ";


    public void printWorld (World world) {
        String[][] stringRep = World.getStringRepresentationOfWorld(world);
        for(int row = 0; row < stringRep.length; row++) {
            for(int column = 0; column < stringRep[row].length; column++) {
                System.out.print(stringRep[row][column] + SPACE);
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }
}
