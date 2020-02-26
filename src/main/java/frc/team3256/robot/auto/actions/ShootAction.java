package frc.team3256.robot.auto.actions;

import frc.team3256.robot.hardware.Limelight;
import frc.team3256.robot.subsystems.Feeder;
import frc.team3256.robot.subsystems.Flywheel;
import frc.team3256.robot.subsystems.Intake;
import frc.team3256.warriorlib.auto.action.Action;

public class ShootAction implements Action {

    Flywheel mFlywheel = Flywheel.getInstance();
    Feeder mFeeder = Feeder.getInstance();
    Intake mIntake = Intake.getInstance();
    double ballCount;

    public ShootAction(double ballCount) {
        this.ballCount = ballCount;
    }

    @Override
    public boolean isFinished() {
        return ballCount <= 0;
    }

    @Override
    public void update() {
        if (mFlywheel.ballShot()) {
            ballCount--;
        }
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
    }
}
