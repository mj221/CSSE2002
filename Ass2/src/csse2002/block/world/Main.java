/**
 * @author Minjae Lee
 * @StudentNO 45363809
 */

package csse2002.block.world;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Handles top-level interaction with performing actions on a WorldMap.
 */

public class Main extends java.lang.Object {

    /**
     * Constructor for Main().
     */
    public Main(){}

    /**
     * The entry point of the application.
     * Takes 3 parameters an input map file (args[0]), actions (args[1]), and an output map file (args[2]).
     * @param args the input arguments to the program.
     */
    public static void main(java.lang.String[] args){

        WorldMap Worldmap = null;
        BufferedReader reader = null;

        if (args.length != 3){
            System.err.println("Usage: program inputMap actions outputMap");
            System.exit(1);
        }else {
            try {
                Worldmap = new WorldMap(args[0]);
            } catch (WorldMapFormatException | WorldMapInconsistentException | IOException e) {
                System.err.println(e);
                System.exit(2);
            }

            if (!args[1].equals("System.in")) {
                java.io.File file = new java.io.File(args[1]);
                try {
                    reader = new BufferedReader(new java.io.FileReader(file));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    reader = new BufferedReader(new InputStreamReader(System.in));
                } catch (Exception e) {
                    System.err.println(e);
                    System.exit(3);
                }

            }
            try {
                Action.processActions(reader, Worldmap);
            } catch (ActionFormatException e) {
                System.err.println(e);
                System.exit(4);
            }

            try {
                Worldmap.saveMap(args[2]);
            } catch (IOException e) {
                System.err.println(e);
                System.exit(5);
            }
        }
    }
}
