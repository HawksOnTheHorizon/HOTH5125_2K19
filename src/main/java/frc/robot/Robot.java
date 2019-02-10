package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Servo;
//import com.ctre.phoenix.motorcontrol.can.talonSRX;
public class Robot extends TimedRobot {
  
VictorSP rightarm = new VictorSP(0);
VictorSP leftarm = new VictorSP(1);
VictorSP Botmotor = new VictorSP(2);
Servo finger = new Servo(0); 
Joystick controller = new Joystick(0);
JoystickButton a = new JoystickButton(controller,1);
JoystickButton b = new JoystickButton(controller,2);
JoystickButton y = new JoystickButton(controller,3);
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
    if (a.get()) {
      leftarm.set(1.0);
      rightarm.set(1.0);
    }
    else {
      leftarm.set(0.0);
      rightarm.set(0.0);   
    }

    if (b.get()) {
      Botmotor.set(0.5);
    }
    else {
      Botmotor.set(0.0);  
    }

    if (y.get()) {
      finger.set(1.0);
      finger.setAngle(180);
    }
    else {
      finger.set(0.0);
    }
  }
}


