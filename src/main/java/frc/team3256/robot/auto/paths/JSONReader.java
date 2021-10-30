package frc.team3256.robot.auto.paths;

import java.io.File;
import java.lang.reflect.Array;
import java.util.*;
import java.io.FileReader;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import org.json.simple.*;
import org.json.simple.parser.*;
import static frc.team3256.robot.constants.AutoConstants.*;

public class JSONReader {
    /**
      * @param file JSON file name to parse
      * @return ArrayList<Vector<Double>> ArrayList of Double Vectors containing x,y coordinates of each market point in JSON file in JSONArray parameter
      * actual function that will be used in real purposes, NOT for testing
     */
    public static ArrayList<Vector<Double>> ParseJSONFile(String file) {
        JSONArray translation = new JSONArray();

        file = new File(Filesystem.getDeployDirectory(),file).getAbsolutePath();

        try {
            translation = (JSONArray) (new JSONParser()).parse(new FileReader(file));
        } catch (Exception e) {
            DriverStation.reportError(e.getLocalizedMessage(), true);
            return new ArrayList<Vector<Double>>();
        }

        return GetCoordinateArray(translation);
    }

    /**
      * @param translation JSONArray translation to parse for testing purposes off of a String not a file
      * @return ArrayList<Vector<Double>> ArrayList of Double Vectors containing x,y coordinates of each market point in JSON file in JSONArray parameter
      * testing function nothing changed from usable one except removal of creation of JSONArray
     */
    public static ArrayList<Vector<Double>> ParseJSONFileTester(JSONArray translation) {
        return GetCoordinateArray(translation);
    }

    /**
     * @param translation JSONArray translation to get the x and y coordinates from the JSON file
     * @return ArrayList<Vector<Double>> ArrayList of coordinates
     */
    private static ArrayList<Vector<Double>> GetCoordinateArray(JSONArray translation) {
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

        return TrimCoordinates(coordinates);
    }

    /**
      * @param coordinates trim by every constantSpace
      * @return ArrayList<Vector<Double>> trimmed coordinates
      * Gets rid of coordinates that are within a certain distance of each other
     */
    private static ArrayList<Vector<Double>> TrimCoordinates(ArrayList<Vector<Double>> coordinates) {
        Vector<Double> firstCoord;
        Vector<Double> secondCoord;

        int nextI = 0;

        ArrayList<Vector<Double>> trimmed = new ArrayList<Vector<Double>>();
        for (int i = 0; i < coordinates.size() - 1; i++) {
            i = nextI;
            firstCoord = coordinates.get(i);
            trimmed.add(firstCoord);

            for (int j = i+1; j < coordinates.size(); j++) {
                secondCoord = coordinates.get(j);
                if (!(Math.abs(secondCoord.get(0) - firstCoord.get(0)) < MIN_SPACE_BETWEEN_POINTS && Math.abs(secondCoord.get(1) - firstCoord.get(1)) < MIN_SPACE_BETWEEN_POINTS)) {
                    nextI = j;
                    break;
                }
            }
        }

        return trimmed;
    }
}
