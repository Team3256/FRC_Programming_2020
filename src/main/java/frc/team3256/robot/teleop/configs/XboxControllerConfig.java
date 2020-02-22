package frc.team3256.robot.teleop.configs;

import edu.wpi.first.wpilibj.XboxController;

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

    //Driver: Left Trigger
    @Override
    public boolean getLowGear() { return driver.getRawAxis(2) > 0.25; }

    //Driver: Right Bumper
    @Override
    public boolean getHangerUp() { return driver.getRawButton(6); }

    //Driver: Left Bumper
    @Override
    public boolean getHangerDown() { return driver.getRawButton(5); }



//--------------Manipulator--------------



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

    //Manipulator: Left Bumper
    @Override
    public boolean autoAlignHood() { return manipulator.getRawButton(5); }

    //Manipulator: Left Analog Stick | Vertical Axis | Up
    @Override
    public boolean manualHoodUp() {
        return manipulator.getRawAxis(1) > 0.5; } //***NEED TO CONFIRM DIRECTION***

    //Manipulator: Left Analog Stick | Vertical Axis | Down
    @Override
    public boolean manualHoodDown() { return manipulator.getRawAxis(1) < -0.5; } //***NEED TO CONFIRM DIRECTION***

    //Manipulator: Right Bumper
    @Override
    public boolean autoAlignTurret() { return manipulator.getRawButton(6); }

    //Manipulator: Right Analog Stick | Horizontal Axis | Left
    @Override
    public boolean manualTurretLeft() {
        return manipulator.getRawAxis(4) > 0.5; } //***NEED TO CONFIRM DIRECTION***

    //Manipulator: Right Analog Stick | Horizontal Axis | Right
    @Override
    public boolean manualTurretRight() { return manipulator.getRawAxis(4) < -0.5; } //***NEED TO CONFIRM DIRECTION***

    //Manipulator: Y Button
    @Override
    public boolean getShoot() { return manipulator.getRawButton(4); }

    //Manipulator: A Button
    @Override
    public boolean getSpin() { return manipulator.getRawButton(1); }
}
