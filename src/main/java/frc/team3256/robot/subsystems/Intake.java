package frc.team3256.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import frc.team3256.robot.constants.IDConstants;
import frc.team3256.warriorlib.hardware.SparkMAXUtil;
import frc.team3256.warriorlib.hardware.TalonSRXUtil;
import frc.team3256.warriorlib.subsystem.SubsystemBase;

public class Intake extends SubsystemBase {
    private CANSparkMax mIntake;
    private WPI_TalonSRX mCenterMech;

    WantedState mPrevWantedState;
    boolean mStateChanged;
    boolean mWantedStateChanged;

    public enum IntakeState {
        UNJAMMING,
        INTAKING,
        INTAKING_AUTO_BACKWARDS,
        EXHAUSTING,
        STOP
    }

    public enum WantedState {
        WANTS_TO_UNJAM,
        WANTS_TO_INTAKE,
        WANTS_TO_EXHAUST,
        WANTS_TO_AUTO_BACKWARDS_INTAKE,
        WANTS_TO_STOP
    }

    private IntakeState mCurrentState = IntakeState.STOP;
    private WantedState mWantedState = WantedState.WANTS_TO_STOP;

    private static Intake instance;

    public static Intake getInstance() { return instance == null ? instance = new Intake() : instance; }

    private Intake() {
        mIntake = SparkMAXUtil.generateGenericSparkMAX(IDConstants.intakeID, CANSparkMaxLowLevel.MotorType.kBrushless);
        mIntake.burnFlash();
        mCenterMech = TalonSRXUtil.generateGenericTalon(IDConstants.centerMechID);

        mIntake.burnFlash();

        mIntake.set(0);
        mCenterMech.set(0);

        mIntake.setInverted(false);
        mCenterMech.setInverted(false);
    }


    public void setWantedState(WantedState wantedState) {
        this.mWantedState = wantedState; }

    @Override
    public void update(double timestamp) {
        if (mPrevWantedState != mWantedState) {
            mWantedStateChanged = true;
            mPrevWantedState = mWantedState;
        } else mWantedStateChanged = false;
        IntakeState newState;
        switch (mCurrentState) {
            case UNJAMMING:
                newState = handleUnJam();
                break;
            case INTAKING:
                newState = handleIntake();
                break;
            case EXHAUSTING:
                newState = handleExhaust();
                break;
            case INTAKING_AUTO_BACKWARDS:
                newState = handleAutoBackwardsIntake();
                break;
            case STOP:
            default:
                newState = handleStop();
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

    public IntakeState handleIntake() {
        mIntake.set(-0.5); //0.5
        mCenterMech.set(1);
        return defaultStateTransfer();
    }

    public IntakeState handleAutoBackwardsIntake() {
        mIntake.set(-0.35);
        mCenterMech.set(1);
        return defaultStateTransfer();
    }

    private IntakeState handleExhaust() {
        mIntake.set(0.7); //-0.5
        mCenterMech.set(-1);
        return defaultStateTransfer();
    }

    private IntakeState handleUnJam() {
        mIntake.set(-0.3);
        mCenterMech.set(1);
        return defaultStateTransfer();
    }

    public IntakeState handleStop() {
        mIntake.set(0);
        mCenterMech.set(0);
        return defaultStateTransfer();
    }

    private IntakeState defaultStateTransfer() {
        switch (mWantedState) {
            case WANTS_TO_UNJAM:
                return IntakeState.UNJAMMING;
            case WANTS_TO_INTAKE:
                return IntakeState.INTAKING;
            case WANTS_TO_AUTO_BACKWARDS_INTAKE:
                return IntakeState.INTAKING_AUTO_BACKWARDS;
            case WANTS_TO_EXHAUST:
                return IntakeState.EXHAUSTING;
            case WANTS_TO_STOP:
                return IntakeState.STOP;
            default:
                return IntakeState.STOP;
        }
    }

    public boolean getWantedStateChanged() {
        return mWantedStateChanged;
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