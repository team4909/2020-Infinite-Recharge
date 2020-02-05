package frc.team4909.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.Vision;

public class ShooterLimelightAssist extends SequentialCommandGroup{
    
    public ShooterLimelightAssist(ShooterSubsystem shooter, Vision limelight){
        new ShootByDistance(shooter, limelight);
        new FollowTarget(shooter, limelight);
    }
}