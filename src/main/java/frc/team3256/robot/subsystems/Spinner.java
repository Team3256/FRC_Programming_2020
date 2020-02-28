package frc.team3256.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;
import frc.team3256.warriorlib.hardware.TalonSRXUtil;
import frc.team3256.warriorlib.subsystem.SubsystemBase;

import java.util.ArrayList;

import static frc.team3256.robot.constants.SpinnerConstants.*;

public class Spinner extends SubsystemBase {
    private final boolean mPracticeColors = true;

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private ColorSensorV3 mColorSensor;


    //private CANSparkMax mMotor;
    private WPI_TalonSRX mMotor;

    //<editor-fold desc="Color Matching Variables">
    private final ColorMatch mColorMatcher = new ColorMatch();
    private Color kBlueTarget;
    private Color kGreenTarget;
    private Color kRedTarget;
    private Color kYellowTarget;

    private double mCurrentConfidence = 0;

    private WheelColor mLastColor = WheelColor.UNKNOWN;
    private WheelColor mWantedColor = WheelColor.UNKNOWN;
    private WheelColor mCurrentColor = WheelColor.UNKNOWN;

    private ArrayList<WheelColor> colorOrder = new ArrayList<>();
    private boolean proximity;

    private enum WheelColor {
        BLUE,
        GREEN,
        RED,
        YELLOW,
        UNKNOWN,
        TOO_FAR;

        public boolean isValidColor(){
            if(this == WheelColor.UNKNOWN || this == WheelColor.TOO_FAR){
                return false;
            }else{
                return true;
            }
        }

    }
    //</editor-fold> VV

    public enum SpinDirection {
        CLOCKWISE(-1),
        COUNTERCLOCKWISE(1),
        STATIONARY(0);

        SpinDirection(int spinFactor){
            this.mSpinFactor = spinFactor;
        }
        private int mSpinFactor;

        public int getSpinFactor(){
            return mSpinFactor;
        }

    }

    //<editor-fold desc="State Machine Variables">
    private enum SystemState {
        STOP,
        ROTATION_CONTROL,
        POSITION_CONTROL,
        MANUAL
    }
    public enum WantedState{
        WANTS_TO_STOP,
        WANTS_TO_ROTATION_CONTROL,
        WANTS_TO_POSITION_CONTROL,
        WANTS_TO_MANUAL
    }


    private SystemState mCurrentState = SystemState.STOP;
    private WantedState mWantedState = WantedState.WANTS_TO_STOP;
    private WantedState mPrevWantedState = WantedState.WANTS_TO_STOP;

    private boolean mStateChanged = false;
    //</editor-fold>

    private double mManualSpeed = 0.0;
    private SpinDirection mSpinDirection = SpinDirection.STATIONARY;
    private int mCount = 0; //Number of Color Changes in Rotation Control
    private int mLastColorChangeCount = 0;
    private boolean mColorChanged = false;

    //RPM Variables
    private double mStartTimeStamp = 0.0;
    private double mRPM = 0.0;
    private int mRPMRotationCount = 0;

    //<editor-fold desc="Singleton Instancing">
    private static Spinner instance;
    public static Spinner getInstance() {
        return instance == null ? instance = new Spinner() : instance;
    }
    //</editor-fold>

    private Spinner(){
        colorOrder.add(WheelColor.YELLOW);
        colorOrder.add(WheelColor.RED);
        colorOrder.add(WheelColor.GREEN);
        colorOrder.add(WheelColor.BLUE);

        if(!mPracticeColors) {
            kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
            kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
        }else {
            kBlueTarget = ColorMatch.makeColor(0.172, 0.478, 0.350); //Practice Blue
            kGreenTarget = ColorMatch.makeColor(0.223, 0.619, 0.18); //Practice Green
        }

        kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
        kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

        mColorMatcher.addColorMatch(kBlueTarget);
        mColorMatcher.addColorMatch(kGreenTarget);
        mColorMatcher.addColorMatch(kRedTarget);
        mColorMatcher.addColorMatch(kYellowTarget);

        mColorSensor = new ColorSensorV3(i2cPort);

        mMotor = TalonSRXUtil.generateGenericTalon(kSpinnerCANID);
        mMotor.setInverted(kInvertedMotor);
    }

    public void setWantedState(WantedState wantedState) { this.mWantedState = wantedState; }

    @Override
    public void outputToDashboard() {
    }

    public double getRotations(){
        return mCount/8.0;
    }

    public String getLastColor(){
        return mLastColor.name();
    }

    @Override
    public void zeroSensors() {

    }

    @Override
    public void init(double timestamp){

    }

    public void initLogging() {
    }

    @Override
    public void update(double timestamp) {
        if (mPrevWantedState != mWantedState){
            mPrevWantedState = mWantedState;
        }

        SystemState newState;
        switch (mCurrentState) {
            case ROTATION_CONTROL:
                newState = handleRotationControl();
                break;
            case POSITION_CONTROL:
                newState = handlePositionControl();
                break;
            case MANUAL:
                newState = handleManual();
                break;
            case STOP:
                newState = handleStop();
                break;
            default:
                newState = SystemState.STOP;
        }

        if (newState != mCurrentState){
            mCurrentState = newState;
            mStateChanged = true;
        }else{
            mStateChanged = false;
        }

        updateColor();
        calculateColorChangeCount();
        this.outputToDashboard();
    }
    private SystemState handleRotationControl(){
        if(mStateChanged){
            mStartTimeStamp = Timer.getFPGATimestamp();
            mRPMRotationCount = 0;
            mCount = 0;

            mSpinDirection = SpinDirection.COUNTERCLOCKWISE;
            mMotor.set(kSpinnerRotationalControlSpeed * mSpinDirection.getSpinFactor());
        }else {
            updateRPM();
            if (mColorChanged){
                mCount += mLastColorChangeCount;
                if (mCount >= (int) (kNeededRotations * 8)) {
                    setWantedState(WantedState.WANTS_TO_STOP);
                }
            }
        }
        return defaultStateTransfer();
    }
    private SystemState handlePositionControl(){
        if (mStateChanged) {
            if(!mCurrentColor.isValidColor()) {
                setWantedState(WantedState.WANTS_TO_STOP);
            }
            mWantedColor = WheelColor.UNKNOWN;
        }
        if(!mWantedColor.isValidColor() && mCurrentColor.isValidColor()) {
            switch (DriverStation.getInstance().getGameSpecificMessage().charAt(0)) {
                case 'B':
                    mWantedColor = WheelColor.BLUE;
                    break;
                case 'G':
                    mWantedColor = WheelColor.GREEN;
                    break;
                case 'R':
                    mWantedColor = WheelColor.RED;
                    break;
                case 'Y':
                    mWantedColor = WheelColor.YELLOW;
                    break;
                default:
                    mWantedColor = WheelColor.UNKNOWN;
            }
            int wantedIndex = colorOrder.indexOf(mWantedColor);
            wantedIndex = (wantedIndex + kPositionOffset) % 4;
            mWantedColor = colorOrder.get(wantedIndex);
            wantedIndex = (colorOrder.indexOf(mWantedColor));

            int currentIndex = colorOrder.indexOf(mCurrentColor);
            int diff = wantedIndex - currentIndex;

            if (diff >= 2) {
                mSpinDirection = SpinDirection.COUNTERCLOCKWISE;
            } else if (diff <= -2) {
                mSpinDirection = SpinDirection.CLOCKWISE;
            } else if (diff < 0) {
                mSpinDirection = SpinDirection.COUNTERCLOCKWISE;
            } else if (diff > 0) {
                mSpinDirection = SpinDirection.CLOCKWISE;
            }
            mMotor.set(kSpinnerPositionalControlSpeed * mSpinDirection.getSpinFactor());
        } else if(!mWantedColor.isValidColor()){
            setWantedState(WantedState.WANTS_TO_STOP);
        } else {
            if (mLastColor == mWantedColor) {
                setWantedState(WantedState.WANTS_TO_STOP);
            }
        }
        return defaultStateTransfer();
    }

    public SystemState handleManual(){
        if(mStateChanged)
        if(Math.abs(mManualSpeed) <= 0.05){
            setWantedState(WantedState.WANTS_TO_STOP);
        }
        mMotor.set(mManualSpeed);
        return defaultStateTransfer();
    }

    public SystemState handleStop(){
        mSpinDirection = SpinDirection.STATIONARY;
        mMotor.set(0);
        return defaultStateTransfer();
    }

    public void setManualSpeed(double speed){
        mManualSpeed = speed;
    }

    public SystemState defaultStateTransfer(){
        switch(mWantedState){
            case WANTS_TO_ROTATION_CONTROL:
                return SystemState.ROTATION_CONTROL;
            case WANTS_TO_POSITION_CONTROL:
                return SystemState.POSITION_CONTROL;
            case WANTS_TO_MANUAL:
                return SystemState.MANUAL;
            case WANTS_TO_STOP:
                return SystemState.STOP;
            default:
                return SystemState.STOP;
        }
    }

    @Override
    public void end(double timestamp) {

    }

    private void calculateColorChangeCount() {
        if (!mCurrentColor.isValidColor()) {
            mLastColorChangeCount = 0;
        }
        else if (mLastColor == WheelColor.UNKNOWN) {
            mLastColor = mCurrentColor;
        }else if(mLastColor == mCurrentColor){
            mColorChanged = false;
            mLastColorChangeCount = 0;
        }
        else if (mLastColor != mCurrentColor) {
            mColorChanged = true;
            int lastIndex = colorOrder.indexOf(mLastColor);
            if (mSpinDirection == SpinDirection.COUNTERCLOCKWISE) {
                int currIndex = (lastIndex + 3) % 4;  //+3 Subtracts 1, adds 4, so it will not be negative
                if (mCurrentColor == colorOrder.get(currIndex)) {
                    mLastColor = mCurrentColor;
                    mLastColorChangeCount = 1;
                }else if (mCurrentColor == colorOrder.get((currIndex+3)%4) && mCurrentState == SystemState.ROTATION_CONTROL){
                    mLastColor = mCurrentColor;
                    mLastColorChangeCount = 2;
                }
            } else if (mSpinDirection == SpinDirection.CLOCKWISE) {
                int currIndex = (lastIndex + 1 ) % 4;
                if (mCurrentColor == colorOrder.get(currIndex)) {
                    mLastColor = mCurrentColor;
                    mLastColorChangeCount = 1;
                } else if(mCurrentColor == colorOrder.get((currIndex + 1) % 4)  && mCurrentState == SystemState.ROTATION_CONTROL){
                    mLastColor = mCurrentColor;
                    mLastColorChangeCount = 2;
                }
            }else{
                mLastColor = mCurrentColor;
            }
        }
    }

    public void updateColor () {
        Color detectedColor = mColorSensor.getColor();

        ColorMatchResult match = mColorMatcher.matchClosestColor(detectedColor);

        int proximity = mColorSensor.getProximity();

        mCurrentConfidence = match.confidence;

        if (proximity >= kProximityThreshold) {
            if (match.color == kBlueTarget) {
                mCurrentColor = WheelColor.BLUE;
            } else if (match.color == kRedTarget) {
                mCurrentColor = WheelColor.RED;
            } else if (match.color == kGreenTarget) {
                mCurrentColor = WheelColor.GREEN;
            } else if (match.color == kYellowTarget) {
                mCurrentColor = WheelColor.YELLOW;
            } else {
                mCurrentColor = WheelColor.UNKNOWN;
            }
        } else {
            mCurrentColor = WheelColor.TOO_FAR;
        }
    }

    private void updateRPM(){
        double now = Timer.getFPGATimestamp();
        mRPMRotationCount += mLastColorChangeCount;
        mRPM = 60/((now - mStartTimeStamp))*(((double) mRPMRotationCount)/8);

    }
}
