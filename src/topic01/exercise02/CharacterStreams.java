package topic01.exercise02;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CharacterStreams {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter file name to read:");
        String inputfile = in.nextLine();
        System.out.println("Please enter name of output file:");
        String outputfile = in.nextLine();
        in.close();

        FileReader fr = null;
        PrintWriter pw = null;
        Scanner sc = null;

        try{
            fr = new FileReader(".\\src\\topic01\\exercise02\\" + inputfile);
            pw = new PrintWriter(".\\src\\topic01\\exercise02\\" + outputfile);
            sc = new Scanner(fr);
            long i = 1;
            while(sc.hasNextLine()){
                pw.write("/* " + i++ + " */ " + sc.nextLine() + "\n");
            }
        }finally{
            if(fr != null) fr.close();
            if(pw != null) pw.close();
            if(sc != null) sc.close();
        }
    }
}
