package frc.team3256.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.team3256.robot.constants.FeederConstants;
import frc.team3256.warriorlib.hardware.SparkMAXUtil;
import frc.team3256.warriorlib.hardware.TalonSRXUtil;
import frc.team3256.warriorlib.subsystem.SubsystemBase;

import static frc.team3256.robot.constants.IDConstants.feederID;
import static frc.team3256.robot.constants.IDConstants.turretBarID;

public class Feeder extends SubsystemBase {

    private CANSparkMax mFeeder;
    private WPI_TalonSRX mBar;

    private enum FeederControlState {
        RUN_FORWARD,
        RUN_BACKWARD,
        AUTO_SHOOTING,
        RUN_INDEX,
        FURTHER_INDEXING,
        PID_POSITIONING,
        SHOOTING,
        IDLE
    }

    public enum WantedState {
        WANTS_TO_RUN_FORWARD,
        WANTS_TO_RUN_BACKWARD,
        WANTS_TO_RUN_INDEX,
        WANTS_TO_FURTHER_INDEX,
        WANTS_TO_PID_POSITION,
        WANTS_TO_AUTO_SHOOT,
        WANTS_TO_SHOOT,
        WANTS_TO_IDLE
    }

    private FeederControlState mCurrentState = FeederControlState.IDLE;
    private WantedState mWantedState = WantedState.WANTS_TO_IDLE;
    private WantedState mPrevWantedState = WantedState.WANTS_TO_IDLE;
    private static PIDController feederPIDController;


    boolean mStateChanged = false;
    boolean mWantedStateChanged = false;

    private static Feeder instance;
    public static Feeder getInstance() { return instance == null ? instance = new Feeder() : instance; }

    private Feeder() {
        mFeeder = SparkMAXUtil.generateGenericSparkMAX(feederID, CANSparkMaxLowLevel.MotorType.kBrushless);
        mFeeder.setInverted(true);
        SparkMAXUtil.setBrakeMode(mFeeder);
        mBar = TalonSRXUtil.generateGenericTalon(turretBarID);
        mFeeder.setSmartCurrentLimit(30);
        mBar.setInverted(false);
        mFeeder.burnFlash();

        feederPIDController = new PIDController(FeederConstants.kP,FeederConstants.kI,FeederConstants.kD);
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
            case FURTHER_INDEXING:
                newState = handleFurtherIndexing();
                break;
            case PID_POSITIONING:
                newState = handlePIDPositioning();
                break;
            case AUTO_SHOOTING:
                newState = handleAutoShoot();
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

    public boolean isRunIndex(){
        return mCurrentState == FeederControlState.RUN_INDEX;
    };

    public boolean isPidPositioning(){
        return mCurrentState == FeederControlState.PID_POSITIONING;
    };


    public void setPIDPositioning(double positionSetpoint){
        CANEncoder encoder = mFeeder.getEncoder();

        if(atSetpoint()){
            encoder.setPosition(0);
        } else {
            double output = feederPIDController.calculate(getPosition(encoder), positionSetpoint);
            mFeeder.set(output);
        }

    }

    public static boolean atSetpoint(){
        feederPIDController.setTolerance(FeederConstants.positionTolerance, FeederConstants.velocityTolerance);
        return feederPIDController.atSetpoint();
    }
    private FeederControlState handleRunForward() {
        mFeeder.set(0.6);
        mBar.set(-0.5);
        return defaultStateTransfer();
    }

    private FeederControlState handleRunBackward() {
        mFeeder.set(-0.6);
        mBar.set(-0.5);
        return defaultStateTransfer();
    }
    private FeederControlState handlePIDPositioning(){
        //TODO: Put PID Control Here, this runs 50hz when PID positioning state is on
        setPIDPositioning(FeederConstants.kSpaceBetweenPowerCells);
        return defaultStateTransfer();
    }

    private FeederControlState handleFurtherIndexing() {

        //TODO: Dylan - What is this delay? Probably remove the delay
//        mFeeder.getEncoder().setPosition(mFeeder.getEncoder().getPosition() - 100);
        mFeeder.set(1);
        try {
            Thread.sleep(70);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mFeeder.set(0);
        return defaultStateTransfer();
    }

    private FeederControlState handleAutoShoot() {
        mFeeder.set(0.25);
        mBar.set(-0.5);
        return defaultStateTransfer();
    }

    //TODO: EVERYTHING 100 TEST
    //TODO: Dylan - This probably needs to be in constants file, actually all of this stuff does
    private FeederControlState handleIndex() {
        mFeeder.set(1); //0.3
        mBar.set(0.5);
        return defaultStateTransfer();
    }

    private FeederControlState handleShoot() {
        mFeeder.set(0.5);
        mBar.set(-0.5);
        return defaultStateTransfer();
    }

    private FeederControlState handleIdle() {

        mFeeder.stopMotor();
        mBar.stopMotor();
        return defaultStateTransfer();
    }

    private FeederControlState defaultStateTransfer() {
        switch (mWantedState) {
            case WANTS_TO_RUN_FORWARD:
                return FeederControlState.RUN_FORWARD;
            case WANTS_TO_RUN_BACKWARD:
                return FeederControlState.RUN_BACKWARD;
            case WANTS_TO_AUTO_SHOOT:
                return FeederControlState.AUTO_SHOOTING;
            case WANTS_TO_RUN_INDEX:
                return FeederControlState.RUN_INDEX;
            case WANTS_TO_FURTHER_INDEX:
                return FeederControlState.FURTHER_INDEXING;
            case WANTS_TO_SHOOT:
                return FeederControlState.SHOOTING;
            case WANTS_TO_PID_POSITION:
                return FeederControlState.PID_POSITIONING;
            case WANTS_TO_IDLE:
            default:
                return FeederControlState.IDLE;
        }
    }

    public double getPosition(CANEncoder encoder) {
        return encoder.getPosition();
    }

    @Override
    public void outputToDashboard() { }

    @Override
    public void zeroSensors() { }

    @Override
    public void init(double timestamp) {}

    @Override
    public void end(double timestamp) { }
}