package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.shooter.ShooterSubsystem;
 
public class HoodUp extends CommandBase{
    public HoodUp(){
        super();
    }

    public void initialize(){
        Robot.hoodSubsystem.moveHood(10);
    }
}