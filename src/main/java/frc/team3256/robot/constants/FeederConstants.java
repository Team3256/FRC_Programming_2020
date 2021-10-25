package frc.team3256.robot.constants;

import frc.team3256.robot.operations.SparkUtil;


public class FeederConstants {
    public static final double kWheelDiameter = 1;
    public static final double kInchesBetweenPowerCells = 6; // 2.5 for 4 ball, 5 for 3 ball feeder
    public static final int kGearRatio = 5;
    public static final double kSpaceBetweenPowerCells = SparkUtil.positionToEncoderUnits(kInchesBetweenPowerCells, kGearRatio);
    public static final double kP = 0.18;
    public static final double kI = 0.00001;
    public static final double kD = 0.0100000000;
    public static final double positionTolerance = 0.15;
}