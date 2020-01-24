package frc.team4909.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.subsystems.Actual_Color_sensing_with_sensor_placement;
import frc.team4909.robot.subsystems.ColorSensor;
import frc.team4909.robot.subsystems.Color_sensor_sensing;
import frc.team4909.robot.subsystems.drivetrain.Drive;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;

public class Robot extends TimedRobot {
  public static DriveTrainSubsystem drivetrainsubsystem;
  public static ColorSensor colorsensor;
  public static BionicF310 driverGamepad;

  @Override
  public void robotInit() {
    drivetrainsubsystem = new DriveTrainSubsystem();
    drivetrainsubsystem.setDefaultCommand(new Drive(drivetrainsubsystem));

    colorsensor = new ColorSensor();

    driverGamepad = new BionicF310(0, // Port
        0.6, // Deadzone
        0.1 // Gamepad sensitivity
    );
  }

  @Override
  public void robotPeriodic() {
    //System.out.print("test");
    Scheduler.getInstance().run();
    CommandScheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().schedule(new Actual_Color_sensing_with_sensor_placement());
  }

  @Override
  public void teleopPeriodic() {


    

  }

  @Override
  public void testPeriodic() {
  }
}
