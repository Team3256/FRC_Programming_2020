package frc.team3256.robot.auto.paths;

import java.util.Iterator;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.*;
import org.json.simple.parser.*;

import javax.swing.text.html.HTMLDocument;

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

    public void ParseJSONFile() {
        JSONArray translation = (JSONArray) ((JSONObject) obj).get("translation"); // array of points

        Iterator itr = ((Map) translation.iterator()).entrySet().iterator();
        int counter = 0;

        float x;
        float y;

        while (itr.hasNext()) {
            Map.Entry pair = (Map.Entry) itr.next();
            if (counter == 0) {
                x = (float) pair.getValue(); // x-value
            } else if (counter == 1) {
                y = (float) pair.getValue(); // y-value
            }

            counter++;
        }
    }
}
