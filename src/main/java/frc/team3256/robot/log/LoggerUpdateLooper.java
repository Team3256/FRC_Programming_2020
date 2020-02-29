package frc.team3256.robot.log;

import frc.team3256.robot.subsystems.Feeder;
import frc.team3256.warriorlib.loop.Loop;

public class LoggerUpdateLooper implements Loop {


   private static LoggerUpdateLooper instance;
   public static LoggerUpdateLooper getInstance() { return instance == null ? instance = new LoggerUpdateLooper() : instance; }

   @Override
   public void init(double timestamp) {

   }

   @Override
   public void update(double timestamp) {
      Logger.update();
   }

   @Override
   public void end(double timestamp) {

   }
}
