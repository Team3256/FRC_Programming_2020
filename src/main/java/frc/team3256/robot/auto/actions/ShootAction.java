package frc.team3256.robot.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.team3256.robot.BallCounter;
import frc.team3256.robot.subsystems.Feeder;
import frc.team3256.robot.subsystems.Flywheel;
import frc.team3256.robot.subsystems.Intake;
import frc.team3256.warriorlib.auto.action.Action;

public class ShootAction implements Action {

    Feeder mFeeder = Feeder.getInstance();
    Intake mIntake = Intake.getInstance();
    Flywheel mFlywheel = Flywheel.getInstance();
    BallCounter ballCounter = BallCounter.getInstance();
    double shootTime;
    double startTime;
    double elapsedTime;

    public ShootAction(double shootTime) {
        this.shootTime = shootTime;
    }

    @Override
    public boolean isFinished() {
        return elapsedTime >= shootTime;
    }

    @Override
    public void update() {
        if (mFlywheel.ballShot()) {
            ballCounter.decrement();
        }
        elapsedTime = Timer.getFPGATimestamp()-startTime;
    }

    @Override
    public void done() {
        mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_IDLE);
        mIntake.setWantedState(Intake.WantedState.WANTS_TO_STOP);
    }

    @Override
    public void start() {
        mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_SHOOT);
        mIntake.setWantedState(Intake.WantedState.WANTS_TO_INTAKE);
        startTime = Timer.getFPGATimestamp();
    }
}
