package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
 
public class HoodUp extends CommandBase{
    public HoodUp(){
        super();
    }

    public void execute(){
        Robot.hoodSubsystem.moveHood(1);
    }
}