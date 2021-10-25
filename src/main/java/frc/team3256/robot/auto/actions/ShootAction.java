package frc.team3256.robot.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.hardware.Limelight;
import frc.team3256.robot.helper.BallCounter;
import frc.team3256.robot.helper.ShootingKinematics;
import frc.team3256.robot.subsystems.*;
import frc.team3256.warriorlib.auto.action.Action;

import java.util.concurrent.Flow;

public class ShootAction implements Action {

    Feeder mFeeder = Feeder.getInstance();
    Intake mIntake = Intake.getInstance();
    BallCounter ballCounter = BallCounter.getInstance();

    Hood mHood = Hood.getInstance();
    Turret mTurret = Turret.getInstance();
    Flywheel mFlywheel = Flywheel.getInstance();
    Limelight limelight = Limelight.getInstance();

    double timeout = -1;
    double startTime = 0;

    public ShootAction() {
    }
    public ShootAction(double timeout) {
        this.timeout = timeout;
    }

    @Override
    public boolean isFinished() {
        if (Timer.getFPGATimestamp() - startTime > timeout && timeout != -1) {
            return true;
        }
        if (Flywheel.getInstance().getReadyToShoot()) {
            return ballCounter.isEmpty();
        }
        else return Flywheel.getInstance().atSetpointVelocity();
    }

    @Override
    public void update() {
        ballCounter.update(0);

        limelight.update(0);
        limelight.calculateKinematics();
        double angle = limelight.calculateTau();
        SmartDashboard.putNumber("angle", angle);
        mTurret.setTurretAutoAlignAngle(angle);
        mTurret.setWantedState(Turret.WantedState.WANTS_TO_AUTO_ALIGN);
        limelight.setWantedEndAngle(0*(Math.PI/180));
        mHood.setPosSetpoint(ShootingKinematics.angleToHoodPos(limelight.getAngleToTarget()));
        mHood.setWantedState(Hood.WantedState.WANTS_TO_POS);
        mFlywheel.setVelocitySetpoint(ShootingKinematics.outputVelToFlywheelVel(limelight.getVelToTarget()));
        mFlywheel.setWantedState(Flywheel.WantedState.WANTS_TO_RUN);


        SmartDashboard.putBoolean("Flywheel Auto Ready: ", mFlywheel.getReadyToShoot() && mFlywheel.atSetpointVelocity());
        SmartDashboard.putNumber("Flywheel Auto Velocity: ", mFlywheel.getVelocity());
        SmartDashboard.putNumber("Flywheel target velocity", ShootingKinematics.outputVelToFlywheelVel(limelight.getVelToTarget()));
        SmartDashboard.putNumber("Flywheel velocity difference", ShootingKinematics.outputVelToFlywheelVel(limelight.getVelToTarget()) - mFlywheel.getVelocity());

        if (mFlywheel.getReadyToShoot() && mFlywheel.atSetpointVelocity()){
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_AUTO_SHOOT);
            mIntake.setWantedState(Intake.WantedState.WANTS_TO_INTAKE);
        }
    }

    @Override
    public void done() {
        mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_IDLE);
        mIntake.setWantedState(Intake.WantedState.WANTS_TO_STOP);
        mFlywheel.setWantedState(Flywheel.WantedState.WANTS_TO_IDLE);
    }

    @Override
    public void start() {
        this.startTime = Timer.getFPGATimestamp();
    }
}
