package frc.team4909.robot.subsystems.climber.commands;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.subsystems.climber.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimberRetract extends CommandBase {

    public ClimberRetract() {
        super();
        addRequirements(Robot.climberSubsystem);
    }

    @Override
    public void initialize() {
        Robot.climberSubsystem.setClimberSpeed(1);
        Robot.leds.setRainbow();

    }

    @Override
    public void end(boolean interupted){
        // Robot.climberSubsystem.setRatchetAngle(-5);
        Robot.climberSubsystem.setClimberSpeed(0);
    }
}