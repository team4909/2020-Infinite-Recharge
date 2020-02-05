package frc.team4909.robot.subsystems.shooter;

import java.text.DecimalFormat;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.Vision;
import frc.team4909.robot.operator.controllers.BionicF310;

public class FollowTarget extends CommandBase {

    private final double kP = 0.018;
    private final double kD = 0.005;
    private double lastError = Robot.vision.getXOffset();
    private double speed;
    private double offset;
    DecimalFormat twodec = new DecimalFormat("#.00");

    public FollowTarget(final ShooterSubsystem subsystem, final Vision vsubsystem) {
        super();
        addRequirements(subsystem);
    }
    
    @Override
    public void initialize() {
        
        Robot.vision.setLights(3);
    }

    public double filterOffset(double off, double last){
        if (Math.abs(off-last) >= 6.0){
            off = (3*last + off)/4;
        }
        return off; 
    }

    @Override
    public void execute(){
        offset = Robot.vision.getXOffset();
        filterOffset(offset, lastError);
        speed = (offset * kP + (offset - lastError) * kD);
        Robot.vision.updateVisionDashboard(); 
        if(Robot.driverGamepad.getRawButton(5))
        {
            Robot.shootersubsystem.setSpeed(speed);
        }
        else
        {
            Robot.shootersubsystem.setSpeed(Robot.driverGamepad.getThresholdAxis(BionicF310.RX)*0.2);
        }
        System.out.println("" + kP + " " + twodec.format(speed) + " " + twodec.format(offset));
        lastError = Robot.vision.getXOffset();
    }
}