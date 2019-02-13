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
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Robot extends TimedRobot {
  WPI_VictorSPX tr = new WPI_VictorSPX(5); 
  WPI_VictorSPX tl = new WPI_VictorSPX(4);
  WPI_VictorSPX br = new WPI_VictorSPX(7); 
  WPI_VictorSPX bl = new WPI_VictorSPX(3);

  SpeedControllerGroup left = new SpeedControllerGroup(tl, bl);
  SpeedControllerGroup right = new SpeedControllerGroup(tr, br);

  DifferentialDrive hothBot = new DifferentialDrive(left, right);
  
  Joystick lJoystick = new Joystick(0);
  Joystick rJoystick = new Joystick(1);

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
    hothBot.tankDrive(-lJoystick.getRawAxis(1), -lJoystick.getRawAxis(5));
  }
}
