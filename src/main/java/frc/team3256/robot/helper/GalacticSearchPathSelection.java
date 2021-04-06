package frc.team3256.robot.helper;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.constants.VisionConstants;
import frc.team3256.robot.hardware.Limelight;

public class GalacticSearchPathSelection {
    private double ballCenterX, ballCenterY, ballXAngle;
    private NetworkTableInstance inst;

    private static GalacticSearchPathSelection instance;

    public static GalacticSearchPathSelection getInstance(){
        return instance == null ? instance = new GalacticSearchPathSelection() : instance;
    }

    public boolean isRed(boolean isPathA) {
        //Setting up NetworkTables
        inst = NetworkTableInstance.getDefault();
        NetworkTable raspi = inst.getTable("SmartDashboard");

        //Getting values
        ballCenterX = raspi.getEntry("Ball Center X").getDouble(0);
        ballCenterY = raspi.getEntry("Ball Center Y").getDouble(0);
        ballXAngle = raspi.getEntry("Ball X Angle").getDouble(0);

        SmartDashboard.putNumber("Ball Center X: ", ballCenterX);
        SmartDashboard.putNumber("Ball Center Y: ", ballCenterY);
        SmartDashboard.putNumber("Ball Center Angle: ", ballXAngle);
        if(isPathA){
            return(!(ballXAngle > VisionConstants.pathABorderAngle)); //true is red, false is blue
        } else {
            return(ballXAngle < VisionConstants.pathBBorderAngle);
        }
    }


}
