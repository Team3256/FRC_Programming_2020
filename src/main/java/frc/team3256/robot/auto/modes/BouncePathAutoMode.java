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

public class BouncePathAutoMode extends AutoModeBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        PurePursuitTracker tracker = PurePursuitTracker.getInstance();
        tracker.setRobotTrack(DriveConstants.kRobotTrackWidth);
        tracker.setPaths(Paths.getBouncePath(), DriveConstants.lookaheadDistance);
//        tracker.setClosestPointLimit(4);

        runAction(new WaitAction(1.0));

        double startTime = Timer.getFPGATimestamp();

        runAction(new ResetPursuitAction());
        DriveTrain.getInstance().setHighGear(true);
        DriveTrain.getInstance().setBrakeMode();
        runAction(new PurePursuitAction(0));
        runAction(new PurePursuitAction(1));
        runAction(new PurePursuitAction(2));
        runAction(new PurePursuitAction(3));

        SmartDashboard.putNumber("Total Auto Time: ", Timer.getFPGATimestamp() - startTime);

    }
}
