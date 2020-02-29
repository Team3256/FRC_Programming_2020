package frc.team3256.robot.auto.modes;

import frc.team3256.robot.auto.actions.GoalAlignRevAction;
import frc.team3256.robot.auto.actions.ShootAction;
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

public class RightDriveShootAutoMode extends AutoModeBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        PurePursuitTracker purePursuitTracker = PurePursuitTracker.getInstance();
        purePursuitTracker.setRobotTrack(DriveConstants.kRobotTrackWidth);
        purePursuitTracker.setPaths(Paths.getRightShootAutoPath(),  DriveConstants.lookaheadDistance);

        runAction(new WaitAction(0.5));
        runAction(new ResetPursuitAction());
        DriveTrain.getInstance().setHighGear(true);
        runAction(new ParallelAction(Arrays.asList(new PurePursuitAction(0), new GoalAlignRevAction(2))));
        runAction(new WaitAction(0.5));
        runAction(new ParallelAction(Arrays.asList(new GoalAlignRevAction(3), new ShootAction(3))));
    }
}
