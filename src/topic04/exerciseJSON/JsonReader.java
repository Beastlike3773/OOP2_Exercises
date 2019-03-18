package topic04.exerciseJSON;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class JsonReader {
    public static void main(String[] args){
        try(Reader reader = new InputStreamReader(JsonReader.class.getResourceAsStream("geography.json"), StandardCharsets.UTF_8)) {
            //Gson gson = new GsonBuilder().create();
            //Geography geo = gson.fromJson(reader, Geography.class);
            //System.out.println(geo);
        } catch(IOException e){

        }
    }
}
