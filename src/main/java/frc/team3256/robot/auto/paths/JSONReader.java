package frc.team3256.robot.auto.paths;

import java.util.Iterator;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.util.ArrayList;

public class JSONReader {
    private String file; // file to read from
    private Object obj; // JSONParser to parse json 'file'
    private JSONArray translation;

    public JSONReader(String file) {
        this.file = file;

        try {
            obj = new JSONParser().parse(new FileReader(file));
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        translation = (JSONArray) ((JSONObject) obj).get("translation"); // array of points
    }

    public JSONReader() {
        this.file = "";
        translation = null;
        obj = null;
    }

    public JSONArray getTranslation() {
        return translation;
    }

    public String ParseJSONFile(JSONObject translation) { //change from here
        String translatedText = "";
        JSONObject translationText = (JSONObject) translation.get("translation");
        Double y = (Double) translationText.get("y");
        Double x = (Double) translationText.get("x");

        translatedText += Double.toString(x) + "\n" + Double.toString(y) + "\n";

        return translatedText;
    }
}
