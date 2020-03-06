package frc.team3256.robot.auto.actions;

import frc.team3256.robot.helper.BallCounter;
import frc.team3256.robot.subsystems.Intake;
import frc.team3256.warriorlib.auto.action.Action;

public class StartIntakeAction implements Action {

    Intake intake = Intake.getInstance();
    private double stopBallCount;

    public StartIntakeAction(double stopBallCount) {
        this.stopBallCount = stopBallCount;
    }

    @Override
    public boolean isFinished() {
        return BallCounter.getInstance().getCount() >= stopBallCount;
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
