package frc.team4909.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team4909.robot.Robot;

public class IntakeDeployAndOn extends ParallelCommandGroup{
    public IntakeDeployAndOn(){
        super();
        addCommands(new IntakeDeploy(),
            new IntakeIn());
    }
}