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
import frc.team4909.robot.subsystems.intake.commands.IntakeDeploy;
import frc.team4909.robot.subsystems.intake.commands.IntakeDeployAndOn;
import frc.team4909.robot.subsystems.intake.commands.IntakeIn;
import frc.team4909.robot.subsystems.intake.IntakeSubsystem;
import frc.team4909.robot.subsystems.indexer.SorterSubsystem;
import frc.team4909.robot.subsystems.camera.CameraSubsystem;
import frc.team4909.robot.subsystems.climber.ClimberSubsystem;
import frc.team4909.robot.subsystems.climber.commands.ClimberExtend;
import frc.team4909.robot.subsystems.climber.commands.ClimberRetract;
import frc.team4909.robot.subsystems.climber.commands.ClimbUp;
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
import frc.team4909.robot.subsystems.shooter.commands.ZeroHoodInit;
import frc.team4909.robot.subsystems.shooter.commands.MoveTurret;
import frc.team4909.robot.subsystems.shooter.commands.SetHoodFar;
import frc.team4909.robot.subsystems.shooter.commands.SetHoodInit;

public class Robot extends TimedRobot {
  // Subsystems
  public static DriveTrainSubsystem drivetrainsubsystem;
  public static ShooterSubsystem shootersubsystem;
  public static IndexerSubsystem indexerSubsystem;
  public static SorterSubsystem sorterSubsystem;
  public static HoodSubsystem hoodSubsystem;
  public static IntakeSubsystem intakeSubsystem;
  public static CameraSubsystem cameraSubsystem;
  public static TurretSubsystem turretSubsystem;
  public static LEDs leds;
  public static Vision vision;
  public static BionicF310 driverGamepad;
  public static FlightStick manipulatorGamepad;
  public static ClimberSubsystem climberSubsystem;

  @Override
  public void robotInit() {

    // Instantiate the Subsystems
    drivetrainsubsystem = new DriveTrainSubsystem();
    drivetrainsubsystem.setDefaultCommand(new Drive(drivetrainsubsystem));

    vision = new Vision();

    shootersubsystem = new ShooterSubsystem();
    // shootersubsystem.setDefaultCommand(new SetShooterSpeed(1)); //(new FollowTarget(shootersubsystem, vision));

    indexerSubsystem = new IndexerSubsystem();
    //indexerSubsystem.setDefaultCommand(new SmartIndexerAndSorterUp());

    sorterSubsystem = new SorterSubsystem();
    //sorterSubsystem.setDefaultCommand(new SorterOn(sorterSubsystem));

    hoodSubsystem = new HoodSubsystem();
    //hoodSubsystem.setDefaultCommand(new MoveHood());

    intakeSubsystem = new IntakeSubsystem();

    turretSubsystem = new TurretSubsystem();

    climberSubsystem = new ClimberSubsystem();

    cameraSubsystem = new CameraSubsystem();
    cameraSubsystem.Stream();

    leds = new LEDs();

    // Initialize Controllers
    driverGamepad = new BionicF310(0, // Port
        0.05, // Deadzone
        0.6 // Gamepad sensitivity
    );

    manipulatorGamepad = new FlightStick(1, // Port
        0.7, // Deadzone
        0.0 // Gamepad sensitivity
    );

    // FlightStick Controls
      //-- Handle Buttons
    manipulatorGamepad.buttonHeld(FlightStick.One, new IndexerAndSorterUp()); //Sorter and Indexer (Joystick: Button 1)
    manipulatorGamepad.buttonHeld(FlightStick.Two, new FollowTarget(turretSubsystem, vision), false); //Toggle Limelight Aim (Joystick: Button 2)
    manipulatorGamepad.buttonPressed(FlightStick.Three, new SetShooterSpeed(0)); //Turn of Shooter (Joystick: Button 3)
    manipulatorGamepad.buttonPressed(FlightStick.Four, new SetHoodFar()); //Set Far Hood Angle (Joystick: Button 4)
    manipulatorGamepad.buttonToggled(FlightStick.Five, new SetShooterSpeed(0.75), false); //Set Shooter Speed 75% (Joystick: Button 5)
    manipulatorGamepad.buttonPressed(FlightStick.Six, new SetHoodInit()); //Set Initial Hood Angle (Joystick: Button 6)

      //-- Base Buttons
    manipulatorGamepad.buttonToggled(FlightStick.Eleven, new SmartIndexerAndSorterUp()); //Depoy the Intake, the Sorter, and the Indexer (Joystick: Button 11)
    manipulatorGamepad.buttonHeld(FlightStick.Twelve, new IndexerAndSorterDown()); //Dump Balls (Joystick: Button 12)

      //-- Axis
    manipulatorGamepad.buttonHeld(FlightStick.Z, 0.4, new MoveTurret(shootersubsystem)); //Move the Turret (Joystick: Twist Stick [Axis Z])

      //-- D-Pad
    manipulatorGamepad.povActive(FlightStick.Top, new HoodUp()); //Position the Hood Up (Joystick: D-Pad UP)
    manipulatorGamepad.povActive(FlightStick.Bottom, new HoodDown()); //Position the Hood Up (Joystick: D-Pad DOWN)

    // Gamepad Controls
      //-- Middle Buttons
    driverGamepad.buttonPressed(BionicF310.Start, new rachetHold(-180)); //Set the Ratchet Speed Up (Gamepad: Start Button)

      //-- Face Buttons
    driverGamepad.buttonHeld(BionicF310.X, new HookIn()); //Set the Hook Inwards (Gamepad: 'X' Button)
    driverGamepad.buttonHeld(BionicF310.B, new HookOut()); //Set the Hook Outwards (Gamepad: 'B' Button)
    
      //-- Bumpers
    driverGamepad.buttonHeld(BionicF310.LB, new ClimberExtend(500)); //Extend the Climber 500 (Gamepad: Light Bumper)
    driverGamepad.buttonPressed(BionicF310.RB, new InvertDrive()); //Invert Drive Direction (Gamepad: Right Bumper)

      //-- Triggers
    driverGamepad.buttonPressed(BionicF310.LT, 0.2, new ClimbUp()); //Start the Climb Up Group (Gamepad: Left Trigger)
    driverGamepad.buttonHeld(BionicF310.RT, 0.2, new ClimberRetract()); //Retract the Climber (Gamepad: Right Trigger)
}


  @Override
  public void robotPeriodic() {
    // Start Scheduling processes
    Scheduler.getInstance().run();
    CommandScheduler.getInstance().run();
    
    // Put values on SmartDashboard
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
    CommandScheduler.getInstance().schedule(new ZeroHoodInit(), new ShootThree());
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().schedule(new ZeroHoodInit());
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
