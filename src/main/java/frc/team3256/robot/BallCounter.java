package frc.team3256.robot;

import frc.team3256.robot.hardware.IRSensor;
import frc.team3256.robot.subsystems.Feeder;
import frc.team3256.robot.subsystems.Intake;
import frc.team3256.robot.subsystems.Turret;
import frc.team3256.warriorlib.loop.Loop;

public class BallCounter implements Loop {
    private static BallCounter instance;
    private double count = 0;
    private IRSensor irSensor = new IRSensor();
    private boolean blocked = false;
    private boolean prevBlocked = false;
    private boolean shouldFeed = false;

    public static BallCounter getInstance() {return instance == null ? instance = new BallCounter() : instance; }

    @Override
    public void init(double timestamp) {
    }

    @Override
    public void update(double timestamp) {

        blocked = !irSensor.isIntact();
        shouldFeed = false;
        if (blocked) {
            if (!prevBlocked) count++;
            if (!isFull()) shouldFeed = true;
        }
        prevBlocked = blocked;
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

    public boolean isFull() {
        return count >= 5;
    }
}
