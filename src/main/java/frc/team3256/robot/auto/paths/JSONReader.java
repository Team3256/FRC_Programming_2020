package frc.team3256.robot.auto.paths;

import java.util.*;
import java.io.FileReader;

import org.ejml.dense.block.VectorOps_DDRB;
import org.json.simple.*;
import org.json.simple.parser.*;

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

    public ArrayList<Vector<Double>> ParseJSONFile(JSONArray translation) { //change from here
        int size = translation.size();
        ArrayList<Vector<Double>> coordinates = new ArrayList<Vector<Double>>();

        for (int i = 0; i < size; i++) {
            JSONObject currentTranslation = (JSONObject) translation.get(i);
            JSONObject translationText = (JSONObject) currentTranslation.get("translation");
            Double y = (Double) translationText.get("y");
            Double x = (Double) translationText.get("x");

            Vector<Double> currentCoord = new Vector<Double>();
            currentCoord.add(0, x);
            currentCoord.add(1, y);

            coordinates.add(currentCoord);
        }

        return coordinates;
    }
}
