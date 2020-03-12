package frc.team3256.robot.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.team3256.robot.helper.BallCounter;
import frc.team3256.robot.subsystems.Feeder;
import frc.team3256.robot.subsystems.Intake;
import frc.team3256.warriorlib.auto.action.Action;
import frc.team3256.warriorlib.auto.purepursuit.PoseEstimator;
import frc.team3256.warriorlib.auto.purepursuit.PurePursuitTracker;
import frc.team3256.warriorlib.math.Vector;

public class FeederIndexAction implements Action {

    Feeder mFeeder = Feeder.getInstance();
    BallCounter ballCounter = BallCounter.getInstance();
//    PoseEstimator poseEstimator = PoseEstimator.getInstance();
//    Vector pose = new Vector(0,0);
    private double endTime;
    private double startTime;

    public FeederIndexAction(double endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime >= endTime; //pose.y >= 160
    }

    @Override
    public void update() {
        ballCounter.update(0);
        if (ballCounter.shouldFeed()) {
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_RUN_INDEX);
        } else {
            mFeeder.setWantedState(Feeder.WantedState.WANTS_TO_IDLE);
        }
//        pose = poseEstimator.getPose();
    }

    @Override
    public void done() {

    }

    @Override
    public void start() {
        startTime = Timer.getFPGATimestamp();
    }
}
