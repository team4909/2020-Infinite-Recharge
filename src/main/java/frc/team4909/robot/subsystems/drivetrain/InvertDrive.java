package frc.team4909.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class InvertDrive extends CommandBase{
    

    public InvertDrive(){
        addRequirements(Robot.drivetrainsubsystem);
    }

    @Override
    public void initialize(){
        Robot.drivetrainsubsystem.invertDriveDirection();;
    }
}
