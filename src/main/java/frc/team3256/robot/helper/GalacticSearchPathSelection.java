package frc.team3256.robot.helper;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GalacticSearchPathSelection {
    private double ballCenterX, ballCenterY, ballXAngle;
    private NetworkTableInstance inst;
    double pathARedX = 0;
    double pathARedY = 0;
    double pathABlueX = 0;
    double pathABlueY = 0;
    double pathBRedX = 0;
    double pathBRedY = 0;
    double pathBBlueX = 0;
    double pathBBlueY = 0;

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
            if(ballXAngle > 10){return true;}else{return false;}
        } else {
            if(ballXAngle > 10){return true;}else{return false;}
        }
    }

}
