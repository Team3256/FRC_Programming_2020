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

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void done() {

    }

    @Override
    public void start() {

    }
}
