package frc.team3256.robot.auto.paths;

import java.util.Iterator;
import java.io.FileReader;
import java.util.Map;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.util.ArrayList;

public class JSONReader {
    private String file; // file to read from
    private Object obj; // JSONParser to parse json 'file'

    public JSONReader(String file) {
        this.file = file;
    }

    public void initJSONReader() {
        try {
             obj = new JSONParser().parse(new FileReader(file));
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
    }

    public ArrayList<float[]> ParseJSONFile() {
        JSONArray translation = (JSONArray) ((JSONObject) obj).get("translation"); // array of points

        Iterator itr = ((Map) translation.iterator()).entrySet().iterator();
        int counter = 0;

        float x = 0.0f;
        float y = 0.0f;

        ArrayList<float[]> coordinates = new ArrayList<float[]>();
        float[] coordinate = {0.0f, 0.0f};

        while (itr.hasNext()) {
            Map.Entry pair = (Map.Entry) itr.next();
            if (counter == 0) {
                x = (float) pair.getValue(); // current x-value
            } else if (counter == 1) {
                y = (float) pair.getValue(); // current y-value
            }

            counter++;

            coordinate[0] = x;
            coordinate[1] = y;

            coordinates.add(coordinate);
        }

        return coordinates;
    }
}
