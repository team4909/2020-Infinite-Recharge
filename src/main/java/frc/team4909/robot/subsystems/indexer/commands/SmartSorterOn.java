package frc.team4909.robot.subsystems.indexer.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.indexer.SorterSubsystem;

public class SmartSorterOn extends CommandBase{
    public SmartSorterOn(SorterSubsystem subsystem){
        super();
        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        if(!(Robot.indexerSubsystem.hasBallUpper() || Robot.indexerSubsystem.hasBallLower())){
            Robot.sorterSubsystem.sorterOn(-0.25);
        }
        else{
            Robot.sorterSubsystem.sorterOn(-0.05); //For GS, so that a pileup of balls is not reqried to run the sorter
            //FOR ALL NON GS REASONS IT SHOULD BE 0 ^
        }
    }


    @Override
    public void end(boolean interupted){
        Robot.sorterSubsystem.sorterOn(0);
    }
}