package frc.team3256.robot.teleop;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.constants.LimelightConstants;
import frc.team3256.robot.hardware.IRSensor;
import frc.team3256.robot.subsystems.*;
import frc.team3256.robot.teleop.configs.ControlsInterface;
import frc.team3256.robot.teleop.configs.XboxControllerConfig;
import frc.team3256.warriorlib.control.DrivePower;

import static frc.team3256.robot.constants.TurretConstants.turretHeight;

//----------EVERYTHING IN THIS CLASS IS FOR THE PURPOSES OF MANUAL TESTING----------

public class TeleopUpdater {
    private ControlsInterface controls = new XboxControllerConfig();

    private Drivetrain mDrivetrain = Drivetrain.getInstance();

    private IntakeStateMachineTest mIntake = IntakeStateMachineTest.getInstance();
    private FeederStateMachineTest mFeeder = FeederStateMachineTest.getInstance();
    private FlywheelStateMachineTest mFlywheel = FlywheelStateMachineTest.getInstance();
    private TurretStateMachineTest mTurret = TurretStateMachineTest.getInstance();
    private HoodStateMachineTest mHood = HoodStateMachineTest.getInstance();
    private IRSensor irSensor = new IRSensor(FeederConstants.kFeederIRID);
    private boolean overrideFeeder = false;
    public int ballCounter = 0;
    private boolean prevFeeding = false;
    private boolean feeding = false;

    private double gravAcc = 32.174;

    private static TeleopUpdaterStateMachine instance;
    public static TeleopUpdaterStateMachine getInstance() { return instance == null ? instance = new TeleopUpdaterStateMachine() : instance; }

    public void update() {
        mDrivetrain.update(0);
        mIntake.update(0);
        mFeeder.update(0);
        mFlywheel.update(0);
        mTurret.update(0);
        mHood.update(0);

        double throttle = controls.getThrottle();
        double turn = controls.getTurn();
        boolean quickTurn = controls.getQuickTurn();
        boolean shiftDown = controls.getLowGear();

        boolean unjam = controls.getUnjam();
        boolean intake = controls.getIntake();
        boolean exhaust = controls.getExhaust();

        boolean feederForward = controls.getFeederForward();
        boolean feederBackward = controls.getFeederBackward();

        boolean manualTurretLeft = controls.manualTurretLeft();
        boolean manualTurretRight = controls.manualTurretRight();

        boolean manualHoodUp = controls.manualHoodUp();
        boolean manualHoodDown = controls.manualHoodDown();

        boolean getShoot = controls.getShoot();

        //Drivetrain Subsystem
        DrivePower drivePower = mDrivetrain.cheesyishDrive(throttle, turn, quickTurn);
        mDrivetrain.setPowerOpenLoop(drivePower.getLeft(), drivePower.getRight());
        mDrivetrain.setHighGear(drivePower.getHighGear());

        //Intake - Feeder - Flywheel Subsystem
        if(unjam) {
            mIntake.setWantedState(IntakeStateMachineTest.WantedState.WANTS_TO_UNJAM);
            mFeeder.setWantedState(FeederStateMachineTest.WantedState.WANTS_TO_RUN_FORWARD);
            mFlywheel.setWantedState(FlywheelStateMachineTest.WantedState.WANTS_TO_IDLE);
        }
        else if(intake) {
            mIntake.setWantedState(IntakeStateMachineTest.WantedState.WANTS_TO_INTAKE);
            mFlywheel.setWantedState(FlywheelStateMachineTest.WantedState.WANTS_TO_IDLE);
        }
        else if(exhaust) {
            overrideFeeder = true;
            mIntake.setWantedState(IntakeStateMachineTest.WantedState.WANTS_TO_EXHAUST);
            mFeeder.setWantedState(FeederStateMachineTest.WantedState.WANTS_TO_RUN_BACKWARD);
            mFlywheel.setWantedState(FlywheelStateMachineTest.WantedState.WANTS_TO_IDLE);
        }
        else if(getShoot) {
            overrideFeeder = true;
            mFlywheel.setWantedState(FlywheelStateMachineTest.WantedState.WANTS_TO_RUN);
            if(mFlywheel.getVelocity() >= 6000) {
                mIntake.setWantedState(IntakeStateMachineTest.WantedState.WANTS_TO_INTAKE);
                mFeeder.setWantedState(FeederStateMachineTest.WantedState.WANTS_TO_SHOOT);
            }
        }
        else {
            mIntake.setWantedState(IntakeStateMachineTest.WantedState.WANTS_TO_STOP);
            overrideFeeder = false;
            mFlywheel.setWantedState(FlywheelStateMachineTest.WantedState.WANTS_TO_IDLE);
        }

        //Feeder Indexing Logic

        System.out.println(irSensor.isIntact());

        feeding = !irSensor.isIntact();
        if (feeding && !overrideFeeder) {
            if(!prevFeeding) {
                ballCounter++;
            }
            if (ballCounter != 5) {
                mFeeder.setWantedState(FeederStateMachineTest.WantedState.WANTS_TO_RUN_FORWARD);
            }
        }
        else if (!feeding && !overrideFeeder) {
            mFeeder.setWantedState(FeederStateMachineTest.WantedState.WANTS_TO_IDLE);
        }

        if(feederForward) {
            System.out.println("D-PAD UP");
            mFeeder.setWantedState(FeederStateMachineTest.WantedState.WANTS_TO_RUN_FORWARD);
        }
        else if(feederBackward) {
            System.out.println("D-PAD DOWN");
            mFeeder.setWantedState(FeederStateMachineTest.WantedState.WANTS_TO_RUN_BACKWARD);
        }

        //Turret Subsystem
        if(manualTurretLeft) {
            mTurret.setWantedState(TurretStateMachineTest.WantedState.WANTS_TO_MANUAL_LEFT);
        }
        else if(manualTurretRight) {
            mTurret.setWantedState(TurretStateMachineTest.WantedState.WANTS_TO_MANUAL_RIGHT);
        }
        else {
            mTurret.setWantedState(TurretStateMachineTest.WantedState.WANTS_TO_IDLE);
        }

        //Hood Subsystem
        if(manualHoodUp) {
            mHood.setWantedState(HoodStateMachineTest.WantedState.WANTS_TO_MANUAL_UP);
        }
        else if (manualHoodDown) {
            mHood.setWantedState(HoodStateMachineTest.WantedState.WANTS_TO_MANUAL_DOWN);
        }
        else {
            mHood.setWantedState(HoodStateMachineTest.WantedState.WANTS_TO_IDLE);
        }
        SmartDashboard.putNumber("Hood Encoder", mHood.getHoodEncoder());
        SmartDashboard.putNumber("turret encoder", mTurret.getPosition());
        prevFeeding = feeding;
    }

    public int getBallCounter() {
        return ballCounter;
    }

    public double calcAngle(double distance, double wantedEndAngle) {
        double heightDif = LimelightConstants.targetMidHeight - turretHeight;
        double t = Math.sqrt(2/gravAcc*(heightDif + Math.tan(wantedEndAngle) * distance));
        double angle = Math.tan((heightDif - .5 * gravAcc * t * t)/(12));
        return angle;
    }

    public double calcVel(double distance, double time, double startAngle) {
        double vel = distance/time/Math.cos(startAngle);
        return vel;
    }

}