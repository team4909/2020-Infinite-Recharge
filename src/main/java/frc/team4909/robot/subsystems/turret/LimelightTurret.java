package frc.team4909.robot.subsystems.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.Vision;
import frc.team4909.robot.operator.controllers.BionicF310;

public class LimelightTurret extends CommandBase{
    
    private final double kP = 0.2;
    private final double kD = 0.1;
    private double lastError;

    public LimelightTurret(final TurretSubsystem subsystem, final Vision vsubsystem){
        super();
        addRequirements(subsystem);
    }
    
    @Override
    public void initialize() {
        
        Robot.vision.setLights(3);
    }

    @Override
    public void execute(){
        Robot.vision.updateVisionDashboard(); 
        lastError = Robot.vision.getXOffset();
        if(Robot.driverGamepad.getRawButton(5))
        {
            System.out.println(lastError);
            Robot.turretsubsystem.setSpeed(Robot.vision.getXOffset() * kP + (Robot.vision.getXOffset() - lastError) * kD);
        }
        Robot.turretsubsystem.setSpeed(Robot.driverGamepad.getThresholdAxis(BionicF310.RX)*0.2);
    }
}