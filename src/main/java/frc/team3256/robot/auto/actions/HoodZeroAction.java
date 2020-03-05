package frc.team3256.robot.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.team3256.robot.subsystems.Hood;
import frc.team3256.warriorlib.auto.action.Action;

public class HoodZeroAction implements Action {

    private double startTime;
    private double wantedTime;

    public HoodZeroAction(double wantedTime) {
        this.wantedTime = wantedTime;
    }

    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime >= wantedTime;
    }

    @Override
    public void update() {

    }

    @Override
    public void done() {
    }

    @Override
    public void start() {
        startTime = Timer.getFPGATimestamp();
        Hood.getInstance().setPosSetpoint(0);
        Hood.getInstance().setWantedState(Hood.WantedState.WANTS_TO_POS);
    }
}
