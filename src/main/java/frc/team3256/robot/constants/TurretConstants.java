package frc.team3256.robot.constants;

public class TurretConstants {
    public static final double turretkP = 0.040;    // need to tune
    public static final double turretkI = 0.0002;
    public static final double turretkD = 0.0013;
    public static final double turretHeight = 22.5;
    public static final double kAutoAlignTolerance = 0.3; //0.1

    public static final double turretPositionkP = 1.7;    // need to tune 2.0
    public static final double turretPositionkI = 1e-4;
    public static final double turretPositionkD = 0;

    public static final double maxTurretPosition = 470;
    public static final double minTurretPosition = -5;
}
