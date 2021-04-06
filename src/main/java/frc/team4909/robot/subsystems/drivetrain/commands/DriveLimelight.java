package frc.team4909.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class DriveLimelight extends CommandBase {
    
    double position;
    double absolutePos;
    double inchesToDrive;

    public DriveLimelight(double pos){
        this.position = pos;
    }

    @Override
    public void initialize() {
        //Calculates posiiton on field in relation to power port
        absolutePos = Robot.vision.calculateDistanceFromCameraHeight(RobotConstants.powerPortHeight, RobotConstants.limelightHeight, RobotConstants.limelightAngle);
        //Gets how many inches to drive based off of power port
        inchesToDrive = position - absolutePos;
        //Calls motion magic with the above number
        new MotionMagicForward(inchesToDrive);
    }

    //Only making this to avoid any problems with commands not ending
    @Override
    public boolean isFinished() {
        if(MotionMagicForward.isFinished){
            return true;
        } else {
            return false;
        }
    }
}