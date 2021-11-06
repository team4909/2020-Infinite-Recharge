package frc.team4909.robot.autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.drivetrain.commands.Drive;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterUp;
import frc.team4909.robot.subsystems.indexer.commands.SmartIndexerAndSorterUp;
import frc.team4909.robot.subsystems.intake.commands.IntakeDeploy;
import frc.team4909.robot.subsystems.intake.commands.IntakeIn;
import frc.team4909.robot.subsystems.shooter.commands.FollowTarget;
import frc.team4909.robot.subsystems.shooter.commands.MoveHood;
import frc.team4909.robot.subsystems.shooter.commands.SetHoodFar;
import frc.team4909.robot.subsystems.shooter.commands.SetShooterVelocity;
import frc.team4909.robot.subsystems.shooter.commands.ShootBalls;
import frc.team4909.robot.subsystems.shooter.commands.ShootByDistance;



public class AutoShootThreePickUpThree extends SequentialCommandGroup{
    final double ShooterRPM = 5000;
    public AutoShootThreePickUpThree(){
        addCommands(
            new ParallelCommandGroup(
                new FollowTarget(Robot.turretSubsystem, Robot.vision),
                new ShootByDistance(),
                new SetShooterVelocity(ShooterRPM)
            ).withTimeout(1.5),
            
            new SetShooterVelocity(ShooterRPM).withTimeout(2), // spin up the flywheel
            new ParallelCommandGroup(
                new SetShooterVelocity(ShooterRPM), 
                new IndexerAndSorterUp()).withTimeout(3), // keep flywheel going, run indexer seconds

            new ParallelDeadlineGroup(
                new Drive(195),
                new SmartIndexerAndSorterUp(),
                new SetShooterVelocity(ShooterRPM)    
            ),
            //new SmartIndexerAndSorterUp().withTimeout(1);
            new ParallelDeadlineGroup(
                new Drive(-135),
                new SmartIndexerAndSorterUp(),
                new SetShooterVelocity(ShooterRPM)),
            new ParallelDeadlineGroup(
                new Drive(21),
                new SetShooterVelocity(ShooterRPM)),
            new ParallelCommandGroup(
                new FollowTarget(Robot.turretSubsystem, Robot.vision),
                new SetHoodFar(),
                new SetShooterVelocity(ShooterRPM)
            ).withTimeout(0.7), //horizontal alignment
            //new SetShooterVelocity(ShooterRPM).withTimeout(2), // spin up the flywheel
            new ParallelCommandGroup(new SetShooterVelocity(ShooterRPM), new IndexerAndSorterUp()).withTimeout(3) // keep flywheel going, run indexer seconds
    
        );
    }
}
