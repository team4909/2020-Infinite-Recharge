package frc.team4909.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class IntakeDeploy extends CommandBase{
    public IntakeDeploy(){
        addRequirements(Robot.intakeSubsystem);
    }

    @Override
    public void initialize() {
        Robot.intakeSubsystem.intakeDeployed = true;
        Robot.intakeSubsystem.setSpeed(0.9);
        Robot.intakeSubsystem.deployIntake(RobotConstants.deploySetpoint);
    }

    @Override
    public void end(boolean interrupted) {
        Robot.intakeSubsystem.intakeDeployed = false;
        Robot.intakeSubsystem.deployIntake(0);
        Robot.intakeSubsystem.setSpeed(0);
    }
}