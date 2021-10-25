package frc.team3256.robot.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.team3256.robot.helper.BallCounter;
import frc.team3256.robot.subsystems.Intake;
import frc.team3256.warriorlib.auto.action.Action;
import frc.team3256.warriorlib.auto.purepursuit.PoseEstimator;
import frc.team3256.warriorlib.auto.purepursuit.PurePursuitTracker;
import frc.team3256.warriorlib.math.Vector;

public class StartIntakeAction implements Action {

    Intake intake = Intake.getInstance();
//    PoseEstimator poseEstimator = PoseEstimator.getInstance();
//    Vector pose = new Vector(0,0);
    private double endTime;
    private double startTime;


    public StartIntakeAction(double endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime >= endTime; //pose.y >= 160
    }

    @Override
    public void update() {
//        pose = poseEstimator.getPose();
        intake.update(0);
    }

    @Override
    public void done() {
        intake.setWantedState(Intake.WantedState.WANTS_TO_STOP);
    }

    @Override
    public void start() {
        startTime = Timer.getFPGATimestamp();
        intake.setWantedState(Intake.WantedState.WANTS_TO_INTAKE);
    }
}
