package frc.team3256.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import frc.team3256.warriorlib.hardware.SparkMAXUtil;
import frc.team3256.warriorlib.hardware.TalonSRXUtil;
import frc.team3256.warriorlib.subsystem.SubsystemBase;

import static frc.team3256.robot.constants.IDConstants.feederID;
import static frc.team3256.robot.constants.IDConstants.turretBarID;

public class Feeder extends SubsystemBase {

    private CANSparkMax mFeeder;
    private WPI_TalonSRX mOmni;

    private enum FeederControlState {
        RUN_FORWARD,
        RUN_BACKWARD,
        RUN_INDEX,
        SHOOTING,
        IDLE
    }

    public enum WantedState {
        WANTS_TO_RUN_FORWARD,
        WANTS_TO_RUN_BACKWARD,
        WANTS_TO_RUN_INDEX,
        WANTS_TO_SHOOT,
        WANTS_TO_IDLE
    }

    private FeederControlState mCurrentState = FeederControlState.IDLE;
    private WantedState mWantedState = WantedState.WANTS_TO_IDLE;
    private WantedState mPrevWantedState = WantedState.WANTS_TO_IDLE;

    boolean mStateChanged = false;
    boolean mWantedStateChanged = false;

    private static Feeder instance;
    public static Feeder getInstance() { return instance == null ? instance = new Feeder() : instance; }

    private Feeder() {
        mFeeder = SparkMAXUtil.generateGenericSparkMAX(feederID, CANSparkMaxLowLevel.MotorType.kBrushless);
        mFeeder.setInverted(true);
        SparkMAXUtil.setBrakeMode(mFeeder);
        mFeeder.burnFlash();
        mOmni = TalonSRXUtil.generateGenericTalon(turretBarID);
        mOmni.setInverted(false);
    }

    public void setWantedState(Feeder.WantedState wantedState) { this.mWantedState = wantedState; }

    @Override
    public void update(double timestamp) {
        if (mPrevWantedState != mWantedState) {
            mWantedStateChanged = true;
            mPrevWantedState = mWantedState;
        } else
            mWantedStateChanged = false;

        FeederControlState newState = FeederControlState.IDLE;
        switch (mCurrentState) {
            case RUN_FORWARD:
                newState = handleRunForward();
                break;
            case RUN_BACKWARD:
                newState = handleRunBackward();
                break;
            case SHOOTING:
                newState = handleShoot();
                break;
            case RUN_INDEX:
                newState = handleIndex();
                break;
            case IDLE:
                newState = handleIdle();
                break;
        }
        if (newState != mCurrentState) {
            mCurrentState = newState;
            mStateChanged = true;
        } else {
            mStateChanged = false;
        }
    }

    private FeederControlState handleRunForward() {
        mFeeder.set(0.3);
        mOmni.set(-0.5);
        return defaultStateTransfer();
    }

    private FeederControlState handleRunBackward() {
        mFeeder.set(-0.2);
        mOmni.set(-0.5);
        return defaultStateTransfer();
    }

    private FeederControlState handleIndex() {
        mFeeder.set(0.3);
        mOmni.set(0.5);
        return defaultStateTransfer();
    }

    private FeederControlState handleShoot() {
        mFeeder.set(0.3);
        mOmni.set(0.5);
        return defaultStateTransfer();
    }

    private FeederControlState handleIdle() {
        mFeeder.stopMotor();
        mOmni.stopMotor();
        return defaultStateTransfer();
    }

    private FeederControlState defaultStateTransfer() {
        switch (mWantedState) {
            case WANTS_TO_RUN_FORWARD:
                return FeederControlState.RUN_FORWARD;
            case WANTS_TO_RUN_BACKWARD:
                return FeederControlState.RUN_BACKWARD;
            case WANTS_TO_RUN_INDEX:
                return FeederControlState.RUN_INDEX;
            case WANTS_TO_SHOOT:
                return FeederControlState.SHOOTING;
            case WANTS_TO_IDLE:
            default:
                return FeederControlState.IDLE;
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
}