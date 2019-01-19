package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.SpeedControllerGroup; 
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot {
  VictorSP tr = new VictorSP(0); 
  VictorSP tl = new VictorSP(1);
  VictorSP br = new VictorSP(2); 
  VictorSP bl = new VictorSP(3);
  
  Joystick lJoystick = new Joystick(0);
  Joystick rJoystick = new Joystick(1);
 
  SpeedControllerGroup right = new SpeedControllerGroup(tr, br);
  SpeedControllerGroup left = new SpeedControllerGroup(tl, bl);
   
  DifferentialDrive hothBot = new DifferentialDrive(left,right);
  @Override
  public void robotInit() {
 // Initialize all components for Teleop
 
}

  @Override
  public void autonomousInit() {
 // Initialize all components for Autonomous
  }

  @Override
  public void autonomousPeriodic() {
 // Write Autnomous code
  }

  @Override
  public void teleopPeriodic() {
    
 // Write Teleop code  
    hothBot.tankDrive(lJoystick.getY(), rJoystick.getY()); //left and right side is being controlled with joysticks
  }
}
