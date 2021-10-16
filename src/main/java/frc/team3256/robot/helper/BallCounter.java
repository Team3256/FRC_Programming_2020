package frc.team3256.robot.helper;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.Robot;
import frc.team3256.robot.hardware.IRSensors;
import frc.team3256.robot.subsystems.Feeder;
import frc.team3256.robot.subsystems.Flywheel;
import frc.team3256.robot.subsystems.Intake;
import frc.team3256.warriorlib.loop.Loop;

public class BallCounter implements Loop {
    private static BallCounter instance;
    private int count = 0;
    private IRSensors irSensors = IRSensors.getInstance();
    private boolean feederBlocked = false;
    private boolean feederPrevBlocked = false;
    private boolean flywheelBlocked = false;
    private boolean flywheelPrevBlocked = false;
    private boolean shouldIndex = false;
    private boolean shouldPID = false;


    public static BallCounter getInstance() {return instance == null ? instance = new BallCounter() : instance; }

    @Override
    public void init(double timestamp) {
    }


    /**
     * Runs at 50hz
     * @param timestamp Time between runs of this method
     */
    @Override
    public void update(double timestamp) {
        feederBlocked = !irSensors.isFeederIRIntact();
        flywheelBlocked = !irSensors.isFlywheelIRIntact();
        SmartDashboard.putBoolean("Feeder Blocked", feederBlocked);
        //TODO: if unjam then ignore everything
        //if (!DriverStation.getInstance().isDisabled())
        //    System.out.println("Feeder blocked: " + feederBlocked);

        //TODO: Dylan - Do logic for choosing PID /  Power Only
        SmartDashboard.putBoolean("SHOULD PID", shouldPID);

        if (feederBlocked) {
            if(!Feeder.getInstance().isRunIndex()){
                Feeder.getInstance().setWantedState(Feeder.WantedState.WANTS_TO_RUN_INDEX);
            }
            shouldPID = true;
        }
        else if(shouldPID){
            if(!Feeder.getInstance().isPidPositioning()){
                Feeder.getInstance().zeroFeederEncoder();
                Feeder.getInstance().setWantedState(Feeder.WantedState.WANTS_TO_PID_POSITION);
                System.out.println("PIDing");
                shouldPID = false;
            }
        }
//        else if (Feeder.getInstance().atSetpoint()){
//            System.out.println("at setpoint");
//            Feeder.getInstance().setWantedState(Feeder.WantedState.WANTS_TO_IDLE);
//            shouldPID = false;
//        }

        //EXAMPLES -------------------------------|

        //This runs the PID Position method at 50hz
        //Feeder.getInstance().setWantedState(Feeder.WantedState.WANTS_TO_PID_POSITION);

        //This Stops the feeder
        //Feeder.getInstance().setWantedState(Feeder.WantedState.WANTS_TO_IDLE);

        //This runs the Index method at 50 hz, which just runs at a current speed
        //Feeder.getInstance().setWantedState(Feeder.WantedState.WANTS_TO_RUN_INDEX);
    }

    @Override
    public void end(double timestamp) {
    }

    public boolean shouldFeed() {
        return shouldIndex;
//        return false;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getCount() {return count;}

    public boolean isFull() {
        return count >= 5;
    }

    public boolean isEmpty() {return count <=0; }
}
