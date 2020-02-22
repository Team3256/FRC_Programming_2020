package frc.team3256.robot.auto.actions;

import frc.team3256.robot.subsystems.Intake;
import frc.team3256.warriorlib.auto.action.Action;

public class BackwardsAutoIntakeAction implements Action {
    Intake intake = Intake.getInstance();

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void update() {

    }

    @Override
    public void done() {

    }

    @Override
    public void start() {
        intake.update(0);
        intake.setWantedState(Intake.WantedState.WANTS_TO_AUTO_BACKWARDS_INTAKE);
    }
}
