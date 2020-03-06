package frc.team3256.robot.teleop.configs;

public interface ControlsInterface {

//--------------Driver--------------

    //Y Axis: Throttle for drive
    double getThrottle();

    //X Axis: Turn for drive
    double getTurn();

    //Hold: Enables quick turn
    boolean getQuickTurn();

    //Hold: Shifts down to low gear (high gear is default)
    boolean getLowGear();

    //Single Press: Shoots the hanger up
    boolean getHangerUp();

    //Hold: Pulls down the hanger
    double getHangerDown();


//--------------Manipulator--------------

    //Hold: Runs intake backwards slowly & feeder forward
    boolean getUnjam();

    //Hold: Runs BOTH intake & feeder (feed state)
    boolean getIntake();

    //Hold: Runs BOTH intake & feeder (feed state) backwards
    boolean getExhaust();

    //Hold: Runs feeder forwards & bar backwards (manual state)
    boolean getFeederForward();

    //Hold: Runs feeder backwards & bar backwards (manual state)
    boolean getFeederBackward();

    //TODO: Delete and replace with getAutoAlign
    //Hold: Auto-aligns hood
    boolean autoAlignHood();

    //Y Axis: Hood manually moves up
    boolean manualHoodUp();

    //Y Axis: Hood manually moves down
    boolean manualHoodDown();

    //TODO: Delete and replace with getAutoAlign
    //Hold: Auto-aligns turret
    boolean autoAlignTurret();

    //X Axis: Turret manually moves left
    boolean manualTurretLeft();

    //X Axis: Turret manually moves right
    boolean manualTurretRight();

    //TODO: Delete and replace with getRevUp & getFeederShoot
    //Hold: Runs flywheel & feeder (shoot state)
    boolean getShoot();

    //Hold: Runs spinner
    boolean getSpin();

    //Toggle: Toggles Intake up and down
    boolean toggleIntake();

//--------------For Final Implementation After Testing & Tuning--------------

    //Hold: Auto-aligns both turret & hood
    boolean getAutoAlign();

    //Hold: Revs up the flywheel to the necessary velocity
    boolean getRevUp();

    //Hold: Runs the feeder & intake to shoot the balls
    boolean getFeederShoot();

    //Hold: Aligns to outer goal
    boolean getOuterGoalAlign();

    //Single Press: Resets the ball counter
    boolean getBallCountReset();

}