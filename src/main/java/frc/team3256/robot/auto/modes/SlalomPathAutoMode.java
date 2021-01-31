package frc.team3256.robot.auto.modes;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.auto.paths.Paths;
import frc.team3256.robot.constants.DriveConstants;
import frc.team3256.robot.subsystems.DriveTrain;
import frc.team3256.warriorlib.auto.AutoModeBase;
import frc.team3256.warriorlib.auto.AutoModeEndedException;
import frc.team3256.warriorlib.auto.action.WaitAction;
import frc.team3256.warriorlib.auto.purepursuit.PurePursuitAction;
import frc.team3256.warriorlib.auto.purepursuit.PurePursuitTracker;
import frc.team3256.warriorlib.auto.purepursuit.ResetPursuitAction;

public class SlalomPathAutoMode extends AutoModeBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        PurePursuitTracker purePursuitTracker = PurePursuitTracker.getInstance();
        purePursuitTracker.setRobotTrack(DriveConstants.kRobotTrackWidth);
        purePursuitTracker.setPaths(Paths.getSlalomPath(), DriveConstants.lookaheadDistance);


        double startTime = Timer.getFPGATimestamp();
        runAction(new WaitAction(5.0));
        runAction(new ResetPursuitAction());
        DriveTrain.getInstance().setHighGear(true);
        runAction(new PurePursuitAction(0));
        SmartDashboard.putNumber("Total Auto Time: ", Timer.getFPGATimestamp() - startTime);
    }
}
