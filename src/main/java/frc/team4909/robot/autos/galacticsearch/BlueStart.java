package frc.team4909.robot.autos.galacticsearch;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.subsystems.drivetrain.commands.DriveForward;
import frc.team4909.robot.subsystems.drivetrain.commands.TurnRobot;

public class BlueStart extends SequentialCommandGroup{

    /*
        1. Turn -14
        2. Move 108.2"
    */

    public BlueStart(){
        super();
        addCommands(
            new TurnRobot(-14),
            new DriveForward(108.2)
        );

    }
}
