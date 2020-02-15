package frc.team4909.robot.subsystems.indexer.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class MoveIndexerAtSpeed extends CommandBase {
    public MoveIndexerAtSpeed(){
        super();
    }

    @Override
    public void execute() {
        if (Robot.shootersubsystem.isAtSpeed) {
            Robot.indexerSubsystem.setSpeed(1);
            Robot.sorterSubsystem.sorterOn(-1);
        } else {
            Robot.indexerSubsystem.setSpeed(0);
            Robot.sorterSubsystem.sorterOn(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.indexerSubsystem.setSpeed(0);
        Robot.sorterSubsystem.sorterOn(0);
    }
}