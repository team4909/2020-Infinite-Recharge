package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class ZeroHoodInit extends CommandBase{
    
    double hoodZeroCurrent = -20;
    boolean atZero = false;

    public ZeroHoodInit(){
        
    }

    @Override
    public void execute() {
        Robot.hoodSubsystem.setSpeed(-1.0);
        if(Robot.hoodSubsystem.getHoodCurrent() > hoodZeroCurrent){
            Robot.hoodSubsystem.setHoodPosition(0);
            atZero = true;
        }
    }

    @Override
    public boolean isFinished() {
        return atZero;
    }
}

