package frc.team3256.robot.helper;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import frc.team3256.robot.constants.IDConstants;


/**
 * A Class to test if CAN devices are online
 */
public class CANTest {

    /**
     * Main method to test all CAN devices except PCM.
     */
    public static void test() {
        boolean noErrors = testTalonFX(CANConstants.SparkMaxIDs) &&
                testSparkMax(CANConstants.TalonFXIDs) &&
                testPDP() &&
                testPigeon();

        if (noErrors) {
            System.out.println("All CAN Devices Online");
        } else {
            System.out.println("CAN Errors Exist");
        }
    }

    /**
     * Helper method to test PDP
     *
     * @return Returns whether the PDP is online
     */
    private static boolean testPDP() {
        PowerDistributionPanel pdp = new PowerDistributionPanel();
        double voltage = pdp.getVoltage();
        if (voltage == 0) {
            System.out.println("PDP Offline");
            return false;
        }
        return true;
    }

    /**
     * Helper method to test multiple TalonFX motors
     *
     * @param ids motor IDs to test
     *
     * @return Returns whether all the TalonFXs are online
     */
    private static boolean testTalonFX(int[] ids) {
        boolean isGood = true;
        for (int id : ids) {
            TalonFX talon = new TalonFX(id);
            double temp = talon.getTemperature();
            if (temp == 0) {
                isGood = false;
                System.out.println("Talon FX ID " + id + " Offline");
            }
        }
        return isGood;
    }

    /**
     * Helper method to test the Pigeon
     *
     * @return Returns whether the Pigeon is online
     */
    private static boolean testPigeon() {
        PigeonIMU pigeon = new PigeonIMU(IDConstants.pigeonID);
        double temp = pigeon.getTemp();
        if (temp == 0) {
            System.out.println("Pigeon is Offline");
            return false;
        }
        return true;
    }

    /**
     * @param ids motor IDs to test
     *
     * @return Returns whether all the SparkMaxes are online
     */
    private static boolean testSparkMax(int[] ids) {
        boolean isGood = true;
        for (int id : ids) {
            CANSparkMax sparkMax = new CANSparkMax(id, CANSparkMaxLowLevel.MotorType.fromId(0));
            double temp = sparkMax.getMotorTemperature();
            if (temp == 0) {
                isGood = false;
                System.out.println("SparkMax " + id + " offline");
            }
        }
        return isGood;
    }
}
