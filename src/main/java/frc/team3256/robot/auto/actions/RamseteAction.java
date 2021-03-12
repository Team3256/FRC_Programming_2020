package frc.team3256.robot.auto.actions;

import edu.wpi.first.wpilibj.controller.RamseteController;
import frc.team3256.warriorlib.auto.action.Action;
import frc.team3256.warriorlib.auto.purepursuit.PoseEstimator;
import frc.team3256.warriorlib.subsystem.DriveTrainBase;

public class RamseteAction implements Action {

    // Using the default constructor of RamseteController. Here
    // the gains are initialized to 2.0 and 0.7.
    RamseteController controller1 = new RamseteController();

    private DriveTrainBase driveTrain = DriveTrainBase.getDriveTrain();
    private PoseEstimator poseEstimator = PoseEstimator.getInstance();

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void done() {

    }

    @Override
    public void start() {

    }
}
