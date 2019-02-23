package topic01.exercise04;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebLinks {

    public static void main(String[] args) throws Exception{
        URL url = new URL("https://www.ti.bfh.ch/");
        InputStream inStream = new BufferedInputStream(url.openStream());
        Pattern pattern = Pattern.compile("href=\"(.*?)\"");
        //Pattern p = Pattern
        Scanner sc = new Scanner(inStream);

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            Matcher matcher = pattern.matcher(line);

            if(matcher.find()){
                System.out.println(new URL(url, matcher.group(1)));
            }
        }
    }

}
