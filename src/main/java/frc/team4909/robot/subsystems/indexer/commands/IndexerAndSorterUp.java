package frc.team4909.robot.subsystems.indexer.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.intake.commands.IntakeDeploy;

public class IndexerAndSorterUp extends ParallelCommandGroup {
    public IndexerAndSorterUp() {
        super();
        addCommands(new SorterOn(Robot.sorterSubsystem),
        new IndexerUp(Robot.indexerSubsystem),
        new IntakeDeploy());
    }
}