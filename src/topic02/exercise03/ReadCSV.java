package topic02.exercise03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadCSV {

    public static List<Movie> movies;
    public static String[] pattern;
    public static List<String> lines;

    public static void main(String[] args){
        movies = new ArrayList<>();
        boolean fileRead = false;
        String filename;
        while(!fileRead) {
            try(Scanner sc = new Scanner(System.in)) {
                System.out.println("Enter file name:");
                filename = sc.nextLine();
                lines = Files.readAllLines(Paths.get("./src/topic02/exercise03/" + filename));
            } catch (IOException e) {
                System.err.println("Not able to open file!");
            }
            fileRead = true;
        }

        readPattern();

        for(int k = 1; k < lines.size(); k++) {
            Movie m = new Movie();
            String[] line = lines.get(k).split(",");
            for (int i = 0; i < pattern.length; i++) {
                switch (pattern[i]) {
                    case "id":
                        m.setId(readId(line, i));
                        break;
                    case "title":
                        m.setTitle(readTitle(line, i));
                        break;
                    case "country":
                        m.setCountry(readCountry(line, i));
                        break;
                    case "year":
                        m.setYear(readYear(line, i));
                        break;
                    default:
                        break;
                }

                movies.add(m);
            }
        }

        for(Movie m : movies){
            System.out.println(m.getDescription());
        }
    }

    public static void readPattern(){
        pattern = lines.get(0).split(",");
    }

    public static String readId(String[] line, int patternNr){
        return line[patternNr];
    }

    public static String readTitle(String[] line, int patternNr){
        return line[patternNr];
    }

    public static String readCountry(String[] line, int patternNr){
        return line[patternNr];
    }

    public static int readYear(String[] line, int patternNr){
        return Integer.parseInt(line[patternNr]);
    }



}
