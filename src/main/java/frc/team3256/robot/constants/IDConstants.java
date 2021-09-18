package frc.team3256.robot.constants;

public class IDConstants {

    //CAN IDs: ------------------------------------------------------------------------------------------------

    //PDP
    public static final int pdpID = 0;

    //Drive Constants
    public static final int leftMasterID = 1;  // spark max
    public static final int leftSlaveID = 2;  // spark max
    public static final int rightMasterID = 3;  // spark max
    public static final int rightSlaveID = 4;  // spark max

    //Intake Constants
    public static final int intakeID = 5;  // talon fx
    public static final int centerMechID = 6;

    //Feeder Constants
    public static final int feederID = 7;  // spark max
    public static final int turretBarID = 8;

    //Shooter Constants
    public static final int hoodID = 9;
    public static final int turretID = 10;
    public static final int leftFlywheelID = 11;  // talon fx
    public static final int rightFlywheelID = 12; // talon fx

    //Gyro
    public static final int pigeonID = 13;

    //PCM
    public static final int pcmID = 14;

    //Hanger
    public static final int winchMotorID = 15;  // talon fx

    //Pneumatics:------------------------------------------------------------------------------------------------

    //Shifter IDs
    public static final int shifterForwardChannel = 4; //4
    public static final int shifterReverseChannel = 3; //3

    //Intake Raise Mech IDs
    public static final int intakeRaiseForwardChannel = 2;
    public static final int intakeRaiseReverseChannel = 5;

    //Hanger Pancakes
    public static final int hangerPancakesForwardChannel = 1; //TBD 1
    public static final int hangerPancakesReverseChannel = 6; //TBD 6

    //DIO Ports:------------------------------------------------------------------------------------------------
    public static final int limitSwitchPort = 1;
    public static final int feederIRID = 6;
    public static final int flywheelIRID = 3; //TBD

    //Analog Ports:---------------------------------------------------------------------------------------------
    public static final int pressureSwitchID = 1;

}
