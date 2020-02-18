package frc.team4909.robot.subsystems.shooter.commands;

import javax.swing.text.Position;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.subsystems.shooter.ShooterSubsystem;

public class SetHoodPosition extends CommandBase{

    int pos;
    double distance;

    public SetHoodPosition(ShooterSubsystem subsystem, int position){
        super();
        addRequirements(subsystem);
        pos = position;
    }

    public void initialize(){
        distance = Robot.vision.calculateDistanceFromCameraHeight(RobotConstants.powerPortHeight, RobotConstants.limelightHeight, RobotConstants.limelightAngle);
        double hoodPos = (199.512)*(Math.pow(0.999707, distance));
        Robot.hoodSubsystem.setHoodPosition((int)hoodPos);
    }
}