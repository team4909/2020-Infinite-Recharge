package frc.team4909.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team4909.robot.autos.ShootThree;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.subsystems.drivetrain.Drive;
import frc.team4909.robot.subsystems.drivetrain.InvertDrive;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterDown;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterUp;
import frc.team4909.robot.subsystems.indexer.IndexerSubsystem;
import frc.team4909.robot.subsystems.indexer.commands.SmartIndexerAndSorterUp;
import frc.team4909.robot.subsystems.intake.IntakeIn;
import frc.team4909.robot.subsystems.intake.IntakeSubsystem;
import frc.team4909.robot.subsystems.indexer.SorterSubsystem;
import frc.team4909.robot.subsystems.leds.LEDs;
import frc.team4909.robot.subsystems.shooter.*;
import frc.team4909.robot.subsystems.shooter.commands.FollowTarget;
import frc.team4909.robot.subsystems.shooter.commands.MoveHood;
import frc.team4909.robot.subsystems.shooter.commands.SetShooterSpeed;
import frc.team4909.robot.subsystems.shooter.commands.SetShooterVelocity;
import frc.team4909.robot.subsystems.shooter.commands.MoveTurret;
import frc.team4909.robot.subsystems.shooter.commands.SetHoodFar;
import frc.team4909.robot.subsystems.shooter.commands.SetHoodInit;

public class Robot extends TimedRobot {
  public static DriveTrainSubsystem drivetrainsubsystem;
  public static ShooterSubsystem shootersubsystem;
  public static IndexerSubsystem indexerSubsystem;
  public static SorterSubsystem sorterSubsystem;
  public static HoodSubsystem hoodSubsystem;
  public static IntakeSubsystem intakeSubsystem;
  public static LEDs leds;
  public static Vision vision;
  public static BionicF310 driverGamepad, manipulatorGamepad;

  public static ParallelCommandGroup shooterLimelightAssist;

  @Override
  public void robotInit() {
    
    drivetrainsubsystem = new DriveTrainSubsystem();
    drivetrainsubsystem.setDefaultCommand(new Drive(drivetrainsubsystem));

    vision = new Vision();

    shootersubsystem = new ShooterSubsystem();
    // shootersubsystem.setDefaultCommand(new FollowTarget(shootersubsystem, vision)); //(new FollowTarget(shootersubsystem, vision));

    indexerSubsystem = new IndexerSubsystem();
    // indexerSubsystem.setDefaultCommand(new IndexerOI(indexerSubsystem));

    sorterSubsystem = new SorterSubsystem();

    hoodSubsystem = new HoodSubsystem();
    hoodSubsystem.setDefaultCommand(new MoveHood());

    intakeSubsystem = new IntakeSubsystem();

    // leds = new LEDs();

    driverGamepad = new BionicF310(0, // Port
        0, // Deadzone
        0 // Gamepad sensitivity
    );

    manipulatorGamepad = new BionicF310(1, // Port
        0, // Deadzone
        0.0 // Gamepad sensitivity
    );

    manipulatorGamepad.buttonHeld(BionicF310.B, new FollowTarget(shootersubsystem, vision));
    manipulatorGamepad.buttonHeld(BionicF310.RT, 0.1, new IndexerAndSorterUp());
    manipulatorGamepad.buttonHeld(BionicF310.RX, 0.2, new MoveTurret(shootersubsystem));
    manipulatorGamepad.buttonPressed(BionicF310.Start, new SetShooterVelocity(shootersubsystem, 4000));
    manipulatorGamepad.buttonPressed(BionicF310.Back, new SetShooterSpeed(0));
    manipulatorGamepad.buttonHeld(BionicF310.LT, 0.1, new SmartIndexerAndSorterUp());
    manipulatorGamepad.buttonHeld(BionicF310.L, new IntakeIn());
    manipulatorGamepad.buttonHeld(BionicF310.A, new IndexerAndSorterDown());
    
    manipulatorGamepad.buttonPressed(BionicF310.LB, new SetHoodInit());
    manipulatorGamepad.buttonPressed(BionicF310.RB, new SetHoodFar());

    driverGamepad.buttonToggled(BionicF310.RB, new InvertDrive());
}

  @Override   
  public void robotPeriodic() {
    //System.out.print("test");
    Scheduler.getInstance().run();
    CommandScheduler.getInstance().run();
     
    SmartDashboard.putNumber("X Offset", vision.getXOffset());
    SmartDashboard.putNumber("Shooter Distance", Robot.vision.calculateDistanceFromCameraHeight(RobotConstants.powerPortHeight, RobotConstants.limelightHeight, RobotConstants.limelightAngle));
    SmartDashboard.putBoolean("Upper Has Ball", indexerSubsystem.hasBallUpper());
    SmartDashboard.putBoolean("Lower Has Ball", indexerSubsystem.hasBallLower());
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
    CommandScheduler.getInstance().schedule(new ShootThree());
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    // CommandScheduler.getInstance().schedule(new ZeroTurret());
    hoodSubsystem.zeroHood();
    shootersubsystem.setSpeed(0);
  }

  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("X  - Offset", vision.getXOffset());
    SmartDashboard.putNumber("Shooter  - Distance", Robot.vision.calculateDistanceFromCameraHeight(RobotConstants.powerPortHeight, RobotConstants.limelightHeight, RobotConstants.limelightAngle));
  }

  @Override
  public void testPeriodic() {
  }
}
