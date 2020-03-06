package frc.team3256.robot.auto.modes;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.auto.actions.*;
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

public class RightDriveTrenchShootWallAutoMode extends AutoModeBase
{
    @Override
    protected void routine() throws AutoModeEndedException {
        PurePursuitTracker purePursuitTracker = PurePursuitTracker.getInstance();
        purePursuitTracker.setRobotTrack(DriveConstants.kRobotTrackWidth);
        purePursuitTracker.setPaths(Paths.getRightTrenchCollectWallAutoPath(), DriveConstants.lookaheadDistance);
        BallCounter.getInstance().setCount(3);
        Flywheel.getInstance().setReadyToShoot(false);
        Intake.getInstance().setIntakeTogglingState(false);
        Intake.getInstance().setWantedState(Intake.WantedState.WANTS_TO_TOGGLE_INTAKE);
        Turret.getInstance().reset();
        runAction(new MoveTurretAction(25));

        double startTime = Timer.getFPGATimestamp();
        runAction(new ResetPursuitAction());
        DriveTrain.getInstance().setHighGear(true);
        Flywheel.getInstance().setReadyToShoot(true);
        runAction(new ShootAction());
        Flywheel.getInstance().setReadyToShoot(false);
        runAction(new ParallelAction(Arrays.asList(new PurePursuitAction(0), new StartIntakeAction(), new FeederIndexAction(), new ShootAction())));
        runAction(new ParallelAction(Arrays.asList(new PurePursuitAction(1), new BackwardsIntakeAction(), new ShootAction())));
        runAction(new StopIntakeAction());
        Flywheel.getInstance().setReadyToShoot(true);
        runAction(new ShootAction());
        SmartDashboard.putNumber("Total Auto Time: ", Timer.getFPGATimestamp() - startTime);
    }
}
