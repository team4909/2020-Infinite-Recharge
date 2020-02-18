package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.shooter.ShooterSubsystem;

public class SetShooterVelocity extends CommandBase {

    double velocity;

    public SetShooterVelocity(ShooterSubsystem subsystem, double v) {
        super();
        addRequirements(subsystem);
        velocity = v;
    }

    public void initialize(){
        Robot.shootersubsystem.setVelocity(velocity);
        System.out.println("init shooter velocity");
    }

    @Override
    public void end(boolean interrupted){
        System.out.println("shooter end" + interrupted);
        Robot.shootersubsystem.setSpeed(0);
    }

    

    // public boolean isFinished(){
    //     if((Robot.shootersubsystem.encoder.getVelocity()-velocity) < 100){
    //         return true;
    //     }
    //     return false;
    // }
}