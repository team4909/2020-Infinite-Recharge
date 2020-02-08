package frc.team4909.robot.subsystems.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class IndexerUp extends CommandBase{
    
    public IndexerUp(IndexerSubsystem subsystem){
        super();
        addRequirements(subsystem);
    }

    public void execute(){
        Robot.indexerSubsystem.setSpeed(1);
    }

    public void end(){
        Robot.indexerSubsystem.setSpeed(0);
    }
}