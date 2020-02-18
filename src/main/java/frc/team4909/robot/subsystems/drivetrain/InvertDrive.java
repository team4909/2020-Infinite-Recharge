package frc.team4909.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team4909.robot.Robot;

public class InvertDrive extends InstantCommand{
    

    public InvertDrive(){
        addRequirements(Robot.drivetrainsubsystem);
    }

    @Override
    public void initialize(){
        Robot.drivetrainsubsystem.invertDriveDirection();
        Robot.cameraSubsystem.toggleCamera();
    }
}
