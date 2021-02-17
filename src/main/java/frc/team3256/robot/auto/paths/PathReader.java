package frc.team3256.robot.auto.paths;

import frc.team3256.warriorlib.math.Vector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PathReader {
    ArrayList<Vector> points = new ArrayList<>();
    private String splitBy = ",";
    private double startX = 0;
    private double startY = 0;
    private boolean left = true;
    private double spacing = 6;

    public ArrayList<Vector> getPath(String csvFile) {
        int index = 0;
        double x = 0;
        double y = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line = "";

            while ((line = br.readLine()) != null) {
                if (index > 0) {
                    String[] pathInfo = line.replace("\"","").split(splitBy);

                    if (left) {
                        x = - Double.parseDouble(pathInfo[4]) - startX; //4
                        y = Double.parseDouble(pathInfo[3]) - startY; //3
                    } else {
                        x = Double.parseDouble(pathInfo[4]) - startX; //4
                        y = - Double.parseDouble(pathInfo[3]) - startY; //3
                    }
                    if (index == 1) {
                        startX = x;
                        startY = y;
                        points.add(new Vector(0, 0));  // adds initial point as 0,0
                    } else {
                        Vector prev = points.get(points.size()-1);
                        if(Math.sqrt(Math.pow((x - prev.x), 2) + Math.pow((y - prev.y), 2)) >= spacing) {
                            points.add(new Vector(x, y));  // adds point if distance is greater than desired spacing
                        }
                    }
                }
                index++;
            }
            points.add(new Vector(x, y));  // adds last point
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    public static void main(String args[]) {
        PathReader p = new PathReader();
        int offsetX = -0;
        int offsetY = 0;
        ArrayList<Vector> arr = p.getPath("C:\\Users\\WB17\\Downloads\\Red Ball Path.wpilib.csv");
        for (int i = 0; i < arr.size(); i++) {
            System.out.println("s.addPoint(new Vector("+ (arr.get(i).x + offsetX) + ", " + (arr.get(i).y + offsetY) +"));");
        }
    }
}
