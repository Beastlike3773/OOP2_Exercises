package test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionTest {
    public static final Logger LOGGER = Logger.getLogger(ExceptionTest.class.getName());

    public static void main(String[] args){
        LOGGER.setLevel(Level.OFF);
        int j = 0;
        byte b = 1312 & 0b1;

        System.out.println(b);
    }
}
