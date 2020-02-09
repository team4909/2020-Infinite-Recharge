package frc.team4909.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team4909.robot.Robot;

public class HoodUp extends CommandBase{
    public HoodUp(ShooterSubsystem subsystem){
        super();
        addRequirements(subsystem);
    }

    public void initialize(){
        Robot.shootersubsystem.hoodControl.set(-0.2);
    }

    @Override
    public void end(boolean interupted){
        Robot.shootersubsystem.hoodControl.set(0);
    }

}