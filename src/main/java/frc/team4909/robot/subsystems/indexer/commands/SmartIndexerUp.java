package frc.team4909.robot.subsystems.indexer.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.indexer.IndexerSubsystem;

public class SmartIndexerUp extends CommandBase{

    private final double delaySec = 0;
    private double time = 0;
    private boolean isRunning = false;
    
    public SmartIndexerUp(IndexerSubsystem subsystem){
        super();
        addRequirements(subsystem);
    }

    public void execute()
    {
        if(!Robot.indexerSubsystem.hasBallUpper())
        {
            if(Robot.indexerSubsystem.hasBallLower())
            {
                Robot.indexerSubsystem.setSpeed(-0.6);
                isRunning = true;
                time = Timer.getFPGATimestamp();
            }
            else
            {
                if (isRunning && Timer.getFPGATimestamp() - time > delaySec)
                {
                    Robot.indexerSubsystem.setSpeed(0);
                    isRunning = false;
                }
            }
        }
        else
        {
            Robot.indexerSubsystem.setSpeed(0);
        }
        // Robot.indexerSubsystem.setSmartSpeed(0.45);
    }
    @Override
    public void end(boolean interupted){
        Robot.indexerSubsystem.setSpeed(0);
    }
}