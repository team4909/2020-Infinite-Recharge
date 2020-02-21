package frc.team4909.robot.subsystems.climber.commands;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.climber.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimberRetract extends CommandBase {

    public ClimberRetract() {
        super();
        addRequirements(Robot.climberSubsystem);
    }

    @Override
    public void execute() {
        Robot.climberSubsystem.setSpeed(-0.2);
    }

    @Override
    public void end(boolean interupted){
        Robot.climberSubsystem.setSpeed(0);
    }
}