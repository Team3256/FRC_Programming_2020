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

    /**
      * Overload constructor with parameter for non-testing, real-time applications
      * @param file String file name location to read from to find array of points
     */
    public JSONReader(String file) {
        translation = new JSONArray();

        this.file = file;

        try {
            translation = (JSONArray) (new JSONParser()).parse(new FileReader(file));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
      * Default constructor generation for testing purposes only
    */
    public JSONReader() {
        file = "";
        translation = null;
        obj = null;
    }

    /**
      * @return JSONArray returns the translation for accessing to plug into ParseJSONFile method
    */
    public JSONArray getTranslation() {
        return translation;
    }

    /**
      * @param translation JSONArray containing JSON text to be parsed*
      * @return ArrayList<Vector<Double>> ArrayList of Double Vectors containing x,y coordinates of each market point in JSON file in JSONArray parameter
     */
    public ArrayList<Vector<Double>> ParseJSONFile(JSONArray translation) { //change from here
        int size = translation.size();
        ArrayList<Vector<Double>> coordinates = new ArrayList<Vector<Double>>();

        for (int i = 0; i < size; i++) {
            JSONObject currentTranslation = (JSONObject) translation.get(i);
            JSONObject poseText = (JSONObject) currentTranslation.get("pose");
            JSONObject translationText = (JSONObject) poseText.get("translation");
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
