package frc.team3256.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Joystick;

public class TeleopUpdater {
    private Joystick driver;
    private WPI_TalonSRX motor;

    public TeleopUpdater() {
        driver = new Joystick(0);
    }
}
