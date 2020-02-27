package frc.team3256.robot.log;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;

public class PigeonAutoLogger {

   public static void autoLog(PigeonIMU device) {
      Logger.createValue("Pigeon Firmware",Integer.toString(device.getFirmwareVersion()));
      Logger.createTopic("Robot","Degrees","Heading Degrees",device::getCompassHeading);
   }
}
