package frc.team4909.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class IntakeIn extends CommandBase{
    public IntakeIn(){
        addRequirements();
    }

    @Override
    public void execute() {

        Robot.intakeSubsystem.setSpeed(1);
    }

    @Override
    public void end(boolean interrupted) {
        Robot.intakeSubsystem.setSpeed(0);
    }
}