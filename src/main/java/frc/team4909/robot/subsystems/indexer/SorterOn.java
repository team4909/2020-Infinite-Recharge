package frc.team4909.robot.subsystems.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team4909.robot.Robot;

public class SorterOn extends CommandBase{
    public SorterOn(IndexerSubsystem subsystem){
        super();
        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        Robot.indexerSubsystem.sorterOn(1);
    }


    @Override
    public void end(boolean interupted){
        Robot.indexerSubsystem.sorterOn(0);
    }
}