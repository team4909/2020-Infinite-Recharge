package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.team4909.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimberExtend extends CommandBase {

    

    public ClimberExtend() {
        super();
        addRequirements(Robot.climberSubsystem);
    }


    @Override
    public void initialize() {
        
    }

   

    @Override
    public void execute() {
        Robot.climberSubsystem.setSpeed(0.2);
    }

    @Override
    public void end(boolean interupted){
        Robot.climberSubsystem.setSpeed(0);
    }
}