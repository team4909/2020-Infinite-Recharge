package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team4909.robot.subsystems.indexer.commands.MoveIndexerAtSpeed;

public class ShootBalls extends ParallelCommandGroup{

    public ShootBalls(int speed){
        super();
        addCommands(new SetShooterVelocity(speed),
            new MoveIndexerAtSpeed() //,
            //new IntakeIn()
        );
    }

}
