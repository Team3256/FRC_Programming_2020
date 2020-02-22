package frc.team3256.robot.hardware;

import edu.wpi.first.wpilibj.DigitalInput;

public class IRSensor {
    DigitalInput ir_sensor;

    public IRSensor (int channel) {
        ir_sensor = new DigitalInput(channel);
    }

    public boolean isIntact() {
        return ir_sensor.get();
    }
}
