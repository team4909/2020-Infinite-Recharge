package frc.team4909.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team4909.robot.autos.AutoShootThreePickUpThree;
import frc.team4909.robot.autos.ShootThree;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.operator.controllers.FlightStick;
import frc.team4909.robot.subsystems.camera.CameraSubsystem;
import frc.team4909.robot.subsystems.climber.ClimberSubsystem;
import frc.team4909.robot.subsystems.climber.commands.ClimberExtend;
import frc.team4909.robot.subsystems.climber.commands.ClimberRetract;
import frc.team4909.robot.subsystems.climber.commands.ClimberSetSpeed;
import frc.team4909.robot.subsystems.climber.commands.HookIn;
import frc.team4909.robot.subsystems.climber.commands.HookOut;
import frc.team4909.robot.subsystems.climber.commands.MoveRatchet;
// import frc.team4909.robot.subsystems.climber.commands.MoveRatchet;
import frc.team4909.robot.subsystems.drivetrain.DriveWithJoystick;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;
import frc.team4909.robot.subsystems.drivetrain.InvertDrive;
import frc.team4909.robot.subsystems.drivetrain.TogglePreciseMode;
import frc.team4909.robot.subsystems.indexer.IndexerSubsystem;
import frc.team4909.robot.subsystems.indexer.SorterSubsystem;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterDown;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterUp;
import frc.team4909.robot.subsystems.indexer.commands.SmartIndexerAndSorterUp;
import frc.team4909.robot.subsystems.intake.IntakeSubsystem;
import frc.team4909.robot.subsystems.leds.LEDSetter;
import frc.team4909.robot.subsystems.leds.LEDs;
import frc.team4909.robot.subsystems.shooter.HoodSubsystem;
import frc.team4909.robot.subsystems.shooter.ShooterSubsystem;
import frc.team4909.robot.subsystems.shooter.TurretSubsystem;
import frc.team4909.robot.subsystems.shooter.commands.FollowAndAim;
import frc.team4909.robot.subsystems.shooter.commands.HoodDown;
import frc.team4909.robot.subsystems.shooter.commands.HoodUp;
import frc.team4909.robot.subsystems.shooter.commands.MoveHood;
import frc.team4909.robot.subsystems.ultrasonic.UltrasonicSensorSubsystem;
import frc.team4909.robot.subsystems.shooter.commands.MoveTurret;
import frc.team4909.robot.subsystems.shooter.commands.SetHoodClose;
import frc.team4909.robot.subsystems.shooter.commands.SetHoodFar;
import frc.team4909.robot.subsystems.shooter.commands.SetHoodInit;
import frc.team4909.robot.subsystems.shooter.commands.SetShooterSpeed;
import frc.team4909.robot.subsystems.shooter.commands.SetShooterVelocity;
import frc.team4909.robot.subsystems.shooter.commands.ZeroHoodInit;

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
  public static UltrasonicSensorSubsystem uSensorSubsystem;
  public static ParallelCommandGroup shooterLimelightAssist;
  public static ClimberSubsystem climberSubsystem;
  public static SendableChooser autoChooser;

  @Override
  public void robotInit() {

    // Instantiate the Subsystems
    drivetrainsubsystem = new DriveTrainSubsystem();
    drivetrainsubsystem.setDefaultCommand(new DriveWithJoystick(drivetrainsubsystem));

    vision = new Vision();

    shootersubsystem = new ShooterSubsystem();
    shootersubsystem.setDefaultCommand(new SetShooterVelocity(0)); //10000 //(new FollowTarget(shootersubsystem, vision));

    indexerSubsystem = new IndexerSubsystem();
    //indexerSubsystem.setDefaultCommand(new SmartIndexerAndSorterUp());

    sorterSubsystem = new SorterSubsystem();
    //sorterSubsystem.setDefaultCommand(new SorterOn(sorterSubsystem));

    hoodSubsystem = new HoodSubsystem();
    //hoodSubsystem.setDefaultCommand(new MoveHood());

    intakeSubsystem = new IntakeSubsystem();

    turretSubsystem = new TurretSubsystem();

    // uSensorSubsystem = new UltrasonicSensorSubsystem();

    climberSubsystem = new ClimberSubsystem();
    climberSubsystem.setDefaultCommand(new ClimberSetSpeed(0.025));

    cameraSubsystem = new CameraSubsystem();
    cameraSubsystem.Stream();

    leds = new LEDs();
    // leds.setDefaultCommand(new LEDSetter());

    autoChooser = new SendableChooser<>();
    // autoChooser.setDefaultOption("Do Nothing", null);
    autoChooser.setDefaultOption("Shoot 3 Pickup 3", new AutoShootThreePickUpThree());
    autoChooser.addOption("Shoot 3", new ShootThree());
    SmartDashboard.putData("Autonomous Mode: ", autoChooser);

    // Initialize Controllers
    driverGamepad = new BionicF310(0, // Port
        0.05, // Deadzone
        0.6 // Gamepad sensitivity
    );

    manipulatorGamepad = new FlightStick(1, // Port
        0.05, // Deadzone
        0.0 // Gamepad sensitivity
    );

// Robot Controls
    // FlightStick Controls
      //-- Handle Buttons
    manipulatorGamepad.buttonHeld(FlightStick.One, new IndexerAndSorterUp()); //Sorter and Indexer (Joystick: Button 1)
    manipulatorGamepad.buttonHeld(FlightStick.Two, new FollowAndAim(), false); //Toggle Limelight Aim (Joystick: Button 2)
    manipulatorGamepad.buttonPressed(FlightStick.Three, new ParallelCommandGroup(new SetShooterSpeed(0.5), new ZeroHoodInit())); //Turn of Shooter (Joystick: Button 3)
    //manipulatorGamepad.buttonPressed(FlightStick.Four, new SetHoodInit()); //Set Far Hood Angle (Joystick: Button 4)
    manipulatorGamepad.buttonToggled(FlightStick.Four, new SetHoodClose());
    manipulatorGamepad.buttonToggled(FlightStick.Five, new SetShooterVelocity(21000), false); //Set Shooter Speed 75% (Joystick: Button 5)
    manipulatorGamepad.buttonPressed(FlightStick.Six, new SetHoodFar()); //Set Initial Hood Angle (Joystick: Button 6)

      //-- Base Buttons
    manipulatorGamepad.buttonToggled(FlightStick.Eleven, new SmartIndexerAndSorterUp()); //Depoy the Intake, the Sorter, and the Indexer (Joystick: Button 11)
    manipulatorGamepad.buttonHeld(FlightStick.Twelve, new IndexerAndSorterDown()); //Dump Balls (Joystick: Button 12)
    manipulatorGamepad.buttonPressed(FlightStick.Seven, new ZeroHoodInit());
    //manipulatorGamepad.buttonPressed(FlightStick.TopRight, new SetGreen()); //Set LEDs to Green When ready to Buddy Climb.

      //-- Axis
    manipulatorGamepad.buttonHeld(FlightStick.Z, 0.6, new MoveTurret(), false); //Move the Turret (Joystick: Twist Stick [Axis Z])

      //-- D-Pad
    manipulatorGamepad.povActive(FlightStick.Top, new HoodUp()); //Position the Hood Up (Joystick: D-Pad UP)
    manipulatorGamepad.povActive(FlightStick.Bottom, new HoodDown()); //Position the Hood Up (Joystick: D-Pad DOWN)

    // Gamepad Controls
      //-- Middle Buttons
    

      //-- Face Buttons
    // driverGamepad.buttonHeld(BionicF310.X, new HookIn()); //Set the Hook Inwards (Gamepad: 'X' Button)
    // driverGamepad.buttonHeld(BionicF310.B, new HookOut()); //Set the Hook Outwards (Gamepad: 'B' Button)
    
      //-- Bumpers
    // driverGamepad.buttonHeld(BionicF310.LB, new ClimberExtend(500)); //Extend the Climber 500 (Gamepad: Light Bumper)
    driverGamepad.buttonPressed(BionicF310.RB, new InvertDrive()); //Invert Drive Direction (Gamepad: Right Bumper)

      //-- Triggers
    driverGamepad.buttonHeld(BionicF310.LT, 0.2, new ClimberExtend()); //Start the Climb Up Group (Gamepad: Left Trigger)
    driverGamepad.buttonHeld(BionicF310.RT, 0.2, new ClimberRetract()); //Retract the Climber (Gamepad: Right Trigger)
    driverGamepad.buttonHeld(BionicF310.A, new MoveRatchet(180));
    driverGamepad.buttonHeld(BionicF310.B, new MoveRatchet(0));

    driverGamepad.buttonPressed(BionicF310.X, new TogglePreciseMode());

    

    vision.setPipeline(1);
}



  @Override
  public void robotPeriodic() {
    // Start Scheduling processes
    Scheduler.getInstance().run();
    CommandScheduler.getInstance().run();

    // Robot.leds.setBlack();
    
    // Put values on SmartDashboards
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
    // Ready Auto Commands
    // CommandScheduler.getInstance().schedule(new ShootThree());
    CommandScheduler.getInstance().schedule((CommandBase)autoChooser.getSelected(), new ZeroHoodInit());
    climberSubsystem.resetClimbEncoder();
    drivetrainsubsystem.zeroGyro();
    hoodSubsystem.zeroHood();
    shootersubsystem.setSpeed(0);
    intakeSubsystem.zeroDeploy();
    leds.setDefault();
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    // Ready Telop Commands.
    // CommandScheduler.getInstance().schedule(new ZeroHoodInit());
    // climberSubsystem.resetClimbEncoder();
    // drivetrainsubsystem.zeroGyro();
    // hoodSubsystem.zeroHood();
    // shootersubsystem.setSpeed(0);
    // intakeSubsystem.zeroDeploy();
    // climberSubsystem.setRatchetSpeed(-0.5);
    // climberSubsystem.setRatchetAngl
  }

  @Override
  public void teleopPeriodic() {
    // Insert Additional Values into Smart Dashboard
    SmartDashboard.putNumber("X  - Offset", vision.getXOffset());
    SmartDashboard.putNumber("Shooter  - Distance", Robot.vision.calculateDistanceFromCameraHeight(RobotConstants.powerPortHeight, RobotConstants.limelightHeight, RobotConstants.limelightAngle));
    // shooterSubsystem.setVelocity(SmartDashboard.getNumber("Shooter Speed", 0));
  }

  @Override
  public void testInit() {
    vision.setLights(3);
  }

  @Override
  public void testPeriodic() {
  }
}
