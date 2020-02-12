package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.Vision;
import frc.team4909.robot.subsystems.shooter.ShooterSubsystem;

public class ShootByDistance extends CommandBase {
    private double accelInS2 = 386.22;
    private double currDist;
    private double timeEstimate;
    private double xComponent;
    private double yComponent;
    private double releaseRPM;

    public ShootByDistance(ShooterSubsystem subsystem, Vision limelight) {
        super();
        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        currDist = Robot.vision.calculateDistanceFromCameraHeight(RobotConstants.powerPortHeight, RobotConstants.limelightHeight, RobotConstants.limelightAngle);
        System.out.println(currDist);
        timeEstimate = currDist/100;//estimate
        xComponent = currDist/timeEstimate;    //inches per second
        yComponent = (currDist-0.5*accelInS2*Math.pow(timeEstimate, 2.0))/timeEstimate;   
        releaseRPM = Math.sqrt(Math.pow(xComponent, 2.0)+Math.pow(yComponent, 2));

        Robot.shootersubsystem.setVelocity(releaseRPM);  
    } 
}