package frc.team4909.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.subsystems.drivetrain.Drive;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;
import frc.team4909.robot.subsystems.turret.LimelightTurret;
import frc.team4909.robot.subsystems.turret.TurretSubsystem;
import edu.wpi.first.wpilibj.I2C;
import frc.team4909.robot.subsystems.Ultrasonic.ultrasonic;
public class Robot extends TimedRobot {
  public static DriveTrainSubsystem drivetrainsubsystem;
  public static TurretSubsystem turretsubsystem;
  public static Vision vision;
  public static BionicF310 driverGamepad;
  public static I2C i2c;
  public static ultrasonic ultra;

  @Override
  public void robotInit() {
    drivetrainsubsystem = new DriveTrainSubsystem();
    drivetrainsubsystem.setDefaultCommand(new Drive(drivetrainsubsystem));
   // i2c=new I2C(I2C.Port.kOnBoard, 0x51);
    vision = new Vision();

    turretsubsystem = new TurretSubsystem();
    turretsubsystem.setDefaultCommand(new LimelightTurret(turretsubsystem, vision));

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
    if (i2c.read()){
        SmartDashboard.putNumber("distance1", ultra.read());
    } 
    SmartDashboard.putNumber("X Offset", vision.getXOffset());
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
