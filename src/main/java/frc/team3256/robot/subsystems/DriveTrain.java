package frc.team3256.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.geometry.Twist2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.warriorlib.subsystem.DriveTrainBase;
import frc.team3256.robot.constants.DriveConstants;
import frc.team3256.robot.constants.IDConstants;
import frc.team3256.warriorlib.control.DrivePower;
import frc.team3256.warriorlib.hardware.SparkMAXUtil;
import frc.team3256.warriorlib.loop.Loop;
import frc.team3256.warriorlib.math.Kinematics;
import frc.team3256.warriorlib.math.Rotation;
import frc.team3256.warriorlib.operations.Util;

public class DriveTrain extends DriveTrainBase implements Loop {
    private CANSparkMax leftMaster, rightMaster, leftSlave, rightSlave;
    private static DriveTrain instance;
    private CANEncoder leftEncoder, rightEncoder;
    private CANPIDController leftPIDController, rightPIDController;
    private PigeonIMU gyro;
    private double gyroOffset = 0;

    private DoubleSolenoid shifter;

    public static DriveTrain getInstance() {
        return instance == null ? instance = new DriveTrain() : instance;
    }

    public DriveTrain() {
        gyro = new PigeonIMU(IDConstants.pigeonID);
        gyro.setAccumZAngle(0, 0);
        gyro.setYaw(0, 0);

        leftMaster = SparkMAXUtil.generateGenericSparkMAX(IDConstants.leftMasterID, CANSparkMaxLowLevel.MotorType.kBrushless);
        leftSlave = SparkMAXUtil.generateSlaveSparkMAX(IDConstants.leftSlaveID, CANSparkMaxLowLevel.MotorType.kBrushless, leftMaster);
        rightMaster = SparkMAXUtil.generateGenericSparkMAX(IDConstants.rightMasterID, CANSparkMaxLowLevel.MotorType.kBrushless);
        rightSlave = SparkMAXUtil.generateSlaveSparkMAX(IDConstants.rightSlaveID, CANSparkMaxLowLevel.MotorType.kBrushless, rightMaster);
        leftMaster.setSmartCurrentLimit(60);
        leftSlave.setSmartCurrentLimit(60);
        rightMaster.setSmartCurrentLimit(60);
        rightSlave.setSmartCurrentLimit(60);

        leftEncoder = leftMaster.getEncoder();
        rightEncoder = rightMaster.getEncoder();

        leftPIDController = leftMaster.getPIDController();
        rightPIDController = rightMaster.getPIDController();

        SparkMAXUtil.setPIDGains(leftPIDController, DriveConstants.kVelocityLowGearSlot, DriveConstants.kVelocityLowGearP, DriveConstants.kVelocityLowGearI, DriveConstants.kVelocityLowGearD, DriveConstants.kVelocityLowGearF, DriveConstants.kVelocityLowGearIZone);
        SparkMAXUtil.setPIDGains(rightPIDController, DriveConstants.kVelocityLowGearSlot, DriveConstants.kVelocityLowGearP, DriveConstants.kVelocityLowGearI, DriveConstants.kVelocityLowGearD, DriveConstants.kVelocityLowGearF, DriveConstants.kVelocityLowGearIZone);

        SparkMAXUtil.setPIDGains(leftPIDController, DriveConstants.kVelocityHighGearSlot, DriveConstants.kVelocityHighGearP, DriveConstants.kVelocityHighGearI, DriveConstants.kVelocityHighGearD, DriveConstants.kVelocityHighGearF, DriveConstants.kVelocityHighGearIZone);
        SparkMAXUtil.setPIDGains(rightPIDController, DriveConstants.kVelocityHighGearSlot, DriveConstants.kVelocityHighGearP, DriveConstants.kVelocityHighGearI, DriveConstants.kVelocityHighGearD, DriveConstants.kVelocityHighGearF, DriveConstants.kVelocityHighGearIZone);

        SparkMAXUtil.setBrakeMode(leftMaster, leftSlave, rightMaster, rightSlave);

//        rightMaster.setInverted(true);
//        leftMaster.setInverted(false);

        rightMaster.setInverted(false);
        leftMaster.setInverted(true);

        Util.setGearRatio(DriveConstants.kGearRatio);
        Util.setWheelDiameter(DriveConstants.kWheelDiameter);

        shifter = new DoubleSolenoid(IDConstants.pcmID,IDConstants.shifterForwardChannel,IDConstants.shifterReverseChannel);
    }

    public void setPowerOpenLoop(double leftPower, double rightPower) {
//        leftMaster.setIdleMode(CANSparkMax.IdleMode.kCoast);
//        rightMaster.setIdleMode(CANSparkMax.IdleMode.kCoast);
        leftMaster.set(leftPower);
        rightMaster.set(rightPower);
    }


    public void setInvertedReverse() {
        leftMaster.setInverted(false);
        rightMaster.setInverted(true);
    }

    public void setInvertedNormal() {
        leftMaster.setInverted(true);
        rightMaster.setInverted(false);
    }

    public DrivePower cheesyishDrive(double throttle, double turn, boolean quickTurn) {
        if (Util.epsilonEquals(throttle, 0.0, 0.04)) {
            throttle = 0.0;
        }

        if (Util.epsilonEquals(turn, 0.0, 0.035)) { //0.035
            turn = 0.0;
        }

        final double kWheelGain = 0.05;
        final double kWheelNonlinearity = 0.07; //0.05, 0.09
        final double denominator = Math.sin(Math.PI / 2.0 * kWheelNonlinearity);
        // Apply a sin function that's scaled to make it feel better.
        if (!quickTurn) {
            turn = Math.sin(Math.PI / 2.0 * kWheelNonlinearity * turn);
            turn = Math.sin(Math.PI / 2.0 * kWheelNonlinearity * turn);
            turn = turn / denominator / denominator * Math.abs(throttle);
        }

        turn *= kWheelGain;
        DrivePower signal = Kinematics.inverseKinematics(new Twist2d(throttle, 0.0, turn), DriveConstants.kRobotTrackWidth, DriveConstants.kTrackScrubFactor);

//        return new DrivePower(signal.getRight(), signal.getLeft(), false); //quickTurn
        return new DrivePower(signal.getLeft(), signal.getRight(), false); //quickTurn
    }

    public void setHighGear(boolean highGear) {
        SmartDashboard.putBoolean("High Gear", highGear);
        shifter.set(highGear ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    }

    public void setBrakeMode() {
        SparkMAXUtil.setBrakeMode(leftMaster, leftSlave, rightMaster, rightSlave);
    }

    public void setCoastMode() {
        SparkMAXUtil.setCoastMode(leftMaster, leftSlave, rightMaster, rightSlave);
    }

    /**
     * Runs to zero power right away
     */
    public void runZeroPower() {
        leftMaster.set(0);
        rightMaster.set(0);
    }

    @Override
    public double getLeftDistance() {
        return Util.rotationsToPosition(leftEncoder.getPosition());
    }

    @Override
    public double getRightDistance() {
        return Util.rotationsToPosition(rightEncoder.getPosition());
    }

    @Override
    public Rotation getRotationAngle() {
        return Rotation.fromDegrees(getAngle() + 90);
    }

    @Override
    public double getLeftVelocity() {
        return Util.rpmToVelocity(leftEncoder.getVelocity());
    }

    @Override
    public double getRightVelocity() {
        return Util.rpmToVelocity(rightEncoder.getVelocity());
    }


    @Override
    public void setVelocityClosedLoop(double left, double right) {
        leftPIDController.setReference(Util.inchesPerSecToRPM(left) * DriveConstants.kGearRatio, ControlType.kVelocity, DriveConstants.kVelocityHighGearSlot);
        rightPIDController.setReference(Util.inchesPerSecToRPM(right) * DriveConstants.kGearRatio, ControlType.kVelocity, DriveConstants.kVelocityHighGearSlot);
    }

    @Override
    public void resetEncoders() {
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    @Override
    public void resetGyro() {
        gyro.setYaw(0, 0);
        gyro.setAccumZAngle(0, 0);
    }

    public PigeonIMU getGyro() {
        return gyro;
    }

    public double getAngle() {
        double[] ypr = new double[3];
        gyro.getYawPitchRoll(ypr);
        return ypr[0] + gyroOffset;
    }

    public void setGyroOffset(double offset) {
        this.gyroOffset = offset;
    }

    @Override
    public void outputToDashboard() { }

    @Override
    public void zeroSensors() { }

    @Override
    public void init(double timestamp) { }

    @Override
    public void update(double timestamp) { }

    @Override
    public void end(double timestamp) { }
}
