package frc.team3256.robot.constants;

import frc.team3256.robot.operations.SparkUtil;


public class FeederConstants {
    public static final double kWheelDiameter = 5;
    public static final double kInchesBetweenPowerCells = 10;
    public static final int kgearRatio = 5/1;
    public static final double kSpaceBetweenPowerCells = SparkUtil.positionToEncoderUnits(kInchesBetweenPowerCells, kgearRatio);
    public static final double kP = 0.00129;
    public static final double kI = 0;
    public static final double kD = 0.0000637;
    public static final double positionTolerance = 0.005;
}