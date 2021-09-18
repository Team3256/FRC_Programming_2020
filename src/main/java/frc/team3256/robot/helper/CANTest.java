package frc.team3256.robot.helper;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import frc.team3256.robot.constants.IDConstants;

import java.util.Objects;


/**
 * A Class to test if CAN devices are online
 */
public class CANTest {
    /**
     * Main method to test can devices.
     */
    public static void test() {
        boolean b = testTalonFX(new int[]{5, 6, 11, 12, 15}) &&
                testSparkMax(new int[]{1, 2, 3, 4, 7}) &&
                testPDP() &&
                testPigeon() &&
                testPCM();

        if (b) {
            System.out.println("All CAN Devices Online");
        } else {
            System.out.println("CAN Errors exist");
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
        PigeonIMU pigeon = new PigeonIMU(13);
        double temp = pigeon.getTemp();
        if (temp == 0) {
            System.out.println("Pigeon is Offline");
            return false;
        }
        return true;
    }

    /**
     * Helper method to test PCM
     *
     * @return Returns whether the PCM is online
     */
    private static boolean testPCM() {
        DoubleSolenoid solenoid = new DoubleSolenoid(IDConstants.pcmID, IDConstants.intakeRaiseForwardChannel, IDConstants.intakeRaiseReverseChannel);
        if (Objects.equals(solenoid.get().toString(), "kOff")) {
            System.out.println("PCM Offline");
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
