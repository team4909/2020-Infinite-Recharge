package frc.team4909.robot.subsystems.climber.commands;

import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.operator.generic.BionicButton;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class rachetHold extends CommandBase {

    int a;

    public rachetHold(int angle) {
        super();
        addRequirements(Robot.climberSubsystem);
        a = angle;
    }   

    @Override
    public void initialize() {
        Robot.climberSubsystem.setrachetAngle(a);
    }

    @Override
    public void end(boolean interrupted){
        Robot.climberSubsystem.setrachetAngle(0);
        System.out.println("rachetHold inturrpted");
    }
}