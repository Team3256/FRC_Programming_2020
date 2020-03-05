package frc.team3256.robot.subsystems;


import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.constants.TurretConstants;
import frc.team3256.robot.hardware.Limelight;
import frc.team3256.warriorlib.hardware.SparkMAXUtil;
import frc.team3256.warriorlib.subsystem.SubsystemBase;

import static frc.team3256.robot.constants.IDConstants.turretID;

public class Turret extends SubsystemBase {
    private CANSparkMax mTurret;

    WantedState mPrevWantedState;
    private boolean mStateChanged;
    private boolean mWantedStateChanged;
    private double angleSetpoint;
    private Limelight limelight = new Limelight();
    private double initialLimelightAngle, headingError;
    private PIDController turretPIDController;
    private PIDController turretPositionPIDController;
    private boolean firstRun;
    private double autoAlignTolerance = TurretConstants.kAutoAlignTolerance;
    private boolean atSetpoint;
    private double turrentWantedPosition;

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
        turrentWantedPosition = 0;
        turretPIDController = new PIDController(TurretConstants.turretkP, TurretConstants.turretkI, TurretConstants.turretkD);
        turretPositionPIDController = new PIDController(TurretConstants.turretPositionkP, TurretConstants.turretPositionkI, TurretConstants.turretPositionkD);
        firstRun = true;
        headingError = 0;
        mTurret = SparkMAXUtil.generateGenericSparkMAX(10, CANSparkMaxLowLevel.MotorType.kBrushless);
        mTurret.getPIDController().setP(50.0);
        mTurret.getPIDController().setI(0.0);
        mTurret.getPIDController().setD(0.0);
        mTurret.getPIDController().setFF(0.0);
        mTurret.setSmartCurrentLimit(30);
        mTurret.setInverted(false);
        atSetpoint = false;
        SparkMAXUtil.setBrakeMode(mTurret);
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
//        if(isSafe()) mTurret.set(0.5);
//        else mTurret.stopMotor();
        mTurret.set(-0.5);
        return defaultStateTransfer();
    }

    private TurretState handleManualRight() {
//        if(isSafe()) mTurret.set(-0.5);
//        else mTurret.stopMotor();
        mTurret.set(0.5);
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
//        if (isSafe()) mTurret.set(speed);
//        else mTurret.stopMotor();
        mTurret.set(-speed);
    }

    public void setAutoTurretSpeed(double speed) {
        mTurret.set(-speed);
    }

    public boolean atAngleSetpoint() {
        return atSetpoint;
    }

    public double angleToEncoder(double angle) {
        return angle * 1.2984;
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
    public void outputToDashboard() {
        SmartDashboard.putNumber("turret encoder", getPosition());
    }

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

    public void reset() {
        mTurret.getEncoder().setPosition(0);
        turrentWantedPosition = 0;
    }

    public void setPosition(double angle) {
        double setpoint = angleToEncoder(angle);
        SmartDashboard.putNumber("Setpoint", setpoint);
        mTurret.getPIDController().setReference(setpoint, ControlType.kPosition);
    }

    public boolean isSafe() {
        if (getPosition() > TurretConstants.maxTurretPosition || getPosition() < TurretConstants.minTurretPosition)
            return false;
        return true;
    }

}