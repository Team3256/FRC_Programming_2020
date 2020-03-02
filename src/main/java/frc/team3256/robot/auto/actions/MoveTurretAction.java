package frc.team3256.robot.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.team3256.robot.subsystems.Turret;
import frc.team3256.warriorlib.auto.action.Action;

public class MoveTurretAction implements Action {

    double startTime;
    double actionTime;
    Turret mTurret = Turret.getInstance();

    public MoveTurretAction(double actionTime) {
        this.actionTime = actionTime;
    }

    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp()-startTime > actionTime;
    }

    @Override
    public void update() {
        mTurret.setAutoTurretSpeed(-1.0);
    }

    @Override
    public void done() {
        mTurret.setAutoTurretSpeed(0);
    }

    @Override
    public void start() {
        startTime = Timer.getFPGATimestamp();
    }
}
