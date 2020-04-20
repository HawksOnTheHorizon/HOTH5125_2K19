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
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class Robot extends TimedRobot {

  Ultrasonic ultra = new Ultrasonic(0, 1);

  WPI_VictorSPX tl = new WPI_VictorSPX(4); //top left wheel 
  WPI_VictorSPX bl = new WPI_VictorSPX(3); //bottom left wheel
  WPI_VictorSPX br = new WPI_VictorSPX(7); //bottom right wheel 
  WPI_VictorSPX tr = new WPI_VictorSPX(5); //top right wheel
  TalonSRX rightW = new TalonSRX(1); //right arm wheel
  TalonSRX leftW = new TalonSRX(2); //left arm wheel 
  VictorSPX arm = new VictorSPX(6); //arm (goes up and down)



  Servo finger = new Servo(0);

  //DigitalInput limit = new DigitalInput(0);

  SpeedControllerGroup left = new SpeedControllerGroup(tl, bl); //left motor group
  SpeedControllerGroup right = new SpeedControllerGroup(tr, br); //right motor group

  DifferentialDrive hothBot = new DifferentialDrive(left, right); //name of robot 

  Joystick vessel = new Joystick(0); //joystick driver 1 
  Joystick contr = new Joystick(1); //joystick driver 2

  JoystickButton a = new JoystickButton(contr, 1); //driver 2
  JoystickButton b = new JoystickButton(contr, 2); //driver 2
  JoystickButton x = new JoystickButton(contr, 3); //driver 2
  JoystickButton y = new JoystickButton(contr, 4); //driver 2
  JoystickButton leftB = new JoystickButton(contr, 5); //driver 2
  JoystickButton rightB = new JoystickButton(contr, 6); //driver 2
  JoystickButton leftFrenchPress = new JoystickButton(vessel, 5); //driver 1
  JoystickButton rightFrenchPress = new JoystickButton(vessel, 6); //driver 1

  boolean pressed = true;
  boolean notPressed = false;

  Compressor c = new Compressor(0);
  Solenoid beakThingTwo = new Solenoid(0);
  Solenoid beakThingOne = new Solenoid(1);

  AnalogPotentiometer stringPot = new AnalogPotentiometer(1, 360, 20);


  @Override
  public void robotInit() {
    ultra.setAutomaticMode(true);

    
    new Thread(() -> { //camera stuff for the first camera (front camera??)
      UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
      camera1.setResolution(640, 240);
      camera1.setExposureManual(60);
      camera1.setBrightness(30);

      CvSink cvSink = CameraServer.getInstance().getVideo();
      CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 240);

      Mat source = new Mat();
      Mat output = new Mat();

      while (!Thread.interrupted()) {
        cvSink.grabFrame(source);
        Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
        outputStream.putFrame(output);
      }
    }).start();

    new Thread(() -> { //camera stuff for the second camera (back camera??)
      UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
      camera2.setResolution(160,120);
      camera2.setExposureManual(60);
      camera2.setBrightness(40);
      CvSink cvSink = CameraServer.getInstance().getVideo();
      CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);

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
    if (leftFrenchPress.get()) { // leftFrenchPress = LT, if the LT button is pressed then 
      hothBot.tankDrive(-vessel.getRawAxis(1), -vessel.getRawAxis(5)); // drive at 100% speed
    } else if (rightFrenchPress.get()) { // rightFrenchPress = RT, otherwise if the RT button is pressed then  
      hothBot.tankDrive(vessel.getRawAxis(5) * .75, vessel.getRawAxis(1) * .75); // reverse drive at 75% speed
    } else { //otherwise if nothing is pressed then 
      hothBot.tankDrive(-vessel.getRawAxis(1)* .75, -vessel.getRawAxis(5)* .75); // drive at 75% speed
    }

    if (y.get() && (stringPot.get() > 57.0)) { // if y buttton is pressed and the potentiometer is more than 57 then
      arm.set(ControlMode.PercentOutput, -.50); // the arm goes up
    } else if (a.get() && stringPot.get() < 159.0) { // otherwise if a button is pressed and the potentiometer is less than 159 then
      arm.set(ControlMode.PercentOutput, .50); // the arm goes down
    } else if (stringPot.get() < 57.0 || stringPot.get() > 159.0) { // otherwise if the potentiometer is less than 57 but more 159 then
      arm.set(ControlMode.PercentOutput, 0); // the arm does nothing
    }

    if (x.get()) { // if x button is pressed then
      rightW.set(ControlMode.PercentOutput, -.75); // right and left wheel turn inward; intake 
      leftW.set(ControlMode.PercentOutput, .75);
    } else if (b.get()) { // if b button is pressed then 
      rightW.set(ControlMode.PercentOutput, .75); // right and left wheel turn outward; shooter
      leftW.set(ControlMode.PercentOutput, -.75);
    } else { // otherwise if nothing is pressed then 
      rightW.set(ControlMode.PercentOutput, 0); // right and left wheel do nothing 
      leftW.set(ControlMode.PercentOutput, 0);
    }

    if (rightB.get()) { // rightB = RB, if RB is pressed then
      beakThingOne.set(true); // set boolean to true and extend pistons  
      beakThingTwo.set(true);
    } else { // otherwise if nothing is pressed then
      beakThingOne.set(false); // set boolean to false and don't extend pistons
      beakThingTwo.set(false);
    }

   SmartDashboard.putBoolean("Ultrasonic", (ultra.getRangeInches()>18 && ultra.getRangeInches()<23)); // ultrasonic values to appear on SmartDashboard

  }

  @Override
  public void teleopPeriodic() { // comments in auto explain teleop code as well
    // Write Teleop code
    System.out.println(stringPot.get()); // print out value of potentiometer 

    if (leftFrenchPress.get()) {
      hothBot.tankDrive(-vessel.getRawAxis(1) , -vessel.getRawAxis(5));
    } else if (rightFrenchPress.get()) {
      hothBot.tankDrive(vessel.getRawAxis(5) * .75, vessel.getRawAxis(1) * .75);
    } else {
      hothBot.tankDrive(-vessel.getRawAxis(1)* .75, -vessel.getRawAxis(5)* .75);
    }

    if (y.get() && (stringPot.get() > 57.0)) {
      arm.set(ControlMode.PercentOutput, -.50);
    } else if (a.get() && stringPot.get() < 175.0) {
      arm.set(ControlMode.PercentOutput, .50);
    } else if (stringPot.get() < 57.0 || stringPot.get() > 175.0) {
      arm.set(ControlMode.PercentOutput, 0);
    }

    if (x.get()) {
      rightW.set(ControlMode.PercentOutput, -.75);
      leftW.set(ControlMode.PercentOutput, .75);
    } else if (b.get()) {
      rightW.set(ControlMode.PercentOutput, .75);
      leftW.set(ControlMode.PercentOutput, -.75);
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

    SmartDashboard.putBoolean("Ultrasonic", (ultra.getRangeInches()>18 && ultra.getRangeInches()<23));

  }
}
