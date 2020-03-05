package frc.team3256.robot.auto.actions;

import frc.team3256.robot.subsystems.Turret;
import frc.team3256.warriorlib.auto.action.Action;

public class MoveTurretAction implements Action {
    Turret mTurret = Turret.getInstance();
    private double angleSetpoint;
    private boolean isFinished = false;

    public MoveTurretAction(double angleSetpoint) {
        this.angleSetpoint = angleSetpoint;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void update() {
        mTurret.setPosition(angleSetpoint);
        if (mTurret.getPosition() >= mTurret.angleToEncoder(angleSetpoint)) {
            isFinished = true;
        }
    }

    @Override
    public void done() {
        mTurret.setAutoTurretSpeed(0);
    }

    @Override
    public void start() {
    }
}
