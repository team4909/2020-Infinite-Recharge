package frc.team4909.robot.subsystems.climber.commands;

import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.operator.generic.BionicButton;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class rachetHold extends CommandBase {

    int a;

    public rachetHold(int speed) {
        super();
        addRequirements(Robot.climberSubsystem);
        a = speed;
    }   

    @Override
    public void initialize() {
        Robot.climberSubsystem.setRatchetSpeed(a);
    }

    @Override
    public void end(boolean interrupted){
        Robot.climberSubsystem.setRatchetSpeed(0);
    }
}