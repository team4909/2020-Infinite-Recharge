package frc.team4909.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;

public class Robot extends TimedRobot {
  public static DriveTrainSubsystem drivetrainsubsystem;
  public static BionicF310 driverGamepad;

  @Override
  public void robotInit() {
    drivetrainsubsystem = new DriveTrainSubsystem();

    driverGamepad = new BionicF310(0, // Port
        0.6, // Deadzone
        0.1 // Gamepad sensitivity
    );
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testPeriodic() {
  }
}
