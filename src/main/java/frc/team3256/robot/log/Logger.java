package frc.team3256.robot.log;

import badlog.lib.BadLog;
import badlog.lib.DataInferMode;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.function.Supplier;

public class Logger {

   //region Static Variables

   private static final boolean SHOULD_LOG_STATE_CHANGE = false;

   private static BadLog logger;
   private static Level levelThreshold = Level.WARNING;

   private static double startTime;

   private static String logsFilePath = "/home/lvuser/logs/";

   //endregion

   //region Class Specific Variables

   private String simpleClassName;

   //endregion

   /**
    * Categories to add to Log Messages
    *
    * <br><b>DEBUG</b> - Very Detailed, only for Diagnosing Problems
    * <br><b>INFO</b> - Confirmation that things are working correctly
    * <br><b>WARNING</b> - Something Unexpected Happened, Robot still Working
    * <br><b>ERROR</b> - Due to an Issue, Robot can not complete task
    * <br><b>CRITICAL</b> - A serious error that indicates that the Robot may not be able to keep working
    */
   public enum Level {
      DEBUG(0),
      INFO(1),
      WARNING(2),
      ERROR(3),
      CRITICAL(4);

      Level(final int levelValue){
         this.levelValue = levelValue;
      }

      private int levelValue;

      public int getValue(){
         return levelValue;
      }

   }

   //region Static Logger Setup

   public static void setLevelThreshold(Level threshold){
      Logger.levelThreshold = threshold;
   }


   /**
    * Called before Adding Topics or Values for Classes
    */
   public static void startInitialization() {
      String timestamp = new SimpleDateFormat("MM'-'dd'-'yyyy'_'HH'-'mm").format(new Date());

      //Create Directory if Not Exist
      File logsDirectory = new File(logsFilePath);
      logsDirectory.mkdir();

      //Creates latest.txt to point to latest File
      File file = new File(logsDirectory + "/_latest.txt");


      try {
         FileWriter fileWriter = new FileWriter(logsDirectory + "/_latest.txt");
         fileWriter.write(timestamp + ".badbag");
         fileWriter.flush();
         fileWriter.close();
      } catch (IOException e) {
         System.out.println(e.toString());
      }

      logger = BadLog.init(String.format(logsDirectory + "/%s.badbag",timestamp));

      BadLog.createTopicSubscriber("Event Log", BadLog.UNITLESS, DataInferMode.DEFAULT, "log");
      BadLog.createTopicSubscriber("Error Log", BadLog.UNITLESS, DataInferMode.DEFAULT, "log");
      BadLog.createTopic("Battery Voltage", "V", RobotController::getBatteryVoltage,"zero");
      BadLog.createValue("Start Time",timestamp);
      BadLog.createTopicSubscriber("Time From Enable", "s",DataInferMode.DEFAULT,"xaxis","hide");

      //General Match Data
      BadLog.createValue("OS Version", System.getProperty("os.version"));
      BadLog.createValue("Match Type", DriverStation.getInstance().getMatchType().toString());
      BadLog.createValue("Match Number", "" + DriverStation.getInstance().getMatchNumber());
      BadLog.createTopic("Match Time", "s", DriverStation.getInstance()::getMatchTime);
      BadLog.createValue("Alliance", DriverStation.getInstance().getAlliance().name());
      BadLog.createValue("Event Name", DriverStation.getInstance().getEventName());
   }
   /**
    * Called when all Values/Topics are initialized to write header.
    */
   public static void finishInitialization() {
      logger.finishInitialization();
   }

   /**
    * Call Every Cycle to write changes and update time
    */
   public static void update() {
      BadLog.publish("Time From Enable",getTimeSinceEnable());
      logger.updateTopics();
      logger.log();
   }

   /**
    * Creates a Named Value at Start of Logging; Must be called during Initialization
    */
   public static void createValue(String name, String value){
      BadLog.createValue(name,value);
   }

   /**
    * (!) Recommended to use auto logger
    * Used to create Topic which will log the returned supplier function every update cycle.
    *
    * @param subsystemName Name of Subsystem
    * @param unit Unit of Data
    * @param name Name of Specific Device
    * @param supplier Function to get Data Points from
    */
   public static void createTopic(String subsystemName, String unit, String name, Supplier<Double> supplier){
      BadLog.createTopic(name,unit,supplier,"hide",String.format("join:/%s/%s",subsystemName,name));
   }

   //endregion

   //region Logging Methods

   public Logger(Object classInstance){
      simpleClassName = classInstance.getClass().getSimpleName();
   }

   public void logEvent(String message, Level level){

      String stringToPublish = String.format(
              "[%s] %s - %s",
              simpleClassName,level,message
      );

      if(level.getValue() < Level.WARNING.getValue()) {
         BadLog.publish("Event Log", stringToPublish);
      } else{
         BadLog.publish("Error Log", stringToPublish);
      }
      if(level.getValue() >= levelThreshold.getValue()){
         System.out.println(stringToPublish);
      }
   }

   public void logStateChange(String fromState, String toState){
      if(SHOULD_LOG_STATE_CHANGE){
         String stringToPublish = String.format(
                 "[%s] Changed (%s) -> (%s)",
                 simpleClassName,fromState,toState);

         BadLog.publish("Event Log", stringToPublish);
         if(Level.DEBUG.getValue() == levelThreshold.getValue()){
            System.out.println(stringToPublish);
         }
      }
   }


   //// Helper Methods ////

   /**
    *  Use for very Detailed Logs, only for Diagnosing Problems
    * @param message Detailed Message to Log
    */
   public void debug(String message){
      logEvent(message,Level.DEBUG);
   }

   /**
    * Use for Confirmation that things are working correctly
    * @param message Detailed Message to Log
    */
   public void info(String message){
      logEvent(message,Level.INFO);
   }

   /**
    * Use for when Something Unexpected Happened, Robot still Working
    * @param warningMessage Detailed Message to Log
    */
   public void warning(String warningMessage){
      logEvent(warningMessage,Level.WARNING);
   }
   /**
    * Due to an Issue, Robot can not complete task
    * @param errorMessage Detailed Message to Log
    */
   public void error(String errorMessage){
      logEvent(errorMessage,Level.ERROR);
   }
   /**
    * A serious error that indicates that the Robot may not be able to keep working
    * @param criticalMessage Detailed Message to Log
    */
   public void critical(String criticalMessage){
      logEvent(criticalMessage,Level.CRITICAL);
   }

   //endregion

   //region Helper Methods

   private static double getTimeSinceEnable(){
      if(startTime == 0)
         startTime = Timer.getFPGATimestamp();

      //Put RoboRIO's FPGA Time to Smart Dashboard for FRC Dashboard
//      SmartDashboard.putNumber("RelTime",Timer.getFPGATimestamp()- startTime);

      return Timer.getFPGATimestamp() - startTime;
   }

   public static void deleteOldFiles(){
      File directory = new File(logsFilePath);
      File[] files = directory.listFiles();
      Arrays.sort(files, Comparator.comparingLong(File::lastModified));
      System.out.println(files);
   }

   //endregion

}