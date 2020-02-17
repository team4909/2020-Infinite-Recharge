package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.climber.Climber;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HookHold extends CommandBase {

    public HookHold(Climber subsystem) {
        // @TODO add in reqs
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Robot.climberSubsystem.setHookSpeed(10);
    }

    @Override
    public void end(boolean interupted){
        Robot.climberSubsystem.hookset.set(0);
    }
}