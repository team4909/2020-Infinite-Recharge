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
import frc.team4909.robot.subsystems.shooter.commands.ShootBalls;


public class AutoShootThreePickUpThree extends SequentialCommandGroup{
    public AutoShootThreePickUpThree(){
        addCommands(
                new ShootThree(),
                new ParallelDeadlineGroup(
                    new Drive(195),
                    new SmartIndexerAndSorterUp()
                ),
                //new SmartIndexerAndSorterUp().withTimeout(1);
                new ParallelDeadlineGroup(new Drive(-135), new IndexerAndSorterUp(), new IntakeDeploy()),
                new Drive(21),
                new FollowTarget(Robot.turretSubsystem, Robot.vision).withTimeout(1.5), //horizontal alignment
                new SetHoodFar(), // hood alignment
                new ShootBalls(10000).withTimeout(2), // spin up the flywheel
                new ParallelCommandGroup(new ShootBalls(10_000), new IndexerAndSorterUp()).withTimeout(3) // keep flywheel going, run indexer seconds
        
        );
    }
}
