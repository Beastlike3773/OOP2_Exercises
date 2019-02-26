package test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionTest {
    public static final Logger LOGGER = Logger.getLogger(ExceptionTest.class.getName());

    public static void main(String[] args){
        LOGGER.setLevel(Level.OFF);
        int j = 0;
        for(int i = 0; i < 100; i++){
            try{
                if(i % 2 == 0){
                    System.out.println(i + " " + j);
                }else{
                    throw new NumberFormatException();
                }
            } catch(NumberFormatException e){
                System.err.println("err " + j);
                LOGGER.log(Level.WARNING, "log " + j);
                j++;
            }
        }
    }
}
