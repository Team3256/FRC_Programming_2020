package frc.team3256.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.team3256.robot.constants.TurretConstants;
import frc.team3256.robot.hardware.Limelight;
import frc.team3256.warriorlib.hardware.SparkMAXUtil;
import frc.team3256.warriorlib.subsystem.SubsystemBase;

import static frc.team3256.robot.constants.IDConstants.turretID;

public class Turret extends SubsystemBase {
    private CANSparkMax mTurret;

    WantedState mPrevWantedState;
    boolean mStateChanged;
    boolean mWantedStateChanged;
    double angleSetpoint;
    private Limelight limelight = new Limelight();
    private double initialLimelightAngle, headingError;
    private PIDController turretPIDController;
    private boolean firstRun;
    private double autoAlignTolerance = TurretConstants.kAutoAlignTolerance;
    private boolean atSetpoint;

    public enum TurretState {
        MANUAL_LEFT,
        MANUAL_RIGHT,
        AUTO_ALIGN,
        IDLE,
    }

    public enum WantedState {
        WANTS_TO_MANUAL_LEFT,
        WANTS_TO_MANUAL_RIGHT,
        WANTS_TO_AUTO_ALIGN,
        WANTS_TO_IDLE,
    }

    private TurretState mCurrentState = TurretState.IDLE;
    private WantedState mWantedState = WantedState.WANTS_TO_IDLE;

    private static Turret instance;

    public static Turret getInstance() { return instance == null ? instance = new Turret() : instance; }

    private Turret() {
        limelight.init();
        initialLimelightAngle = 0;
        turretPIDController = new PIDController(TurretConstants.turretkP, TurretConstants.turretkI, TurretConstants.turretkD);
        firstRun = true;
        headingError = 0;
        mTurret = SparkMAXUtil.generateGenericSparkMAX(turretID, CANSparkMaxLowLevel.MotorType.kBrushless);
        SparkMAXUtil.setBrakeMode(mTurret);
        mTurret.setInverted(false);
        atSetpoint = false;
    }

    public void setWantedState(WantedState wantedState) { this.mWantedState = wantedState; }

    @Override
    public void update(double timestamp) {
        if (mPrevWantedState != mWantedState) {
            mWantedStateChanged = true;
            mPrevWantedState = mWantedState;
        } else mWantedStateChanged = false;
        TurretState newState;
        switch (mCurrentState) {
            case MANUAL_LEFT:
                newState = handleManualLeft();
                break;
            case MANUAL_RIGHT:
                newState = handleManualRight();
                break;
            case AUTO_ALIGN:
                newState = handleAutoAlign();
                break;
            case IDLE:
                newState = handleIdle();
                break;
            default:
                newState = handleIdle();
                break;
        }

        if (newState != mCurrentState) {
            mCurrentState = newState;
            mStateChanged = true;
        } else {
            mStateChanged = false;
        }
        this.outputToDashboard();
    }

    private TurretState handleManualLeft() {
        mTurret.set(0.5);
        return defaultStateTransfer();
    }

    private TurretState handleManualRight() {
        mTurret.set(-0.5);
        return defaultStateTransfer();
    }

    private TurretState handleIdle() {
        mTurret.stopMotor();
        return defaultStateTransfer();
    }

    private TurretState handleAutoAlign() {
        if (Math.abs(angleSetpoint) <= autoAlignTolerance) {
            atSetpoint = true;
            setTurretSpeed(0);
        }
        else {
            double getTo = angleSetpoint;
            if (limelight.getTopSkew() < -45) {
                headingError = angleSetpoint - limelight.getTx();
                getTo = -angleSetpoint;
            } else
                headingError = -angleSetpoint - limelight.getTx();
            if (firstRun) {
                initialLimelightAngle = limelight.getTx();
            }
            double command = -getTo;
            double c = turretPIDController.calculate(0, command);
            setTurretSpeed(c);
            atSetpoint = false;
        }
        return defaultStateTransfer();
    }

    private void setTurretSpeed(double speed) {
        // if not past soft limits
        mTurret.set(speed);
    }

    public boolean atAngleSetpoint() {
        return atSetpoint;
    }

    public void setTurretAutoAlignAngle(double angle) {
        this.angleSetpoint = angle;
    }

    private TurretState defaultStateTransfer() {
        switch (mWantedState) {
            case WANTS_TO_MANUAL_LEFT:
                return TurretState.MANUAL_LEFT;
            case WANTS_TO_MANUAL_RIGHT:
                return TurretState.MANUAL_RIGHT;
            case WANTS_TO_AUTO_ALIGN:
                return TurretState.AUTO_ALIGN;
            case WANTS_TO_IDLE:
            default:
                return TurretState.IDLE;
        }
    }

    @Override
    public void outputToDashboard() { }

    @Override
    public void zeroSensors() { }

    @Override
    public void init(double timestamp) { }

    @Override
    public void end(double timestamp) { }


    public double getPosition() {
        return mTurret.getEncoder().getPosition();
    }

    public double getVelocity() {
        return mTurret.getEncoder().getVelocity();
    }
}