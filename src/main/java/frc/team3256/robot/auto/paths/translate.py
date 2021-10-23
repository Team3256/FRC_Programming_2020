import json
import argparse
import pathlib
import math
import platform
import os

parser = argparse.ArgumentParser(description="Turns Path Weaver Jsons to Java Code")

parser.add_argument("target_json_path",type=pathlib.Path, help="Target Path of Json")
parser.add_argument("--offsetX",type=int, default=0, help="X Coord Robot Should Start On")
parser.add_argument("--offsetY",type=int, default = 0,help="Y Coord Robot Should Start On")
parser.add_argument("--spacing",type=int, default =6, help="Max Spacing between points")
args = parser.parse_args()

points = [(0,0)]
prevPoint = (0,0)

pointString = ""

startX = 0
startY = 0
try:
    with open(args.target_json_path) as json_file:
        pathJson = json.load(json_file)

        for i,point in enumerate(pathJson):
            x =  - point["pose"]["translation"]["y"] -startX
            y = point["pose"]["translation"]["x"] -startY

            if i == 0:
                startX = x
                startY = y
            else:
                if math.sqrt((x-prevPoint[0])** 2 + (y-prevPoint[1]) ** 2) >= args.spacing:
                    points.append((x,y)) #Only Adds Point if greater then spacing
                    prevPoint = (x,y)
        points.append((x,y)) #Adds Last Point
        for point in points:
            pointString += "s.addPoint(new Vector({}, {}));".format(point[0]+args.offsetX,point[1]+args.offsetY)

        if platform.system() == "Windows":
            #Formatting to Copy to Clipboard
            os.system("cmd /c \"{}\" | clip".format(pointString.replace("s.addPoint","echo s.addPoint").replace(";",";& ")))
            print("Copied to Cliboard")
        else:
            print("Failed to Copy to Clipboard\n\n\n----------")
            print(pointString)
except FileNotFoundError:
    print("Error! {} not found".format(args.target_json_path))
