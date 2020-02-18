package frc.team4909.robot.subsystems.climber.commands;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.team4909.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.subsystems.climber.Climber;

public class ClimberRetract extends CommandBase {

    public ClimberRetract(Climber climberSubsystem) {
        // @TODO add in reqs
        addRequirements(climberSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Robot.climberSubsystem.set(-0.2);
    }

    @Override
    public void end(boolean interupted){
        Robot.climberSubsystem.set(0);
    }
}