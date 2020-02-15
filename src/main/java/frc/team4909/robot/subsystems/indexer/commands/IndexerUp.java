package frc.team4909.robot.subsystems.indexer.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.indexer.IndexerSubsystem;

public class IndexerUp extends CommandBase{
    
    public IndexerUp(IndexerSubsystem subsystem){
        super();
        addRequirements(subsystem);
    }

    public void execute(){
        Robot.indexerSubsystem.setSpeed(1);
    }
    @Override
    public void end(boolean interupted){
        Robot.indexerSubsystem.setSpeed(0);
    }
}