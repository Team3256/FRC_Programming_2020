package frc.team3256.robot.helper;

import frc.team3256.robot.constants.IDConstants;

public class CANConstants {
    public static final int[] SparkMaxIDs;
    static {
        SparkMaxIDs = new int[]{IDConstants.leftMasterID,
                IDConstants.leftSlaveID,
                IDConstants.rightMasterID,
                IDConstants.rightSlaveID,
                IDConstants.feederID};
    }

    public static final int[] TalonFXIDs;
    static {
        TalonFXIDs = new int[]{
                IDConstants.intakeID,
                IDConstants.centerMechID,
                IDConstants.leftFlywheelID,
                IDConstants.rightFlywheelID};
    }

}
