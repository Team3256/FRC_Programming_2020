package frc.team3256.robot.teleop;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.constants.DriveConstants;
import frc.team3256.robot.hardware.IRSensors;
import frc.team3256.robot.helper.BallCounter;
import frc.team3256.robot.helper.ShootingKinematics;
import frc.team3256.robot.hardware.Limelight;
import frc.team3256.robot.subsystems.*;
import frc.team3256.robot.teleop.configs.ControlsInterface;
import frc.team3256.robot.teleop.configs.XboxControllerConfig;
import frc.team3256.warriorlib.control.DrivePower;

public class TeleopUpdater {
    private ControlsInterface controls = new XboxControllerConfig();

    private DriveTrain driveTrain = DriveTrain.getInstance();
    private Intake intake = Intake.getInstance();
    private Feeder feeder = Feeder.getInstance();
    private Flywheel flywheel = Flywheel.getInstance();
    private Turret turret = Turret.getInstance();
    private Hood hood = Hood.getInstance();
    //private Hanger hanger = Hanger.getInstance();
    private Limelight limelight = Limelight.getInstance();
    private BallCounter ballCounter = BallCounter.getInstance();

    private boolean overrideFeeder = false;
    private boolean intakeUp = true;
    private boolean prevIntakeToggle = false;
    private boolean prevDrivetrainReverseToggle = false;
    private boolean hangerRelease = true;
    private boolean prevHangerPancakesToggle = false;
    private boolean wantsToAutoIndex = true;

    private boolean readyToHang = false;

    //private DoubleSolenoid hangerPancake;

    private static TeleopUpdater instance;
    public static TeleopUpdater getInstance() { return instance == null ? instance = new TeleopUpdater() : instance; }

    public void update() {
        driveTrain.update(0);
        intake.update(0);
        feeder.update(0);
        flywheel.update(0);
        turret.update(0);
        hood.update(0);
        limelight.update(0);
        ballCounter.update(0);
        //hanger.update(0);
        //hangerPancake = new DoubleSolenoid(1, 6);

        //Drivetrain
        double throttle = controls.getThrottle();
        double turn = controls.getTurn();
        boolean quickTurn = controls.getQuickTurn();

        //Intake
        boolean unjam = controls.getUnjam();
        boolean intakePressed = controls.getIntake();
        boolean exhaust = controls.getExhaust();

        //Feeder
        boolean feederForward = controls.getFeederForward();
        boolean feederBackward = controls.getFeederBackward();
        boolean ballCountReset = controls.getBallCountReset();

        //Turret
        boolean manualTurretLeft = controls.manualTurretLeft();
        boolean manualTurretRight = controls.manualTurretRight();
        boolean getAlignToOuter = controls.getOuterGoalAlign();

        //Hood
        boolean manualHoodUp = controls.manualHoodUp();
        boolean manualHoodDown = controls.manualHoodDown();

        boolean hangerPancakesToggle = controls.toggleHangerPancakes();
        double getHangerDown = controls.getHangerDown();

        //TODO: Implement these for final
        boolean getAutoAlign = controls.getAutoAlign();
        boolean getRevUp = controls.getRevUp();
        boolean getFeederShoot = controls.getFeederShoot();
        boolean getDriverShoot = controls.getDriverShoot();
        boolean intakeToggle = controls.toggleIntake();
        boolean drivetrainReverseToggle = controls.getToggleReverseDrivetrain();

        if (SmartDashboard.getNumber("Ball Count Reset", 0) == 1) {
            ballCounter.setCount(0);
        }

        SmartDashboard.putBoolean("Ball IR", IRSensors.getInstance().isFeederIRIntact());

        // DriveTrain ---------------------------------------------------------------------------------------
        DrivePower drivePower = driveTrain.cheesyishDrive(throttle, turn, quickTurn);
        driveTrain.setPowerOpenLoop(drivePower.getLeft(), drivePower.getRight());
        driveTrain.setHighGear(!drivePower.getHighGear());

        //TODO: Dylan - This is a mess, we probably want to get rid of any bad logic here
        //Intake Subsystem | Some Feeder interactions
        if (unjam) {
            this.intake.setWantedState(Intake.WantedState.WANTS_TO_UNJAM);
        } else if (intakePressed) {
            if (ballCounter.getCount() == 4) {
                //change this later
                feeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_INDEX);
                intake.setWantedState(Intake.WantedState.WANTS_TO_INDEX_LAST_BALL);
            }
            else {
                this.intake.setWantedState(Intake.WantedState.WANTS_TO_INTAKE);
            }
        } else if (exhaust) {
            overrideFeeder = true;
            this.intake.setWantedState(Intake.WantedState.WANTS_TO_EXHAUST);
            feeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_BACKWARD);
        } else {
            overrideFeeder = false;
            if (feeder.isRunningBackward()) feeder.setWantedState(Feeder.WantedState.WANTS_TO_IDLE);
            this.intake.setWantedState(Intake.WantedState.WANTS_TO_STOP);
        }

        //Feeder Indexing Logic
        if (!overrideFeeder) {
            if (ballCounter.shouldFeed()) {

//                feeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_INDEX);
                feeder.setWantedState(Feeder.WantedState.WANTS_TO_FURTHER_INDEX);
//                this.intake.setWantedState(Intake.WantedState.WANTS_TO_STOP);
            }
            else {
//                feeder.setWantedState(Feeder.WantedState.WANTS_TO_IDLE);
            }
        }

        if (feederForward) {
            feeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_FORWARD);
        } else if (feederBackward) {
            feeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_BACKWARD);
        }

        //Turret ---------------------------------------------------------------------------------------
        if (manualTurretLeft) {
            turret.setWantedState(Turret.WantedState.WANTS_TO_MANUAL_LEFT);
        } else if (manualTurretRight) {
            turret.setWantedState(Turret.WantedState.WANTS_TO_MANUAL_RIGHT);
        } else {
            turret.setWantedState(Turret.WantedState.WANTS_TO_IDLE);
        }

        //Hood ---------------------------------------------------------------------------------------
        if (manualHoodUp) {
            hood.setWantedState(Hood.WantedState.WANTS_TO_MANUAL_UP);
        } else if (manualHoodDown) {
            hood.setWantedState(Hood.WantedState.WANTS_TO_MANUAL_DOWN);
        } else {
            hood.setWantedState(Hood.WantedState.WANTS_TO_IDLE);
        }

//        if(getHangerUp) {
//            hanger.setWantedState(Hanger.WantedState.WANTS_TO_HANGER_ACTUATE_RELEASE);
//            readyToHang = true;
//        }
//        else if (readyToHang) {
//            hanger.setWinchDownPower(Math.pow(getHangerDown, 5));
//            hanger.setWantedState(Hanger.WantedState.WANTS_TO_HANGER_DRIVE_DOWN);
//        }
//        else {
//            hanger.setWantedState(Hanger.WantedState.WANTS_TO_IDLE);
//        }

        //Turn on Limelight if autoalign
        limelight.turnOn();

        //Auto Aligning Turret
        double angle = limelight.calculateTau();
        if(getAutoAlign) {
            if (getAlignToOuter) {
                angle = limelight.getTx();
            }
            turret.setTurretAutoAlignAngle(angle);
            turret.setWantedState(Turret.WantedState.WANTS_TO_AUTO_ALIGN);

            //Auto Aligning Hood
            limelight.calculateKinematics();
            limelight.setWantedEndAngle(5*(Math.PI/180));
            if (limelight.getDistanceToInner() > 230) { // red
                hood.setPosSetpoint(ShootingKinematics.angleToHoodPos(0.651));
            } else if (limelight.getDistanceToInner() > 190) { // blue
                hood.setPosSetpoint(ShootingKinematics.angleToHoodPos(38*Math.PI/180.0)); //33.5
            } //280 220
            else // yellow and green
                hood.setPosSetpoint(ShootingKinematics.angleToHoodPos(limelight.getAngleToTarget()));
            hood.setWantedState(Hood.WantedState.WANTS_TO_POS);
            SmartDashboard.putNumber("angle to target", limelight.getAngleToTarget());
        }
        else {
            //Turn off Limelight if not autoalign
//            limelight.turnOff();
        }

      //  controls.rumble(flywheel.atSetpointVelocity());

        if (getRevUp) {
            if (limelight.getDistanceToInner() > 230) { //230
                flywheel.setVelocitySetpoint(4800); //4800
            } else if (limelight.getDistanceToInner() > 160) {
                flywheel.setVelocitySetpoint(4300); //6000
            } //280 220
            else
                flywheel.setVelocitySetpoint(ShootingKinematics.outputVelToFlywheelVel(limelight.getVelToTarget()));
            SmartDashboard.putNumber("flywheel vel",flywheel.getVelocity());
            SmartDashboard.putNumber("target vel",ShootingKinematics.outputVelToFlywheelVel(limelight.getVelToTarget()));
//            flywheel.setVelocitySetpoint(4000);
            flywheel.setWantedState(Flywheel.WantedState.WANTS_TO_RUN);

        } else {
            flywheel.setWantedState(Flywheel.WantedState.WANTS_TO_IDLE);
        }

        if(getFeederShoot || getDriverShoot) {
            feeder.setWantedState(Feeder.WantedState.WANTS_TO_SHOOT);
            intake.setWantedState(Intake.WantedState.WANTS_TO_INTAKE);
        }
        else{
            if (feeder.isShooting()) feeder.setWantedState(Feeder.WantedState.WANTS_TO_IDLE);
        }
        //TODO: doesn't idle after shoot ^^^

        if(intakeToggle && !prevIntakeToggle) {
            this.intake.setIntakeTogglingState(!intakeUp);
            this.intake.setWantedState(Intake.WantedState.WANTS_TO_TOGGLE_INTAKE);
            intakeUp = !intakeUp;
        }

        prevIntakeToggle = intakeToggle;

//        if(drivetrainReverseToggle && !prevDrivetrainReverseToggle) {
//            if (drivetrainReverseToggle) {
//                DriveTrain.getInstance().setInvertedReverse();
//            }
//            else
//                DriveTrain.getInstance().setInvertedNormal();
//        }
//
//        prevDrivetrainReverseToggle = drivetrainReverseToggle;
//        SmartDashboard.putBoolean("drivetrain invert",drivetrainReverseToggle);

        //TODO: Making hanger actuators similar to intake toggle
//        if(hangerPancakesToggle && !prevHangerPancakesToggle) {
//            this.hanger.setHangerPancakesTogglingState(!hangerRelease);
//            this.hanger.setWantedState(Hanger.WantedState.WANTS_TO_HANGER_TOGGLE);
//            hangerRelease = !hangerRelease;
//            readyToHang = true;
//        }

        //TODO: Implement after making sure the hangers can actuate up
//        if(readyToHang) {
//            this.hanger.setWinchDownPower(getHangerDown * getHangerDown * getHangerDown);
//            hanger.setWantedState(Hanger.WantedState.WANTS_TO_HANGER_DRIVE_DOWN);
//        }
//        else {
//            hanger.setWantedState(Hanger.WantedState.WANTS_TO_IDLE);
//        }

        //TODO: Ball counter reset is also hood reset
        if(ballCountReset) {
            ballCounter.setCount(0);
            hood.setWantedState(Hood.WantedState.WANTS_TO_ZERO_HOOD);
        }

        //TODO: Smartdashboard Ball Counting Green Lights
        if(ballCounter.getCount() == 0) {
            SmartDashboard.putBoolean("BALL 1", false);
            SmartDashboard.putBoolean("BALL 2", false);
            SmartDashboard.putBoolean("BALL 3", false);
            SmartDashboard.putBoolean("BALL 4", false);
            SmartDashboard.putBoolean("BALL 5", false);
        }
        else if(ballCounter.getCount() == 1) {
            SmartDashboard.putBoolean("BALL 1", true);
        }
        else if(ballCounter.getCount() == 2) {
            SmartDashboard.putBoolean("BALL 2", true);
        }
        else if(ballCounter.getCount() == 3) {
            SmartDashboard.putBoolean("BALL 3", true);
        }
        else if(ballCounter.getCount() == 4) {
            SmartDashboard.putBoolean("BALL 4", true);
        }
        else if(ballCounter.getCount() == 5) {
            SmartDashboard.putBoolean("BALL 5", true);
        }
    }

}