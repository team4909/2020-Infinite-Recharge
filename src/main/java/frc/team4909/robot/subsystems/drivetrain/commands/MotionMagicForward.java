package frc.team4909.robot.subsystems.drivetrain.commands;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

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
    public double LSD;
    public double RSD;


    public MotionMagicForward(double in){
        super();
        this.inches = in;
    }

    @Override
    public void initialize() {
       
        LSD = Robot.drivetrainsubsystem.backLeft.getSelectedSensorPosition();
        RSD = Robot.drivetrainsubsystem.backRight.getSelectedSensorPosition();

        // leftTicksToMove = LSD + (RobotConstants.TICKS_PER_INCH * inches);
        // rightTicksToMove = RSD + (RobotConstants.TICKS_PER_INCH * inches);
        
//         leftTicksToMove = (RobotConstants.TICKS_PER_INCH * inches) + LSD;
//         rightTicksToMove = (RobotConstants.TICKS_PER_INCH * inches) + RSD;
        
        leftTicksToMove = 80000 + LSD;
        rightTicksToMove = 80000 + RSD;
        
        SmartDashboard.putNumber("Left Ticks to Move", leftTicksToMove);
        SmartDashboard.putNumber("Right Ticks to Move", rightTicksToMove);

        Robot.drivetrainsubsystem.frontLeft.setInverted(TalonFXInvertType.Clockwise);
        Robot.drivetrainsubsystem.backLeft.setInverted(TalonFXInvertType.Clockwise);
        Robot.drivetrainsubsystem.frontRight.setInverted(TalonFXInvertType.CounterClockwise);
        Robot.drivetrainsubsystem.backRight.setInverted(TalonFXInvertType.CounterClockwise);


        Robot.drivetrainsubsystem.frontLeft.set(TalonFXControlMode.MotionMagic, leftTicksToMove);
        Robot.drivetrainsubsystem.frontRight.set(TalonFXControlMode.MotionMagic, rightTicksToMove);

        Robot.drivetrainsubsystem.backLeft.set(TalonFXControlMode.MotionMagic, leftTicksToMove);
        Robot.drivetrainsubsystem.backRight.set(TalonFXControlMode.MotionMagic, rightTicksToMove);
    } 

    @Override
    public void execute() {

        SmartDashboard.putNumber("Left Ticks to Move", leftTicksToMove);
        SmartDashboard.putNumber("Right Ticks to Move", rightTicksToMove);

    }

    @Override
    public boolean isFinished() {
        // if(Robot.drivetrainsubsystem.frontLeft.getSelectedSensorPosition() >= RobotConstants.TOLERANCE_TICKS && 
        //    Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition() >= RobotConstants.TOLERANCE_TICKS){
        //     return true;
        // } else {
        //     return false;
        // }

        return false;
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
