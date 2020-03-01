package frc.team3256.robot.hardware;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;

import static frc.team3256.robot.constants.IDConstants.*;

public class AirCompressor {
    private Compressor compressor;
    private AnalogInput pressureSensor;

    private static AirCompressor instance;

    public static AirCompressor getInstance() {
        return instance == null ? instance = new AirCompressor() : instance;
    }

    private AirCompressor() {
        compressor = new Compressor(pcmID);
        pressureSensor = new AnalogInput(pressureSwitchID);
    }

    public void turnOnCompressor() {
        compressor.setClosedLoopControl(true);
    }

    public void turnOffCompressor() {
        compressor.setClosedLoopControl(false);
    }

    public double getAirPressurePSI() {
        return 250.0 * (pressureSensor.getVoltage() / 4.75) - 25;
    }

    //In the rare case where the compressor needs to be handled by software
    public void turnOffCompressorAtPSI(double PSI) {
        if (getAirPressurePSI() == PSI) {
            turnOffCompressor();
        }
        else turnOnCompressor();
    }
}
