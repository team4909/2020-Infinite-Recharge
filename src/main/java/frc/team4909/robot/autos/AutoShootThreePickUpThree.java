package frc.team4909.robot.autos;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.subsystems.drivetrain.commands.Drive;

import frc.team4909.robot.subsystems.indexer.commands.SmartIndexerAndSorterUp;


public class AutoShootThreePickUpThree extends SequentialCommandGroup{
    public AutoShootThreePickUpThree(){
        addCommands(
                // new ShootThree(),
                // new ParallelDeadlineGroup(
                    new Drive(100)
                //     // ,new SmartIndexerAndSorterUp()
                // ),
                ,new WaitCommand(5)
                ,new Drive(-100)
                // new ShootThree()
        
        );
    }
}
