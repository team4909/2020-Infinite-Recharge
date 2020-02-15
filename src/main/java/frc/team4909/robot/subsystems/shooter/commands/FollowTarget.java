package frc.team4909.robot.subsystems.shooter.commands;

import java.text.DecimalFormat;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.Vision;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.subsystems.shooter.ShooterSubsystem;

public class FollowTarget extends CommandBase {

    private final double kP = 0.022;
    private final double kD = 0.0005;
    private double lastError = Robot.vision.getXOffset();
    private double speedTurret;
    private double speedShooter;
    private double offset;
    DecimalFormat twodec = new DecimalFormat("#.00");

    public FollowTarget(ShooterSubsystem subsystem, final Vision vsubsystem) {
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
        offset = -Robot.vision.getXOffset();
        filterOffset(offset, lastError);
        speedTurret = (offset * kP + Math.abs(offset - lastError) * kD);
        Robot.vision.updateVisionDashboard();   
        //Robot.shootersubsystem.setHoodPosition(10000);
        //System.out.println(Robot.shootersubsystem.hoodControl.getSelectedSensorPosition());
        Robot.shootersubsystem.setTurnSpeed(speedTurret);
        //System.out.println("Aiming" + speedTurret);
        //System.out.println("" + kP + " " + twodec.format(speedTurret) + " " + twodec.format(offset));
        lastError = Robot.vision.getXOffset();
    }
    @Override
    public void end(boolean interupted){
        Robot.shootersubsystem.setTurnSpeed(0);
        Robot.vision.setLights(0);
    }
}