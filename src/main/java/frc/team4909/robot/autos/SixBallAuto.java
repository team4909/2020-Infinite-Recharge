package frc.team4909.robot.autos;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team4909.robot.subsystems.shooter.commands.SetShooterVelocity;
import frc.team4909.robot.subsystems.shooter.commands.ShootBalls;

public class SixBallAuto extends SequentialCommandGroup{
public SixBallAuto() {
    addCommands(
            new SetShooterVelocity(10000)
        
        );
}
}
