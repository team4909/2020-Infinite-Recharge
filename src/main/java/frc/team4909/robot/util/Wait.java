package frc.team4909.robot.util;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

//Tells the robot to wait a specified number of SECONDS used in GS to wait between two Sequential Command Commands

public class Wait extends CommandBase{

    double targetTime;
    double startTime;

    public Wait(double duration) {
        startTime = Timer.getFPGATimestamp(); 
        targetTime = startTime + duration;
    }

    @Override
    public void initialize() {
    }

    @Override
    public boolean isFinished() {
        if(Timer.getFPGATimestamp() >= targetTime){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        SmartDashboard.putBoolean("HOLY $HIT FIX YOUR CODE", interrupted); 
    }

    
    
}