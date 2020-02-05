package frc.team4909.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class SetShooterVelocity extends CommandBase {

    double velocity;

    public SetShooterVelocity(ShooterSubsystem subsystem, double v) {
        super();
        addRequirements(subsystem);
        velocity = v;
    }

    public void initialize(){
        Robot.shootersubsystem.setVelocity(velocity);
    }

    // public boolean isFinished(){
    //     if((Robot.shootersubsystem.encoder.getVelocity()-velocity) < 100){
    //         return true;
    //     }
    //     return false;
    // }
}