package topic01.exercise01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DisplayHex extends Application {

    public static final Logger LOGGER = Logger.getLogger(DisplayHex.class.getName());
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        LOGGER.setLevel(Level.ALL);
        List<String> args = this.getParameters().getRaw();
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 600, 300);

        Label label = new Label();
        root.getChildren().add(label);

        TextArea ta = new TextArea();
        ta.minHeightProperty().bind(root.heightProperty());
        ta.minWidthProperty().bind(root.widthProperty());
        String content = readFileText(args.get(0));
        ta.setText(content);
        root.getChildren().add(ta);



        stage.setTitle("Display Hex");
        stage.setScene(scene);
        stage.show();
    }

    public String readFileText(String filepath) throws IOException{
        FileInputStream in = null;
        String result = "";
        LOGGER.log(Level.INFO, "Reading file as text: " + filepath);

        try{
            in = new FileInputStream(filepath);
            int c = in.read();
            while(c != -1){
                result += (char)c + " ";
                c = in.read();

            }
        }finally{
            if(in != null) in.close();
        }
        return result;
    }

    public String readFileHex(String filepath) throws IOException{
        FileInputStream in = null;
        String result = "";
        LOGGER.log(Level.INFO, "Reading file as hex: " + filepath);

        try{
            in = new FileInputStream(filepath);
            int c = in.read();
            int i = 0;
            while(c != -1){
                String hex = Integer.toHexString(c & 0xFF);
                result += hex + " ";
                i++;
                if(i == 32){
                    result += "\n";
                    i = 0;
                }
                c = in.read();

            }
        }finally{
            if(in != null) in.close();
        }
        return result;
    }
}
