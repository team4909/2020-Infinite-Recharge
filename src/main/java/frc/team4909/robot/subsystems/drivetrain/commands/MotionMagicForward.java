package frc.team4909.robot.subsystems.drivetrain.commands;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

/**
 * MotionMagicForward
 */
public class MotionMagicForward extends CommandBase{

    public double inches;
    public double leftTicksToMove;
    public double rightTicksToMove;

    public MotionMagicForward(double in){
        super();
        this.inches = in;
    }

    @Override
    public void initialize() {
        leftTicksToMove = Robot.drivetrainsubsystem.frontLeft.getSelectedSensorPosition() + (RobotConstants.TICKS_PER_INCH * inches);
        rightTicksToMove = Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition() + (RobotConstants.TICKS_PER_INCH * inches);
        
        SmartDashboard.putNumber("Left Ticks to Move", leftTicksToMove);
        SmartDashboard.putNumber("Right Ticks to Move", rightTicksToMove);
    } 

    @Override
    public void execute() {
        Robot.drivetrainsubsystem.frontLeft.set(TalonFXControlMode.MotionMagic, leftTicksToMove);
        Robot.drivetrainsubsystem.frontRight.set(TalonFXControlMode.MotionMagic, leftTicksToMove);

        Robot.drivetrainsubsystem.backLeft.set(TalonFXControlMode.MotionMagic, rightTicksToMove);
        Robot.drivetrainsubsystem.backRight.set(TalonFXControlMode.MotionMagic, rightTicksToMove);

        SmartDashboard.putNumber("Left Ticks to Move", leftTicksToMove);
        SmartDashboard.putNumber("Right Ticks to Move", rightTicksToMove);

    }

    @Override
    public boolean isFinished() {
        if(Robot.drivetrainsubsystem.frontLeft.getSelectedSensorPosition() >= RobotConstants.TOLERANCE_TICKS && 
           Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition() >= RobotConstants.TOLERANCE_TICKS){
            return true;
        } else {
            return false;
        }
    }   

    @Override
    public void end(boolean interrupted) {
        SmartDashboard.putBoolean("End Method in MotionMagicForward called", true);
        Robot.drivetrainsubsystem.arcadeDrive(0, 0, true);
        
        Robot.drivetrainsubsystem.frontLeft.set(TalonFXControlMode.MotionMagic, 0);
        Robot.drivetrainsubsystem.frontRight.set(TalonFXControlMode.MotionMagic, 0);
        Robot.drivetrainsubsystem.backLeft.set(TalonFXControlMode.MotionMagic, 0);
        Robot.drivetrainsubsystem.backRight.set(TalonFXControlMode.MotionMagic, 0);

    }
}
