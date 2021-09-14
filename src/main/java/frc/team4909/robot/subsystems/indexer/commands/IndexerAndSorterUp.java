package frc.team4909.robot.subsystems.indexer.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.intake.commands.IntakeDeploy;
import frc.team4909.robot.subsystems.intake.commands.IntakeIn;

public class IndexerAndSorterUp extends ParallelCommandGroup {
    public IndexerAndSorterUp() {
        super();
        System.out.println("sorter indexer up");
        addCommands(new SorterOn(Robot.sorterSubsystem),
        new IndexerUp(Robot.indexerSubsystem));
        //new IntakeDeploy());
    }
}