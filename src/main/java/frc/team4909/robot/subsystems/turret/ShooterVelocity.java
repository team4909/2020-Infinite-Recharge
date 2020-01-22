package frc.team4909.robot.subsystems.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.ShooterSubsystem;

public class ShooterVelocity extends CommandBase{
    
    double velocity;

    public ShooterVelocity(ShooterSubsystem subsystem, double v){
        super();
        addRequirements(subsystem);
        velocity = v;
    }

    public void initialize(){
        Robot.shootersubsystem.setSpeed(velocity);
    }

    // public boolean isFinished(){
    //     if((Robot.shootersubsystem.encoder.getVelocity()-velocity) < 100){
    //         return true;
    //     }
    //     return false;
    // }
}