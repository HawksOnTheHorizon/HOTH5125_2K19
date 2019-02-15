package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class Robot extends TimedRobot {
  WPI_VictorSPX tl = new WPI_VictorSPX(4);
  WPI_VictorSPX bl = new WPI_VictorSPX(3);
  WPI_VictorSPX br = new WPI_VictorSPX(7);
  WPI_VictorSPX tr = new WPI_VictorSPX(5);
  TalonSRX rightW = new TalonSRX(1);
  TalonSRX leftW = new TalonSRX(2);
  VictorSPX arm = new VictorSPX(6);

  Servo finger = new Servo(0);

  DigitalInput limit = new DigitalInput(0);
  AnalogPotentiometer armPot = new AnalogPotentiometer(1);

  SpeedControllerGroup left = new SpeedControllerGroup(tl, bl);
  SpeedControllerGroup right = new SpeedControllerGroup(tr, br);

  DifferentialDrive hothBot = new DifferentialDrive(left, right);

  Joystick lJoystick = new Joystick(0);
  Joystick contr = new Joystick(1);

  JoystickButton a = new JoystickButton(contr, 1);
  JoystickButton b = new JoystickButton(contr, 2);
  JoystickButton x = new JoystickButton(contr, 3);
  JoystickButton y = new JoystickButton(contr, 4);
  JoystickButton rightB = new JoystickButton(contr, 6);
  JoystickButton leftFrenchPress = new JoystickButton(contr, 9);
  JoystickButton rightFrenchPress = new JoystickButton(contr, 10);

  boolean pressed = true;
  boolean notPressed = false;
  

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
    System.out.println(armPot.get());
    

    if (leftFrenchPress.get()) {
      hothBot.tankDrive(-lJoystick.getRawAxis(1) * .75, -lJoystick.getRawAxis(5) * .75);
    } else {
      hothBot.tankDrive(-lJoystick.getRawAxis(1), -lJoystick.getRawAxis(5));
    }
    
    if (rightFrenchPress.get()) {
      hothBot.tankDrive(lJoystick.getRawAxis(1) * .75, lJoystick.getRawAxis(5) * .75);
    } else {
      hothBot.tankDrive(-lJoystick.getRawAxis(1), -lJoystick.getRawAxis(5));
    }



    if ((a.get() == pressed) && (limit.get() == notPressed)) {
      arm.set(ControlMode.PercentOutput, .50);
    } else if ((a.get() == pressed) && (limit.get() == pressed)) {
      arm.set(ControlMode.PercentOutput, 0);
    } else{
      arm.set(ControlMode.PercentOutput, 0);
    }

    if (y.get()) {
      arm.set(ControlMode.PercentOutput, -.50);
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

    if (rightB.get()) {
      finger.setAngle(175);
    } else {
      finger.setAngle(70);
    }

  }
}
