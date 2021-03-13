package frc.team4909.robot.autos.galacticsearch;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.subsystems.drivetrain.commands.DriveForward;
import frc.team4909.robot.subsystems.drivetrain.commands.TurnRobot;

public class A_Blue extends SequentialCommandGroup{

    /*
        1. Turn -31.0
        2. Move 42.4"
        3. Turn 116.6
        4. Move 94.9"
        5. Turn 98.1
        6. Move 67.1"
        7. Move 65"
    */

    public A_Blue(){    
        super();
        addCommands(
            new TurnRobot(31.),
            new DriveForward(42.4),
            new TurnRobot(-107.6),
            new DriveForward(100),
            new TurnRobot(98.1),
            new DriveForward(67.1),
            new TurnRobot(-30),
            new DriveForward(132.5) 
        );
    }    
}    