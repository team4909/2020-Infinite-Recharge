package frc.team4909.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;

public class Drive extends CommandBase{

    private final double kP = 0.025;
    private final double kD = 0.0015;
    private double lastError;
    private double output;

    public Drive(DriveTrainSubsystem subsystem){
        addRequirements(subsystem);
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        if(Robot.driverGamepad.getRawButton(6) && Robot.pixy.getNumBlocks() != 0){
            output = (Robot.pixy.getX()-Robot.pixy.getWidth()/2) * kP + ((Robot.pixy.getX()-Robot.pixy.getWidth()/2) - lastError) * kD;
            Robot.drivetrainsubsystem.arcadeDrive(
                -Robot.driverGamepad.getThresholdAxis(BionicF310.LY), output);
          lastError = Robot.pixy.getX()-Robot.pixy.getWidth()/2;
          System.out.print(output);
          System.out.print("   |||   ");
          System.out.println(lastError);
        }else{
            System.out.print("test2");
            Robot.drivetrainsubsystem.arcadeDrive(
                -Robot.driverGamepad.getThresholdAxis(BionicF310.LY),
                Robot.driverGamepad.getThresholdAxis(BionicF310.RX)
            );
        }
    }
}