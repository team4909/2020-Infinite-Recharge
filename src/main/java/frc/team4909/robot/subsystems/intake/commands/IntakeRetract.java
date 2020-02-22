package frc.team4909.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team4909.robot.Robot;

public class IntakeRetract extends InstantCommand{
    public IntakeRetract(){
        addRequirements(Robot.intakeSubsystem);
    }

    @Override
    public void initialize() {
        Robot.intakeSubsystem.deployIntake(0);
    }
}