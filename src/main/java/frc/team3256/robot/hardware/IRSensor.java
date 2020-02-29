package frc.team3256.robot.hardware;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.team3256.robot.constants.IDConstants;
import frc.team3256.robot.subsystems.Drivetrain;

public class IRSensor {
    DigitalInput ir_sensor;
    private static IRSensor instance;


    public static IRSensor getInstance() {
        return instance == null ? instance = new IRSensor() : instance;
    }

    public IRSensor () {
        ir_sensor = new DigitalInput(IDConstants.feederIRID);
    }

    public boolean isIntact() {
        return ir_sensor.get();
    }
}
