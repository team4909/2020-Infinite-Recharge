package frc.team4909.robot.autos.galacticsearch;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.subsystems.drivetrain.commands.DriveForward;
import frc.team4909.robot.subsystems.drivetrain.commands.TurnRobot;

public class B_Blue extends SequentialCommandGroup {

    /*
     * 1. Turn 14 2. Move 30" 3. Turn 45 4. Move 84.8" 5. Turn -90 6. Move 84.8" 7.
     * Move 30"
     */

    public B_Blue() {
        super();

        addCommands(
            new TurnRobot(-13.04),
            new DriveForward(30),
            new TurnRobot(-45),
            new DriveForward(84.8),
            new TurnRobot(90),
            new DriveForward(84.8),
            new TurnRobot(-45),
            new DriveForward(30)
        );
    }
    
}