package frc.team3256.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.constants.FeederConstants;
import frc.team3256.robot.constants.IDConstants;
import frc.team3256.robot.operations.SparkUtil;
import frc.team3256.warriorlib.hardware.SparkMAXUtil;
import frc.team3256.warriorlib.hardware.TalonFXUtil;
import frc.team3256.warriorlib.hardware.TalonSRXUtil;
import frc.team3256.warriorlib.subsystem.SubsystemBase;

import static frc.team3256.robot.constants.IDConstants.*;
import static frc.team3256.robot.constants.IDConstants.rightFlywheelID;

public class Feeder extends SubsystemBase {

    private CANSparkMax mFeeder;
    private WPI_TalonSRX mCenterMech;
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

    private WPI_TalonFX mLeftFlywheel, mRightFlywheel;


    boolean mStateChanged = false;
    boolean mWantedStateChanged = false;

    private static Feeder instance;
    public static Feeder getInstance() { return instance == null ? instance = new Feeder() : instance; }

    private Feeder() {
        mCenterMech = TalonSRXUtil.generateGenericTalon(IDConstants.centerMechID);
        mCenterMech.enableCurrentLimit(true);
        mCenterMech.configContinuousCurrentLimit(30);
        mCenterMech.set(0);


        mLeftFlywheel = TalonFXUtil.generateGenericTalon(leftFlywheelID); //TBD
        mRightFlywheel = TalonFXUtil.generateGenericTalon(rightFlywheelID);
//        flywheelPIDController = new PIDController(0.0006,0.00000065,0.000073); //0.000063
        TalonFXUtil.setCoastMode(mLeftFlywheel, mRightFlywheel);
        mLeftFlywheel.setInverted(true);
        mRightFlywheel.setInverted(false);

        mFeeder = SparkMAXUtil.generateGenericSparkMAX(feederID, CANSparkMaxLowLevel.MotorType.kBrushless);
        mFeeder.setInverted(true);
        SparkUtil.setWheelDiameter(FeederConstants.kWheelDiameter);
        SparkMAXUtil.setBrakeMode(mFeeder);
        mBar = TalonSRXUtil.generateGenericTalon(turretBarID);
        mFeeder.setSmartCurrentLimit(30);
        mBar.setInverted(false);
        mFeeder.burnFlash();

        feederPIDController = new PIDController(FeederConstants.kP,FeederConstants.kI,FeederConstants.kD);
        feederPIDController.setTolerance(FeederConstants.positionTolerance);

        SmartDashboard.putData(feederPIDController);
    }

    public void setWantedState(Feeder.WantedState wantedState) { this.mWantedState = wantedState; }

    @Override
    public void update(double timestamp) {
//        return;
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
//        mCenterMech.set(-0.5);
//        mRightFlywheel.set(0.3);
//        mLeftFlywheel.set(0.3);

    }

    public boolean isRunIndex(){
        return mCurrentState == FeederControlState.RUN_INDEX;
    }

    public boolean isPidPositioning(){
        return mCurrentState == FeederControlState.PID_POSITIONING;
    }

    public boolean isIdle(){
        return mCurrentState == FeederControlState.IDLE;
    }

    public void zeroFeederEncoder() {
        System.out.println("ZEROING ENCODER");
        mFeeder.getEncoder().setPosition(0);
    }

    public void setPIDPositioning(double positionSetpoint){
        System.out.println("PID: " + mFeeder.getEncoder().getPosition());
        CANEncoder encoder = mFeeder.getEncoder();
        feederPIDController.setSetpoint(positionSetpoint);
        SmartDashboard.putNumber("encoder position", getPosition(encoder));
        double output = feederPIDController.calculate(getPosition(encoder));


        if(atSetpoint()){
            zeroFeederEncoder();
            Feeder.getInstance().setWantedState(WantedState.WANTS_TO_IDLE);
        } else {
            System.out.println("Position Position Error: " + feederPIDController.getSetpoint());
            System.out.println("Output PID: " + output);
            System.out.println("At Stint: " + feederPIDController.atSetpoint());
            mFeeder.set(output);
        }

    }

    public boolean atSetpoint() {
//        System.out.println("At Setpoint: " );
        return feederPIDController.atSetpoint();
    }
    private FeederControlState handleRunForward() {
        SmartDashboard.putString("Feeder State", "FORWARD");
        mFeeder.set(1.0);
        mBar.set(-0.5);
        return defaultStateTransfer();
    }

    private FeederControlState handleRunBackward() {
        //TODO: Put PID Control Here, this runs 50hz when PID posiFeederConstants.kSpaceBetweenPowerCellstioning state is on
        SmartDashboard.putString("Feeder State", "BACKWARD");
        mFeeder.set(-1.0); //0.6
        mBar.set(-0.5);
        return defaultStateTransfer();
    }

    public boolean isRunningBackward(){
        return mCurrentState == FeederControlState.RUN_BACKWARD;
    }

    public boolean isShooting(){
        return mCurrentState == FeederControlState.SHOOTING;
    }
    private FeederControlState handlePIDPositioning(){
        //TODO: Put PID Control Here, this runs 50hz when PID posiFeederConstants.kSpaceBetweenPowerCellstioning state is on
        SmartDashboard.putString("Feeder State", "PID");
//        mBar.set(-0.5);
        setPIDPositioning(FeederConstants.kSpaceBetweenPowerCells);
        return defaultStateTransfer();
    }

    private FeederControlState handleFurtherIndexing() {
        SmartDashboard.putString("Feeder State", "Further INDEXING");

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
        SmartDashboard.putString("Feeder State", "Auto Shoot");
        mFeeder.set(0.7);
        mBar.set(-0.5);
        return defaultStateTransfer();
    }

    //TODO: EVERYTHING 100 TEST
    //TODO: Dylan - This probably needs to be in constants file, actually all of this stuff does
    private FeederControlState handleIndex() {
        mFeeder.set(0.75); //0.3
//        mBar.set(-0.5);
        SmartDashboard.putString("State", "INDEXING");
        return defaultStateTransfer();
    }

    private FeederControlState handleShoot() {
        SmartDashboard.putString("Feeder State", "Shoot");
        mFeeder.set(0.5);
        mBar.set(-0.5);
        return defaultStateTransfer();
    }

    private FeederControlState handleIdle() {
        SmartDashboard.putString("Feeder State", "IDLING");
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
    public void outputToDashboard() {
        SmartDashboard.putNumber("PID Error", feederPIDController.getPositionError());
        SmartDashboard.putNumber("Feeder Position", SparkUtil.encoderUnitsToPosition(mFeeder.getEncoder().getPosition(), FeederConstants.kGearRatio));
        SmartDashboard.putNumber("At Setpoint",atSetpoint() ? 1 : 0);
    }

    @Override
    public void zeroSensors() { }

    @Override
    public void init(double timestamp) {}

    @Override
    public void end(double timestamp) { }
}