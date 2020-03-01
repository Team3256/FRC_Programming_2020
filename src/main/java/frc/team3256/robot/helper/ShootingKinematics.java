package frc.team3256.robot.helper;

public class ShootingKinematics {

    public static double angleToHoodPos(double angle) {
        return 0.3342*(angle*180/Math.PI) - 18.302;
    }

    public static double outputVelToFlywheelVel(double outputVel) {
        return 9.839*outputVel + 742.37;
    }
}
