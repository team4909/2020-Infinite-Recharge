package frc.team4909.robot.autos.galacticsearch;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.subsystems.drivetrain.commands.DriveForward;
import frc.team4909.robot.subsystems.drivetrain.commands.TurnRobot;

public class A_Red extends SequentialCommandGroup {

    /*
        1. Move 60"
        2. Turn -26.6
        3. Move 67.1"
        4. Turn 81.8
        5. Move 94.9"
        6. Turn -81.8
        7. Move 155"
    */

    public A_Red(){
        super();
        addCommands(//negatived angles for testing
            new DriveForward(60),
            new TurnRobot(26.6), //right is positive, I believe
            new DriveForward(67.1),
            new TurnRobot(-108.4),
            new DriveForward(94.9),
            new TurnRobot(81.8),
            new DriveForward(155)    
        );
    }

}