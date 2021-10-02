package auto.paths;

import frc.team3256.robot.auto.paths.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TestJSONReaderArrayList {
    @Test
    public void GetTranslationsArrayList() {

        String sampleText = "[\n" + "{\n" +
                "\"acceleration\": 10.0,\n" +
                "\"curvature\": 0.0,\n" +
                "\"pose\": {\n" +
                "\"rotation\": {\n" +
                "\"radians\": -0.23210383807515578\n" +
                "},\n" +
                "\"translation\": {    \n" +
                "\"x\": 123.30239583340617,\n" +
                "\"y\": 44.4397520790277\n" +
                "}\n" +
                "},\n" +
                "\"time\": 0.0,\n" +
                "\"velocity\": 0.0\n" +
                "},\n" +
                "{\n" +
                "\"acceleration\": 10.000000000000002,\n" +
                "\"curvature\": 0.00048125905761470765,\n" +
                "\"pose\": {\n" +
                "\"rotation\": {\n" +
                "\"radians\": -0.232077508168375\n" +
                "},\n" +
                "\"translation\": {\n" +
                "\"x\": 123.40866955812137,\n" +
                "\"y\": 44.414633820785355\n" +
                "}\n" +
                "},\n" +
                "\"time\": 0.1477848384602367,\n" +
                "\"velocity\": 1.4778483846023671\n" +
                "},\n" + "]";

        JSONArray sampleArray = new JSONArray();
        JSONParser obj = new JSONParser();
        try {
            sampleArray = (JSONArray) obj.parse(sampleText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ArrayList<Vector<Double>> inputList = JSONReader.ParseJSONFileTester(sampleArray);

        ArrayList<Vector<Double>> compareWith = new ArrayList<Vector<Double>>();
        Vector<Double> coord1 = new Vector<Double>();
        coord1.add(0, 123.30239583340617);
        coord1.add(1, 44.4397520790277);

        compareWith.add(coord1);

        assertEquals(compareWith, inputList);
    }
}