package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.cameraserver.CameraServer;
public class Robot extends TimedRobot {

  
  @Override
  public void robotInit() {
 // Initialize all components for Teleop
CameraServer.getInstance().startAutomaticCapture();
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
  }
}
