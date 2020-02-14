package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class TurretSpeed extends CommandBase{

    double speed;

    public TurretSpeed(double s){
        speed = s;
    }

    public void initialize(){
        Robot.shootersubsystem.setTurnSpeed(speed);
    }

    @Override
    public void end(boolean interrupted) {
        Robot.shootersubsystem.setTurnSpeed(0);
    }
}