package frc.team3256.robot.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.team3256.robot.subsystems.Turret;
import frc.team3256.warriorlib.auto.action.Action;

public class MoveTurretAction implements Action {
    Turret mTurret = Turret.getInstance();
    private double startTime;
    private double time;
    private double angleSetpoint;
    private boolean isFinished = false;

    public MoveTurretAction(double angleSetpoint) {
//        this.time = time;
        this.angleSetpoint = angleSetpoint;
    }

    @Override
    public boolean isFinished() {
//        return Timer.getFPGATimestamp() - startTime >= time;
        return isFinished;
    }

    @Override
    public void update() {
//        mTurret.setAutoTurretSpeed(0.7);
//        mTurret.setPosition(angleSetpoint);
        if (mTurret.getPosition() >= mTurret.angleToEncoder(angleSetpoint)) {
            isFinished = true;
        }
        else {
            mTurret.setAutoTurretSpeed(-1.0);
        }
    }

    @Override
    public void done() {
        mTurret.setAutoTurretSpeed(0);
    }

    @Override
    public void start() {
//        startTime = Timer.getFPGATimestamp();
    }
}
