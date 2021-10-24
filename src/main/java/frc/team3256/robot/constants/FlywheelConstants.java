package frc.team3256.robot.constants;

public class FlywheelConstants {
    public static final double kFlywheelP = 0.0003014;
    public static final double kFlywheelI = 0.00000;  //0.0000065 // .00015
    public static final double kFlywheelD = 0.0011134; //0.000063
    public static final double kFlywheelF = 1023/22050;    //1023/21800
    public static final double kAtSetpointTolerance = 30; //in RPM
    public static final double maxFlywheelVel = 1500; //6500
}
