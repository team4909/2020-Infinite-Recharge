package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class HookOut extends CommandBase{

    public HookOut() {
        super();
        addRequirements(Robot.climberSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Robot.climberSubsystem.hookMotor.set(0.2);
    }

    @Override
    public void end(boolean interupted){
        Robot.climberSubsystem.hookMotor.set(0);
    }
}
}