package frc.team3256.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.team3256.warriorlib.hardware.TalonFXUtil;
import frc.team3256.warriorlib.subsystem.SubsystemBase;

import static frc.team3256.robot.constants.IDConstants.*;

public class Hanger extends SubsystemBase {
    private DoubleSolenoid hangerPancakes;
    private WPI_TalonFX winchMotor;

    WantedState mPrevWantedState;
    private boolean mStateChanged;
    private boolean mWantedStateChanged;
    private double winchDownPower;
    private boolean hangerPancakesToggle;

    public enum HangerState {
        HANGER_PANCAKE_TOGGLE,
        DRIVE_DOWN,
        IDLING,
    }

    public enum WantedState {
        WANTS_TO_HANGER_TOGGLE,
        WANTS_TO_HANGER_DRIVE_DOWN,
        WANTS_TO_IDLE
    }

    private HangerState mCurrentState = HangerState.IDLING;
    private WantedState mWantedState = WantedState.WANTS_TO_IDLE;

    private static Hanger instance;

    public static Hanger getInstance() { return instance == null ? instance = new Hanger() : instance; }

    private Hanger() {
        hangerPancakes = new DoubleSolenoid(hangerPancakesForwardChannel,hangerPancakesReverseChannel);
        winchMotor = TalonFXUtil.generateGenericTalon(winchMotorID);
        winchDownPower = 0;
        TalonFXUtil.setBrakeMode(winchMotor);
        winchMotor.setInverted(true);
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
            case DRIVE_DOWN:
                newState = handleDriveDown();
                break;
            case HANGER_PANCAKE_TOGGLE:
                newState = handlePancakesToggle();
                break;
            case IDLING:
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

    private HangerState handlePancakesToggle() {
        if(hangerPancakesToggle) {
            hangerPancakes.set(DoubleSolenoid.Value.kReverse);
        }
        else {
            hangerPancakes.set(DoubleSolenoid.Value.kForward);
        }
        return defaultStateTransfer();
    }

    private HangerState handleDriveDown() {
        winchMotor.set(ControlMode.PercentOutput, winchDownPower);
        return defaultStateTransfer();
    }

    private HangerState handleIdle() {
        winchMotor.stopMotor();
        return defaultStateTransfer();
    }

    public void setHangerPancakesTogglingState(boolean raise) { hangerPancakesToggle = raise; }

    public void setWinchDownPower(double winchDownPower) { this.winchDownPower = winchDownPower; }

    private HangerState defaultStateTransfer() {
        switch (mWantedState) {
            case WANTS_TO_HANGER_TOGGLE:
                return HangerState.HANGER_PANCAKE_TOGGLE;
            case WANTS_TO_HANGER_DRIVE_DOWN:
                return HangerState.DRIVE_DOWN;
            case WANTS_TO_IDLE:
            default:
                return HangerState.IDLING;
        }
    }

    public void outputToDashboard() { }

    @Override
    public void zeroSensors() { }

    @Override
    public void init(double timestamp) {
    }

    @Override
    public void end(double timestamp) { }

}
