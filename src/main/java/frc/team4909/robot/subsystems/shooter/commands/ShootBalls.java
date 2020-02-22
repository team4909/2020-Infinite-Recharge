package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterUp;
import frc.team4909.robot.subsystems.indexer.commands.MoveIndexerAtSpeed;
import frc.team4909.robot.subsystems.intake.commands.IntakeIn;

public class ShootBalls extends ParallelCommandGroup{

    public ShootBalls(int speed){
        super();
        addCommands(new SetShooterVelocity(Robot.shootersubsystem, speed),
            new IndexerAndSorterUp() //,
            //new IntakeIn()
        );
    }

}
