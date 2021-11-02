package frc.team4909.robot.autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.drivetrain.SetDriveSpeed;
import frc.team4909.robot.subsystems.drivetrain.commands.DriveForward;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterUp;
import frc.team4909.robot.subsystems.indexer.commands.SmartIndexerAndSorterUp;
import frc.team4909.robot.subsystems.intake.commands.IntakeDeploy;
import frc.team4909.robot.subsystems.shooter.commands.FollowTarget;
import frc.team4909.robot.subsystems.shooter.commands.SetHoodInit;
import frc.team4909.robot.subsystems.shooter.commands.ShootBalls;

public class ShootThreePickUpThree extends SequentialCommandGroup{
    public ShootThreePickUpThree(){
        addCommands(
                new ShootThree(),
                new IntakeDeploy(),
                new ParallelCommandGroup(new IntakeDeploy(), new IndexerAndSorterUp(), new DriveForward(200)).withTimeout(4), 
                new ShootThree()
        
        );
    }
}
