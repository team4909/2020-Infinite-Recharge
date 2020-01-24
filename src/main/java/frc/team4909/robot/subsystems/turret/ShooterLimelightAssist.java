package frc.team4909.robot.subsystems.turret;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.Vision;
import frc.team4909.robot.subsystems.ShooterSubsystem;

public class ShooterLimelightAssist extends SequentialCommandGroup{
    
    public ShooterLimelightAssist(ShooterSubsystem shooter, Vision limelight){
        new LimelightShooter(shooter, limelight);
        new LimelightTurret(shooter, limelight);
    }
}