package frc.team3256.robot.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.team3256.robot.helper.ShootingKinematics;
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
    double elapsedTime;
    double revTime;
    double startTime;

    public GoalAlignRevAction(double revTime) {
        this.revTime = revTime;
    }

    @Override
    public boolean isFinished() {
        return elapsedTime >= revTime;
    }

    @Override
    public void update() {
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

        elapsedTime = Timer.getFPGATimestamp() - startTime;
    }

    @Override
    public void done() {

    }

    @Override
    public void start() {
        startTime = Timer.getFPGATimestamp();
    }

}
