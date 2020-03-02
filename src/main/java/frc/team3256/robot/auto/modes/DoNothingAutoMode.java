package frc.team3256.robot.auto.modes;

import frc.team3256.robot.auto.actions.FeederIndexAction;
import frc.team3256.robot.auto.actions.MoveTurretAction;
import frc.team3256.robot.helper.BallCounter;
import frc.team3256.robot.subsystems.Intake;
import frc.team3256.warriorlib.auto.AutoModeBase;
import frc.team3256.warriorlib.auto.AutoModeEndedException;

public class DoNothingAutoMode extends AutoModeBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        BallCounter.getInstance().setCount(0);
        Intake.getInstance().setWantedState(Intake.WantedState.WANTS_TO_INTAKE);
        runAction(new FeederIndexAction());
    }
}
