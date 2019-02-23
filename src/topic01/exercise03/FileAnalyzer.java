package topic01.exercise03;

import java.io.IOException;
import java.util.Scanner;
/**
 * This class prints a report of the contents of a file.
 * @author lua1
 */
public class FileAnalyzer {
    /** Main program.
     * @param args unused
     * @throws IOException if a file cannot be opened, read, or written.
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Filename: ");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        in.close();
        FileCounter counter = new FileCounter();
        counter.analyzeFile(name);
        System.out.println("Characters: " + counter.getCharacterCount());
        System.out.println("Words: " + counter.getWordCount());
        System.out.println("Lines: " + counter.getLineCount());
        System.out.println("Nbr of 'at': " + counter.getNbrAt());
    }
}
