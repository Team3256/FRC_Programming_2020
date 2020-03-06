package frc.team3256.robot.auto.modes;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.auto.actions.FeederIndexAction;
import frc.team3256.robot.auto.actions.MoveTurretAction;
import frc.team3256.robot.auto.actions.ShootAction;
import frc.team3256.robot.auto.actions.StartIntakeAction;
import frc.team3256.robot.auto.paths.Paths;
import frc.team3256.robot.constants.DriveConstants;
import frc.team3256.robot.helper.BallCounter;
import frc.team3256.robot.subsystems.*;
import frc.team3256.warriorlib.auto.AutoModeBase;
import frc.team3256.warriorlib.auto.AutoModeEndedException;
import frc.team3256.warriorlib.auto.action.ParallelAction;
import frc.team3256.warriorlib.auto.action.WaitAction;
import frc.team3256.warriorlib.auto.purepursuit.PurePursuitAction;
import frc.team3256.warriorlib.auto.purepursuit.PurePursuitTracker;
import frc.team3256.warriorlib.auto.purepursuit.ResetPursuitAction;

import java.util.Arrays;

public class StealTwoBallAutoMode extends AutoModeBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        PurePursuitTracker purePursuitTracker = PurePursuitTracker.getInstance();
        purePursuitTracker.setRobotTrack(DriveConstants.kRobotTrackWidth);
        purePursuitTracker.setPaths(Paths.getStealTwoBallAutoPath(), DriveConstants.lookaheadDistance);
        BallCounter.getInstance().setCount(3);
        Flywheel.getInstance().setReadyToShoot(false);
        Intake.getInstance().setIntakeTogglingState(false);
        Intake.getInstance().setWantedState(Intake.WantedState.WANTS_TO_TOGGLE_INTAKE);
        Turret.getInstance().reset();
        runAction(new MoveTurretAction(25));

        runAction(new WaitAction(0.5));
        runAction(new ResetPursuitAction());
        DriveTrain.getInstance().setHighGear(true);
        runAction(new ParallelAction(Arrays.asList(new PurePursuitAction(0), new StartIntakeAction(5), new FeederIndexAction(5))));
        runAction(new WaitAction(1.0));
        runAction(new PurePursuitAction(1));
    }
}
