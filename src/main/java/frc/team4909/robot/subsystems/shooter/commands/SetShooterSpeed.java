package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class SetShooterSpeed extends CommandBase{
    int speed;
    
    public SetShooterSpeed(int position){
        super();
        addRequirements(Robot.shootersubsystem);
        speed = position;
    }

    public void initialize(){
        Robot.shootersubsystem.setSpeed(speed);;
    }
}