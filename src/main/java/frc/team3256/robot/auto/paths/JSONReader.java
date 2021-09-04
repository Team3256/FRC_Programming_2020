package frc.team3256.robot.auto.paths;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.*;
import org.json.simple.parser.*;

public class JSONReader {
    String file = "Hi";
    Object obj;

    JSONObject jo;
    public JSONReader() {
        try {
             obj = new JSONParser().parse(new FileReader(file));
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        jo = (JSONObject) obj;
    }






}
