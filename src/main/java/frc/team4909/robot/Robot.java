package frc.team4909.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.subsystems.ShooterSubsystem;
import frc.team4909.robot.subsystems.drivetrain.Drive;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;
import frc.team4909.robot.subsystems.turret.LimelightTurret;
import frc.team4909.robot.subsystems.turret.ShooterVelocity;
import frc.team4909.robot.subsystems.turret.TurretSubsystem;

public class Robot extends TimedRobot {
  public static DriveTrainSubsystem drivetrainsubsystem;
  public static TurretSubsystem turretsubsystem;
  public static ShooterSubsystem shootersubsystem;
  public static Vision vision;
  public static BionicF310 driverGamepad;

  @Override
  public void robotInit() {
    drivetrainsubsystem = new DriveTrainSubsystem();
    drivetrainsubsystem.setDefaultCommand(new Drive(drivetrainsubsystem));

    vision = new Vision();

    turretsubsystem = new TurretSubsystem();
    turretsubsystem.setDefaultCommand(new LimelightTurret(turretsubsystem, vision));

    shootersubsystem = new ShooterSubsystem();
    shootersubsystem.setDefaultCommand(new ShooterVelocity(shootersubsystem, 1500));

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
     
    SmartDashboard.putNumber("X Offset", vision.getXOffset());
    SmartDashboard.putNumber("Distance", vision.calculateDistanceFromCameraHeight(-9.5, 43.25, 0));
    SmartDashboard.putNumber("Speed1", shootersubsystem.encoder.getVelocity());
    SmartDashboard.putNumber("Speed2", shootersubsystem.encoder2.getVelocity());
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
