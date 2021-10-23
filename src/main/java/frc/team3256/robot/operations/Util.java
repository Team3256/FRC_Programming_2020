package frc.team3256.robot.operations;

public abstract class Util {

    public static double kWheelDiameter;
    public static double kGearRatio;

    public static boolean epsilonEquals(double a, double b, double epsilon) {
        return (a - epsilon <= b) && (a + epsilon >= b);
    }

    public static double handleDeadband(double a, double deadband) {
        if (Math.abs(a) <= deadband)
            return 0;
        else
            return a;
    }

    public static double clip(double a, double min, double max) {
        if (a < min) a = min;
        else if (a > max) a = max;
        return a;
    }

    public static void setWheelDiameter(double wheelDiameter) {
        frc.team3256.robot.operations.Util.kWheelDiameter = wheelDiameter;
    }

    public static void setGearRatio(double gearRatio) {
        frc.team3256.robot.operations.Util.kGearRatio = gearRatio;
    }

    public static double inchesToRotations(double inches) {
        return inches / (kWheelDiameter * Math.PI);
    }

    public static double rotationsToInches(double rotations) {
        return rotations * Math.PI * kWheelDiameter;
    }

    public static double inchesPerSecToEncoderUnits(double inchesPerSec) { // method cannot be abstract and static at the same time
        throw new IllegalStateException("Use a different Util Class");
    }

    public static double encoderUnitsToInchesPerSec(double encoderUnits) {
        throw new IllegalStateException("Use a different Util Class");
    }

    public static double encoderUnitsToVelocity(double encoderUnits) {
        throw new IllegalStateException("Use a different Util Class");
    }

    public static double encoderUnitsToPosition(double encoderUnits) {
        throw new IllegalStateException("Use a different Util Class");
    }

}