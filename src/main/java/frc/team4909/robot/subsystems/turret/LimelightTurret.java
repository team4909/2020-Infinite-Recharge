package frc.team4909.robot.subsystems.turret;
import java.text.DecimalFormat;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.Vision;
import frc.team4909.robot.operator.controllers.BionicF310;

public class LimelightTurret extends CommandBase{
    
    private final double kP = 0.025;
    private final double kD = 0.0005;
    private double lastError;
    private double speed;
    DecimalFormat twodec = new DecimalFormat("#.00");

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
        speed = (Robot.vision.getXOffset() * kP + (Robot.vision.getXOffset() - lastError) * kD);
        Robot.vision.updateVisionDashboard(); 
        if(Robot.driverGamepad.getRawButton(5))
        {
            Robot.turretsubsystem.setSpeed(speed);
        }
        else
        {
            Robot.turretsubsystem.setSpeed(Robot.driverGamepad.getThresholdAxis(BionicF310.RX)*0.2);
        }
        System.out.println("" + kP + " " + twodec.format(speed) + " " + Robot.vision.getXOffset());
        lastError = Robot.vision.getXOffset();
    }
}