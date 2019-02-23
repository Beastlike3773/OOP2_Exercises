package topic01.exercise03;

import java.io.*;
import java.util.Scanner;

public class FileCounter {

    private long characterCount;
    private long wordCount;
    private long lineCount;
    private long atCount;


    public long getWordCount() {
        return wordCount;
    }

    public long getLineCount() {
        return lineCount;
    }

    public long getNbrAt() {
        return atCount;
    }

    public long getCharacterCount(){
        return characterCount;
    }



    public void analyzeFile(String name) throws IOException {
        try(Reader r = new BufferedReader(new FileReader(".\\src\\topic01\\exercise03\\" + name));
            Scanner sc = new Scanner(r)){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] words = line.split("\\s");
                wordCount += words.length;
                lineCount++;
                for(String word : words){
                    if(word.contains("at")) atCount++;
                    characterCount += word.length();
                }
            }
        }
    }
}
