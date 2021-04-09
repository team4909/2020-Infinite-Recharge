package frc.team4909.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class TogglePreciseMode extends CommandBase {
    public TogglePreciseMode() {
        super();
        addRequirements(Robot.drivetrainsubsystem);
    }

    public void initialize() {
        Robot.drivetrainsubsystem.togglePreciseMode();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}