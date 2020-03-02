package frc.team3256.robot.hardware;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.team3256.robot.constants.IDConstants;

public class IRSensors {
    private static IRSensors instance;
    private DigitalInput feederIRSensor;
    private DigitalInput flywheelIRSensor;

    public IRSensors() {
        feederIRSensor = new DigitalInput(IDConstants.feederIRID);
        flywheelIRSensor = new DigitalInput(IDConstants.flywheelIRID);
    }

    public static IRSensors getInstance() {return instance == null ? instance = new IRSensors() : instance;}

    public boolean isFeederIRIntact() { return feederIRSensor.get(); }

    public boolean isFlywheelIRIntact() { return flywheelIRSensor.get(); }
}
