package frc.team4909.robot.subsystems.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class IndexerOI extends CommandBase{
    public IndexerOI(IndexerSubsystem subsystem){
        addRequirements(subsystem);
    }

    public void execute(){
        if(Robot.driverGamepad.getRawButton(2)){
            Robot.indexerSubsystem.setSpeed(1);
        }else{Robot.indexerSubsystem.setSpeed(0);}
    }
}