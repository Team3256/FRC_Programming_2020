package frc.team3256.robot.log;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class HighGearAutoLogger {
   public static void autoLog(DoubleSolenoid device) {
      Logger.createTopic("Drivetrain","ul","High Gear",() -> DoubleSolenoid.Value.kForward == device.get() ?  1.0 : 0.0);
   }
}
