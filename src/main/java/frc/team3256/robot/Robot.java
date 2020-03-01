/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team3256.robot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.hardware.AirCompressor;
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
  //TODO: add burnFlash() to generateSlave method on warriorlib

  private final boolean WANTS_TO_LOG = false;

  TeleopUpdater teleopUpdater;
//  private DriveTrain drivetrain;
  private Intake intake;
  private PoseEstimator poseEstimator;
  private PurePursuitTracker purePursuitTracker;
  private Limelight limelight = Limelight.getInstance();
  private Flywheel flywheel = Flywheel.getInstance();
  private Feeder feeder = Feeder.getInstance();
  private Hood hood = Hood.getInstance();
  private Turret turret = Turret.getInstance();

  private AirCompressor airCompressor = AirCompressor.getInstance();

  private AutoModeExecuter autoModeExecuter;
  private boolean maintainAutoExecution = true;

  Looper loggerLooper;
  private Looper enabledLooper, poseEstimatorLooper, limelightLooper, flywheelLooper;
  SendableChooser<AutoModeBase> autoChooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    airCompressor.turnOnCompressor();
    teleopUpdater = new TeleopUpdater();
//    drivetrain = DriveTrain.getInstance();
    intake = Intake.getInstance();
//    DriveTrainBase.setDriveTrain(drivetrain);
    purePursuitTracker = PurePursuitTracker.getInstance();
    limelight.init();

    Paths.initialize();

    // Reset sensors
//    drivetrain.resetEncoders();
//    drivetrain.resetGyro();

    enabledLooper = new Looper(1 / 200D);
    enabledLooper.addLoops(intake, turret, hood, feeder);
    enabledLooper.start();

    flywheelLooper = new Looper(1/500D);
    flywheelLooper.addLoops(flywheel);

    poseEstimatorLooper = new Looper(1 / 50D);
    poseEstimator = PoseEstimator.getInstance();
    poseEstimatorLooper.addLoops(poseEstimator);
    poseEstimatorLooper.start();

    limelightLooper = new Looper(1 / 100D);
    limelightLooper.addLoops(limelight);
    limelightLooper.start();


    autoChooser.setDefaultOption("Do Nothing", new DoNothingAutoMode());
    autoChooser.addOption("Right Shoot Auto", new RightDriveShootAutoMode());
    autoChooser.addOption("Right Trench Shoot Auto", new RightDriveTrenchShootAutoMode());
    autoChooser.addOption("Right Trench Ten Ball Shoot Auto", new RightDriveTrenchTenBallAutoMode());

    SmartDashboard.putData(autoChooser);

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
//    drivetrain.resetEncoders();
//    drivetrain.resetGyro();
//    drivetrain.setBrakeMode();

    poseEstimator.reset();
    purePursuitTracker.reset();

    enabledLooper.start();

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
//    SmartDashboard.putNumber("Gyro Angle", drivetrain.getRotationAngle().degrees());
    intake.update(0);
    feeder.update(0);
    turret.update(0);
    hood.update(0);

    if (!maintainAutoExecution) {
      teleopUpdater.update();
    }

    else if (autoModeExecuter.isFinished()) {
      maintainAutoExecution = false;
//      drivetrain.runZeroPower();
//      drivetrain.setCoastMode();
    }
  }

  @Override
  public void teleopInit() {
    airCompressor.turnOnCompressor();
    enabledLooper.start();
    flywheelLooper.start();
    limelightLooper.start();
//    drivetrain.resetGyro();
//    drivetrain.resetEncoders();
//    drivetrain.setBrakeMode();
    poseEstimator.reset();
    if(WANTS_TO_LOG) loggerLooper.start();
  }

  @Override
  public void teleopPeriodic() {
    teleopUpdater.update();
    SmartDashboard.putNumber("distance to inner", limelight.getDistanceToInner());
    SmartDashboard.putNumber("Ball counter", BallCounter.getInstance().getCount());
    SmartDashboard.putNumber("wanted hood degrees", limelight.getAngleToTarget() * 180/Math.PI);
//    SmartDashboard.putNumber("wanted vel", ShootingKinematics.velToFlywheelVel(limelight.getVelToTarget()));
    SmartDashboard.putNumber("wanted vel", 4000);
    SmartDashboard.putNumber("TAU", limelight.getTx());
    SmartDashboard.putNumber("ACTUAL VEL", flywheel.getVelocity());
    SmartDashboard.putNumber("ACTUAL VEL NUM", flywheel.getVelocity());
    SmartDashboard.putNumber("ACTUAL VEL SENSOR UNITS", flywheel.getSensorVelocity());
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
  public void disabledInit(){
    airCompressor.turnOffCompressor();
    if(WANTS_TO_LOG) {
      loggerLooper.stop();
      Logger.flush();
    }
  }

}
