package frc.team3256.robot.auto.paths;

import java.util.ArrayList;

public class TestJSONParsing {
    public static void main (String[] args) {
        JSONReader testRead = new JSONReader("Hi.wpilib.json");

        testRead.initJSONReader();
        ArrayList<float[]> testCoords = testRead.ParseJSONFile();
        for (float[] coordPair : testCoords) {
            System.out.println("Current Coordinate Pair:\nx: " + coordPair[0] + "\ny: " + coordPair[1] + "\n");
        }
    }
}
