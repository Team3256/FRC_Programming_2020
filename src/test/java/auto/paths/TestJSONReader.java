package auto.paths;

import frc.team3256.robot.auto.paths.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TestJSONReader {
    @Test
    public void GettingAllTranslationsFromParser() {
        String sampleText = "{\n" +
                "\"acceleration\": 10.0,\n" +
                "\"curvature\": 0.0,\n" +
                "\"pose\": {},\n" +
                "\"rotation\": {\n" +
                "\"radians\": -0.23210383807515578\n" +
                "},\n" +
                "\"translation\": {    \n" +
                "\"x\": 123.30239583340617,\n" +
                "\"y\": 44.4397520790277\n" +
                "}\n" +
                "}";

        System.out.println(sampleText);
        JSONParser obj = new JSONParser();
        try {
            JSONObject data = (JSONObject) obj.parse(sampleText);
            JSONObject translation = (JSONObject) data.get("translation");
            Double y = (Double) translation.get("y");
            Double x = (Double) translation.get("x");
            System.out.println(x);
            System.out.println(y);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        String result = "";
        List<float[]> coordinates = new ArrayList<float[]>();
        float[] pair = {123.30239583340617f, 44.4397520790277f};
        coordinates.add(pair);

        String translated = coordinates.stream().map(Object::toString).collect(Collectors.joining(", "));
        try {
            JSONObject translation = (JSONObject) ((JSONObject) new JSONParser().parse(sampleText)).get("translation");

            ArrayList<Object[]> translatedCoords = (new JSONReader()).ParseJSONFile(translation); //change this
            result = translatedCoords.toString();
        } catch (Exception e) {
            result = e.toString();
        }

        assertEquals(translated, result);

    }

}
