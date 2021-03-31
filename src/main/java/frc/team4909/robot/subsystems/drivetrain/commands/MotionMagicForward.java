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

    public boolean isFinished;

    public MotionMagicForward(double in){
        super();
        this.inches = in;

        leftTicksToMove = inches * RobotConstants.TICKS_PER_INCH;
        rightTicksToMove = inches * RobotConstants.TICKS_PER_INCH;
    }

  
    @Override
    public void initialize() {


        //Sets all of the sensor positions to zero so that when calculating Ticks to move, we don't have to use the starting position
        Robot.drivetrainsubsystem.frontLeft.setSelectedSensorPosition(0);
        Robot.drivetrainsubsystem.backLeft.setSelectedSensorPosition(0);
        Robot.drivetrainsubsystem.frontRight.setSelectedSensorPosition(0);
        Robot.drivetrainsubsystem.backRight.setSelectedSensorPosition(0);
        
     
        
        SmartDashboard.putNumber("Left Ticks to Move", leftTicksToMove);
        SmartDashboard.putNumber("Right Ticks to Move", rightTicksToMove);

        //Inverts the right side of the robot becuase of the position of the motors on the robot.
        Robot.drivetrainsubsystem.frontLeft.setInverted(TalonFXInvertType.Clockwise);
        Robot.drivetrainsubsystem.backLeft.setInverted(TalonFXInvertType.Clockwise);
        Robot.drivetrainsubsystem.frontRight.setInverted(TalonFXInvertType.CounterClockwise);
        Robot.drivetrainsubsystem.backRight.setInverted(TalonFXInvertType.CounterClockwise);

        //Gets the left ticks to move and puts it in the Talon's Motion Magic function
        //MOTION MAGIC DOES NOT NEED YOU TO ADD THE CURRENT POSITION TO IT...
        //...also because we are setting to sensor positions to zero
        Robot.drivetrainsubsystem.frontLeft.set(TalonFXControlMode.MotionMagic, leftTicksToMove);
        Robot.drivetrainsubsystem.frontRight.set(TalonFXControlMode.MotionMagic, rightTicksToMove);

        Robot.drivetrainsubsystem.backLeft.set(TalonFXControlMode.MotionMagic, leftTicksToMove);
        Robot.drivetrainsubsystem.backRight.set(TalonFXControlMode.MotionMagic, rightTicksToMove);
    } 

    @Override
    public void execute() {
        //you don't acctually need an execute this is just to know where the robot is going and for printing purposes
        SmartDashboard.putNumber("Left Ticks to Move", leftTicksToMove);
        SmartDashboard.putNumber("Right Ticks to Move", rightTicksToMove);
        SmartDashboard.putNumber("Current Left Ticks", Robot.drivetrainsubsystem.frontLeft.getSelectedSensorPosition());
        SmartDashboard.putNumber("Current Right Ticks", Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition());

    }

    @Override
    public boolean isFinished() {
        //When the sensor position is past the ticks needed to move, it returns finishes
        //Subtract the tolerance because you want a leeway of how much ever your tolerance is
        if(Robot.drivetrainsubsystem.frontLeft.getSelectedSensorPosition() >= leftTicksToMove - RobotConstants.TOLERANCE_TICKS){
            isFinished = true;
            return true;
        
        } else {
            return false;
        }


    }   

    @Override
    public void end(boolean interrupted) {
        //We use percent output to set all of the speeds to zero becuase it's more reliable and final than using other control modes
        //It sets all of the motor speeds to zero so that it does not move
        Robot.drivetrainsubsystem.frontLeft.set(TalonFXControlMode.PercentOutput, 0);
        Robot.drivetrainsubsystem.backLeft.set(TalonFXControlMode.PercentOutput, 0);
        Robot.drivetrainsubsystem.frontRight.set(TalonFXControlMode.PercentOutput, 0);
        Robot.drivetrainsubsystem.backRight.set(TalonFXControlMode.PercentOutput, 0);

    }
}