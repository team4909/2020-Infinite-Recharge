package frc.team4909.robot.subsystems.shooter.commands;

import java.text.DecimalFormat;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.Vision;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.subsystems.shooter.ShooterSubsystem;
import frc.team4909.robot.subsystems.shooter.TurretSubsystem;

public class FollowTarget extends CommandBase {

    private final double kP = 0.08;
    private final double kD = 0.0008;
    private double lastError = Robot.vision.getXOffset();
    private double speedTurret;
    private double speedShooter;
    private double offset;
    DecimalFormat twodec = new DecimalFormat("#.00");
    public boolean isAligned;

    /**
     * 
     * @param subsystem The subsystem for the Turret
     * @param vsubsystem The subsystem for the vision
     */
    public FollowTarget(TurretSubsystem subsystem, final Vision vsubsystem) {
        super();
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        Robot.vision.setLights(3);

        isAligned = false;
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


        if(!Robot.vision.targetAcquired()){
            //System.out.println("No target seen!");
            SmartDashboard.putBoolean("Target Seen?", false);
            speedTurret = 0;
        } else {  
            SmartDashboard.putBoolean("Target Seen?", true);

        }

        Robot.vision.updateVisionDashboard();

        Robot.turretSubsystem.setTurnSpeed(speedTurret);
        lastError = Robot.vision.getXOffset();

        if (Math.abs(Robot.vision.getXOffset()) <= 5){

            if (Robot.shootersubsystem.isAtSpeed){

                Robot.leds.setGreen();

            } else Robot.leds.setBlack();

            Robot.turretSubsystem.setTurnSpeed(0);
        }
        
    }

    @Override
    public void end(boolean interrupted){
        System.out.println("Limelight End");

        Robot.turretSubsystem.setTurnSpeed(0);
        Robot.vision.setLights(1);

    }
}