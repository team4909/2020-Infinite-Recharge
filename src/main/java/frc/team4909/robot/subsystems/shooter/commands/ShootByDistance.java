package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class ShootByDistance extends CommandBase {

    private double a = RobotConstants.hoodCofA;
    private double b = RobotConstants.hoodCofB;
    private double c = RobotConstants.hoodCofC;

    public ShootByDistance() {
        super();
        addRequirements(Robot.hoodSubsystem);
    }

    @Override
    public void initialize() {
        Robot.hoodSubsystem.setHoodAngle(calculateAngle(Robot.vision.calculateDistanceFromCameraHeight(
                RobotConstants.powerPortHeight, RobotConstants.limelightHeight, RobotConstants.limelightAngle)));
    }

    private int calculateAngle(double distance) {
        double y;
        double firstMono = a * Math.pow(distance, 2);
        double secondMono = b * distance;
        y = firstMono + secondMono + c;
        return (int) y;
    }
}
