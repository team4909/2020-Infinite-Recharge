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
        if(Robot.climberSubsystem.getClimbCurrent() < 25){
            Robot.leds.setRainbow();
            Robot.climberSubsystem.setRatchetAngle(1);
            Robot.climberSubsystem.setClimberSpeed(0.25);
        }else{
            Robot.leds.setDefault();
            Robot.climberSubsystem.setClimberSpeed(1);
        }
    }

    @Override
    public void end(boolean interupted){
        Robot.climberSubsystem.setClimberSpeed(0);
    }
}