package frc.team4909.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team4909.robot.operator.controllers.BionicF310;
//<<<<<<< I2C_Ultrasonic_Testing
//import frc.team4909.robot.subsystems.drivetrain.Drive;
//import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;
//import frc.team4909.robot.subsystems.turret.LimelightTurret;
//import frc.team4909.robot.subsystems.turret.TurretSubsystem;
import frc.team4909.robot.subsystems.ultrasonic.UltrasonicSensor;

public class Robot extends TimedRobot {
  //public static DriveTrainSubsystem drivetrainsubsystem;
//  public static TurretSubsystem turretsubsystem;
  public static UltrasonicSensor uSensor;
=======
 import frc.team4909.robot.subsystems.drivetrain.Drive;
 import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;
 import frc.team4909.robot.subsystems.indexer.IndexerAndSorterDown;
 import frc.team4909.robot.subsystems.indexer.IndexerAndSorterUp;
 import frc.team4909.robot.subsystems.indexer.IndexerOI;
 import frc.team4909.robot.subsystems.indexer.IndexerSubsystem;
 import frc.team4909.robot.subsystems.indexer.IndexerUp;
 import frc.team4909.robot.subsystems.indexer.SorterOn;
 import frc.team4909.robot.subsystems.indexer.SorterSubsystem;
 import frc.team4909.robot.subsystems.leds.LEDs;
 import frc.team4909.robot.subsystems.shooter.*;

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
     drivetrainsubsystem = new DriveTrainSubsystem();
     drivetrainsubsystem.setDefaultCommand(new Drive(drivetrainsubsystem));

    
    SmartDashboard.putNumber("Set RPM", 1000);
    drivetrainsubsystem = new DriveTrainSubsystem();
    drivetrainsubsystem.setDefaultCommand(new Drive(drivetrainsubsystem));
    vision = new Vision();
    uSensor = new UltrasonicSensor();

    shootersubsystem = new ShooterSubsystem();
    shootersubsystem.setDefaultCommand(new FollowTarget(shootersubsystem, vision)); //(new FollowTarget(shootersubsystem, vision));

    indexerSubsystem = new IndexerSubsystem();
     indexerSubsystem.setDefaultCommand(new IndexerOI(indexerSubsystem));

    sorterSubsystem = new SorterSubsystem();

     leds = new LEDs();

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
//<<<<<<< I2C_Ultrasonic_Testing
    SmartDashboard.putNumber("distance", uSensor.read());
    //SmartDashboard.putNumber("X Offset", vision.getXOffset());
    System.out.print(uSensor.read());
=======
     
    //SmartDashboard.putNumber("X Offset", vision.getXOffset());
  }

 // @Override
  public void disabledInit() {
    
  }

  @Override
  public void disabledPeriodic() {
    //leds.setAllianceColor();  
//>>>>>>> master
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
