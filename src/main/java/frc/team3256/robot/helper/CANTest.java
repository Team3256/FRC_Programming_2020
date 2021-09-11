package frc.team3256.robot.helper;
import com.ctre.phoenix.CTREJNIWrapper;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import frc.team3256.robot.constants.IDConstants;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.hal.CANData;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import java.util.concurrent.TimeUnit;

public class CANTest {
    public void scan() throws InterruptedException {
        System.out.println("running can test...");
        //create can to pdp
        PowerDistributionPanel pdp = new PowerDistributionPanel();
        System.out.println(pdp.getVoltage());

        TalonFX talon = new TalonFX(12);
        System.out.println(talon.getTemperature());

//        CAN c = new CAN(0);
//        while (true) {
//            for (int someApiId = 0; someApiId < 16; someApiId++) {
//                CANData d = new CANData();
//
//                c.readPacketLatest(someApiId, d);
//                for (byte canData : d.data) {
//                    System.out.print(canData);
//                }
//                System.out.println("-----");
//            }
//            TimeUnit.SECONDS.sleep(5);
//        }
    }

    public void testTalon() {
        TalonFX t = new TalonFX(0);
        System.out.println(t.getFirmwareVersion());
    }

}
