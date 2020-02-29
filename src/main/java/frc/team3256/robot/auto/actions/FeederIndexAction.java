package frc.team3256.robot.auto.actions;

import frc.team3256.robot.constants.IDConstants;
import frc.team3256.robot.hardware.IRSensor;
import frc.team3256.robot.subsystems.Feeder;
import frc.team3256.robot.subsystems.Intake;
import frc.team3256.warriorlib.auto.action.Action;

public class FeederIndexAction implements Action {

    Feeder mFeeder = Feeder.getInstance();
    Intake mIntake = Intake.getInstance();
    public int ballCounter = 0;
    private boolean prevFeeding = false;
    private boolean feeding = false;
    private IRSensor irSensor = IRSensor.getInstance();

    @Override
    public boolean isFinished() {
        if (ballCounter == 5) {
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        feeding = !irSensor.isIntact();
        if (feeding) {
            if(!prevFeeding) {
                ballCounter++;
            }
            if (ballCounter != 5) {
                mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_INDEX);
                mIntake.setWantedState(Intake.WantedState.WANTS_TO_STOP);
            }
            else {
                mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_IDLE);
            }
        }
        else if (!feeding) {
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_IDLE);
        }
        prevFeeding = feeding;
    }

    @Override
    public void done() {

    }

    @Override
    public void start() {
    }
}
