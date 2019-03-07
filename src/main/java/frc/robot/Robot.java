package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;

public class Robot extends TimedRobot {
  DigitalInput lightfollwer;
  Ultrasonic ultra = new Ultrasonic(5, 6);
  Joystick controller = new Joystick (0);
  JoystickButton rightB = new JoystickButton(controller, 9);
  UsbCamera camera1;
  UsbCamera camera2;
  VideoSink server;
  @Override
  public void robotInit() {
    // Initialize all components for Teleop
    lightfollwer = new DigitalInput   (4);
    ultra.setAutomaticMode(true);
    camera1 = CameraServer.getInstance().startAutomaticCapture(0);
    camera2 = CameraServer.getInstance().startAutomaticCapture(1);
    server = CameraServer.getInstance().getServer();
   
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
    SmartDashboard.putBoolean("LightSensor", lightfollwer.get());
    SmartDashboard.putBoolean("Ultrasonic", ultra.getRangeInches()>10);
    System.out.println(lightfollwer.get());
    System.out.println(ultra.getRangeInches());

    // Write Teleop code
      if(rightB.get()){
        System.out.println("Setting camera 1\n");
        server.setSource(camera1);
    }else{
      System.out.println("Setting camera 2\n");
      server.setSource(camera2);
    }
  }
}
