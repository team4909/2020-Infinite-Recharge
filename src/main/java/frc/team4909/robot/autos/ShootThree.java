package frc.team4909.robot.autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.drivetrain.SetDriveSpeed;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterUp;
import frc.team4909.robot.subsystems.shooter.commands.FollowTarget;
import frc.team4909.robot.subsystems.shooter.commands.SetHoodFar;
import frc.team4909.robot.subsystems.shooter.commands.SetHoodInit;
import frc.team4909.robot.subsystems.shooter.commands.SetShooterSpeed;
import frc.team4909.robot.subsystems.shooter.commands.ShootBalls;

public class ShootThree extends SequentialCommandGroup{
    public ShootThree(){
        addCommands(
            new ParallelCommandGroup(new FollowTarget(Robot.turretSubsystem, Robot.vision), new SetShooterSpeed(0.5)).withTimeout(1),
            new SetHoodInit(),
            // new SetShooterSpeed(0.9),
            ///new IndexerAndSorterUp().withTimeout(2),
            //new ShootByDistance(),
            new ShootBalls(3225).withTimeout(8.25),
            new SetDriveSpeed(-0.9).withTimeout(0.5)
        );
    }
}
