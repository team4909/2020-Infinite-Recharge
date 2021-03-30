package frc.team4909.robot.autos.galacticsearch;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.subsystems.drivetrain.commands.DriveForward;
import frc.team4909.robot.subsystems.drivetrain.commands.MotionMagicForward;
import frc.team4909.robot.subsystems.drivetrain.commands.TurnRobot;

public class B_Red extends SequentialCommandGroup {

    /*
    1. Turn 26.6
    2. Move 61"
    3. Turn -71.6
    4. Move 84.8"
    5. Turn 90
    6. Move 84.8"
    7. Turn -45
    8. Move 130"
    */

    public B_Red() {
        super();
        addCommands(
            new TurnRobot(-26.6),
            new MotionMagicForward(61),
            new TurnRobot(71.6),
            new MotionMagicForward(84.8),
            new TurnRobot(-95),
            new MotionMagicForward(84.8),
            new TurnRobot(40),
            new MotionMagicForward(120)
            
        );
    }
    
}
