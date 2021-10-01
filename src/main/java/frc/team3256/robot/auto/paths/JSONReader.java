package frc.team3256.robot.auto.paths;

import java.io.File;
import java.util.*;
import java.io.FileReader;

import edu.wpi.first.wpilibj.Filesystem;
import org.json.simple.*;
import org.json.simple.parser.*;

public class JSONReader {
    /**
      * @param file JSON file name to parse
      * @return ArrayList<Vector<Double>> ArrayList of Double Vectors containing x,y coordinates of each market point in JSON file in JSONArray parameter
      * actual function that will be used in real purposes, NOT for testing
     */
    public static ArrayList<Vector<Double>> ParseJSONFile(String file) { //change from here
         JSONArray translation = new JSONArray();

        file = new File(Filesystem.getDeployDirectory(),file).getAbsolutePath();;

        try {
            translation = (JSONArray) (new JSONParser()).parse(new FileReader(file));
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<Vector<Double>>();
        }

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

    /**
      * @param translation JSONArray translation to parse for testing purposes off of a String not a file
      * @return ArrayList<Vector<Double>> ArrayList of Double Vectors containing x,y coordinates of each market point in JSON file in JSONArray parameter
      * testing function nothing changed from usable one except removal of creation of JSONArray
     */
    public static ArrayList<Vector<Double>> ParseJSONFileTester(JSONArray translation) { //change from here
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
