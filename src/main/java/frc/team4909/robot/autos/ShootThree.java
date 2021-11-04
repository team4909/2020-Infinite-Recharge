package frc.team4909.robot.autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.drivetrain.SetDriveSpeed;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterUp;
import frc.team4909.robot.subsystems.shooter.commands.FollowTarget;
import frc.team4909.robot.subsystems.shooter.commands.ShootBalls;
import frc.team4909.robot.subsystems.shooter.commands.ShootByDistance;

public class ShootThree extends SequentialCommandGroup{
    public ShootThree(){
        addCommands(
            new FollowTarget(Robot.turretSubsystem, Robot.vision).withTimeout(0.7), //horizontal alignment
            new ShootByDistance().withTimeout(0.5), // hood alignment
            new ShootBalls(10000).withTimeout(2), // spin up the flywheel
            new ParallelCommandGroup(new ShootBalls(10_000), new IndexerAndSorterUp()).withTimeout(3) // keep flywheel going, run indexer seconds
        );
    }
}
