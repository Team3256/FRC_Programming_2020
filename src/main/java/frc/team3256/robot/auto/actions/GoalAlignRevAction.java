package frc.team3256.robot.auto.actions;

import frc.team3256.robot.hardware.Limelight;
import frc.team3256.robot.subsystems.Flywheel;
import frc.team3256.robot.subsystems.Hood;
import frc.team3256.robot.subsystems.Turret;
import frc.team3256.warriorlib.auto.action.Action;

public class GoalAlignRevAction implements Action {

    Hood mHood = Hood.getInstance();
    Turret mTurret = Turret.getInstance();
    Flywheel mFlywheel = Flywheel.getInstance();
    Limelight limelight = Limelight.getInstance();

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void update() {
        limelight.update(0);
        limelight.calculateKinematics();
        double angle = limelight.calculateTau();
        mTurret.setTurretAutoAlignAngle(angle);
        mTurret.setWantedState(Turret.WantedState.WANTS_TO_AUTO_ALIGN);
        limelight.setWantedEndAngle(0*(Math.PI/180));
        mHood.setPosSetpoint(angleToHoodPos(limelight.getAngleToTarget()));
        mHood.setWantedState(Hood.WantedState.WANTS_TO_POS);
        mFlywheel.setVelocitySetpoint(velToFlywheelVel(limelight.getVelToTarget()));
        mFlywheel.setWantedState(Flywheel.WantedState.WANTS_TO_RUN);
    }

    @Override
    public void done() {

    }

    @Override
    public void start() {

    }

    public double angleToHoodPos(double angle) {
        return 0.3342*(angle*180/Math.PI) - 18.302;
    }

    public double velToFlywheelVel(double outputVel) {
        return 9.839*outputVel + 742.37;
    }

}
