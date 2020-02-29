package frc.team3256.robot;

import frc.team3256.robot.hardware.IRSensor;
import frc.team3256.robot.subsystems.Flywheel;
import frc.team3256.robot.subsystems.Intake;
import frc.team3256.warriorlib.loop.Loop;

public class BallCounter implements Loop {
    private static BallCounter instance;
    private double count = 0;
    private IRSensor feederIRSensor = new IRSensor();
    private IRSensor flywheelIRSensor = new IRSensor();
    private boolean feederBlocked = false;
    private boolean feederPrevBlocked = false;
    private boolean flywheelBlocked = false;
    private boolean flywheelPrevBlocked = false;
    private boolean shouldFeed = false;

    public static BallCounter getInstance() {return instance == null ? instance = new BallCounter() : instance; }

    @Override
    public void init(double timestamp) {
    }

    @Override
    public void update(double timestamp) {
        feederBlocked = !feederIRSensor.isFeederIRIntact();
        shouldFeed = false;
        if (feederBlocked) {
            if (!feederPrevBlocked && Intake.getInstance().getWantedState() == Intake.WantedState.WANTS_TO_INTAKE ) count++;
            if (!isFull()) shouldFeed = true;
        }
        feederPrevBlocked = feederBlocked;

        flywheelBlocked = !flywheelIRSensor.isFlywheelIRIntact();
        if (flywheelBlocked) {
            if (!feederPrevBlocked && Flywheel.getInstance().getWantedState() == Flywheel.WantedState.WANTS_TO_RUN) decrement();
        }
        flywheelPrevBlocked = flywheelBlocked;
    }

    @Override
    public void end(double timestamp) {

    }

    public boolean shouldFeed() {
        return shouldFeed;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getCount() {return count;}

    public void decrement() {
        this.count--;
    }

    public boolean isFull() {
        return count >= 5;
    }
}
