package frc.team3256.robot;

public class ShootingKinematics {

    public double angleToHoodPos(double angle) {
        return 0.3342*(angle*180/Math.PI) - 18.302;
    }

    public double velToFlywheelVel(double outputVel) {
        return 9.839*outputVel + 742.37;
    }
}
