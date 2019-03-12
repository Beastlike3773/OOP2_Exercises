package topic01.exercise06;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.nio.file.StandardCopyOption.*;

public class NIOAPI {
    public static final Logger LOGGER = Logger.getLogger(NIOAPI.class.getName());
    public static void main(String[] args){
        LOGGER.setLevel(Level.ALL);
        if(args.length >= 2) {
            String command = args[0];
            Path source = Paths.get(args[1]);
            switch (command) {
                case "-C":
                    Path target;
                    try {
                        target = Paths.get(args[2]);
                        Files.copy(source, target, REPLACE_EXISTING);
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, e.getMessage());
                    }
                    break;
                case "-R":
                    try {
                        List<String> lines = Files.readAllLines(source);
                        List<String> rLines = new ArrayList<>();
                        for (int i = lines.size() - 1; i >= 0; i--) {
                            rLines.add(lines.get(i));
                        }
                        StringBuffer text = new StringBuffer();
                        for (String line : rLines) {
                            text.append(line + "\n");
                        }

                        Files.writeString(source, text);
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, e.getMessage());
                    }
                    break;
                case "-LN":
                    try {
                        List<String> lines = Files.readAllLines(source);
                        System.out.println(lines.get(0).matches("/\\*\\d\\*/ ?(.*)"));
                        if(!lines.get(0).matches("/\\*\\d\\*/ ?(.*)")) {
                            for (int i = 1; i <= lines.size(); i++) {
                                lines.set(i - 1, "/*" + i + "*/ " + lines.get(i - 1));
                            }
                            StringBuffer text = new StringBuffer();
                            for (String line : lines) {
                                text.append(line + "\n");
                            }
                            Files.writeString(source, text);
                        }else{
                            LOGGER.log(Level.INFO, "File already contains line numbers.");
                        }
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, e.getMessage());
                    }
                    break;
                case "-CAL":
                    try {
                        List<String> lines = Files.readAllLines(source);
                        long charCount = 0;
                        for (String line : lines) {
                            charCount += line.length() + 1;
                        }
                        System.out.println("Nbr of lines: " + lines.size());
                        System.out.println("Nbr of characters: " + charCount);

                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, e.getMessage());
                    }
                    break;
                default:
                    LOGGER.log(Level.INFO, "Command not valid: \"" + command + "\"");
                    break;
            }
        } else{
            LOGGER.log(Level.SEVERE, "No command given.");
        }
    }
}
