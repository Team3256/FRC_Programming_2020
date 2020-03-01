package frc.team3256.robot.auto.actions;

import frc.team3256.robot.hardware.Limelight;
import frc.team3256.robot.helper.BallCounter;
import frc.team3256.robot.helper.ShootingKinematics;
import frc.team3256.robot.subsystems.*;
import frc.team3256.warriorlib.auto.action.Action;

public class ShootAction implements Action {

    Feeder mFeeder = Feeder.getInstance();
    Intake mIntake = Intake.getInstance();
    BallCounter ballCounter = BallCounter.getInstance();

    Hood mHood = Hood.getInstance();
    Turret mTurret = Turret.getInstance();
    Flywheel mFlywheel = Flywheel.getInstance();
    Limelight limelight = Limelight.getInstance();

    public ShootAction() {

        ballCounter.update(0);

        limelight.update(0);
        limelight.calculateKinematics();
        double angle = limelight.calculateTau();
        mTurret.setTurretAutoAlignAngle(angle);
        mTurret.setWantedState(Turret.WantedState.WANTS_TO_AUTO_ALIGN);
        limelight.setWantedEndAngle(0*(Math.PI/180));
        mHood.setPosSetpoint(ShootingKinematics.angleToHoodPos(limelight.getAngleToTarget()));
        mHood.setWantedState(Hood.WantedState.WANTS_TO_POS);
        mFlywheel.setVelocitySetpoint(ShootingKinematics.outputVelToFlywheelVel(limelight.getVelToTarget()));
        mFlywheel.setWantedState(Flywheel.WantedState.WANTS_TO_RUN);

        if (mFlywheel.atSetpointVelocity() && mTurret.atAngleSetpoint() && mHood.atHoodSetpoint() && Flywheel.getInstance().getReadyToShoot()) {
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_SHOOT);
            mIntake.setWantedState(Intake.WantedState.WANTS_TO_INTAKE);
        }

    }

    @Override
    public boolean isFinished() {
        return ballCounter.isEmpty();
    }

    @Override
    public void update() {
    }

    @Override
    public void done() {
        mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_IDLE);
        mIntake.setWantedState(Intake.WantedState.WANTS_TO_STOP);
        mFlywheel.setWantedState(Flywheel.WantedState.WANTS_TO_IDLE);
    }

    @Override
    public void start() {
    }
}
