package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.shooter.ShooterSubsystem;

public class SetHoodPosition extends CommandBase{

    int pos;

    public SetHoodPosition(ShooterSubsystem subsystem, int position){
        super();
        addRequirements(subsystem);
        pos = position;
    }

    public void initialize(){
        Robot.hoodSubsystem.setHoodPosition(100);
    }
}