/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team3256.robot;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.auto.modes.DoNothingAutoMode;
import frc.team3256.robot.auto.modes.RightDriveShootAutoMode;
import frc.team3256.robot.auto.modes.RightDriveTrenchShootAutoMode;
import frc.team3256.robot.auto.modes.RightDriveTrenchTenBallAutoMode;
import frc.team3256.robot.auto.paths.Paths;
import frc.team3256.robot.hardware.Limelight;
import frc.team3256.robot.subsystems.Drivetrain;
import frc.team3256.robot.subsystems.Flywheel;
import frc.team3256.robot.subsystems.Intake;
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

  TeleopUpdater teleopUpdater;
  private Drivetrain drivetrain;
  private Intake intake;
  private PoseEstimator poseEstimator;
  private PurePursuitTracker purePursuitTracker;
  private Limelight limelight = Limelight.getInstance();
  private Flywheel flywheel = Flywheel.getInstance();

  private AutoModeExecuter autoModeExecuter;
  private boolean maintainAutoExecution = true;

  private Looper enabledLooper, poseEstimatorLooper, limelightLooper;
  SendableChooser<AutoModeBase> autoChooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    teleopUpdater = new TeleopUpdater();
    drivetrain = Drivetrain.getInstance();
    intake = Intake.getInstance();
    DriveTrainBase.setDriveTrain(drivetrain);
    purePursuitTracker = PurePursuitTracker.getInstance();
    limelight.init();

    Paths.initialize();
    enabledLooper = new Looper(1 / 200D);

    // Reset sensors
    drivetrain.resetEncoders();
    drivetrain.resetGyro();

    enabledLooper.addLoops(drivetrain);

    poseEstimatorLooper = new Looper(1 / 50D);
    poseEstimator = PoseEstimator.getInstance();
    poseEstimatorLooper.addLoops(poseEstimator);
    poseEstimatorLooper.start();

    limelightLooper = new Looper(1 / 100D);

    autoChooser.setDefaultOption("Do Nothing", new DoNothingAutoMode());
    autoChooser.addOption("Right Shoot Auto", new RightDriveShootAutoMode());
    autoChooser.addOption("Right Trench Shoot Auto", new RightDriveTrenchShootAutoMode());
    autoChooser.addOption("Right Trench Ten Ball Shoot Auto", new RightDriveTrenchTenBallAutoMode());

    SmartDashboard.putData(autoChooser);

    SmartDashboard.putNumber("wanted vel", 1000);
    SmartDashboard.putNumber("hood pos", 0);
    SmartDashboard.putNumber("Ball Count Reset", 0);
  }

  @Override
  public void autonomousInit() {
    drivetrain.resetEncoders();
    drivetrain.resetGyro();
    drivetrain.setBrakeMode();

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
    SmartDashboard.putNumber("Gyro Angle", drivetrain.getRotationAngle().degrees());
    intake.update(0);

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
    enabledLooper.start();
    limelightLooper.start();
    drivetrain.resetGyro();
    drivetrain.resetEncoders();
    drivetrain.setBrakeMode();
//    drivetrain.setCoastMode();
    poseEstimator.reset();
  }

  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("Pose X", poseEstimator.getPose().x);
    SmartDashboard.putNumber("Pose Y", poseEstimator.getPose().y);
    SmartDashboard.putNumber("Gyro Angle", drivetrain.getAngle());
    SmartDashboard.putNumber("distance to target", limelight.getDistanceToTarget());
    SmartDashboard.putNumber("distance to inner", limelight.getDistanceToInner());
    SmartDashboard.putNumber("Ball counter", teleopUpdater.getBallCounter());
    SmartDashboard.putNumber("wanted hood", teleopUpdater.angleToHoodPos(limelight.getAngleToTarget()));
    SmartDashboard.putNumber("wanted hood degrees", limelight.getAngleToTarget() * 180/Math.PI);
    SmartDashboard.putNumber("wanted vel", teleopUpdater.velToFlywheelVel(limelight.getVelToTarget()));
    SmartDashboard.putNumber("ACTUAL VEL", flywheel.getVelocity());
    teleopUpdater.update();
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
