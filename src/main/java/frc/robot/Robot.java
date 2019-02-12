package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Compressor;

public class Robot extends TimedRobot {

  Compressor c = new Compressor(0);
  
  @Override
  public void robotInit() {
 // Initialize all components for Teleop
 c.setClosedLoopControl(true);
 
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
