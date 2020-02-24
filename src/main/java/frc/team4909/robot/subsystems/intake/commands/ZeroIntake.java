package frc.team4909.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class ZeroIntake extends CommandBase{
    public ZeroIntake(){
        super();
        addRequirements(Robot.intakeSubsystem);
    }

    @Override
    public void initialize() {
        Robot.intakeSubsystem.zeroDeploy();
    }
}