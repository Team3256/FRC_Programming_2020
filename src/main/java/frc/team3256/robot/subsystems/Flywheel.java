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

public class Flywheel extends SubsystemBase { //A test for the flywheel state machine, only runs. does not adjust velocity.
    private WPI_TalonFX mLeftFlywheel, mRightFlywheel;

    WantedState mPrevWantedState;
    boolean mStateChanged;
    boolean mWantedStateChanged;
    double velocitySetpoint;
    private PIDController flywheelPIDController;

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
        flywheelPIDController = new PIDController(0.0006*0.5,0.00000065,0.000073); //0.000063
        TalonFXUtil.setPIDGains(mLeftFlywheel, 0, FlywheelConstants.kFlywheelP, FlywheelConstants.kFlywheelI, FlywheelConstants.kFlywheelD, FlywheelConstants.kFlywheelF);
        TalonFXUtil.setPIDGains(mRightFlywheel, 0, FlywheelConstants.kFlywheelP, FlywheelConstants.kFlywheelI, FlywheelConstants.kFlywheelD, FlywheelConstants.kFlywheelF);
        TalonFXUtil.setCoastMode(mLeftFlywheel, mRightFlywheel);
        mLeftFlywheel.setInverted(true);
        mRightFlywheel.setInverted(false);
    }

    public void setWantedState(WantedState wantedState) { this.mWantedState = wantedState; }

    @Override
    public void update(double timestamp) {
        if (mPrevWantedState != mWantedState) {
            mWantedStateChanged = true;
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

        this.outputToDashboard();
    }

    private FlywheelState handleRun() {
        setFlywheelVelocityPID(velocitySetpoint);
        return defaultStateTransfer();
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

    private void setFlywheelVelocity(double speed) { //Run flywheel at set velocity in RPM
//        mLeftFlywheel.set(ControlMode.Velocity, rpmToSensorUnits(speed));
//        mRightFlywheel.set(ControlMode.Velocity, rpmToSensorUnits(speed));
        mLeftFlywheel.set(0.5);
        mRightFlywheel.set(0.5);
    }

    private void setFlywheelVelocityPID(double speed) {
        double output = flywheelPIDController.calculate(getVelocity(), speed) + calculateFeedForward(speed);
        mLeftFlywheel.set(ControlMode.PercentOutput, output);
        mRightFlywheel.set(ControlMode.PercentOutput, output);
    }

    private double calculateFeedForward(double flywheelRPM) {
        return flywheelRPM/6521.5;
    }

    private double rpmToSensorUnits(double rpm) {
        return (rpm/600) * 2048;
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