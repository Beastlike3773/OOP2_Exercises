package topic02.exercise02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NioListOfIntegers {
    public static final Logger LOGGER = Logger.getLogger(NioListOfIntegers.class.getName());
    public static final String PATH = "./src/topic02/exercise02/";

    private List<Integer> intList;
    private static final int SIZE = 10;

    /**
     * Initialize the intList object and fills it with Integers 0..9.
     */
    public NioListOfIntegers() {
        LOGGER.setLevel(Level.OFF);
        intList = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            intList.add(i);
        }
    }

    /**
     * Outputs the formatted content of intList in the file OutFile.txt
     */
    public void writeList() {
        List<String> lines = new ArrayList<>();

        for (int i = 0; i < intList.size(); i++) {
            lines.add("Value at: " + i + " = " + intList.get(i));
        }
        try {
            Files.write(Paths.get(PATH + "OutFile.txt"), lines);
        } catch(IOException e){
            System.err.println("IOException when writing to OutFile.txt!");
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    /**
     * Reads the content from the given file and adds all Integer values to the intList.
     * @param fileName name of the given file
     */
    public void readList(String fileName) {
        try {
            List<String> numbers = Files.readAllLines(Paths.get(fileName));

            for(String n : numbers){
                try{
                    int number = Integer.parseInt(n);
                    intList.add(number);
                    System.out.println(number);
                } catch(NumberFormatException e){
                    System.err.println("Given value is not a number and will be skipped. Value: " + n);
                    LOGGER.log(Level.WARNING, e.getMessage(), e);
                }
            }

        } catch(IOException e){
            System.err.println("IOException when reading file: " + fileName + "\nMessage: " + e.getMessage());
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    /**
     * Main method.
     * @param args unused
     */
    public static void main(String[] args) {
        NioListOfIntegers list = new NioListOfIntegers();
        list.readList(PATH + "InFile.txt");
        list.writeList();
        System.out.println("Done !!");
    }
}
