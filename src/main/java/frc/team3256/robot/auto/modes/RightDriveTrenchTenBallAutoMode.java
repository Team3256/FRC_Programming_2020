package frc.team3256.robot.auto.modes;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.auto.actions.StartIntakeAction;
import frc.team3256.robot.auto.actions.StopIntakeAction;
import frc.team3256.robot.auto.paths.Paths;
import frc.team3256.robot.constants.DriveConstants;
import frc.team3256.robot.subsystems.DriveTrain;
import frc.team3256.warriorlib.auto.AutoModeBase;
import frc.team3256.warriorlib.auto.AutoModeEndedException;
import frc.team3256.warriorlib.auto.action.ParallelAction;
import frc.team3256.warriorlib.auto.action.WaitAction;
import frc.team3256.warriorlib.auto.purepursuit.PurePursuitAction;
import frc.team3256.warriorlib.auto.purepursuit.PurePursuitTracker;
import frc.team3256.warriorlib.auto.purepursuit.ResetPursuitAction;

import java.util.Arrays;

public class RightDriveTrenchTenBallAutoMode extends AutoModeBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        PurePursuitTracker purePursuitTracker = PurePursuitTracker.getInstance();
        purePursuitTracker.setRobotTrack(DriveConstants.kRobotTrackWidth);
        purePursuitTracker.setPaths(Paths.getRightTrenchCollectTenBallAutoPath(), DriveConstants.lookaheadDistance);

        double startTime = Timer.getFPGATimestamp();
        runAction(new WaitAction(0.5));
        runAction(new ResetPursuitAction());
        DriveTrain.getInstance().setHighGear(true);
        runAction(new ParallelAction(Arrays.asList(new PurePursuitAction(0), new StartIntakeAction())));
        runAction(new WaitAction(10.0));
        runAction(new ParallelAction(Arrays.asList(new PurePursuitAction(1), new StartIntakeAction())));
        runAction(new StopIntakeAction());
        runAction(new WaitAction(10.0));
        runAction(new ParallelAction(Arrays.asList(new PurePursuitAction(2), new StartIntakeAction())));
        runAction(new WaitAction(0.5));
        runAction(new ParallelAction(Arrays.asList(new PurePursuitAction(3), new StartIntakeAction())));
        runAction(new StopIntakeAction());
        SmartDashboard.putNumber("Total Auto Time: ", Timer.getFPGATimestamp() - startTime);
    }
}
