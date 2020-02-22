package frc.team4909.robot.subsystems.climber.commands;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.climber.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbUp extends SequentialCommandGroup{
    public ClimbUp() {
        super();
        addCommands(new ClimberExtend(10), new rachetHold(1).withTimeout(0.5),
        new HookIn().withTimeout(3));

        

    }
}