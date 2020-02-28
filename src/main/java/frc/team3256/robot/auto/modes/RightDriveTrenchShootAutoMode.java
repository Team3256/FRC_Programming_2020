package frc.team3256.robot.auto.modes;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.auto.actions.BackwardsIntakeAction;
import frc.team3256.robot.auto.actions.StartIntakeAction;
import frc.team3256.robot.auto.actions.StopIntakeAction;
import frc.team3256.robot.auto.paths.Paths;
import frc.team3256.robot.constants.DriveConstants;
import frc.team3256.robot.subsystems.Drivetrain;
import frc.team3256.warriorlib.auto.AutoModeBase;
import frc.team3256.warriorlib.auto.AutoModeEndedException;
import frc.team3256.warriorlib.auto.action.ParallelAction;
import frc.team3256.warriorlib.auto.action.WaitAction;
import frc.team3256.warriorlib.auto.purepursuit.PurePursuitAction;
import frc.team3256.warriorlib.auto.purepursuit.PurePursuitTracker;
import frc.team3256.warriorlib.auto.purepursuit.ResetPursuitAction;

import java.util.Arrays;

public class RightDriveTrenchShootAutoMode extends AutoModeBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        PurePursuitTracker purePursuitTracker = PurePursuitTracker.getInstance();
        purePursuitTracker.setRobotTrack(DriveConstants.kRobotTrackWidth);
        purePursuitTracker.setPaths(Paths.getRightTrenchCollectAutoPath(), DriveConstants.lookaheadDistance);

        double startTime = Timer.getFPGATimestamp();
        runAction(new ResetPursuitAction());
        Drivetrain.getInstance().setHighGear(true);
        runAction(new WaitAction(1.5));
        runAction(new ParallelAction(Arrays.asList(new PurePursuitAction(0), new StartIntakeAction())));
        runAction(new ParallelAction(Arrays.asList(new PurePursuitAction(1), new BackwardsIntakeAction())));
        runAction(new StopIntakeAction());
        SmartDashboard.putNumber("Total Auto Time: ", Timer.getFPGATimestamp() - startTime);
    }
}
