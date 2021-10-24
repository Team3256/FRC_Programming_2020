package frc.team3256.robot.auto.modes;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.auto.actions.GoalAlignRevAction;
import frc.team3256.robot.auto.actions.MoveTurretAction;
import frc.team3256.robot.auto.actions.ShootAction;
import frc.team3256.robot.auto.paths.Paths;
import frc.team3256.robot.constants.DriveConstants;
import frc.team3256.robot.helper.BallCounter;
import frc.team3256.robot.subsystems.DriveTrain;
import frc.team3256.robot.subsystems.Flywheel;
import frc.team3256.robot.subsystems.Intake;
import frc.team3256.robot.subsystems.Turret;
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
        BallCounter.getInstance().setCount(3);
        Flywheel.getInstance().setReadyToShoot(false);
        Turret.getInstance().reset();
//        Intake.getInstance().setIntakeTogglingState(false);
//        Intake.getInstance().setWantedState(Intake.WantedState.WANTS_TO_TOGGLE_INTAKE);
//        runAction(new MoveTurretAction(25));

        runAction(new WaitAction(0.5));
        runAction(new ResetPursuitAction());
        DriveTrain.getInstance().setHighGear(true);
        runAction(new ParallelAction(Arrays.asList(new PurePursuitAction(0), new ShootAction())));
        runAction(new WaitAction(0.5));
        Flywheel.getInstance().setReadyToShoot(true);
        runAction(new ShootAction());
    }
}
