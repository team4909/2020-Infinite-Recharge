package frc.team4909.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;

public class Drive extends CommandBase{
    

    public Drive(DriveTrainSubsystem subsystem){
        addRequirements(subsystem);
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        Robot.drivetrainsubsystem.arcadeDrive(
            Robot.driverGamepad.getThresholdAxis(BionicF310.LY),
            Robot.driverGamepad.getThresholdAxis(BionicF310.RX)
        );
    }
    
    
}
