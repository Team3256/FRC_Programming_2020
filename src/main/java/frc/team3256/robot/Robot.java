/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team3256.robot;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.hardware.AirCompressor;
import frc.team3256.robot.hardware.IRSensors;
import frc.team3256.robot.helper.BallCounter;
import frc.team3256.robot.helper.ShootingKinematics;
import frc.team3256.robot.auto.modes.DoNothingAutoMode;
import frc.team3256.robot.auto.modes.RightDriveShootAutoMode;
import frc.team3256.robot.auto.modes.RightDriveTrenchShootAutoMode;
import frc.team3256.robot.auto.modes.RightDriveTrenchTenBallAutoMode;
import frc.team3256.robot.auto.paths.Paths;
import frc.team3256.robot.hardware.Limelight;
import frc.team3256.robot.log.FalconAutoLogger;
import frc.team3256.robot.log.Logger;
import frc.team3256.robot.log.LoggerUpdateLooper;
import frc.team3256.robot.subsystems.*;
import frc.team3256.robot.teleop.TeleopUpdater;
import frc.team3256.warriorlib.auto.AutoModeBase;
import frc.team3256.warriorlib.auto.AutoModeExecuter;
import frc.team3256.warriorlib.auto.purepursuit.PoseEstimator;
import frc.team3256.warriorlib.auto.purepursuit.PurePursuitTracker;
import frc.team3256.warriorlib.control.DrivePower;
import frc.team3256.warriorlib.hardware.SparkMAXUtil;
import frc.team3256.warriorlib.loop.Looper;
import frc.team3256.warriorlib.subsystem.DriveTrainBase;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private final boolean WANTS_TO_LOG = false;

  private TeleopUpdater teleopUpdater;
  private DriveTrain drivetrain = DriveTrain.getInstance();
  private Intake intake = Intake.getInstance();
  private Limelight limelight = Limelight.getInstance();
  private Flywheel flywheel = Flywheel.getInstance();
  private Feeder feeder = Feeder.getInstance();
  private Hood hood = Hood.getInstance();
  private Turret turret = Turret.getInstance();
  private Hanger hanger = Hanger.getInstance();
  private BallCounter ballCounter = BallCounter.getInstance();
  private AirCompressor airCompressor = AirCompressor.getInstance();
  private PoseEstimator poseEstimator;
  private PurePursuitTracker purePursuitTracker = PurePursuitTracker.getInstance();

  private AutoModeExecuter autoModeExecuter;
  private boolean maintainAutoExecution = true;

  private Looper enabledLooper,
          poseEstimatorLooper,
          limelightLooper,
          flywheelLooper,
          loggerLooper;

  SendableChooser<AutoModeBase> autoChooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    airCompressor.turnOnCompressor();
    teleopUpdater = new TeleopUpdater();
    DriveTrainBase.setDriveTrain(drivetrain);

    Paths.initialize();

    drivetrain.resetEncoders();
    drivetrain.resetGyro();

    enabledLooper = new Looper(1 / 200D);
    enabledLooper.addLoops(intake, hood, feeder, ballCounter, hanger, turret);

    flywheelLooper = new Looper(1/500D);
    flywheelLooper.addLoops(flywheel);

    poseEstimatorLooper = new Looper(1 / 50D);
    poseEstimator = PoseEstimator.getInstance();
    poseEstimatorLooper.addLoops(poseEstimator);

    limelightLooper = new Looper(1 / 100D);
    limelightLooper.addLoops(limelight);

    autoChooser.setDefaultOption("Do Nothing", new DoNothingAutoMode());
    autoChooser.addOption("Right Shoot Auto", new RightDriveShootAutoMode());
    autoChooser.addOption("Right Trench Shoot Auto", new RightDriveTrenchShootAutoMode());
    autoChooser.addOption("Right Trench Ten Ball Shoot Auto", new RightDriveTrenchTenBallAutoMode());
    SmartDashboard.putData(autoChooser);

    limelight.init();

    if(WANTS_TO_LOG) {
      loggerLooper  = new Looper(1/15D);
      Logger.startInitialization();
      FalconAutoLogger.autoLog("Flywheel", "Motor", Flywheel.getInstance().getMotor());
      Logger.finishInitialization();
      loggerLooper.addLoops(LoggerUpdateLooper.getInstance());
    }
  }

  @Override
  public void autonomousInit() {
    drivetrain.resetEncoders();
    drivetrain.resetGyro();
    drivetrain.setBrakeMode();

    limelight.turnOn();

    poseEstimator.reset();
    purePursuitTracker.reset();

    enabledLooper.start();
    flywheelLooper.start();
    poseEstimatorLooper.start();
    limelightLooper.start();

    turret.reset();

    if (SmartDashboard.getBoolean("autoEnabled", true)) {
      maintainAutoExecution = true;

      autoModeExecuter = new AutoModeExecuter();
      autoModeExecuter.setAutoMode(autoChooser.getSelected());
      autoModeExecuter.start();
    }
    else {
      maintainAutoExecution = false;
    }
  }

  @Override
  public void autonomousPeriodic() {
    SmartDashboard.putNumber("Pose X", poseEstimator.getPose().x);
    SmartDashboard.putNumber("Pose Y", poseEstimator.getPose().y);
    SmartDashboard.putNumber("Gyro Angle", drivetrain.getRotationAngle().degrees());

    intake.update(0);
    feeder.update(0);
    turret.update(0);
    hood.update(0);
    flywheel.update(0);

    if (!maintainAutoExecution) {
      teleopUpdater.update();
    }

    else if (autoModeExecuter.isFinished()) {
      maintainAutoExecution = false;
      drivetrain.runZeroPower();
      drivetrain.setCoastMode();
    }
  }

  @Override
  public void teleopInit() {
    limelight.turnOn();
    airCompressor.turnOnCompressor();
    enabledLooper.start();
    flywheelLooper.start();
    limelightLooper.start();

    drivetrain.resetGyro();
    drivetrain.resetEncoders();
    drivetrain.setBrakeMode();
    poseEstimator.reset();
    BallCounter.getInstance().setCount(0);
    if(WANTS_TO_LOG) loggerLooper.start();
  }

  @Override
  public void teleopPeriodic() {
    teleopUpdater.update();
    SmartDashboard.putNumber("distance to outer", limelight.getDistanceToTarget());
    SmartDashboard.putNumber("Ball counter", BallCounter.getInstance().getCount());
    SmartDashboard.putNumber("wanted hood degrees", limelight.getAngleToTarget() * 180/Math.PI);
    SmartDashboard.putNumber("wanted vel", ShootingKinematics.outputVelToFlywheelVel(limelight.getVelToTarget()));
    SmartDashboard.putNumber("TAU", limelight.calculateTau());
    SmartDashboard.putNumber("ACTUAL VEL", flywheel.getVelocity());
    SmartDashboard.putBoolean("Hood Zeroed", Hood.getInstance().isZeroed());
    SmartDashboard.putNumber("wantedEnd", limelight.optimalEndAngle());
//    turret.outputToDashboard();
    if(WANTS_TO_LOG){
      Logger.update();
    }
  }

  @Override
  public void testInit() {

  }

  @Override
  public void testPeriodic() {

  }

  @Override
  public void disabledInit(){
    //TODO: Get rid of coast mode for final
    limelight.turnOff();
    drivetrain.setCoastMode();
    airCompressor.turnOffCompressor();
    if(WANTS_TO_LOG) {
      loggerLooper.stop();
      Logger.flush();
    }
  }

}
