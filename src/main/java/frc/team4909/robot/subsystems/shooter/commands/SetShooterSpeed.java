package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class SetShooterSpeed extends CommandBase{
    double speed;
    
    public SetShooterSpeed(double s){
        super();
        addRequirements(Robot.shootersubsystem);
        speed = s;
    }

    public void initialize(){
        Robot.shootersubsystem.setSpeed(speed);
    }
}