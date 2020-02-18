package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.climber.Climber;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HookHold extends CommandBase {

    double a;

    public HookHold(double angle) {
        addRequirements(Robot.climberSubsystem);
        a = angle;
    }

    @Override
    public void initialize() {
        Robot.climberSubsystem.setHookAngle(a);
    }

    @Override
    public void end(boolean interupted){
        Robot.climberSubsystem.setHookAngle(0);
    }
}