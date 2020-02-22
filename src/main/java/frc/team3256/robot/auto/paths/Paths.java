package frc.team3256.robot.auto.paths;

import frc.team3256.warriorlib.auto.purepursuit.Path;
import frc.team3256.warriorlib.auto.purepursuit.PathGenerator;
import frc.team3256.warriorlib.auto.purepursuit.PoseEstimator;
import frc.team3256.warriorlib.math.Vector;

import java.util.Arrays;
import java.util.List;

import static frc.team3256.robot.constants.DriveConstants.*;

public class Paths {
    private static List<Path> rightShootAutoPath;
    private static List<Path> rightTrenchCollectAutoPath;
    private static List<Path> rightTrenchCollectTenBallAutoPath;
    private static PoseEstimator poseEstimator = PoseEstimator.getInstance();

    public static void initialize() {
        getRightShootAutoPath();
        getRightTrenchCollectAutoPath();
        getRightTrenchCollectTenBallAutoPath();
    }

    public static List<Path> getRightShootAutoPath() {
        if (rightShootAutoPath != null)
            return rightShootAutoPath;
        PathGenerator firstSegment = new PathGenerator(spacing, true);

        firstSegment.addPoint(new Vector(0,0));
        firstSegment.addPoint(new Vector(0,30));

        firstSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        firstSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path firstSegmentPath = firstSegment.generatePath();

        rightShootAutoPath = Arrays.asList(firstSegmentPath);
        return rightShootAutoPath;
    }

    public static List<Path> getRightTrenchCollectAutoPath() {
        if (rightTrenchCollectAutoPath != null)
            return rightTrenchCollectAutoPath;

        //First Segment (Forward Spline)

        PathGenerator firstSegment = new PathGenerator(spacing, true);

        firstSegment.addPoint(new Vector(0,0));
        firstSegment.addPoint(new Vector(0,18));
        firstSegment.addPoint(new Vector(-0.05, 18.61));
        firstSegment.addPoint(new Vector(-1.52, 21.37));
        firstSegment.addPoint(new Vector(-2.49, 22.51));
        firstSegment.addPoint(new Vector(-5.31, 25.35));
        firstSegment.addPoint(new Vector(-7.24, 27.12));
        firstSegment.addPoint(new Vector(-9, 28.66));
        firstSegment.addPoint(new Vector(-10.4,29.82));
        firstSegment.addPoint(new Vector(-10.99, 30.40));
        firstSegment.addPoint(new Vector(-11.88, 31.15));
        firstSegment.addPoint(new Vector(-13.34, 32.38));
        firstSegment.addPoint(new Vector(-14.66, 33.47));
        firstSegment.addPoint(new Vector(-15.77, 34.39));
        firstSegment.addPoint(new Vector(-16.55, 35.03));
        firstSegment.addPoint(new Vector(-18.05, 36.25));
        firstSegment.addPoint(new Vector(-19.32, 37.28));
        firstSegment.addPoint(new Vector(-21.28, 38.86));
        firstSegment.addPoint(new Vector(-21.91, 39.37));
        firstSegment.addPoint(new Vector(-23.57, 40.7));
        firstSegment.addPoint(new Vector(-28.92, 44.98));
        firstSegment.addPoint(new Vector(-31.69, 47.22));
        firstSegment.addPoint(new Vector(-35,50));
        firstSegment.addPoint(new Vector(-37.62,52.27));
        firstSegment.addPoint(new Vector(-35,50));
        firstSegment.addPoint(new Vector(-37.31,52));
        firstSegment.addPoint(new Vector(-39.95,54.37));
        firstSegment.addPoint(new Vector(-41.10,55.42));
        firstSegment.addPoint(new Vector(-45.38, 59.51));
        firstSegment.addPoint(new Vector(-46.18,60.31));
        firstSegment.addPoint(new Vector(-46.91,61.04));
        firstSegment.addPoint(new Vector(-49.43,63.61));
        firstSegment.addPoint(new Vector(-51.66,65.98));
        firstSegment.addPoint(new Vector(-53.86,68.41));
        firstSegment.addPoint(new Vector(-55.51,70.31));
        firstSegment.addPoint(new Vector(-58.06,73.38));
        firstSegment.addPoint(new Vector(-59.79,75.61));
        firstSegment.addPoint(new Vector(-61,78.01));
        firstSegment.addPoint(new Vector(-64.09,82.01));
        firstSegment.addPoint(new Vector(-66.05,85.84));
        firstSegment.addPoint(new Vector(-67.46,90.03));
        firstSegment.addPoint(new Vector(-71.5, 96));
        firstSegment.addPoint(new Vector(-71.5, 100));
        firstSegment.addPoint(new Vector(-71.5, 110));
        firstSegment.addPoint(new Vector(-71.5, 120));
        firstSegment.addPoint(new Vector(-71.5, 130));
        firstSegment.addPoint(new Vector(-71.5, 140));
        firstSegment.addPoint(new Vector(-71.5, 150));
        firstSegment.addPoint(new Vector(-71.5, 160));
        firstSegment.addPoint(new Vector(-71.5, 170));
        firstSegment.addPoint(new Vector(-71.5, 237)); //174

        firstSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        firstSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path firstSegmentPath = firstSegment.generatePath();

        //Third Segment (Backwards Spline)

        PathGenerator secondSegment = new PathGenerator(spacing, false);

        secondSegment.addPoint(new Vector(-71.5, 237));
        secondSegment.addPoint(new Vector(-71.5, 170));
        secondSegment.addPoint(new Vector(-71.5, 160));
        secondSegment.addPoint(new Vector(-71.5, 150));
        secondSegment.addPoint(new Vector(-71.5, 140));
        secondSegment.addPoint(new Vector(-71.5, 130));
        secondSegment.addPoint(new Vector(-71.5, 120));
        secondSegment.addPoint(new Vector(-71.5, 110));
        secondSegment.addPoint(new Vector(-71.5, 100));
        secondSegment.addPoint(new Vector(-71.5, 96));
        secondSegment.addPoint(new Vector(-67.46,90.03));
        secondSegment.addPoint(new Vector(-66.05,85.84));
        secondSegment.addPoint(new Vector(-64.09,82.01));
        secondSegment.addPoint(new Vector(-61,78.01));
        secondSegment.addPoint(new Vector(-59.79,75.61));
        secondSegment.addPoint(new Vector(-58.06,73.38));
        secondSegment.addPoint(new Vector(-55.51,70.31));
        secondSegment.addPoint(new Vector(-53.86,68.41));
        secondSegment.addPoint(new Vector(-51.66,65.98));
        secondSegment.addPoint(new Vector(-49.43,63.61));
        secondSegment.addPoint(new Vector(-46.91,61.04));
        secondSegment.addPoint(new Vector(-46.18,60.31));
        secondSegment.addPoint(new Vector(-45.38, 59.51));
        secondSegment.addPoint(new Vector(-41.10,55.42));
        secondSegment.addPoint(new Vector(-39.95,54.37));
        secondSegment.addPoint(new Vector(-37.31,52));
        secondSegment.addPoint(new Vector(-35,50));
        secondSegment.addPoint(new Vector(-37.62,52.27));
        secondSegment.addPoint(new Vector(-35,50));
        secondSegment.addPoint(new Vector(-31.69, 47.22));
        secondSegment.addPoint(new Vector(-28.92, 44.98));
        secondSegment.addPoint(new Vector(-23.57, 40.7));
        secondSegment.addPoint(new Vector(-21.91, 39.37));
        secondSegment.addPoint(new Vector(-21.28, 38.86));
        secondSegment.addPoint(new Vector(-19.32, 37.28));
        secondSegment.addPoint(new Vector(-18.05, 36.25));
        secondSegment.addPoint(new Vector(-16.55, 35.03));
        secondSegment.addPoint(new Vector(-15.77, 34.39));
        secondSegment.addPoint(new Vector(-14.66, 33.47));
        secondSegment.addPoint(new Vector(-13.34, 32.38));
        secondSegment.addPoint(new Vector(-11.88, 31.15));
        secondSegment.addPoint(new Vector(-10.99, 30.40));
        secondSegment.addPoint(new Vector(-10.4,29.82));
        secondSegment.addPoint(new Vector(-9, 28.66));
        secondSegment.addPoint(new Vector(-7.24, 27.12));
        secondSegment.addPoint(new Vector(-5.31, 25.35));
        secondSegment.addPoint(new Vector(-2.49, 22.51));
        secondSegment.addPoint(new Vector(-1.52, 21.37));
        secondSegment.addPoint(new Vector(-0.05, 18.61));
        secondSegment.addPoint(new Vector(0, 18));

        secondSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        secondSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path secondSegmentPath = secondSegment.generatePath();

        rightTrenchCollectAutoPath = Arrays.asList(firstSegmentPath, secondSegmentPath);
        return rightTrenchCollectAutoPath;
    }

    public static List<Path> getRightTrenchCollectTenBallAutoPath() {
        if (rightTrenchCollectTenBallAutoPath != null)
            return rightTrenchCollectTenBallAutoPath;

        //First Segment (Forward to Intake Two Balls)

        PathGenerator firstSegment = new PathGenerator(spacing, true);

        firstSegment.addPoint(new Vector(0,0));
        firstSegment.addPoint(new Vector(-0.00997438101811099,4.109449873323854));
        firstSegment.addPoint(new Vector(-0.07221798706818561,8.142432941288604));
        firstSegment.addPoint(new Vector(-0.22663426507743623,12.245540703187032));
        firstSegment.addPoint(new Vector(-0.4922460541405087,16.313403405380797));
        firstSegment.addPoint(new Vector(-0.8756010986213738,20.347441223216123));
        firstSegment.addPoint(new Vector(-1.3735342568415234,24.341291513736167));
        firstSegment.addPoint(new Vector(-1.9871308240016958,28.349751494710034));
        firstSegment.addPoint(new Vector(-2.6946502802075827,32.29401648786302));
        firstSegment.addPoint(new Vector(-3.49316042768956   ,36.22561258542538));
        firstSegment.addPoint(new Vector(-4.368284946800898,40.129357068477844));
        firstSegment.addPoint(new Vector(-5.321459633874511,44.05829034560722));
        firstSegment.addPoint(new Vector(-7.386240243802803,51.85685480472273));
        firstSegment.addPoint(new Vector(-8.483850292598945,55.75950828845703));
        firstSegment.addPoint(new Vector(-9.595308353933135,59.61565853503009));
        firstSegment.addPoint(new Vector(-10.720902302507042,63.47119439151038));
        firstSegment.addPoint(new Vector(-11.858003756518372,67.36500946763454));
        firstSegment.addPoint(new Vector(-12.980033085037917,71.26350904779639));
        firstSegment.addPoint(new Vector(-14.072061068887905,75.18801634654949));
        firstSegment.addPoint(new Vector(-15.093200088128214,79.08766607570999));
        firstSegment.addPoint(new Vector(-16.03048652380278,83.06131703509911));
        firstSegment.addPoint(new Vector(-16.80323438639374,87.00796759082237));
        firstSegment.addPoint(new Vector(-17.333455599979047,90.9812027947782));
        firstSegment.addPoint(new Vector(-17.45600303702753,94.99983276452679));
        firstSegment.addPoint(new Vector(-16.828469529082895,99.01563692990226));
        firstSegment.addPoint(new Vector(-15.010332325792092,102.58761800763077));
        firstSegment.addPoint(new Vector(-12.124000000000052,105.45999999999981));
        firstSegment.addPoint(new Vector(-9.059702094009708,108.04265065871749));
        firstSegment.addPoint(new Vector(-5.791160919785526,110.51584173248023));
        firstSegment.addPoint(new Vector(-2.4138572615254077,112.72533368363358));
        firstSegment.addPoint(new Vector(1.0781708981700717,114.70830791368184));
        firstSegment.addPoint(new Vector(4.6660199365480395,116.5027073357482));
        firstSegment.addPoint(new Vector(8.32524666598161,118.13978770011121));
        firstSegment.addPoint(new Vector(12.113702848616654,119.67641618499914));
        firstSegment.addPoint(new Vector(15.928233673142273,121.09804248021825));
        firstSegment.addPoint(new Vector(19.720935637040924,122.42061066111415));
        firstSegment.addPoint(new Vector(23.54531299845445,123.71597176924672));
        firstSegment.addPoint(new Vector(26.777000000000086,124.91099999999994));

        firstSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        firstSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path firstSegmentPath = firstSegment.generatePath();

        //First Back Segment (Backwards after Intake Two Balls)

        PathGenerator firstBackSegment = new PathGenerator(spacing, false);

        firstBackSegment.addPoint(new Vector(26.777000000000086,124.91099999999994));
        firstBackSegment.addPoint(new Vector(23.54531299845445,123.71597176924672));
        firstBackSegment.addPoint(new Vector(19.720935637040924,122.42061066111415));
        firstBackSegment.addPoint(new Vector(15.928233673142273,121.09804248021825));
        firstBackSegment.addPoint(new Vector(12.113702848616654,119.67641618499914));
        firstBackSegment.addPoint(new Vector(8.32524666598161,118.13978770011121));
        firstBackSegment.addPoint(new Vector(4.6660199365480395,116.5027073357482));
        firstBackSegment.addPoint(new Vector(1.0781708981700717,114.70830791368184));
        firstBackSegment.addPoint(new Vector(-2.4138572615254077,112.72533368363358));
        firstBackSegment.addPoint(new Vector(-5.791160919785526,110.51584173248023));
        firstBackSegment.addPoint(new Vector(-9.059702094009708,108.04265065871749));
        firstBackSegment.addPoint(new Vector(-12.124000000000052,105.45999999999981));
        firstBackSegment.addPoint(new Vector(-15.010332325792092,102.58761800763077));
        firstBackSegment.addPoint(new Vector(-16.828469529082895,99.01563692990226));
        firstBackSegment.addPoint(new Vector(-17.45600303702753,94.99983276452679));
        firstBackSegment.addPoint(new Vector(-17.333455599979047,90.9812027947782));
        firstBackSegment.addPoint(new Vector(-16.80323438639374,87.00796759082237));
        firstBackSegment.addPoint(new Vector(-16.03048652380278,83.06131703509911));
        firstBackSegment.addPoint(new Vector(-15.093200088128214,79.08766607570999));
        firstBackSegment.addPoint(new Vector(-14.072061068887905,75.18801634654949));
        firstBackSegment.addPoint(new Vector(-12.980033085037917,71.26350904779639));
        firstBackSegment.addPoint(new Vector(-11.858003756518372,67.36500946763454));
        firstBackSegment.addPoint(new Vector(-10.720902302507042,63.47119439151038));
        firstBackSegment.addPoint(new Vector(-9.595308353933135,59.61565853503009));
        firstBackSegment.addPoint(new Vector(-8.483850292598945,55.75950828845703));
        firstBackSegment.addPoint(new Vector(-7.386240243802803,51.85685480472273));
        firstBackSegment.addPoint(new Vector(-5.321459633874511,44.05829034560722));
        firstBackSegment.addPoint(new Vector(-4.368284946800898,40.129357068477844));
        firstBackSegment.addPoint(new Vector(-3.49316042768956   ,36.22561258542538));
        firstBackSegment.addPoint(new Vector(-2.6946502802075827,32.29401648786302));
        firstBackSegment.addPoint(new Vector(-1.9871308240016958,28.349751494710034));
        firstBackSegment.addPoint(new Vector(-1.3735342568415234,24.341291513736167));
        firstBackSegment.addPoint(new Vector(-0.8756010986213738,20.347441223216123));
        firstBackSegment.addPoint(new Vector(-0.4922460541405087,16.313403405380797));
        firstBackSegment.addPoint(new Vector(-0.22663426507743623,12.245540703187032));
        firstBackSegment.addPoint(new Vector(-0.07221798706818561,8.142432941288604));
        firstBackSegment.addPoint(new Vector(-0.00997438101811099,4.109449873323854));
        firstBackSegment.addPoint(new Vector(0,0));

        firstBackSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        firstBackSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path firstBackSegmentPath = firstBackSegment.generatePath();

        //Second Segment (Forward Spline)

        PathGenerator secondSegment = new PathGenerator(spacing, true);

        secondSegment.addPoint(new Vector(poseEstimator.getPose().x,poseEstimator.getPose().y));
        secondSegment.addPoint(new Vector(-0.05, 18.61));
        secondSegment.addPoint(new Vector(-1.52, 21.37));
        secondSegment.addPoint(new Vector(-2.49, 22.51));
        secondSegment.addPoint(new Vector(-5.31, 25.35));
        secondSegment.addPoint(new Vector(-7.24, 27.12));
        secondSegment.addPoint(new Vector(-9, 28.66));
        secondSegment.addPoint(new Vector(-10.4,29.82));
        secondSegment.addPoint(new Vector(-10.99, 30.40));
        secondSegment.addPoint(new Vector(-11.88, 31.15));
        secondSegment.addPoint(new Vector(-13.34, 32.38));
        secondSegment.addPoint(new Vector(-14.66, 33.47));
        secondSegment.addPoint(new Vector(-15.77, 34.39));
        secondSegment.addPoint(new Vector(-16.55, 35.03));
        secondSegment.addPoint(new Vector(-18.05, 36.25));
        secondSegment.addPoint(new Vector(-19.32, 37.28));
        secondSegment.addPoint(new Vector(-21.28, 38.86));
        secondSegment.addPoint(new Vector(-21.91, 39.37));
        secondSegment.addPoint(new Vector(-23.57, 40.7));
        secondSegment.addPoint(new Vector(-28.92, 44.98));
        secondSegment.addPoint(new Vector(-31.69, 47.22));
        secondSegment.addPoint(new Vector(-35,50));
        secondSegment.addPoint(new Vector(-37.62,52.27));
        secondSegment.addPoint(new Vector(-35,50));
        secondSegment.addPoint(new Vector(-37.31,52));
        secondSegment.addPoint(new Vector(-39.95,54.37));
        secondSegment.addPoint(new Vector(-41.10,55.42));
        secondSegment.addPoint(new Vector(-45.38, 59.51));
        secondSegment.addPoint(new Vector(-46.18,60.31));
        secondSegment.addPoint(new Vector(-46.91,61.04));
        secondSegment.addPoint(new Vector(-49.43,63.61));
        secondSegment.addPoint(new Vector(-51.66,65.98));
        secondSegment.addPoint(new Vector(-53.86,68.41));
        secondSegment.addPoint(new Vector(-55.51,70.31));
        secondSegment.addPoint(new Vector(-58.06,73.38));
        secondSegment.addPoint(new Vector(-59.79,75.61));
        secondSegment.addPoint(new Vector(-61,78.01));
        secondSegment.addPoint(new Vector(-64.09,82.01));
        secondSegment.addPoint(new Vector(-66.05,85.84));
        secondSegment.addPoint(new Vector(-67.46,90.03));
        secondSegment.addPoint(new Vector(-68, 96));
        secondSegment.addPoint(new Vector(-68, 100));
        secondSegment.addPoint(new Vector(-68, 110));
        secondSegment.addPoint(new Vector(-68, 120));
        secondSegment.addPoint(new Vector(-68, 130));
        secondSegment.addPoint(new Vector(-68, 140));
        secondSegment.addPoint(new Vector(-68, 150));
        secondSegment.addPoint(new Vector(-68, 160));
        secondSegment.addPoint(new Vector(-68, 170));
        secondSegment.addPoint(new Vector(-68, 174));

        secondSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        secondSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path secondSegmentPath = secondSegment.generatePath();

        //Third Segment (Backwards Spline)

        PathGenerator thirdSegment = new PathGenerator(spacing, false);

        thirdSegment.addPoint(new Vector(-68, 174));
        thirdSegment.addPoint(new Vector(-68, 170));
        thirdSegment.addPoint(new Vector(-68, 160));
        thirdSegment.addPoint(new Vector(-68, 150));
        thirdSegment.addPoint(new Vector(-68, 140));
        thirdSegment.addPoint(new Vector(-68, 130));
        thirdSegment.addPoint(new Vector(-68, 120));
        thirdSegment.addPoint(new Vector(-68, 110));
        thirdSegment.addPoint(new Vector(-68, 100));
        thirdSegment.addPoint(new Vector(-68, 96));
        thirdSegment.addPoint(new Vector(-67.46,90.03));
        thirdSegment.addPoint(new Vector(-66.05,85.84));
        thirdSegment.addPoint(new Vector(-64.09,82.01));
        thirdSegment.addPoint(new Vector(-61,78.01));
        thirdSegment.addPoint(new Vector(-59.79,75.61));
        thirdSegment.addPoint(new Vector(-58.06,73.38));
        thirdSegment.addPoint(new Vector(-55.51,70.31));
        thirdSegment.addPoint(new Vector(-53.86,68.41));
        thirdSegment.addPoint(new Vector(-51.66,65.98));
        thirdSegment.addPoint(new Vector(-49.43,63.61));
        thirdSegment.addPoint(new Vector(-46.91,61.04));
        thirdSegment.addPoint(new Vector(-46.18,60.31));
        thirdSegment.addPoint(new Vector(-45.38, 59.51));
        thirdSegment.addPoint(new Vector(-41.10,55.42));
        thirdSegment.addPoint(new Vector(-39.95,54.37));
        thirdSegment.addPoint(new Vector(-37.31,52));
        thirdSegment.addPoint(new Vector(-35,50));
        thirdSegment.addPoint(new Vector(-37.62,52.27));
        thirdSegment.addPoint(new Vector(-35,50));
        thirdSegment.addPoint(new Vector(-31.69, 47.22));
        thirdSegment.addPoint(new Vector(-28.92, 44.98));
        thirdSegment.addPoint(new Vector(-23.57, 40.7));
        thirdSegment.addPoint(new Vector(-21.91, 39.37));
        thirdSegment.addPoint(new Vector(-21.28, 38.86));
        thirdSegment.addPoint(new Vector(-19.32, 37.28));
        thirdSegment.addPoint(new Vector(-18.05, 36.25));
        thirdSegment.addPoint(new Vector(-16.55, 35.03));
        thirdSegment.addPoint(new Vector(-15.77, 34.39));
        thirdSegment.addPoint(new Vector(-14.66, 33.47));
        thirdSegment.addPoint(new Vector(-13.34, 32.38));
        thirdSegment.addPoint(new Vector(-11.88, 31.15));
        thirdSegment.addPoint(new Vector(-10.99, 30.40));
        thirdSegment.addPoint(new Vector(-10.4,29.82));
        thirdSegment.addPoint(new Vector(-9, 28.66));
        thirdSegment.addPoint(new Vector(-7.24, 27.12));
        thirdSegment.addPoint(new Vector(-5.31, 25.35));
        thirdSegment.addPoint(new Vector(-2.49, 22.51));
        thirdSegment.addPoint(new Vector(-1.52, 21.37));
        thirdSegment.addPoint(new Vector(-0.05, 18.61));
        thirdSegment.addPoint(new Vector(0, 18));

        thirdSegment.setSmoothingParameters(purePursuitA, purePursuitB, smoothingTolerance);
        thirdSegment.setVelocities(maxVel, maxAccel, maxVelk);

        Path thirdSegmentPath = thirdSegment.generatePath();

        rightTrenchCollectTenBallAutoPath = Arrays.asList(firstSegmentPath, firstBackSegmentPath, secondSegmentPath, thirdSegmentPath);
        return rightTrenchCollectTenBallAutoPath;
    }
}
