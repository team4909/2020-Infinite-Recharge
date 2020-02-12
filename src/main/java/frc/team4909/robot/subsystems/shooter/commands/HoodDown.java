package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.shooter.ShooterSubsystem;
 
public class HoodDown extends CommandBase{
    public HoodDown(ShooterSubsystem subsystem){
        super();
        addRequirements(subsystem);
    }

    public void initialize(){
        Robot.shootersubsystem.hoodControl.set(0.1);
    }

    @Override
    public void end(boolean interupted){
        Robot.shootersubsystem.hoodControl.set(0);
    }
}