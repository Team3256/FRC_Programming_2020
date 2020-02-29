package frc.team3256.robot.auto.actions;

import frc.team3256.robot.subsystems.Hood;
import frc.team3256.warriorlib.auto.action.Action;

public class ZeroHoodAction implements Action {

    private Hood mHood = Hood.getInstance();

    @Override
    public boolean isFinished() {
        return mHood.isZeroed();
    }

    @Override
    public void update() {
        mHood.setWantedState(Hood.WantedState.WANTS_TO_MANUAL_DOWN);
    }

    @Override
    public void done() {
        mHood.setWantedState(Hood.WantedState.WANTS_TO_IDLE);
    }

    @Override
    public void start() {
    }
}
