package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class ZeroTurret extends CommandBase{
    public ZeroTurret(){
        addRequirements(Robot.shootersubsystem);
    }

    @Override
    public void initialize() {
        Robot.shootersubsystem.setTurnSpeed(0.25);
    }

    @Override
    public void end(boolean interrupted) {
        Robot.shootersubsystem.zeroTurret();
        Robot.shootersubsystem.setTurnSpeed(0);
    }

    @Override
    public boolean isFinished() {
    
        return false;
    }
}