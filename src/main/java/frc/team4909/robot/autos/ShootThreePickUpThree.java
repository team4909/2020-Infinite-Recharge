package frc.team4909.robot.autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.drivetrain.SetDriveSpeed;
import frc.team4909.robot.subsystems.indexer.commands.SmartIndexerAndSorterUp;
import frc.team4909.robot.subsystems.shooter.commands.FollowTarget;
import frc.team4909.robot.subsystems.shooter.commands.SetHoodInit;
import frc.team4909.robot.subsystems.shooter.commands.ShootBalls;

public class ShootThreePickUpThree extends SequentialCommandGroup{
    public ShootThreePickUpThree(){
        addCommands(
(
                new FollowTarget(Robot.turretSubsystem, Robot.vision).withTimeout(1)),
                new SetHoodInit(),
                // new SetShooterSpeed(0.9),
                // new IndexerAndSorterUp().withTimeout(2),
                //new ShootByDistance(),
                new ShootBalls(3500).withTimeout(5),
                new ParallelCommandGroup(new SetDriveSpeed(-0.9), new SmartIndexerAndSorterUp()).withTimeout(5),
                new ShootBalls(4000).withTimeout(5)
        
        );
    }
}
