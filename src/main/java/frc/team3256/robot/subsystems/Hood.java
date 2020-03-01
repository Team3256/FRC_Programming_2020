package frc.team3256.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.team3256.robot.constants.HoodConstants;
import frc.team3256.warriorlib.hardware.SparkMAXUtil;
import frc.team3256.warriorlib.subsystem.SubsystemBase;

import static frc.team3256.robot.constants.HoodConstants.*;
import static frc.team3256.robot.constants.IDConstants.hoodID;
import static frc.team3256.robot.constants.IDConstants.limitSwitchPort;

public class Hood extends SubsystemBase {
    private CANSparkMax mHood;
    private DigitalInput bottomLimit;

    WantedState mPrevWantedState;
    private boolean mStateChanged;
    private boolean mWantedStateChanged;
    private double posSetpoint;
    private boolean isZeroed = false;
    private boolean atSetpoint = false;

    public enum HoodState {
        MANUAL_UP,
        MANUAL_DOWN,
        IDLE,
        CLOSED_LOOP
    }

    public enum WantedState {
        WANTS_TO_MANUAL_UP,
        WANTS_TO_MANUAL_DOWN,
        WANTS_TO_IDLE,
        WANTS_TO_POS
    }

    private HoodState mCurrentState = HoodState.IDLE;
    private WantedState mWantedState = WantedState.WANTS_TO_IDLE;

    private static Hood instance;

    public static Hood getInstance() { return instance == null ? instance = new Hood() : instance; }

    private Hood() {
        mHood = SparkMAXUtil.generateGenericSparkMAX(hoodID, CANSparkMaxLowLevel.MotorType.kBrushless); //TBD
        mHood.setInverted(false);
        bottomLimit = new DigitalInput(limitSwitchPort);
        SparkMAXUtil.setPIDGains(mHood.getPIDController(),
                kHoodAdjustSlot,
                kHoodAdjustP,
                kHoodAdjustI,
                kHoodAdjustD,
                kHoodAdjustF,
                kHoodAdjustIZone
        );
    }

    public void setWantedState(WantedState wantedState) { this.mWantedState = wantedState; }

    @Override
    public void update(double timestamp) {
        if (mPrevWantedState != mWantedState) {
            mWantedStateChanged = true;
            mPrevWantedState = mWantedState;
        } else mWantedStateChanged = false;
        HoodState newState;
        switch (mCurrentState) {
            case MANUAL_UP:
                newState = handleManualUp();
                break;
            case MANUAL_DOWN:
                newState = handleManualDown();
                break;
            case CLOSED_LOOP:
                newState = handleClosedLoop();
                break;
            case IDLE:
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

    private HoodState handleManualUp() { //Runs Hood Up until encoder position of -8.8
        if(mHood.getEncoder().getPosition() <= kHoodMaxPos) {
            mHood.stopMotor();
        }
        else {
            mHood.set(-kHoodSpeed);
        }
        return defaultStateTransfer();
    }

    private HoodState handleManualDown() { //Runs Hood Down until it hits the limit switch
        if(isLimitSwitchPressed()) {
            mHood.stopMotor();
            mHood.getEncoder().setPosition(0);
            isZeroed = true;
        }
        else {
            mHood.set(kHoodSpeed);
        }
        return defaultStateTransfer();
    }

    private HoodState handleClosedLoop() {
        if(posSetpoint <= kHoodMaxPos) {
            posSetpoint = kHoodMaxPos;
        } else if (posSetpoint > 0) {
            posSetpoint = 0;
        }
        mHood.getPIDController().setReference(posSetpoint, ControlType.kPosition);
        atSetpoint = getHoodEncoder() > posSetpoint - kHoodSetpointTolerance && getHoodEncoder() < posSetpoint + kHoodSetpointTolerance;
        return defaultStateTransfer();
    }

    public boolean atHoodSetpoint() {
        return atSetpoint;
    }

    private HoodState handleIdle() { //Stops turret motor
        mHood.stopMotor();
        return defaultStateTransfer();
    }

    private HoodState defaultStateTransfer() {
        switch (mWantedState) {
            case WANTS_TO_MANUAL_UP:
                return HoodState.MANUAL_UP;
            case WANTS_TO_MANUAL_DOWN:
                return HoodState.MANUAL_DOWN;
            case WANTS_TO_POS:
                return HoodState.CLOSED_LOOP;
            case WANTS_TO_IDLE:
            default:
                return HoodState.IDLE;
        }
    }

    private boolean isLimitSwitchPressed() {
        return !bottomLimit.get();
    }

    public double getHoodEncoder() {
        return mHood.getEncoder().getPosition();
    }

    public void setPosSetpoint(double pos) {
        posSetpoint = pos;
    }

    private double encoderToAngle(double encoder) {
        return 0;
    }

    private double angleToEncoder(double angle) {
        return 0;
    }

    public boolean isZeroed() {
        return isZeroed;
    }

    @Override
    public void outputToDashboard() {
    }

    @Override
    public void zeroSensors() {
        mHood.getEncoder().setPosition(0);
    }

    @Override
    public void init(double timestamp) {
    }

    @Override
    public void end(double timestamp) { }
}