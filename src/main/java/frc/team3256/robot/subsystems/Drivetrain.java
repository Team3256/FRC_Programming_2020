package frc.team3256.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.geometry.Twist2d;
import frc.team3256.robot.constants.DriveConstants;
import frc.team3256.warriorlib.control.DrivePower;
import frc.team3256.warriorlib.hardware.SparkMAXUtil;
import frc.team3256.warriorlib.loop.Loop;
import frc.team3256.warriorlib.math.Kinematics;
import frc.team3256.warriorlib.math.Rotation;
import frc.team3256.warriorlib.operations.Util;
import frc.team3256.warriorlib.subsystem.DriveTrainBase;

public class Drivetrain extends DriveTrainBase implements Loop {
    private CANSparkMax leftMaster, rightMaster, leftSlave, rightSlave;
    private static Drivetrain instance;
    private CANEncoder leftEncoder, rightEncoder;
    private CANPIDController leftPIDController, rightPIDController;
    private PigeonIMU gyro;
    private double gyroOffset = 0;
    private static double prevTurn = 0.0;

    private DoubleSolenoid shifter;

    public static Drivetrain getInstance() {
        return instance == null ? instance = new Drivetrain() : instance;
    }

    public Drivetrain() {
        gyro = new PigeonIMU(DriveConstants.pigeonIMUID);
        gyro.setAccumZAngle(0, 0);
        gyro.setYaw(0, 0);

        leftMaster = SparkMAXUtil.generateGenericSparkMAX(DriveConstants.leftMasterID, CANSparkMaxLowLevel.MotorType.kBrushless);
        leftSlave = SparkMAXUtil.generateSlaveSparkMAX(DriveConstants.leftSlaveID, CANSparkMaxLowLevel.MotorType.kBrushless, leftMaster);
        rightMaster = SparkMAXUtil.generateGenericSparkMAX(DriveConstants.rightMasterID, CANSparkMaxLowLevel.MotorType.kBrushless);
        rightSlave = SparkMAXUtil.generateSlaveSparkMAX(DriveConstants.rightSlaveID, CANSparkMaxLowLevel.MotorType.kBrushless, rightMaster);
        leftMaster.setSmartCurrentLimit(60);
        leftSlave.setSmartCurrentLimit(60);
        rightMaster.setSmartCurrentLimit(60);
        rightSlave.setSmartCurrentLimit(60);

        leftMaster.burnFlash();
        leftSlave.burnFlash();
        rightMaster.burnFlash();
        rightSlave.burnFlash();

        leftEncoder = leftMaster.getEncoder();
        rightEncoder = rightMaster.getEncoder();

        leftPIDController = leftMaster.getPIDController();
        rightPIDController = rightMaster.getPIDController();

        SparkMAXUtil.setPIDGains(leftPIDController, DriveConstants.kVelocityLowGearSlot, DriveConstants.kVelocityLowGearP, DriveConstants.kVelocityLowGearI, DriveConstants.kVelocityLowGearD, DriveConstants.kVelocityLowGearF, DriveConstants.kVelocityLowGearIZone);
        SparkMAXUtil.setPIDGains(rightPIDController, DriveConstants.kVelocityLowGearSlot, DriveConstants.kVelocityLowGearP, DriveConstants.kVelocityLowGearI, DriveConstants.kVelocityLowGearD, DriveConstants.kVelocityLowGearF, DriveConstants.kVelocityLowGearIZone);

        SparkMAXUtil.setPIDGains(leftPIDController, DriveConstants.kVelocityHighGearSlot, DriveConstants.kVelocityHighGearP, DriveConstants.kVelocityHighGearI, DriveConstants.kVelocityHighGearD, DriveConstants.kVelocityHighGearF, DriveConstants.kVelocityHighGearIZone);
        SparkMAXUtil.setPIDGains(rightPIDController, DriveConstants.kVelocityHighGearSlot, DriveConstants.kVelocityHighGearP, DriveConstants.kVelocityHighGearI, DriveConstants.kVelocityHighGearD, DriveConstants.kVelocityHighGearF, DriveConstants.kVelocityHighGearIZone);

        SparkMAXUtil.setBrakeMode(leftMaster, leftSlave, rightMaster, rightSlave);

        rightMaster.setInverted(false);
        leftMaster.setInverted(true);

        Util.setGearRatio(DriveConstants.kGearRatio);
        Util.setWheelDiameter(DriveConstants.kWheelDiameter);

        shifter = new DoubleSolenoid(0,4,3);
    }

    public void setPowerOpenLoop(double leftPower, double rightPower) {
        leftMaster.set(leftPower);
        rightMaster.set(rightPower);
    }

    public DrivePower cheesyishDrive(double throttle, double turn, boolean quickTurn) {
        if (Util.epsilonEquals(throttle, 0.0, 0.04)) {
            throttle = 0.0;
        }

        if (Util.epsilonEquals(turn, 0.0, 0.035)) { //0.035
            turn = 0.0;
        }

        final double kWheelGain = 0.05;
        final double kWheelNonlinearity = 0.05;
        final double denominator = Math.sin(Math.PI / 2.0 * kWheelNonlinearity);
        // Apply a sin function that's scaled to make it feel better.
        if (!quickTurn) {
            turn = Math.sin(Math.PI / 2.0 * kWheelNonlinearity * turn);
            turn = Math.sin(Math.PI / 2.0 * kWheelNonlinearity * turn);
            turn = turn / denominator / denominator * Math.abs(throttle);
        }

        turn *= kWheelGain;
        DrivePower signal = Kinematics.inverseKinematics(new Twist2d(throttle, 0.0, turn), DriveConstants.kRobotTrackWidth, DriveConstants.kTrackScrubFactor);
        double scaling_factor = Math.max(1.0, Math.max(Math.abs(signal.getLeft()), Math.abs(signal.getRight())));
        return new DrivePower(signal.getLeft(), signal.getRight(), quickTurn);
    }

    public static double clamp(double val) {
        return Math.max(Math.min(val, 1.0), -1.0);
    }

    public void setHighGear(boolean highGear) {
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

    public static DrivePower curvatureDrive(double throttle, double turn, boolean quickTurn, boolean highGear) {
        //boolean highGear = true;
        if (Math.abs(turn) <= 0.15) { //deadband
            turn = 0;
        }
        if (Math.abs(throttle) <= 0.15) {
            throttle = 0;
        }
        double angularPower, overPower;

        if (quickTurn) {
            highGear = false;
            if (Math.abs(throttle) < 0.2) {
                DriveConstants.quickStopAccumulator = (1 - DriveConstants.kQuickStopAlpha) * DriveConstants.quickStopAccumulator + DriveConstants.kQuickStopAlpha * clamp(turn) * DriveConstants.kQuickStopScalar;
            }
            overPower = 1.0;
            angularPower = turn/DriveConstants.kAngularPowerScalar;
            if (Math.abs(turn - prevTurn) > DriveConstants.kQuickTurnDeltaLimit) {
                //System.out.println("TURN: " + turn);
                //System.out.println("PREVIOUS TURN: " + prevTurn);
                if (turn > 0) {
                    angularPower = prevTurn + DriveConstants.kQuickTurnDeltaLimit;
                    //System.out.println("ANGULAR POWER: " + angularPower);
                } else
                    angularPower = prevTurn - DriveConstants.kQuickTurnDeltaLimit;
            }
        } else {
            overPower = 0.0;
            angularPower = (Math.abs(throttle) * turn - DriveConstants.quickStopAccumulator)/DriveConstants.kAngularPowerScalar;
            if (DriveConstants.quickStopAccumulator > 1) {
                DriveConstants.quickStopAccumulator -= 1;
            } else if (DriveConstants.quickStopAccumulator < -1) {
                DriveConstants.quickStopAccumulator += 1;
            } else {
                DriveConstants.quickStopAccumulator = 0.0;
            }
        }
        prevTurn = turn;
        double left, right;

        left = throttle + angularPower;
        right = throttle - angularPower;

        if (left > 1.0) {
            right -= overPower * (left - 1.0);
            left = 1.0;
        } else if (right > 1.0) {
            left -= overPower * (right - 1.0);
            right = 1.0;
        } else if (left < -1.0) {
            right += overPower * (-1.0 - left);
            left = -1.0;
        } else if (right < -1.0) {
            left += overPower * (-1.0 - right);
            right = -1.0;
        }
        if (!quickTurn) { //we only want cubic drive if we aren't quickturning
            left *= left * left;
            right *= right * right;
        }
        return new DrivePower(left, right, highGear);
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

    public CANSparkMax getLeftMotor(){
        return leftMaster;
    }
    public CANSparkMax getRightMotor(){
        return rightMaster;
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
    public void outputToDashboard() {

    }

    @Override
    public void zeroSensors() {

    }

    @Override
    public void init(double timestamp) {

    }

    @Override
    public void update(double timestamp) {

    }

    @Override
    public void end(double timestamp) {

    }
}
