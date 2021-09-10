package auto.paths;

import frc.team3256.robot.auto.paths.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import java.io.FileReader;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestJSONReader {
    @Test
    public void GettingAllTranslationsFromParser() {
        String sampleText = "\"translation\": {    \n" +
                "\"x\": 123.30239583340617,\n" +
                "\"y\": 44.4397520790277\n" +
                "}";

        String result = "";
        ArrayList<float[]> coordinates = new ArrayList<float[]>();
        float[] pair = {123.30239583340617f, 44.4397520790277f};
        coordinates.add(pair);

        String translated = coordinates.toString();
        try {
            JSONArray translation = (JSONArray) ((JSONObject) new JSONParser().parse(sampleText)).get("translation");

            ArrayList<float[]> translatedCoords = (new JSONReader()).ParseJSONFile(translation);
            result = translatedCoords.toString();
        } catch (Exception e) {
            result = e.getLocalizedMessage();
        }

        assertEquals(translated, result);
    }

}
