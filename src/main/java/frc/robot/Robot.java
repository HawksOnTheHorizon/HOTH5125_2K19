package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;

public class Robot extends TimedRobot {
  DigitalInput lightfollwer;
  Ultrasonic ultra = new Ultrasonic(5, 6);

  @Override
  public void robotInit() {
    // Initialize all components for Teleop
    lightfollwer = new DigitalInput   (4);
    ultra.setAutomaticMode(true);
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
    //System.out.println(lightfollwer.get());
    System.out.println(ultra.getRangeInches());

  }
}
