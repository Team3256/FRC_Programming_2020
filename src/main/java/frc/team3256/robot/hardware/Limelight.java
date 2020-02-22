package frc.team3256.robot.hardware;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3256.robot.constants.LimelightConstants;
import frc.team3256.warriorlib.loop.Loop;
import static frc.team3256.robot.constants.LimelightConstants.*;
import static frc.team3256.robot.constants.TurretConstants.turretHeight;

public class Limelight implements Loop {
    private static Limelight instance;
    public static Limelight getInstance() { return instance == null ? instance = new Limelight() : instance; }

    NetworkTableEntry limeLightTx, limeLightTy, limeLightTa, limeLightTs, limeLightTcornx, limeLightTcorny;
    double tx, ty, ta, ts;
    double [] tcornx, tcorny;

    //change in theta and change in distance
    double dThetaMean = 0;
    int dThetaCount = 0;
    double dTheta;
    double dDistance;

    double lastTheta;
    double lastDistance;
    double difference = 0;

    double lastTimestamp = 0;

    //kinematics
    double heightDif = targetMidHeight - turretHeight;
    double wantedEndAngle = 0;
    double timeToTarget = 0;
    double angleToTarget = 0;
    double velToTarget = 0;

    public void init() {
        //Setting up NetworkTables
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable limelight = inst.getTable("limelight");

        //Getting values
        limeLightTx = limelight.getEntry("tx");
        limeLightTy = limelight.getEntry("ty");
        limeLightTa = limelight.getEntry("ta");
        limeLightTs = limelight.getEntry("ts");
        limeLightTcornx = limelight.getEntry("tcornx");
        limeLightTcorny = limelight.getEntry("tcorny");

        //Setting up default stream
        inst.getTable("limelight").getEntry("ledMode").setNumber(0); //Uses LED Mode in current pipeline
        inst.getTable("limelight").getEntry("camMode").setNumber(0); //Uses Vision Processor Mode
        inst.getTable("limelight").getEntry("pipeline").setNumber(0); //Uses pipeline #0
        inst.getTable("limelight").getEntry("stream").setNumber(2); //Driver Camera Main, Vision Camera Lower-Right Corner
        inst.getTable("limelight").getEntry("snapshot").setNumber(0); //Takes no snapshots
    }

    public void update() {
    }

    public double getTx() { return tx; }
    public double getTy() { return ty; }

    public double getTopSkew() {
        SmartDashboard.putNumber("before skew", ts);
        double skewAngle = ts;
        if (skewAngle < -45) {
            skewAngle += 90;
        }
        SmartDashboard.putNumber("after skew", skewAngle);
        return skewAngle;
    }

    public double [] getTcornx() {
        return tcornx;
    }

    public double [] getTcorny() {
        return tcorny;
    }

    public double getBottomSkew() {
        double skew = 0;
        double lowestY = Double.MAX_VALUE;
        int lowestIndex = 0;
        double secLowestY = Double.MAX_VALUE;
        int secLowestIndex = 0;
        for (int i = 0; i < tcorny.length; i++) {
            if (tcorny[i] < lowestY) {
                secLowestY = lowestY;
                secLowestIndex = lowestIndex;
                lowestY = tcorny[i];
                lowestIndex = i;
            } else if (tcorny[i] < secLowestY) {
                secLowestY = tcorny[i];
                secLowestIndex = i;
            }
        }

        double skewRad = Math.atan2(lowestY - secLowestY, tcornx[lowestIndex] - tcornx[secLowestIndex]);
        skew = Math.abs(skewRad * (180/Math.PI));
        return skew;
    }

    public double getDistanceToTarget() { return (targetMidHeight-mountingHeight) / Math.tan((mountingAngle + ty) * Math.PI/180); }

    public double getDistanceToInner() { return getDistanceToTarget() + toInnerTarget / Math.cos(tx);}

    public double getAbsoluteHorizontalOffset(double targetHeight, double skew) {
        double skewAngle = skew * Math.PI / 180;
        double cosSSquared = Math.pow(Math.cos(skewAngle),2);
        double height = targetHeight - mountingHeight;
        double distance = getDistanceToTarget();
        double numerator = (Math.pow(height, 2))*cosSSquared;
        double denominator = (Math.pow(distance, 2))-((Math.pow(distance, 2) * cosSSquared)) + Math.pow(height, 2);
        return (180/Math.PI) * (Math.acos(Math.sqrt(numerator/denominator)));
    }

    public double calculateTopTheta() {
        SmartDashboard.putNumber("top theta", getAbsoluteHorizontalOffset(targetTopHeight, getTopSkew()));
        return getAbsoluteHorizontalOffset(targetTopHeight, getTopSkew());
    }

    public double calculateBottomTheta() {
        SmartDashboard.putNumber("bottom theta", getAbsoluteHorizontalOffset(targetBottomHeight, getBottomSkew()));
        return getAbsoluteHorizontalOffset(targetBottomHeight, getBottomSkew());
    }

    public double calculateTau() {
        double tx = getTx();
        double d = getDistanceToTarget();
        double f = toInnerTarget;
        double side = getTopSkew() > 0 ? 1 : -1;
        SmartDashboard.putNumber("side", side);
        double hashtagOne = (calculateBottomTheta())/2 * side;

        double offset = Math.atan2(d*Math.sin((tx+hashtagOne)*Math.PI/180), f+d*Math.cos((tx+hashtagOne)*Math.PI/180))-(hashtagOne*Math.PI/180);
        return (offset*180/Math.PI);
    }

    public double calculateMovingAngle (double outputVelocity, double hoodAngle) {
        //horizontal distance to target - may have to change distance to the inner goal
        double horizontalDistance = getDistanceToTarget()+toInnerTarget;

        //use angular velocity and distance to target (ground) to find the velocity perpendicular to the target
        double perpendicularVelocity = (dTheta*Math.PI/180)*horizontalDistance;

        //horizontal 2d output velocity
        double horizontalOutputVelocity = outputVelocity*Math.cos(hoodAngle*Math.PI/180);

        //time it takes to reach the target
        double timeToTarget = horizontalDistance/horizontalOutputVelocity;

        //how far the ball would go perpendicular to the target
        double offsetDist = perpendicularVelocity*timeToTarget;

        SmartDashboard.putNumber("offset distance", offsetDist);

        double newOffsetAngle = Math.atan2(offsetDist, horizontalDistance) * (180/Math.PI);

        if (newOffsetAngle<0) {
            newOffsetAngle += 90;
        } else if (newOffsetAngle>0) {
            newOffsetAngle -= 90;
        }

        return newOffsetAngle;
    }

    public void outputToDashboard() {
        SmartDashboard.putNumber("Horizontal Degree", tx);
        SmartDashboard.putNumber("Vertical Degree", ty);
        SmartDashboard.putNumber("Target Area", ta);
    }

    @Override
    public void init(double timestamp) {

    }

    @Override
    public void update(double timestamp) {
        double timeDif = timestamp-lastTimestamp;
        SmartDashboard.putNumber("timestamp", timeDif);

        tx = limeLightTx.getDouble(2.0);
        ty = limeLightTy.getDouble(2.0);
        ta = limeLightTa.getDouble(2.0);
        ts = limeLightTs.getDouble(0);
        tcornx = limeLightTcornx.getDoubleArray(new double[4]);
        tcorny = limeLightTcorny.getDoubleArray(new double[4]);
        getBottomSkew();
        getTopSkew();

        SmartDashboard.putNumber("tau", calculateTau());

        if (timeDif > 0.05) {
            lastTimestamp = timestamp;
            double meanDiff = 0;
            meanDiff = dThetaMean;
            SmartDashboard.putNumber("dtheta", meanDiff);


            if (lastTheta != 0 && lastDistance != 0) {
                dTheta = meanDiff;
                dDistance = (getDistanceToTarget()-lastDistance)/timeDif;
                SmartDashboard.putNumber("Top Theta", calculateTopTheta());
                SmartDashboard.putNumber("Last Theta", lastTheta);
            }
            dTheta = meanDiff;
            double thetaSign = calculateTopTheta()*Math.signum(getTopSkew());
            lastTheta = thetaSign+tx;
            SmartDashboard.putNumber("Last Theta", lastTheta);
            lastDistance = getDistanceToTarget();
            SmartDashboard.putNumber("moving correction", calculateMovingAngle(10.6, 48));

        } else {
            double thetaSign = calculateTopTheta()*Math.signum(getTopSkew());
            if (Math.abs(thetaSign) <= 30) {
                dThetaMean = dThetaMean*dThetaCount;
                dThetaMean += thetaSign+tx-lastTheta;
                dThetaCount++;
                dThetaMean /= dThetaCount;
            }
        }
    }

    @Override
    public void end(double timestamp) {

    }

    public void calculateKinematics() {   // inches
        timeToTarget = Math.sqrt(2/gravAcceleration*(heightDif - Math.tan(wantedEndAngle) * getDistanceToInner()));
        angleToTarget = Math.atan((heightDif + .5 * gravAcceleration * timeToTarget * timeToTarget)/getDistanceToInner());
        velToTarget = getDistanceToInner()/timeToTarget/Math.cos(angleToTarget);
    }

    public void setWantedEndAngle(double wantedEndAngle) {  // radians
        this.wantedEndAngle = wantedEndAngle;
    }

    public double getAngleToTarget() {
        return angleToTarget;
    }

    public double getVelToTarget() {
        return velToTarget;
    }
}
