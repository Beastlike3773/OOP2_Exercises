package topic02.exercise01;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputValidation {

    public static final Logger LOGGER = Logger.getLogger(InputValidation.class.getName());

    public static void main(String[] args) {
        LOGGER.setLevel(Level.OFF);
        System.out.println("Which TV Channel do you want to watch?");
        System.out.print("Press 132 for SRF1, press 140 for Arte and press 151 for SRF2:");
        int input = 0;
        try (Scanner scanner = new Scanner(System.in)){
            input = scanner.nextInt();
            if (input == 132) {
                System.out.println("This is SRF1");
            } else if (input == 140) {
                System.out.println("This is Arte");
            } else if (input == 151) {
                System.out.println("This is SRF2");
            } else {
                throw new IllegalArgumentException("Number pressed is not a TV Channel. Number Pressed: " + input +
                        "\nValid inputs: 132 (SRF1), 140 (Arte), 151 (SRF2)");
            }
        } catch(InputMismatchException e){
            System.err.println("Input is not a number!");
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        } catch(IllegalArgumentException e) {
            System.err.println(e.getMessage());
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }
    }
}

