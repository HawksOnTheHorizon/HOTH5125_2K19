package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends TimedRobot {
  DigitalInput lightfollwer;

  @Override
  public void robotInit() {
    // Initialize all components for Teleop
    lightfollwer = new DigitalInput(1);
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
    System.out.println(lightfollwer.get());
  }
}
