package frc.team3256.robot.teleop.configs;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.team3256.warriorlib.auto.operations.Util;

public class XboxControllerConfig implements ControlsInterface {

    private XboxController driver = new XboxController(0);
    private XboxController manipulator = new XboxController(1);

//--------------Driver--------------

    //Driver: Left Analog Stick | Vertical Axis
    @Override
    public double getThrottle() { return -Util.handleDeadband(driver.getRawAxis(1), 0.2); }

    //Driver: Right Analog Stick | Horizontal Axis
    @Override
    public double getTurn() { return -Util.handleDeadband(driver.getRawAxis(4), 0.2); }

    //Driver: Right Trigger
    @Override
    public boolean getQuickTurn() { return driver.getRawAxis(3) > 0.25; }

    //Driver: Left Trigger - unused
    @Override
    public boolean getLowGear() { return driver.getRawAxis(2) > 0.25; }

    //Driver: Left & Right Bumper
    @Override
    public boolean toggleHangerPancakes() { return driver.getRawButton(5) && driver.getRawButton(6); }

    //Driver: Left Trigger
    @Override
    public double getHangerDown() { return -Util.handleDeadband(driver.getRawAxis(2), 0.15); }

    @Override
    public boolean getDriverShoot() {
        return driver.getRawButton(1);
    }

    @Override
    public boolean getToggleReverseDrivetrain() {
        return driver.getRawButton(3);
    }


//--------------Manipulator--------------

/* Buttons
 *
 * Left Trigger - Exhaust/Unjam
 * Right Trigger - Intake/ Unjam
 * Left Bumper - Auto Align Turret & Hood
 * Right Bumper - Rev Up Flywheel
 * Left Stick Y-Axis
 * Right Stick X-Axis
 * A Button
 * B Button
 * X Button
 * Y Button - Feeder Shoot (Forward)
 * D-Pad Up - Feeder Manual Forward
 * D-Pad Down - Feeder Manual Backward
 * D-Pad Right
 * D-Pad Left
 * Back Button - Intake Raise
 * Start Button - Intake Drop
 *
 */



    //Manipulator: BOTH Left and Right Trigger
    @Override
    public boolean getUnjam() {return manipulator.getRawAxis(2) > 0.25 && manipulator.getRawAxis(3) > 0.25; }

    //Manipulator: Right Trigger
    @Override
    public boolean getIntake() { return manipulator.getRawAxis(3) > 0.25; }

    //Manipulator: Left Trigger
    @Override
    public boolean getExhaust() { return manipulator.getRawAxis(2) > 0.25; }

    //Manipulator: D-Pad Up
    @Override
    public boolean getFeederForward() { return manipulator.getPOV() == 0; }

    //Manipulator: D-Pad Down
    @Override
    public boolean getFeederBackward() { return manipulator.getPOV() == 180; }

    //Manipulator: Left Analog Stick | Vertical Axis | Up
    @Override
    public boolean manualHoodUp() {
        return manipulator.getRawAxis(1) < -.5; } //***NEED TO CONFIRM DIRECTION***

    //Manipulator: Left Analog Stick | Vertical Axis | Down
    @Override
    public boolean manualHoodDown() { return manipulator.getRawAxis(1) > .5; } //***NEED TO CONFIRM DIRECTION***

    //Manipulator: Right Analog Stick | Horizontal Axis | Left
    @Override
    public boolean manualTurretLeft() { return manipulator.getRawAxis(4) < -0.5; } //***NEED TO CONFIRM DIRECTION***

    //Manipulator: Right Analog Stick | Horizontal Axis | Right
    @Override
    public boolean manualTurretRight() { return manipulator.getRawAxis(4) > 0.5; } //***NEED TO CONFIRM DIRECTION***

    //Manipulator: A Button
    @Override
    public boolean getSpin() { return manipulator.getRawButton(1); }

    //Manipulator: Start Button
    @Override
    public boolean toggleIntake() { return manipulator.getRawButton(8); }

//--------------For Final Implementation After Testing & Tuning--------------

    //Manipulator: Right Bumper
    @Override
    public boolean getAutoAlign() { return manipulator.getRawButton(5); }

    //Manipulator: Left Bumper
    @Override
    public boolean getRevUp() { return manipulator.getRawButton(5) || manipulator.getRawButton(6); }

    //Manipulator: Y Button
    @Override
    public boolean getFeederShoot() { return manipulator.getRawButton(4); }

    //Manipulator: B Button
    @Override
    public boolean getOuterGoalAlign() { return manipulator.getRawButton(2); }

    //Manipulator: X Button
    @Override
    public boolean getBallCountReset() { return manipulator.getRawButton(3); }

    @Override
    public void rumble(boolean rumbleState) {
        if(rumbleState) {
            manipulator.setRumble(GenericHID.RumbleType.kLeftRumble, 0.5);
            manipulator.setRumble(GenericHID.RumbleType.kRightRumble, 0.5);
        } else {
            manipulator.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
            manipulator.setRumble(GenericHID.RumbleType.kRightRumble, 0);
        }
    }
}
