package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

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

  SpeedControllerGroup left = new SpeedControllerGroup(tl, bl);
  SpeedControllerGroup right = new SpeedControllerGroup(tr, br);

  DifferentialDrive hothBot = new DifferentialDrive(left, right);

  Joystick vessel = new Joystick(0);
  Joystick contr = new Joystick(1);

  JoystickButton a = new JoystickButton(contr, 1);
  JoystickButton b = new JoystickButton(contr, 2);
  JoystickButton x = new JoystickButton(contr, 3);
  JoystickButton y = new JoystickButton(contr, 4);
  JoystickButton leftB = new JoystickButton(contr, 5);
  JoystickButton rightB = new JoystickButton(contr, 6);
  JoystickButton leftFrenchPress = new JoystickButton(vessel, 5);
  JoystickButton rightFrenchPress = new JoystickButton(vessel, 6);

  boolean pressed = true;
  boolean notPressed = false;

  Compressor c = new Compressor(0);
  Solenoid beakThingTwo = new Solenoid(0);
  Solenoid beakThingOne = new Solenoid(1);

  AnalogPotentiometer stringPot = new AnalogPotentiometer(1, 360, 20);

  @Override
  public void robotInit() {
    // Initialize all components for Teleop
    new Thread(() -> {
    UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
    camera1.setResolution(640, 480);

    CvSink cvSink = CameraServer.getInstance().getVideo();
    CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640,
    480);

    Mat source = new Mat();
    Mat output = new Mat();

    while (!Thread.interrupted()) {
    cvSink.grabFrame(source);
    Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
    outputStream.putFrame(output);
    }
    }).start();

    new Thread(() -> {
    UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
    camera2.setResolution(640, 480);

    CvSink cvSink = CameraServer.getInstance().getVideo();
    CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640,
    480);

    Mat source = new Mat();
    Mat output = new Mat();

    while (!Thread.interrupted()) {
    cvSink.grabFrame(source);
    Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
    outputStream.putFrame(output);
    }
    }).start();

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
    System.out.println(stringPot.get());

    if (leftFrenchPress.get()) {
      hothBot.tankDrive(-vessel.getRawAxis(1) * .75, -vessel.getRawAxis(5) * .75);
    } else if (rightFrenchPress.get()) {
      hothBot.tankDrive(vessel.getRawAxis(5) * .75, vessel.getRawAxis(1) * .75);
    } else {
      hothBot.tankDrive(-vessel.getRawAxis(1), -vessel.getRawAxis(5));
    }

    if (y.get() && (stringPot.get() > 57.0)) {
      arm.set(ControlMode.PercentOutput, -.50);
    } else if (a.get() && stringPot.get() < 159.0) {
      arm.set(ControlMode.PercentOutput, .50);
    } else if (stringPot.get() < 57.0 || stringPot.get() > 159.0) {
      arm.set(ControlMode.PercentOutput, 0);
    }

    if (x.get()) {
      rightW.set(ControlMode.PercentOutput, -.60);
      leftW.set(ControlMode.PercentOutput, .60);
    } else if (b.get()) {
      rightW.set(ControlMode.PercentOutput, .60);
      leftW.set(ControlMode.PercentOutput, -.60);
    } else {
      rightW.set(ControlMode.PercentOutput, 0);
      leftW.set(ControlMode.PercentOutput, 0);
    }

    if (rightB.get()) {
      beakThingOne.set(true);
      beakThingTwo.set(true);
    } else {
      beakThingOne.set(false);
      beakThingTwo.set(false);
    }
  }
}