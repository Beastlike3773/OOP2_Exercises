package topic02.exercise03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ReadCSV2 {


    public static List<Movie> movies;

    public static void main(String[] args) {
        movies = new ArrayList<>();
        boolean fileRead = false;
        String filename;
        List<String> lines = new ArrayList<>();

        while (!fileRead) {
            try (Scanner sc = new Scanner(System.in)) {
                System.out.println("Enter file name:");
                filename = sc.nextLine();
                lines = Files.readAllLines(Paths.get("./src/topic02/exercise03/" + filename));

            } catch (IOException e) {
                System.err.println("Not able to open file!");
            }
            fileRead = true;
        }

        HashMap<String, List<String>> csvValueMap = new HashMap<>();

        if (!lines.isEmpty()) {
            String[] keys = lines.get(0).split(",");
            for (String key : keys) {
                csvValueMap.put(key.toLowerCase(), new ArrayList<>());
            }

            for (int i = 1; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");

                for (int k = 0; k < values.length; k++) {
                    csvValueMap.get(keys[k]).add(values[k]);
                }
            }

            for (int i = 0; i < lines.size() - 1; i++) {
                Movie m = new Movie();
                // If the Id tag was in the file and the value at this spot was not empty
                if (csvValueMap.containsKey("id") && !csvValueMap.get("id").get(i).equals("")) {
                    m.setId(csvValueMap.get("id").get(i));
                }
                if (csvValueMap.containsKey("year") && !csvValueMap.get("year").get(i).equals("")) {
                    try {
                        m.setYear(Integer.parseInt(csvValueMap.get("year").get(i)));
                    } catch(NumberFormatException e){

                    }
                }
                if (csvValueMap.containsKey("country") && !csvValueMap.get("country").get(i).equals("")) {
                    m.setCountry(csvValueMap.get("country").get(i));
                }
                if (csvValueMap.containsKey("title") && !csvValueMap.get("title").get(i).equals("")) {
                    m.setTitle(csvValueMap.get("title").get(i));
                }

                movies.add(m);
            }
        } else {
            //throw
        }

        for(Movie m : movies){
            System.out.println(m.getDescription());
        }
    }
}
