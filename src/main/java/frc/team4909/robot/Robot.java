package frc.team4909.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team4909.robot.autos.ShootThree;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.operator.controllers.FlightStick;
import frc.team4909.robot.subsystems.drivetrain.Drive;
import frc.team4909.robot.subsystems.drivetrain.InvertDrive;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterDown;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterUp;
import frc.team4909.robot.subsystems.indexer.commands.IndexerUp;
import frc.team4909.robot.subsystems.indexer.IndexerSubsystem;
import frc.team4909.robot.subsystems.indexer.commands.SmartIndexerAndSorterUp;
import frc.team4909.robot.subsystems.indexer.commands.SorterOn;
import frc.team4909.robot.subsystems.intake.IntakeIn;
import frc.team4909.robot.subsystems.intake.IntakeSubsystem;
import frc.team4909.robot.subsystems.indexer.SorterSubsystem;
import frc.team4909.robot.subsystems.climber.ClimberSubsystem;
import frc.team4909.robot.subsystems.climber.commands.ClimberExtend;
import frc.team4909.robot.subsystems.climber.commands.ClimberRetract;
import frc.team4909.robot.subsystems.climber.commands.HookIn;
import frc.team4909.robot.subsystems.climber.commands.HookOut;
import frc.team4909.robot.subsystems.climber.commands.rachetHold;
import frc.team4909.robot.subsystems.leds.LEDs;
import frc.team4909.robot.subsystems.shooter.*;
import frc.team4909.robot.subsystems.shooter.commands.FollowTarget;
import frc.team4909.robot.subsystems.shooter.commands.HoodDown;
import frc.team4909.robot.subsystems.shooter.commands.HoodUp;
import frc.team4909.robot.subsystems.shooter.commands.MoveHood;
import frc.team4909.robot.subsystems.shooter.commands.SetShooterSpeed;
import frc.team4909.robot.subsystems.shooter.commands.SetShooterVelocity;
import frc.team4909.robot.subsystems.shooter.commands.ShootBalls;
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
  public static TurretSubsystem turretSubsystem;
  public static LEDs leds;
  public static Vision vision;
  public static BionicF310 driverGamepad;
  public static FlightStick manipulatorGamepad;
  public static ClimberSubsystem climberSubsystem;

  @Override
  public void robotInit() {


    
    drivetrainsubsystem = new DriveTrainSubsystem();
    drivetrainsubsystem.setDefaultCommand(new Drive(drivetrainsubsystem));

    vision = new Vision();

    shootersubsystem = new ShooterSubsystem();
    shootersubsystem.setDefaultCommand(new SetShooterSpeed(0.2)); //(new FollowTarget(shootersubsystem, vision));

    indexerSubsystem = new IndexerSubsystem();
    //indexerSubsystem.setDefaultCommand(new SmartIndexerAndSorterUp());

    sorterSubsystem = new SorterSubsystem();
    //sorterSubsystem.setDefaultCommand(new SorterOn(sorterSubsystem));

    hoodSubsystem = new HoodSubsystem();
    //hoodSubsystem.setDefaultCommand(new MoveHood());

    intakeSubsystem = new IntakeSubsystem();

    turretSubsystem = new TurretSubsystem();

    climberSubsystem = new ClimberSubsystem();
    

    leds = new LEDs();

    driverGamepad = new BionicF310(0, // Port
        0.05, // Deadzone
        0.6 // Gamepad sensitivity
    );

    manipulatorGamepad = new FlightStick(1, // Port
        0.05, // Deadzone
        0.0 // Gamepad sensitivity
    );


    manipulatorGamepad.buttonHeld(FlightStick.Two, new FollowTarget(turretSubsystem, vision), false);
    manipulatorGamepad.buttonToggled(FlightStick.Five, new SetShooterVelocity(shootersubsystem, 3500), false);
    manipulatorGamepad.buttonPressed(FlightStick.Three, new SetShooterSpeed(0.2));
    manipulatorGamepad.buttonHeld(FlightStick.Twelve, new IndexerAndSorterDown());
    manipulatorGamepad.buttonHeld(FlightStick.One, new IndexerUp(indexerSubsystem));
    manipulatorGamepad.buttonPressed(FlightStick.Six, new SetHoodInit());
    manipulatorGamepad.buttonPressed(FlightStick.Four, new SetHoodFar());
    manipulatorGamepad.buttonHeld(FlightStick.Z, 0.3, new MoveTurret(shootersubsystem));

    manipulatorGamepad.povActive(FlightStick.Top, new HoodUp());
    manipulatorGamepad.povActive(FlightStick.Bottom, new HoodDown());

    driverGamepad.buttonPressed(BionicF310.RB, new InvertDrive());


    driverGamepad.buttonPressed(BionicF310.Start, new rachetHold(-180));
    driverGamepad.buttonHeld(BionicF310.X, new HookIn());
    driverGamepad.buttonHeld(BionicF310.B, new HookOut());



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
