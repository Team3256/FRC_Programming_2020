package frc.team3256.robot.operations;

public class SparkUtil extends Util{
    public static double inchesPerSecToEncoderUnits(double inchesPerSec) { // inches per sec to RPM
        return inchesToRotations(inchesPerSec) * 60D;
    }

    public static double encoderUnitsToInchesPerSec(double encoderUnits) { // RPM to inches per sec
        return rotationsToInches(encoderUnits) / 60D;
    }

    public static double encoderUnitsToVelocity(double encoderUnits, double kGearRatio) { // RPM to velocity (based on gear ratio)
        return encoderUnitsToInchesPerSec(encoderUnits) / kGearRatio;
    }

    public static double encoderUnitsToPosition(double encoderUnits, double kGearRatio) { // rotations to position (inches)
        return rotationsToInches(encoderUnits) / kGearRatio;
    }
    public static double positionToEncoderUnits(double inches, double kGearRatio) { // rotations to position (inches)
        return inchesToRotations(inches) * kGearRatio;
    }
}
