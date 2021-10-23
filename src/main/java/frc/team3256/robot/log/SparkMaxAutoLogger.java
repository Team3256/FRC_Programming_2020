package frc.team3256.robot.log;

import com.revrobotics.CANSparkMax;

public class SparkMaxAutoLogger{

   /**
    * Automatically Logs Current and RPM of the Motor
    *
    * @param subsystemName Name of the Subsystem of Motor
    * @param motorName Name of the specific motor
    * @param device Reference to Can SparkMAX
    **/

   public static void autoLog(String subsystemName, String motorName, CANSparkMax device) {
      Logger.createValue(motorName + " Firmware", "" + device.getFirmwareVersion());

      Logger.createTopic(subsystemName,"%",motorName + " Percent",device::getAppliedOutput);
      Logger.createTopic(subsystemName,"A",motorName + " Current",device::getOutputCurrent);
      Logger.createTopic(subsystemName,"RPM",motorName + " Velocity",() -> {return device.getEncoder().getVelocity();});
   }


}
