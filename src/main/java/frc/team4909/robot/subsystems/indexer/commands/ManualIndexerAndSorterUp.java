package frc.team4909.robot.subsystems.indexer.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team4909.robot.Robot;

public class ManualIndexerAndSorterUp extends ParallelCommandGroup {
    public ManualIndexerAndSorterUp() {
        super();
        addCommands(new SorterOn(Robot.sorterSubsystem),
        new IndexerUp(Robot.indexerSubsystem));
    }
}