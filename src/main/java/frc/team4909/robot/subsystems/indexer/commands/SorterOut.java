package frc.team4909.robot.subsystems.indexer.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.indexer.SorterSubsystem;

public class SorterOut extends CommandBase{
    public SorterOut(SorterSubsystem subsystem){
        super();
        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        Robot.sorterSubsystem.sorterOn(-0.5);
    }


    @Override
    public void end(boolean interupted){
        Robot.sorterSubsystem.sorterOn(0);
    }
}