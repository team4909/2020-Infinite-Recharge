package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team4909.robot.Robot;

public class FollowAndAim extends ParallelCommandGroup {
    public FollowAndAim() {
        addCommands(
            new ParallelCommandGroup(
                //new FollowTarget(Robot.turretSubsystem, Robot.vision),
                new ShootByDistance()
            )
        );
    }
}