package frc.team4909.robot.autos.geometricpaths;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.subsystems.drivetrain.commands.DriveForward;
import frc.team4909.robot.subsystems.drivetrain.commands.TurnRobot;


public class ClockwiseLine extends SequentialCommandGroup {

    /*
     * 1. Turn 14 2. Move 30" 3. Turn 45 4. Move 84.8" 5. Turn -90 6. Move 84.8" 7.
     * Move 30"
     */

    private final double turnAngle = 180;
    private final double distance = 150;

    public ClockwiseLine() {
        super();

        addCommands(
            new DriveForward(distance),
            new TurnRobot(turnAngle),
            new DriveForward(distance),
            new TurnRobot(turnAngle)
        );
    }
    
}