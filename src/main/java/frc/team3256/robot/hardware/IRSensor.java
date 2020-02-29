package frc.team3256.robot.hardware;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.team3256.robot.constants.IDConstants;

public class IRSensor {
    DigitalInput feederIRSensor;
    DigitalInput flywheelIRSensor;

    public IRSensor () {
        feederIRSensor = new DigitalInput(IDConstants.feederIRID);
        flywheelIRSensor = new DigitalInput(IDConstants.flywheelIRID);
    }

    public boolean isFeederIRIntact() {
        return feederIRSensor.get();
    }
    public boolean isFlywheelIRIntact() {
        return flywheelIRSensor.get();
    }
}
