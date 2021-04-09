package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class ZeroHoodInit extends CommandBase{
    
    double hoodZeroCurrent = -20;
    boolean atZero = false;

    public ZeroHoodInit(){
        
    }

    @Override
    public void initialize() {
        Robot.hoodSubsystem.zeroing = true;
    }

    @Override
    public void execute() {
        Robot.hoodSubsystem.hoodControl.set(-1.0);
        if(Robot.hoodSubsystem.getHoodCurrent() > hoodZeroCurrent){
            Robot.hoodSubsystem.setHoodPosition(0);
            atZero = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.hoodSubsystem.zeroing = false;
    }

    @Override
    public boolean isFinished() {
        return atZero;
    }
}

