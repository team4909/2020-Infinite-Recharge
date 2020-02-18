package frc.team4909.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class IntakeDeploy extends InstantCommand{
    public IntakeDeploy(){
        addRequirements(Robot.intakeSubsystem);
    }

    @Override
    public void initialize() {
        Robot.intakeSubsystem.deployIntake(RobotConstants.deploySetpoint);
    }
}