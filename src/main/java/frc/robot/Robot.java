package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.SpeedControllerGroup; 
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot {

  VictorSP leftW = new VictorSP(0);
  VictorSP rightW = new VictorSP(1);
  VictorSP arm = new VictorSP(2);
  Joystick contr = new Joystick(0);
  JoystickButton a =new JoystickButton(contr, 1);
  JoystickButton b = new JoystickButton(contr, 2);
  JoystickButton x = new JoystickButton(contr, 3);  
  JoystickButton y = new JoystickButton(contr, 4);

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
    if(x.get()){
      arm.set(0.25);
    }else{
      arm.set(0.0);
    }

    if(b.get()){
      arm.set(-0.25);
    }else{
      arm.set(0.0);
    }

  //   if(x.get()){
  //   rightW.set(1.0);
  //   leftW.set(-1.0);
  // }else if(b.get()){
  //   rightW.set(-1.0);
  //   leftW.set(1.0);
  // }else{
  //   rightW.set(0.0);
  //   leftW.set(0.0);
//  }
}
}
