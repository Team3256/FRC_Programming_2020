/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team3256.robot;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.auto.modes.*;
import frc.team3256.robot.hardware.AirCompressor;
import frc.team3256.robot.helper.BallCounter;
import frc.team3256.robot.auto.paths.Paths;
import frc.team3256.robot.hardware.Limelight;
import frc.team3256.robot.helper.GalacticSearchPathSelection;
import frc.team3256.robot.helper.ShootingKinematics;
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
  private final boolean WANTS_TO_LOG = false;

  private TeleopUpdater teleopUpdater;
  private DriveTrain drivetrain = DriveTrain.getInstance();
  private Intake intake = Intake.getInstance();
  private Limelight limelight = Limelight.getInstance();
  private Flywheel flywheel = Flywheel.getInstance();
  private Feeder feeder = Feeder.getInstance();
  private Hood hood = Hood.getInstance();
  private Turret turret = Turret.getInstance();
//  private Hanger hanger = Hanger.getInstance();
  private BallCounter ballCounter = BallCounter.getInstance();
  private AirCompressor airCompressor = AirCompressor.getInstance();
  private PoseEstimator poseEstimator;
  private PurePursuitTracker purePursuitTracker = PurePursuitTracker.getInstance();


  private GalacticSearchPathSelection g = GalacticSearchPathSelection.getInstance();

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
    LiveWindow.disableAllTelemetry();
    airCompressor.turnOnCompressor();
    teleopUpdater = new TeleopUpdater();
    DriveTrainBase.setDriveTrain(drivetrain);
    autoModeExecuter = new AutoModeExecuter();

    limelight.turnOff();

    Paths.initialize();

    drivetrain.resetEncoders();
    drivetrain.resetGyro();

    enabledLooper = new Looper(1 / 200D);
    //TODO: NEED TO ADD BACK IN INTAKE
    enabledLooper.addLoops(hood, feeder, ballCounter, turret, intake); //intake

    flywheelLooper = new Looper(1/500D);
    flywheelLooper.addLoops(flywheel);

    poseEstimatorLooper = new Looper(1 / 50D);
    poseEstimator = PoseEstimator.getInstance();
    poseEstimatorLooper.addLoops(poseEstimator);

    limelightLooper = new Looper(1 / 100D);
    limelightLooper.addLoops(limelight);

    autoChooser.setDefaultOption("Do Nothing", new DoNothingAutoMode());
//    autoChooser.addOption("Right 3 Ball Towards Shoot Auto", new RightDriveTowardsShootAutoMode());
//    autoChooser.addOption("Right 3 Ball Away Shoot Auto", new RightDriveShootAutoMode());
//    autoChooser.addOption("Right 6 Ball Shoot Auto", new RightDriveTrenchSixBallAutoMode());
//    autoChooser.addOption("Right Wall 6 Shoot Auto", new RightDriveTrenchShootWallAutoMode());
//    autoChooser.addOption("Right Trench Ten Ball Shoot Auto", new RightDriveTrenchTenBallAutoMode());
    autoChooser.addOption("Cross Baseline", new CrossBaselineAutoMode());
    autoChooser.addOption("Slalom Path Auto", new SlalomPathAutoMode());
//    autoChooser.addOption("Galactic Search Red A Path Auto", new GalacticSearchRedAPathAutoMode());
//    autoChooser.addOption("Galactic Search Red B Path Auto", new GalacticSearchRedBPathAutoMode());
//    autoChooser.addOption("Galactic Search Blue B Path Auto", new GalacticSearchBlueBPathAutoMode());
//    autoChooser.addOption("Galactic Search Blue A Path Auto", new GalacticSearchBlueAPathAutoMode());
    autoChooser.addOption("Galactic Search A Path Auto", new GalacticSearchAPathAuto());
    autoChooser.addOption("Galactic Search B Path Auto", new GalacticSearchBPathAuto());
    autoChooser.addOption("Bounce Path", new BouncePathAutoMode());
    autoChooser.addOption("Barrel Racing Path", new BarrelRacingAutoMode());
    SmartDashboard.putData(autoChooser);

    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setResolution(240, 160);
    camera.setFPS(30);
    camera.setPixelFormat(VideoMode.PixelFormat.kMJPEG);

    limelight.init();

    if(WANTS_TO_LOG) {
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

    turret.reset();

    enabledLooper.start();
    flywheelLooper.start();
    poseEstimatorLooper.start();
    limelightLooper.start();

    if (SmartDashboard.getBoolean("autoEnabled", true)) {
      maintainAutoExecution = true;
      autoModeExecuter.setAutoMode(autoChooser.getSelected());
      autoModeExecuter.start();
    }
    else {
      maintainAutoExecution = false;
    }
  }

  @Override
  public void autonomousPeriodic() {
    //TODO: DO WE NEED THESE IN DASHBOARD DURING COMP???
//    SmartDashboard.putNumber("Pose X", poseEstimator.getPose().x);
//    SmartDashboard.putNumber("Pose Y", poseEstimator.getPose().y);
//    SmartDashboard.putNumber("Gyro Angle", drivetrain.getRotationAngle().degrees());

    SmartDashboard.putNumber("Pose X", poseEstimator.getPose().x);
    SmartDashboard.putNumber("Pose Y", poseEstimator.getPose().y);
    SmartDashboard.putNumber("Gyro Angle", drivetrain.getRotationAngle().degrees());
    SmartDashboard.putNumber("left encoder",drivetrain.getLeftDistance());
    SmartDashboard.putNumber("right encoder",drivetrain.getRightDistance());
    SmartDashboard.putBoolean("Should Index", ballCounter.shouldFeed());

//    intake.update(0);
//    feeder.update(0);
//    turret.update(0);
//    hood.update(0);
//    flywheel.update(0);

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
    maintainAutoExecution = false;
    enabledLooper.stop();
    purePursuitTracker.reset();
    //limelight.turnOn();
    airCompressor.turnOnCompressor();
    enabledLooper.start();
    flywheelLooper.start();
    //limelightLooper.start();
    autoModeExecuter.setFinished(true);

    drivetrain.resetGyro();
    drivetrain.resetEncoders();
    drivetrain.setBrakeMode();
    drivetrain.setHighGear(true);
    poseEstimator.reset();
    turret.reset();
    BallCounter.getInstance().setCount(3);
    hood.setWantedState(Hood.WantedState.WANTS_TO_ZERO_HOOD);
    if(WANTS_TO_LOG) loggerLooper.start();
  }

  @Override
  public void teleopPeriodic() {
    g.isRed(true);
    teleopUpdater.update();
    SmartDashboard.putNumber("Ball counter", BallCounter.getInstance().getCount());
    SmartDashboard.putBoolean("Hood Zeroed", Hood.getInstance().isZeroed());
    //SmartDashboard.putBoolean("Correct Distance", 60 < limelight.getDistanceToInner() && limelight.getDistanceToInner() < 144);
    SmartDashboard.putNumber("Target Distance", limelight.getDistanceToInner());
    SmartDashboard.putBoolean("Correct Auto Align", turret.atAngleSetpoint() && hood.atHoodSetpoint() && flywheel.atSetpointVelocity());

    //TODO: COMMENTED OUT TO INCREASE LATENCY, COMMENT BACK IN FOR DEBUG
//    SmartDashboard.putNumber("distance to outer", limelight.getDistanceToTarget());
    //SmartDashboard.putNumber("wanted hood degrees", limelight.getAngleToTarget() * 180/Math.PI);
//   SmartDashboard.putNumber("wanted vel", ShootingKinematics.outputVelToFlywheelVel(limelight.getVelToTarget()));
    //SmartDashboard.putNumber("TAU", limelight.calculateTau());
//    SmartDashboard.putNumber("ACTUAL VEL", flywheel.getVelocity());
//    SmartDashboard.putNumber("wantedEnd", limelight.optimalEndAngle());
//    private DoubleSolenoid hangerPancakes;
    if(WANTS_TO_LOG){
    }
  }

  @Override
  public void testInit() {
    autoModeExecuter.setFinished(true);
    limelight.turnOn();
    limelightLooper.start();
    poseEstimator.reset();
    poseEstimatorLooper.start();
    drivetrain.resetGyro();
    drivetrain.resetEncoders();
    drivetrain.setCoastMode();
  }

  @Override
  public void testPeriodic() {
    SmartDashboard.putNumber("Pose X", poseEstimator.getPose().x);
    SmartDashboard.putNumber("Pose Y", poseEstimator.getPose().y);
    SmartDashboard.putNumber("Gyro Angle", drivetrain.getRotationAngle().degrees());
    SmartDashboard.putNumber("left encoder",drivetrain.getLeftDistance());
    SmartDashboard.putNumber("right encoder",drivetrain.getRightDistance());
    g.isRed(true);
  }

  @Override
  public void disabledInit(){
    //TODO: Get rid of coast mode for final
    limelight.turnOff();
    airCompressor.turnOffCompressor();
    if(WANTS_TO_LOG) {
      loggerLooper.stop();
    }
  }

}
