package frc.team3256.robot.teleop;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.helper.BallCounter;
import frc.team3256.robot.helper.ShootingKinematics;
import frc.team3256.robot.hardware.Limelight;
import frc.team3256.robot.subsystems.*;
import frc.team3256.robot.teleop.configs.ControlsInterface;
import frc.team3256.robot.teleop.configs.XboxControllerConfig;
import frc.team3256.warriorlib.control.DrivePower;
import frc.team3256.warriorlib.hardware.TalonFXUtil;

public class TeleopUpdater {
    private ControlsInterface controls = new XboxControllerConfig();
    private DriveTrain mDrivetrain = DriveTrain.getInstance();

    private Intake mIntake = Intake.getInstance();
    private Feeder mFeeder = Feeder.getInstance();
    private Flywheel mFlywheel = Flywheel.getInstance();
    private Turret mTurret = Turret.getInstance();
    private Hood mHood = Hood.getInstance();
    private Hanger mHanger = Hanger.getInstance();
    private Limelight limelight = Limelight.getInstance();
    private BallCounter ballCounter = BallCounter.getInstance();
    private boolean overrideFeeder = false;

    private boolean intakeUp = true;
    private boolean prevIntakeToggle = false;

    private boolean readyToHang = false;


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
        ballCounter.update(0);
        mHanger.update(0);


        //Drivetrain
        double throttle = controls.getThrottle();
        double turn = controls.getTurn();
        boolean quickTurn = controls.getQuickTurn();
        boolean shiftDown = controls.getLowGear();

        //Intake
        boolean unjam = controls.getUnjam();
        boolean intake = controls.getIntake();
        boolean exhaust = controls.getExhaust();

        //Feeder
        boolean feederForward = controls.getFeederForward();
        boolean feederBackward = controls.getFeederBackward();

        //Turret
        boolean manualTurretLeft = controls.manualTurretLeft();
        boolean manualTurretRight = controls.manualTurretRight();

        //Hood
        boolean manualHoodUp = controls.manualHoodUp();
        boolean manualHoodDown = controls.manualHoodDown();

        boolean getHangerUp = controls.getHangerUp();
        double getHangerDown = controls.getHangerDown();

        //TODO: Implement these for final
        boolean getAutoAlign = controls.getAutoAlign();
        boolean getRevUp = controls.getRevUp();
        boolean getFeederShoot = controls.getFeederShoot();
        boolean intakeToggle = controls.toggleIntake();

        if (SmartDashboard.getNumber("Ball Count Reset", 0) == 1) {
            ballCounter.setCount(0);
        }

        //Drivetrain Subsystem
        DrivePower drivePower = mDrivetrain.cheesyishDrive(throttle, turn, quickTurn);
        mDrivetrain.setPowerOpenLoop(drivePower.getLeft(), drivePower.getRight());
//        mDrivetrain.setHighGear(drivePower.getHighGear());

        //Intake Subsystem | Some Feeder interactions
        if (unjam) {
            mIntake.setWantedState(Intake.WantedState.WANTS_TO_UNJAM);
        } else if (intake) {
            mIntake.setWantedState(Intake.WantedState.WANTS_TO_INTAKE);
        } else if (exhaust) {
            overrideFeeder = true;
            mIntake.setWantedState(Intake.WantedState.WANTS_TO_EXHAUST);
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_BACKWARD);
        } else {
            overrideFeeder = false;
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_IDLE);
            mIntake.setWantedState(Intake.WantedState.WANTS_TO_STOP);
        }

        //Feeder Indexing Logic
        if (!overrideFeeder) {
            if (ballCounter.shouldFeed()) {
                mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_INDEX);
                mIntake.setWantedState(Intake.WantedState.WANTS_TO_STOP);
            } else {
                mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_IDLE);
            }
        }

        if (feederForward) {
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_FORWARD);
        } else if (feederBackward) {
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_BACKWARD);
        }

        //Turret Subsystem
        if (manualTurretLeft) {
            mTurret.setWantedState(Turret.WantedState.WANTS_TO_MANUAL_LEFT);
        } else if (manualTurretRight) {
            mTurret.setWantedState(Turret.WantedState.WANTS_TO_MANUAL_RIGHT);
        } else {
            mTurret.setWantedState(Turret.WantedState.WANTS_TO_IDLE);
        }

        //Hood Subsystem
        if (manualHoodUp) {
            mHood.setWantedState(Hood.WantedState.WANTS_TO_MANUAL_UP);
        } else if (manualHoodDown) {
            mHood.setWantedState(Hood.WantedState.WANTS_TO_MANUAL_DOWN);
        } else {
            mHood.setWantedState(Hood.WantedState.WANTS_TO_IDLE);
        }

        if(getHangerUp) {
            mHanger.setWantedState(Hanger.WantedState.WANTS_TO_HANGER_ACTUATE_RELEASE);
            readyToHang = true;
        }
        else if (readyToHang) {
            mHanger.setWinchDownPower(getHangerDown);
            mHanger.setWantedState(Hanger.WantedState.WANTS_TO_HANGER_DRIVE_DOWN);
        }
        else {
            mHanger.setWantedState(Hanger.WantedState.WANTS_TO_IDLE);
        }

        if(getAutoAlign) {
            //Auto Aligning Turret
            double angle = limelight.calculateTau();
            mTurret.setTurretAutoAlignAngle(angle);
            mTurret.setWantedState(Turret.WantedState.WANTS_TO_AUTO_ALIGN);

            //Auto Aligning Hood
            limelight.calculateKinematics();
            limelight.setWantedEndAngle(0*(Math.PI/180));
            mHood.setPosSetpoint(ShootingKinematics.angleToHoodPos(limelight.getAngleToTarget()));
            mHood.setWantedState(Hood.WantedState.WANTS_TO_POS);
        }

        if (getRevUp) {
            mFlywheel.setVelocitySetpoint(ShootingKinematics.outputVelToFlywheelVel(limelight.getVelToTarget()));
            mFlywheel.setWantedState(Flywheel.WantedState.WANTS_TO_RUN);
        } else {
            mFlywheel.setWantedState(Flywheel.WantedState.WANTS_TO_IDLE);
        }

        if(getFeederShoot) {
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_SHOOT);
            mIntake.setWantedState(Intake.WantedState.WANTS_TO_INTAKE);
        }

        if(intakeToggle && !prevIntakeToggle) {
            mIntake.setIntakeTogglingState(!intakeUp);
            mIntake.setWantedState(Intake.WantedState.WANTS_TO_TOGGLE_INTAKE);
            intakeUp = !intakeUp;
        }

        prevIntakeToggle = intakeToggle;
    }

}