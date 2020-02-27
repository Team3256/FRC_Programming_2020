package frc.team3256.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.team3256.warriorlib.hardware.TalonFXUtil;
import frc.team3256.warriorlib.subsystem.SubsystemBase;

import static frc.team3256.robot.constants.IDConstants.*;

public class Hanger extends SubsystemBase {
    private DoubleSolenoid hangerPancakes;
    private TalonFX winchMotor;

    WantedState mPrevWantedState;
    boolean mStateChanged;
    boolean mWantedStateChanged;

    public enum HangerState {
        ACTUATE_RELEASE,
        DRIVE_DOWN,
        ACTUATE_HOLD
    }

    public enum WantedState {
        WANTS_TO_HANGER_ACTUATE_RELEASE,
        WANTS_TO_HANGER_DRIVE_DOWN,
        WANTS_TO_HANGER_ACTUATE_HOLD
    }

    private HangerState mCurrentState = HangerState.ACTUATE_HOLD;
    private WantedState mWantedState = WantedState.WANTS_TO_HANGER_ACTUATE_HOLD;

    private static Hanger instance;

    private static Hanger getInstance() { return instance == null ? instance = new Hanger() : instance; }

    private Hanger() {
        hangerPancakes = new DoubleSolenoid(hangerPancakesForwardChannel,hangerPancakesReverseChannel);
        winchMotor = TalonFXUtil.generateGenericTalon(winchMotorID);
    }

    public void setWantedState(WantedState wantedState) { this.mWantedState = wantedState; }

    @Override
    public void update(double timestamp) {
        if (mPrevWantedState != mWantedState) {
            mWantedStateChanged = true;
            mPrevWantedState = mWantedState;
        } else mWantedStateChanged = false;
        HangerState newState;
        switch (mCurrentState) {
            case ACTUATE_RELEASE:
                newState = handleActuateRelease();
                break;
            case DRIVE_DOWN:
                newState = handleDriveDown();
                break;
            case ACTUATE_HOLD:
            default:
                newState = handleActuateHold();
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

    private HangerState handleActuateRelease() {
        hangerPancakes.set(DoubleSolenoid.Value.kReverse);
        return defaultStateTransfer();
    }

    private HangerState handleDriveDown() {
        winchMotor.set(ControlMode.PercentOutput, 1);
        return defaultStateTransfer();
    }

    private HangerState handleActuateHold() {
        hangerPancakes.set(DoubleSolenoid.Value.kForward);
        return defaultStateTransfer();
    }

    private HangerState defaultStateTransfer() {
        switch (mWantedState) {
            case WANTS_TO_HANGER_ACTUATE_RELEASE:
                return HangerState.ACTUATE_RELEASE;
            case WANTS_TO_HANGER_DRIVE_DOWN:
                return HangerState.DRIVE_DOWN;
            case WANTS_TO_HANGER_ACTUATE_HOLD:
            default:
                return HangerState.ACTUATE_HOLD;
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
