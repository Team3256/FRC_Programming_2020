package frc.team3256.robot.operations;

public class TalonUtil extends Util {
    public static double inchesPerSecToEncoderUnits(double inchesPerSec) { // inches per second to TalonFX encoder units (2048 units per rotation) per 100ms
        return inchesToRotations(inchesPerSec) * (2048D/10D);
    }

    public static double encoderUnitsToInchesPerSec(double encoderUnits) { // TalonFX encoder units per 100ms to inches per second
        return rotationsToInches(encoderUnits * (10D/2048D));
    }

    public static double encoderUnitsToVelocity(double encoderUnits) { // TalonFX encoder units per 100ms to velocity (based on gear ratio)
        return encoderUnitsToInchesPerSec(encoderUnits) / kGearRatio;
    }

    public static double encoderUnitsToPosition(double encoderUnits) { // TalonFX encoder units to position (inches)
        return rotationsToInches(encoderUnits/2048D) / kGearRatio;
    }
}
