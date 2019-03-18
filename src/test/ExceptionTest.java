package test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionTest {
    public static final Logger LOGGER = Logger.getLogger(ExceptionTest.class.getName());

    public static void main(String[] args) {
        LOGGER.setLevel(Level.OFF);

        for (long i = 0; i < 1000000; i += i + 1) {
            System.out.println(i);
        }
    }
}

