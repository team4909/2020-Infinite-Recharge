package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

//Executed from the ParallelCommandGroup FollowAndAim.
public class ShootByDistance extends CommandBase {

    /**
     * Values obtained through 6 tested points: 
     * (Distance to Goal, Hood Angle Reqired to Make The Inner Port).
     */
    private double a = RobotConstants.hoodCofA;
    private double b = RobotConstants.hoodCofB;
    private double c = RobotConstants.hoodCofC;

    public ShootByDistance() {
        super();
        addRequirements(Robot.hoodSubsystem);
    }

    @Override
    public void initialize() {
        Robot.hoodSubsystem.setHoodAngle(calculateAngle((int)Robot.vision.calculateDistanceFromCameraHeight(
                RobotConstants.powerPortHeight, RobotConstants.limelightHeight, RobotConstants.limelightAngle)));
    }

    /**
     * @param distance The distance from the Limelight to the Goal.
     * @return The angle of the quadratic
     */
    private int calculateAngle(int distance) {
        // Use previously tested coefficients to calculate angle
        double outputAngle;
        double firstMono = a * Math.pow(distance, 2);
        double secondMono = b * distance;
        outputAngle = firstMono + secondMono + c;
        return (int) outputAngle;
    }
}
