package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.VictorSP;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;;
import edu.wpi.first.wpilibj.Servo;

public class Robot extends TimedRobot {
  WPI_VictorSPX tl = new WPI_VictorSPX(4);
  WPI_VictorSPX br = new WPI_VictorSPX(7); 
  WPI_VictorSPX bl = new WPI_VictorSPX(3);

  Servo finger = new Servo(0);

  
  TalonSRX rightW = new TalonSRX(1);
  TalonSRX leftW = new TalonSRX(2);
  TalonSRX arm = new TalonSRX(6);

  SpeedControllerGroup left = new SpeedControllerGroup(tl, bl);
  SpeedControllerGroup right = new SpeedControllerGroup(tr, br);

  DifferentialDrive hothBot = new DifferentialDrive(left, right);
  
  Joystick lJoystick = new Joystick(0);
  
  Joystick contr = new Joystick(1);
  JoystickButton a = new JoystickButton(contr, 1);
  JoystickButton b = new JoystickButton(contr, 2);
  JoystickButton x = new JoystickButton(contr, 3);
  JoystickButton y = new JoystickButton(contr, 4);
  JoystickButton rightB = new JoystickButton(contr, 5);

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

    if (a.get()) {
      arm.set(ControlMode.PercentOutput, .25);
    } else {
      arm.set(ControlMode.PercentOutput, 0);
    }

    if (y.get()) {
      arm.set(ControlMode.PercentOutput, -.25);
    } else {
      arm.set(ControlMode.PercentOutput, 0);
    }

    if (x.get()) {
      rightW.set(ControlMode.PercentOutput, -1);
      leftW.set(ControlMode.PercentOutput, 1);
    } else if (b.get()) {
      rightW.set(ControlMode.PercentOutput, 1);
      leftW.set(ControlMode.PercentOutput, -1);
    } else {
      rightW.set(ControlMode.PercentOutput, 0);
      leftW.set(ControlMode.PercentOutput, 0);
    }

    if (y.get()) {
      finger.setAngle(150);
    } else {
      finger.setAngle(70);
    }

  }
}
