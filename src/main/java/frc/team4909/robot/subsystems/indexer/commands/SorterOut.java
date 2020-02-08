package frc.team4909.robot.subsystems.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team4909.robot.Robot;

public class SorterOut extends CommandBase{
    public SorterOut(SorterSubsystem subsystem){
        super();
        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        Robot.sorterSubsystem.sorterOn(-1);
    }


    @Override
    public void end(boolean interupted){
        Robot.sorterSubsystem.sorterOn(0);
    }
}