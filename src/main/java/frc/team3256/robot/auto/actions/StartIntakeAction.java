package frc.team3256.robot.auto.actions;

import frc.team3256.robot.helper.BallCounter;
import frc.team3256.robot.subsystems.Intake;
import frc.team3256.warriorlib.auto.action.Action;
import frc.team3256.warriorlib.auto.purepursuit.PurePursuitTracker;

public class StartIntakeAction implements Action {

    Intake intake = Intake.getInstance();

    public StartIntakeAction() {
    }

    @Override
    public boolean isFinished() {
        return PurePursuitTracker.getInstance().isDone();
    }

    @Override
    public void update() {
    }

    @Override
    public void done() {
        intake.setWantedState(Intake.WantedState.WANTS_TO_STOP);
    }

    @Override
    public void start() {
        intake.update(0);
        intake.setWantedState(Intake.WantedState.WANTS_TO_INTAKE);
    }
}
