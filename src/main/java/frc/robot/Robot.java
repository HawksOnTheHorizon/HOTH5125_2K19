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
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;;
import edu.wpi.first.wpilibj.Servo;

public class Robot extends TimedRobot {

  Servo finger = new Servo(0);
  Joystick contr = new Joystick(0);
  JoystickButton a = new JoystickButton(contr, 1);
  JoystickButton b = new JoystickButton(contr, 2);
  JoystickButton x = new JoystickButton(contr, 3);
  JoystickButton y = new JoystickButton(contr, 4);
  JoystickButton rightB = new JoystickButton(contr, 5);

  TalonSRX rightW = new TalonSRX(1);
  TalonSRX leftW = new TalonSRX(2);
  TalonSRX arm = new TalonSRX(6);

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
