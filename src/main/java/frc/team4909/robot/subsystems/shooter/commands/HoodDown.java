package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.shooter.ShooterSubsystem;
 
public class HoodDown extends CommandBase{
    public HoodDown(){
        super();
    }

    public void execute(){
        Robot.hoodSubsystem.moveHood(-1);
    }
}