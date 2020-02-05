package frc.team4909.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.subsystems.drivetrain.Drive;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;
import frc.team4909.robot.subsystems.indexer.IndexerOI;
import frc.team4909.robot.subsystems.indexer.IndexerSubsystem;
import frc.team4909.robot.subsystems.leds.LEDs;
import frc.team4909.robot.subsystems.shooter.*;

public class Robot extends TimedRobot {
  public static DriveTrainSubsystem drivetrainsubsystem;
  public static ShooterSubsystem shootersubsystem;
  public static IndexerSubsystem indexerSubsystem;
  public static LEDs leds;
  public static Vision vision;
  public static BionicF310 driverGamepad;

  public static ParallelCommandGroup shooterLimelightAssist;

  @Override
  public void robotInit() {
    drivetrainsubsystem = new DriveTrainSubsystem();
    drivetrainsubsystem.setDefaultCommand(new Drive(drivetrainsubsystem));

    vision = new Vision();

    shootersubsystem = new ShooterSubsystem();
    shooterLimelightAssist = new ParallelCommandGroup(new ShooterOI(shootersubsystem), new ShooterLimelightAssist(shootersubsystem, vision));
  
    shootersubsystem.setDefaultCommand(new ShootByDistance(shootersubsystem, vision)); //(new FollowTarget(shootersubsystem, vision));
    //new CANSparkMax(5, MotorType.kBrushless);
    // new CANSparkMax(6, MotorType.kBrushless)

    indexerSubsystem = new IndexerSubsystem();
    indexerSubsystem.setDefaultCommand(new IndexerOI(indexerSubsystem));

    leds = new LEDs();

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
     
    //SmartDashboard.putNumber("X Offset", vision.getXOffset());
  }

  @Override
  public void disabledInit() {
    
  }

  @Override
  public void disabledPeriodic() {
    leds.setAllianceColor();  
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
