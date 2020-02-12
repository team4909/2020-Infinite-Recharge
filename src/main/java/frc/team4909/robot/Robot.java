package frc.team4909.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.subsystems.drivetrain.Drive;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterDown;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterUp;
import frc.team4909.robot.subsystems.indexer.IndexerSubsystem;
import frc.team4909.robot.subsystems.indexer.commands.IndexerUp;
import frc.team4909.robot.subsystems.indexer.commands.SorterOn;
import frc.team4909.robot.subsystems.indexer.SorterSubsystem;
import frc.team4909.robot.subsystems.leds.LEDs;
import frc.team4909.robot.subsystems.shooter.*;
import frc.team4909.robot.subsystems.shooter.commands.FollowTarget;
import frc.team4909.robot.subsystems.shooter.commands.HoodDown;
import frc.team4909.robot.subsystems.shooter.commands.HoodUp;
import frc.team4909.robot.subsystems.shooter.commands.SetShooterVelocity;

public class Robot extends TimedRobot {
  public static DriveTrainSubsystem drivetrainsubsystem;
  public static ShooterSubsystem shootersubsystem;
  public static IndexerSubsystem indexerSubsystem;
  public static SorterSubsystem sorterSubsystem;
  public static LEDs leds;
  public static Vision vision;
  public static BionicF310 driverGamepad;

  public static ParallelCommandGroup shooterLimelightAssist;

  @Override
  public void robotInit() {
    
    SmartDashboard.putNumber("Set RPM", 1000);
    drivetrainsubsystem = new DriveTrainSubsystem();
    drivetrainsubsystem.setDefaultCommand(new Drive(drivetrainsubsystem));

    vision = new Vision();

    shootersubsystem = new ShooterSubsystem();
    // shootersubsystem.setDefaultCommand(new FollowTarget(shootersubsystem, vision)); //(new FollowTarget(shootersubsystem, vision));

    indexerSubsystem = new IndexerSubsystem();
    // indexerSubsystem.setDefaultCommand(new IndexerOI(indexerSubsystem));

    sorterSubsystem = new SorterSubsystem();

    // leds = new LEDs();

    driverGamepad = new BionicF310(0, // Port
        0.6, // Deadzone
        0.1 // Gamepad sensitivity
    );

    shootersubsystem.setVelocity(0);

    driverGamepad.buttonPressed(BionicF310.A, new FollowTarget(shootersubsystem, vision));
    driverGamepad.buttonToggled(BionicF310.B, new SetShooterVelocity(shootersubsystem, 5400));
    driverGamepad.buttonHeld(BionicF310.X, new IndexerAndSorterUp());
    driverGamepad.buttonHeld(BionicF310.Y, new IndexerAndSorterDown());
    driverGamepad.buttonHeld(BionicF310.LB, new HoodUp(shootersubsystem));
    driverGamepad.buttonHeld(BionicF310.RB, new HoodDown(shootersubsystem));
    
}

  @Override   
  public void robotPeriodic() {
    //System.out.print("test");
    Scheduler.getInstance().run();
    CommandScheduler.getInstance().run();
     
    //SmartDashboard.putNumber("X Offset", vision.getXOffset());
  }

  @Override
  public void disabledInit() {
    
  }

  @Override
  public void disabledPeriodic() {
    //leds.setAllianceColor();  
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
