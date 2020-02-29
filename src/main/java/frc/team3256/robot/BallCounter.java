package frc.team3256.robot;

import frc.team3256.robot.hardware.IRSensor;
import frc.team3256.robot.subsystems.Feeder;
import frc.team3256.robot.subsystems.Intake;
import frc.team3256.warriorlib.loop.Loop;

public class BallCounter implements Loop {
    private double count = 0;
    private IRSensor irSensor = IRSensor.getInstance();
    private boolean blocked = false;
    private boolean prevBlocked = false;

    public enum SystemState {
        FEEDING,
        NOT_FEEDING
    }

    private SystemState state;

    @Override
    public void init(double timestamp) {
    }

    @Override
    public void update(double timestamp) {

        blocked = !irSensor.isIntact();
        if (blocked) {
            if (!prevBlocked) count++;
            if (count != 5) state = SystemState.FEEDING;
            else state = SystemState.NOT_FEEDING;
        }
        else {
            state = SystemState.NOT_FEEDING;
        }
        prevBlocked = blocked;
    }

    @Override
    public void end(double timestamp) {

    }

    public SystemState getState() {
        return state;
    }
}
