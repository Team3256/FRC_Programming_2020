package frc.team3256.robot.constants;

public class DriveConstants {

    //General Information
    public static final double kWheelDiameter = 6;
    public static final double kRobotTrackWidth = 21.735; //inches
    public static final double kTrackScrubFactor = 1.0469745223;
    public static final double kGearRatio = 19.2 / 60 * 27;

    //Low Gear
    public static final int kVelocityLowGearSlot = 0;
    public static final double kVelocityLowGearP = 5e-6;
    public static final double kVelocityLowGearI = 1e-7;
    public static final double kVelocityLowGearD = 0;
    public static final double kVelocityLowGearIZone = 0;
    public static final double kVelocityLowGearF = 1e-5;
    //High Gear
    public static final int kVelocityHighGearSlot = 1;
    public static final double kVelocityHighGearP = 5e-4;
    public static final double kVelocityHighGearI = 1E-9;
    public static final double kVelocityHighGearD = 0;
    public static final double kVelocityHighGearIZone = 0;
    public static final double kVelocityHighGearF = 8e-5; //4e-4

    //Pure Pursuit
    public static final double spacing = 6; //inches
    public static final double kA = 0.002; //0.002
    public static final double kP = 0.01; //0.01
    public static final double purePursuitB = 0.8; //0.9
    public static final double purePursuitA = 1 - purePursuitB;
    public static final double smoothingTolerance = 0.001;
    public static final double loopTime = 1.0 / 200.0; //how often Looper updates
    public static final double lookaheadDistance = 15;
    public static final double maxAccel = 15; //max robot acceleration 15 / 5
    public static final double maxVel = 200; //max robot velocity 200 / 100
    public static final double kV = 1/maxVel; // 1/max robot speed
    public static final double maxVelk = .5; //generally between 1-5 //2 //1 //5
}
