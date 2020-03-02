package frc.team3256.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.team3256.robot.constants.FlywheelConstants;
import frc.team3256.warriorlib.hardware.TalonFXUtil;
import frc.team3256.warriorlib.operations.Util;
import frc.team3256.warriorlib.subsystem.SubsystemBase;

import static frc.team3256.robot.constants.IDConstants.leftFlywheelID;
import static frc.team3256.robot.constants.IDConstants.rightFlywheelID;

public class Flywheel extends SubsystemBase {
    private WPI_TalonFX mLeftFlywheel, mRightFlywheel;

    WantedState mPrevWantedState;
    boolean mStateChanged;
    boolean mWantedStateChanged;
    double velocitySetpoint;
    private PIDController flywheelPIDController;
    private boolean atSetpoint;
    private boolean readyToShoot;

    public enum FlywheelState {
        RUN,
        IDLE,
    }

    public enum WantedState {
        WANTS_TO_RUN,
        WANTS_TO_IDLE,
    }

    private FlywheelState mCurrentState = FlywheelState.IDLE;
    private WantedState mWantedState = WantedState.WANTS_TO_IDLE;

    private static Flywheel instance;

    public WantedState getWantedState() {
        return mWantedState;
    }

    public static Flywheel getInstance() { return instance == null ? instance = new Flywheel() : instance; }

    private Flywheel() {
        mLeftFlywheel = TalonFXUtil.generateGenericTalon(leftFlywheelID); //TBD
        mRightFlywheel = TalonFXUtil.generateGenericTalon(rightFlywheelID);
        flywheelPIDController = new PIDController(0.0006,0.00000065,0.000073); //0.000063
        TalonFXUtil.setCoastMode(mLeftFlywheel, mRightFlywheel);
        mLeftFlywheel.setInverted(true);
        mRightFlywheel.setInverted(false);
        atSetpoint = false;
    }

    public void setWantedState(WantedState wantedState) { this.mWantedState = wantedState; }

    @Override
    public void update(double timestamp) {
        if (mPrevWantedState != mWantedState) {
            mWantedStateChanged = true;
            if(mCurrentState == FlywheelState.RUN)
                flywheelPIDController.reset();
            mPrevWantedState = mWantedState;
        } else mWantedStateChanged = false;
        FlywheelState newState;
        switch (mCurrentState) {
            case RUN:
                newState = handleRun();
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
        atSetpoint = getVelocity() < (velocitySetpoint + FlywheelConstants.kAtSetpointTolerance) && getVelocity() > (velocitySetpoint - FlywheelConstants.kAtSetpointTolerance);
        this.outputToDashboard();
    }

    private FlywheelState handleRun() {
//        setFlywheelVelocity(velocitySetpoint);
//        bangBangFlywheel(velocitySetpoint);
        setFlywheelVelocityPID(velocitySetpoint);
        return defaultStateTransfer();
    }

    private double toActualSetpoint(double velocity) {
        return velocity * 1.02 - 90;
    }

    private FlywheelState handleIdle() { //Stops all flywheel motors
        stopFlywheel();
        return defaultStateTransfer();
    }

    private FlywheelState defaultStateTransfer() {
        switch (mWantedState) {
            case WANTS_TO_RUN:
                return FlywheelState.RUN;
            case WANTS_TO_IDLE:
            default:
                return FlywheelState.IDLE;
        }
    }

    public void setVelocitySetpoint(double velocity) {
        velocitySetpoint = velocity;
    }

    private void setFlywheelVoltage(double speed) { //Sets flywheel speed
        mLeftFlywheel.set(speed);
        mRightFlywheel.set(speed);
    }

    private void stopFlywheel(){ //Stops flywheel
        mLeftFlywheel.stopMotor();
        mRightFlywheel.stopMotor();
    }

    private void bangBangFlywheel(double speed) {
        double output = flywheelPIDController.calculate(getVelocity(), speed);
        output = Util.clip(output, -1, 1);
        mLeftFlywheel.set(output);
        mRightFlywheel.set(output);
    }

    private void setFlywheelVelocityPID(double speed) {
        double output = flywheelPIDController.calculate(getVelocity(), speed) + calculateFeedForward(speed);
        mLeftFlywheel.set(ControlMode.PercentOutput, output);
        mRightFlywheel.set(ControlMode.PercentOutput, output);
    }

    public void setReadyToShoot(boolean readyToShoot) {
        this.readyToShoot = readyToShoot;
    }

    public boolean getReadyToShoot() {
        return readyToShoot;
    }

    private double calculateFeedForward(double flywheelRPM) {
        return flywheelRPM/6521.5;
    }

    private double rpmToSensorUnits(double rpm) {
        return (rpm/600) * 2048;
    }

    public boolean atSetpointVelocity() {
        return atSetpoint;
    }

    private double sensorUnitsToRPM(double sensorUnits) {
        return (sensorUnits/2048) * 600;
    }

    @Override
    public void outputToDashboard() {
    }

    @Override
    public void zeroSensors() { }

    @Override
    public void init(double timestamp) {
        velocitySetpoint = 0;
    }

    @Override
    public void end(double timestamp) { }

    public double getVelocity() {
        return sensorUnitsToRPM(mLeftFlywheel.getSelectedSensorVelocity());
    }

    public double getSensorVelocity() {
        return mLeftFlywheel.getSelectedSensorVelocity();
    }

    public TalonFX getMotor(){
        return mLeftFlywheel;
    }
}