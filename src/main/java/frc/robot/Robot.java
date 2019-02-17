package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;

public class Robot extends TimedRobot {

  Compressor c = new Compressor(0);
  DoubleSolenoid beakThingTwo = new DoubleSolenoid(0, 1);
 // DoubleSolenoid beakThingOne = new DoubleSolenoid(0, 0);
  
  Joystick cont = new Joystick(0);

  JoystickButton a = new JoystickButton(cont, 1);
  JoystickButton b = new JoystickButton(cont, 2);
  JoystickButton x = new JoystickButton(cont, 3);

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
    if(a.get()){
     // beakThingOne.set(DoubleSolenoid.Value.kForward);
      beakThingTwo.set(DoubleSolenoid.Value.kForward);

    }else{
     // beakThingOne.set(DoubleSolenoid.Value.kOff);
      beakThingTwo.set(DoubleSolenoid.Value.kOff);

    }

  }
}
