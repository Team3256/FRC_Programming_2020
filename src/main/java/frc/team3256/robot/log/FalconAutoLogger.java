package frc.team3256.robot.log;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import java.time.temporal.TemporalAccessor;

public class FalconAutoLogger {


   /**
    * Automatically Logs Current and Percent of the Motor
    *
    * @param subsystemName Name of the Subsystem of Motor
    * @param motorName Name of the specific motor
    * @param device Reference to Can SparkMAX
    **/

   public static void autoLog(String subsystemName, String motorName, TalonFX device) {
      Logger.createValue(motorName + " Firmware", "" + device.getFirmwareVersion());

      Logger.createTopic(subsystemName,"%",motorName + " Percent",device::getMotorOutputPercent);
      Logger.createTopic(subsystemName,"A",motorName + " Current",device::getStatorCurrent);
      Logger.createTopic(subsystemName,"RPM",motorName + " RPM",()->{return Double.valueOf((device.getSelectedSensorVelocity(0)/2048.0) * 600);});
   }
}
