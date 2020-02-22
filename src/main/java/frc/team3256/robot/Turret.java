package frc.team3256.robot;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMax;
import frc.team3256.warriorlib.hardware.SparkMAXUtil;
import frc.team3256.warriorlib.hardware.TalonSRXUtil;
import frc.team3256.warriorlib.subsystem.SubsystemBase;

public class Turret extends SubsystemBase {
    private WPI_TalonSRX mTurret;

    WantedState mPrevWantedState;
    boolean mStateChanged;
    boolean mWantedStateChanged;

    public enum TurretState {
        MANUAL_LEFT,
        MANUAL_RIGHT,
        IDLE,
    }

    public enum WantedState {
        WANTS_TO_MANUAL_LEFT,
        WANTS_TO_MANUAL_RIGHT,
        WANTS_TO_IDLE,
    }

    private TurretState mCurrentState = TurretState.IDLE;
    private WantedState mWantedState = WantedState.WANTS_TO_IDLE;

    private static Turret instance;

    public static Turret getInstance() { return instance == null ? instance = new Turret() : instance; }

    private Turret() {
        mTurret = TalonSRXUtil.generateGenericTalon(_);
        TalonSRXUtil.configMagEncoder(mTurret);
        TalonSRXUtil.setBrakeMode(mTurret);
        mTurret.setInverted(false);
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

    private TurretState handleManualLeft() {
        mTurret.set(0.05);
        return defaultStateTransfer();
    }

    private TurretState handleManualRight() {
        mTurret.set(-0.05);
        return defaultStateTransfer();
    }

    private TurretState handleIdle() {
        mTurret.stopMotor();
        return defaultStateTransfer();
    }

    private TurretState defaultStateTransfer() {
        switch (mWantedState) {
            case WANTS_TO_MANUAL_LEFT:
                return TurretState.MANUAL_LEFT;
            case WANTS_TO_MANUAL_RIGHT:
                return TurretState.MANUAL_RIGHT;
            case WANTS_TO_IDLE:
            default:
                return TurretState.IDLE;
        }
    }

    @Override
    public void outputToDashboard() { }

    @Override
    public void zeroSensors() {
    }

    @Override
    public void init(double timestamp) { }

    @Override
    public void end(double timestamp) { }

    public double getPosition() {
        return mTurret.getSelectedSensorPosition();
    }

    public double getVelocity() {
        return mTurret.getSelectedSensorVelocity();
    }

}