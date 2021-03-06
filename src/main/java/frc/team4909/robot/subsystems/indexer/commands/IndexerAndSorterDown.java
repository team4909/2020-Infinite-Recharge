package frc.team4909.robot.subsystems.indexer.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team4909.robot.Robot;

public class IndexerAndSorterDown extends ParallelCommandGroup {
    public IndexerAndSorterDown() {
        super();
        addCommands(new SorterOut(Robot.sorterSubsystem),
        new IndexerOut(Robot.indexerSubsystem));
    }

}