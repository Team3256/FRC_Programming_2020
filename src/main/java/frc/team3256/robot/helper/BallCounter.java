package frc.team3256.robot.helper;

import frc.team3256.robot.hardware.IRSensors;
import frc.team3256.robot.subsystems.Feeder;
import frc.team3256.robot.subsystems.Flywheel;
import frc.team3256.robot.subsystems.Intake;
import frc.team3256.warriorlib.loop.Loop;

public class BallCounter implements Loop {
    private static BallCounter instance;
    private double count = 0;
    private IRSensors irSensors = IRSensors.getInstance();
    private boolean feederBlocked = false;
    private boolean feederPrevBlocked = false;
    private boolean flywheelBlocked = false;
    private boolean flywheelPrevBlocked = false;
    private boolean shouldIndex = false;

    public static BallCounter getInstance() {return instance == null ? instance = new BallCounter() : instance; }

    @Override
    public void init(double timestamp) {
    }

    @Override
    public void update(double timestamp) {
        feederBlocked = !irSensors.isFeederIRIntact();
        flywheelBlocked = !irSensors.isFlywheelIRIntact();
        shouldIndex = false;

        if (feederBlocked) {
            if (!feederPrevBlocked && Intake.getInstance().getWantedState() == Intake.WantedState.WANTS_TO_INTAKE ) {
                count++;
//                Feeder.getInstance().setWantedState(Feeder.WantedState.WANTS_TO_FURTHER_INDEX);
            }
            if (!isFull()) shouldIndex = true;
        }

        if (flywheelBlocked) {
            if (!flywheelPrevBlocked && Flywheel.getInstance().getWantedState() == Flywheel.WantedState.WANTS_TO_RUN) count--;
        }

        feederPrevBlocked = feederBlocked;
        flywheelPrevBlocked = flywheelBlocked;
    }

    @Override
    public void end(double timestamp) {
    }

    public boolean shouldFeed() {
        //return shouldIndex;
        return false;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getCount() {return count;}

    public boolean isFull() {
        return count >= 5;
    }

    public boolean isEmpty() {return count <=0; }
}
