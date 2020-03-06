package frc.team3256.robot.auto.actions;

import frc.team3256.robot.helper.BallCounter;
import frc.team3256.robot.subsystems.Feeder;
import frc.team3256.robot.subsystems.Intake;
import frc.team3256.warriorlib.auto.action.Action;

public class FeederIndexAction implements Action {

    Feeder mFeeder = Feeder.getInstance();
    Intake mIntake = Intake.getInstance();
    BallCounter ballCounter = BallCounter.getInstance();
    private double endBallCount;

    public FeederIndexAction(double endBallCount) {
        this.endBallCount = endBallCount;
    }

    @Override
    public boolean isFinished() {
        return ballCounter.isFull() || ballCounter.getCount() >= endBallCount;
    }

    @Override
    public void update() {
        ballCounter.update(0);
        if (ballCounter.shouldFeed()) {
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_INDEX);
        } else {
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_IDLE);
        }
    }

    @Override
    public void done() {

    }

    @Override
    public void start() {
    }
}
