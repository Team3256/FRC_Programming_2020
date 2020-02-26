package frc.team3256.robot.auto.modes;

import frc.team3256.robot.auto.actions.FeederIndexAction;
import frc.team3256.warriorlib.auto.AutoModeBase;
import frc.team3256.warriorlib.auto.AutoModeEndedException;

public class DoNothingAutoMode extends AutoModeBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        runAction(new FeederIndexAction());
    }
}