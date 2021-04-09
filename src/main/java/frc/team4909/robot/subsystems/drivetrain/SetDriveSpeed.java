package frc.team4909.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class SetDriveSpeed extends CommandBase{

    double speed;

    public SetDriveSpeed(double speed){
        addRequirements(Robot.drivetrainsubsystem);
        this.speed = speed;
    }

    @Override
    public void execute() {
        Robot.drivetrainsubsystem.arcadeDrive(speed, 0);
    }

    @Override
    public void end(boolean interrupted) {
        Robot.drivetrainsubsystem.arcadeDrive(0, 0);
    }

}