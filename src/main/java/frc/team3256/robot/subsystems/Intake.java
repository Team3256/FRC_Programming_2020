package frc.team3256.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.constants.IDConstants;
import frc.team3256.warriorlib.hardware.SparkMAXUtil;
import frc.team3256.warriorlib.hardware.TalonFXUtil;
import frc.team3256.warriorlib.hardware.TalonSRXUtil;
import frc.team3256.warriorlib.subsystem.SubsystemBase;

public class Intake extends SubsystemBase {
    private WPI_TalonFX mIntake;
    private WPI_TalonSRX mCenterMech;
    private DoubleSolenoid mRaiseMech;
    private boolean intakeRaise;

    WantedState mPrevWantedState;
    private boolean mStateChanged;
    private boolean mWantedStateChanged;

    public enum IntakeState {
        UNJAMMING,
        INTAKING,
        INDEXING_LAST_BALL,
        INTAKING_AUTO_BACKWARDS,
        EXHAUSTING,
        TOGGLING,
        STOP
    }

    public enum WantedState {
        WANTS_TO_UNJAM,
        WANTS_TO_INTAKE,
        WANTS_TO_INDEX_LAST_BALL,
        WANTS_TO_EXHAUST,
        WANTS_TO_AUTO_BACKWARDS_INTAKE,
        WANTS_TO_TOGGLE_INTAKE,
        WANTS_TO_STOP
    }

    public WantedState getWantedState() {
        return mWantedState;
    }

    private IntakeState mCurrentState = IntakeState.STOP;
    private WantedState mWantedState = WantedState.WANTS_TO_STOP;

    private static Intake instance;

    public static Intake getInstance() { return instance == null ? instance = new Intake() : instance; }

    private Intake() {


        mIntake = TalonFXUtil.generateGenericTalon(IDConstants.intakeID);
        mCenterMech = TalonSRXUtil.generateGenericTalon(IDConstants.centerMechID);
        mCenterMech.enableCurrentLimit(true);
        mCenterMech.configContinuousCurrentLimit(30);

        mRaiseMech = new DoubleSolenoid(IDConstants.pcmID,IDConstants.intakeRaiseForwardChannel,IDConstants.intakeRaiseReverseChannel);

        mIntake.set(0);
        mCenterMech.set(0);

        mIntake.setInverted(false);
        mCenterMech.setInverted(false);
        SmartDashboard.putString("Intake State", "STOP");
    }


    public void setWantedState(WantedState wantedState) { this.mWantedState = wantedState; }

    @Override
    public void update(double timestamp) {
//        return;
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
            case TOGGLING:
                newState = handleToggling();
                break;
            case INDEXING_LAST_BALL:
                newState = handleIndexingLastBall();
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

    //TODO: EVERYTHING 100 TEST
    public IntakeState handleIntake() {
        SmartDashboard.putString("Intake State", "Intaking");
        mIntake.set(-1.0); //-0.5 //-0.7
        mCenterMech.set(-0.75); //-0.8
        return defaultStateTransfer();
    }

    public IntakeState handleIndexingLastBall() {
        SmartDashboard.putString("Intake State", "Indexing Last Ball");
        mIntake.set(-0.7);
        mCenterMech.set(0);
        return defaultStateTransfer();
    }

    public IntakeState handleAutoBackwardsIntake() {
        SmartDashboard.putString("Intake State", "Run Backwards");
        mIntake.set(-0.35);
        mCenterMech.set(-0.75);
        return defaultStateTransfer();
    }

    private IntakeState handleExhaust() {
        SmartDashboard.putString("Intake State", "Run Exhaust");
        mIntake.set(0.7); //-0.5
        mCenterMech.set(0.75);
        return defaultStateTransfer();
    }

    private IntakeState handleToggling() {
        SmartDashboard.putString("Intake State", "Toggling: " + (intakeRaise ? "Raised" : "Not Raised"));
        if (intakeRaise) {
            setRaise(true);
            mIntake.set(-0.7);
        }
        else
            setRaise(false);
        return defaultStateTransfer();
    }

    public void setIntakeTogglingState(boolean raise) { intakeRaise = raise; }

    private IntakeState handleUnJam() {
        SmartDashboard.putString("Intake State", "unjam");
        mIntake.set(-0.3);
        mCenterMech.set(0.75);
        return defaultStateTransfer();
    }

    private IntakeState handleStop() {
        SmartDashboard.putString("Intake State", "STOP");
        mIntake.set(0);
        mCenterMech.set(0);
        return defaultStateTransfer();
    }

    public boolean isUnjamming(){
        return mCurrentState == IntakeState.UNJAMMING;
    }


    private void setRaise(boolean raise) {
        mRaiseMech.set(raise ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    }


    private IntakeState defaultStateTransfer() {
        switch (mWantedState) {
            case WANTS_TO_UNJAM:
                return IntakeState.UNJAMMING;
            case WANTS_TO_INTAKE:
                return IntakeState.INTAKING;
            case WANTS_TO_INDEX_LAST_BALL:
                return IntakeState.INDEXING_LAST_BALL;
            case WANTS_TO_AUTO_BACKWARDS_INTAKE:
                return IntakeState.INTAKING_AUTO_BACKWARDS;
            case WANTS_TO_EXHAUST:
                return IntakeState.EXHAUSTING;
            case WANTS_TO_TOGGLE_INTAKE:
                return IntakeState.TOGGLING;
            case WANTS_TO_STOP:
            default:
                return IntakeState.STOP;
        }
    }

    public boolean getWantedStateChanged() {
        return mWantedStateChanged;
    }

    @Override
    public void outputToDashboard() {
    }

    @Override
    public void zeroSensors() { }

    @Override
    public void init(double timestamp) { }

    @Override
    public void end(double timestamp) { }
}
