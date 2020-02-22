package frc.team3256.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.team3256.warriorlib.hardware.SparkMAXUtil;
import frc.team3256.warriorlib.subsystem.SubsystemBase;

public class Hood extends SubsystemBase {
    private CANSparkMax mHood;
    private DigitalInput bottomLimit;

    WantedState mPrevWantedState;
    boolean mStateChanged;
    boolean mWantedStateChanged;

    public enum HoodState {
        MANUAL_UP,
        MANUAL_DOWN,
        IDLE,
        ZEROING
    }

    public enum WantedState {
        WANTS_TO_MANUAL_UP,
        WANTS_TO_MANUAL_DOWN,
        WANTS_TO_IDLE,
        WANTS_TO_ZERO
    }

    private HoodState mCurrentState = HoodState.IDLE;
    private WantedState mWantedState = WantedState.WANTS_TO_IDLE;

    private static Hood instance;

    public static Hood getInstance() { return instance == null ? instance = new Hood() : instance; }

    private Hood() {
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
            case ZEROING:
                newState = handleZeroing();
                break;
            case MANUAL_UP:
                newState = handleManualUp();
                break;
            case MANUAL_DOWN:
                newState = handleManualDown();
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
        if(mHood.getEncoder().getPosition() <= -8.8) {
            mHood.stopMotor();
        }
        else {
            mHood.set(-0.15);
        }
        return defaultStateTransfer();
    }

    private HoodState handleManualDown() { //Runs Hood Down until it hits the limit switch
        if(isLimitSwitchPressed()) {
            mHood.stopMotor();
            mHood.getEncoder().setPosition(0);
        }
        else {
            mHood.set(0.15);
        }
        return defaultStateTransfer();
    }

    private HoodState handleIdle() { //Stops turret motor
        mHood.stopMotor();
        return defaultStateTransfer();
    }

    private HoodState handleZeroing() {
        return defaultStateTransfer();
    }

    private HoodState defaultStateTransfer() {
        switch (mWantedState) {
            case WANTS_TO_ZERO:
                return HoodState.ZEROING;
            case WANTS_TO_MANUAL_UP:
                return HoodState.MANUAL_UP;
            case WANTS_TO_MANUAL_DOWN:
                return HoodState.MANUAL_DOWN;
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

    private double encoderToAngle(double encoder) {
        return 0;
    }

    private double angleToEncoder(double angle) {
        return 0;
    }

    @Override
    public void outputToDashboard() { }

    @Override
    public void zeroSensors() {
        mHood.getEncoder().setPosition(0);
    }

    @Override
    public void init(double timestamp) {

        mHood = SparkMAXUtil.generateGenericSparkMAX(_, CANSparkMaxLowLevel.MotorType.kBrushless); //TBD
        mHood.setInverted(false);
        mHood.burnFlash();
        bottomLimit = new DigitalInput(_); //TBD
    }

    @Override
    public void end(double timestamp) { }
}