package frc.team3256.robot.teleop;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.constants.IDConstants;
import frc.team3256.robot.hardware.IRSensor;
import frc.team3256.robot.hardware.Limelight;
import frc.team3256.robot.subsystems.*;
import frc.team3256.robot.teleop.configs.ControlsInterface;
import frc.team3256.robot.teleop.configs.XboxControllerConfig;
import frc.team3256.warriorlib.control.DrivePower;

import static frc.team3256.robot.constants.LimelightConstants.gravAcceleration;

public class TeleopUpdater {
    private ControlsInterface controls = new XboxControllerConfig();
    private Drivetrain mDrivetrain = Drivetrain.getInstance();

    private Intake mIntake = Intake.getInstance();
    private Feeder mFeeder = Feeder.getInstance();
    private Flywheel mFlywheel = Flywheel.getInstance();
    private Turret mTurret = Turret.getInstance();
    private Hood mHood = Hood.getInstance();
    private IRSensor irSensor = new IRSensor(IDConstants.feederIRID);
    private Limelight limelight = Limelight.getInstance();
    private boolean overrideFeeder = false;
    public int ballCounter = 0;
    private boolean prevFeeding = false;
    private boolean feeding = false;

    private double gravAcc = 32.174;

    private static TeleopUpdater instance;
    public static TeleopUpdater getInstance() { return instance == null ? instance = new TeleopUpdater() : instance; }

    public void update() {
        mDrivetrain.update(0);
        mIntake.update(0);
        mFeeder.update(0);
        mFlywheel.update(0);
        mTurret.update(0);
        mHood.update(0);
        limelight.update(0);

        double throttle = controls.getThrottle();
        double turn = controls.getTurn();
        boolean quickTurn = controls.getQuickTurn();
        boolean shiftDown = controls.getLowGear();

        boolean unjam = controls.getUnjam();
        boolean intake = controls.getIntake();
        boolean exhaust = controls.getExhaust();
        boolean intakeToggle = controls.getIntakeToggle();

        boolean feederForward = controls.getFeederForward();
        boolean feederBackward = controls.getFeederBackward();

        boolean manualTurretLeft = controls.manualTurretLeft();
        boolean manualTurretRight = controls.manualTurretRight();

        boolean manualHoodUp = controls.manualHoodUp();
        boolean manualHoodDown = controls.manualHoodDown();

        boolean autoAlign = controls.autoAlignTurret();
        boolean autoAlignHood = controls.autoAlignHood();

        boolean getShoot = controls.getShoot();

        if (SmartDashboard.getNumber("Ball Count Reset", 0) == 1){
            ballCounter = 0;
        }

        //Drivetrain Subsystem
        DrivePower drivePower = mDrivetrain.cheesyishDrive(throttle, turn, quickTurn);
        mDrivetrain.setPowerOpenLoop(drivePower.getLeft(), drivePower.getRight());
        mDrivetrain.setHighGear(drivePower.getHighGear());

//        Intake - Feeder - Flywheel Subsystem
        if(unjam) {
            mIntake.setWantedState(Intake.WantedState.WANTS_TO_UNJAM);
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_FORWARD);
            mFlywheel.setWantedState(Flywheel.WantedState.WANTS_TO_IDLE);
        }
        else if(intake) {
            mIntake.setWantedState(Intake.WantedState.WANTS_TO_INTAKE);
            mFlywheel.setWantedState(Flywheel.WantedState.WANTS_TO_IDLE);
        }
        else if(exhaust) {
            overrideFeeder = true;
            mIntake.setWantedState(Intake.WantedState.WANTS_TO_EXHAUST);
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_BACKWARD);
            mFlywheel.setWantedState(Flywheel.WantedState.WANTS_TO_IDLE);
        }
//        else if(getShoot) {
//            overrideFeeder = true;
//            mFlywheel.setWantedState(Flywheel.WantedState.WANTS_TO_RUN);
//            if(mFlywheel.getVelocity() >= 6000) {
//                mIntake.setWantedState(Intake.WantedState.WANTS_TO_INTAKE);
//                mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_SHOOT);
//            }
//        }
        else {
            mIntake.setWantedState(Intake.WantedState.WANTS_TO_STOP);
            overrideFeeder = false;
//            mFlywheel.setWantedState(Flywheel.WantedState.WANTS_TO_IDLE);
        }

//        Feeder Indexing Logic

        feeding = !irSensor.isIntact();
        if (feeding && !overrideFeeder) {
            if(!prevFeeding) {
                ballCounter++;
            }
            if (ballCounter != 5) {
                mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_INDEX);
                mIntake.setWantedState(Intake.WantedState.WANTS_TO_STOP);
            }
            else {
                mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_IDLE);
            }
        }
        else if (!feeding && !overrideFeeder) {
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_IDLE);
        }

        if(feederForward) {
            System.out.println("D-PAD UP");
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_FORWARD);
        }
        else if(feederBackward) {
            System.out.println("D-PAD DOWN");
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_BACKWARD);
        }

        //Turret Subsystem
        if(manualTurretLeft) {
            mTurret.setWantedState(Turret.WantedState.WANTS_TO_MANUAL_LEFT);
        }
        else if(manualTurretRight) {
            mTurret.setWantedState(Turret.WantedState.WANTS_TO_MANUAL_RIGHT);
        }
        else {
            mTurret.setWantedState(Turret.WantedState.WANTS_TO_IDLE);
        }

        //Hood Subsystem
        if(manualHoodUp) {
            mHood.setWantedState(Hood.WantedState.WANTS_TO_MANUAL_UP);
        }
        else if (manualHoodDown) {
            mHood.setWantedState(Hood.WantedState.WANTS_TO_MANUAL_DOWN);
        }
        else {
            mHood.setWantedState(Hood.WantedState.WANTS_TO_IDLE);
        }
        prevFeeding = feeding;

//        if(setposHood) {
//            double hoodPos = SmartDashboard.getNumber("hood pos", 0);
//            mHood.setPosSetpoint(hoodPos);
//            mHood.setWantedState(Hood.WantedState.WANTS_TO_POS);
//        }

//        if(getShoot) {
//            overrideFeeder = true;
//            double vel = SmartDashboard.getNumber("wanted vel", 0);
//            mFlywheel.setVelocitySetpoint(vel);
//            mFlywheel.setWantedState(Flywheel.WantedState.WANTS_TO_RUN);
//            if(autoAlign) {
//                mIntake.setWantedState(Intake.WantedState.WANTS_TO_INTAKE);
//                mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_SHOOT);
//            }
//        } else {
//            mFlywheel.setWantedState(Flywheel.WantedState.WANTS_TO_IDLE);
//            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_IDLE);
//        }

        if (getShoot) {
            mFlywheel.setVelocitySetpoint(velToFlywheelVel(limelight.getVelToTarget()));
            SmartDashboard.putNumber("wanted vel", velToFlywheelVel(limelight.getVelToTarget()));
            mFlywheel.setWantedState(Flywheel.WantedState.WANTS_TO_RUN);
        }
        else {
            mFlywheel.setWantedState(Flywheel.WantedState.WANTS_TO_IDLE);
        }

        if (autoAlign) {
            double angle = limelight.calculateTau();
            mTurret.setTurretAutoAlignAngle(angle);
            mTurret.setWantedState(Turret.WantedState.WANTS_TO_AUTO_ALIGN);
        }
        if (autoAlignHood) {
            limelight.calculateKinematics();
            limelight.setWantedEndAngle(0*(Math.PI/180));
            mHood.setPosSetpoint(angleToHoodPos(limelight.getAngleToTarget() - 0*Math.PI/180));
            mHood.setWantedState(Hood.WantedState.WANTS_TO_POS);
//            if(autoAlign) {
//                mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_SHOOT);
//            }
        }

        //BELOW IS FOR HOOD TESTING/TUNING PURPOSES...ACTUAL INTAKE TOGGLE LOGIC ADDED LATER

        if (intakeToggle) {
            double wantedHoodPos = SmartDashboard.getNumber("hood pos", 0);
            mHood.setPosSetpoint(wantedHoodPos);
            mHood.setWantedState(Hood.WantedState.WANTS_TO_POS);
        }
    }

    public double angleToHoodPos(double angle) {
        return 0.3342*(angle*180/Math.PI) - 18.302;
    }

    public double velToFlywheelVel(double outputVel) {
        return 9.839*outputVel + 742.37;
    }

    public int getBallCounter() {
        return ballCounter;
    }

    public static void main(String args[]) {
        double heightDif = 80;
        double wantedEndAngle = 0;
        double getDistanceToInner = 250;
        double timeToTarget = Math.sqrt(2/gravAcceleration*(heightDif - Math.tan(wantedEndAngle) * getDistanceToInner));
        double angleToTarget = Math.atan((heightDif + .5 * gravAcceleration * timeToTarget * timeToTarget)/getDistanceToInner);
        double velToTarget = getDistanceToInner/timeToTarget/Math.cos(angleToTarget);
        System.out.println(angleToTarget*180/Math.PI);
        System.out.println(velToTarget);
    }

}