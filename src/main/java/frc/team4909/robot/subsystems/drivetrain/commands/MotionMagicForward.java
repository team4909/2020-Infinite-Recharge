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

    public boolean isFinished;


    public MotionMagicForward(double in){
        super();
        this.inches = in;

        leftTicksToMove = inches * RobotConstants.TICKS_PER_INCH;
        rightTicksToMove = inches * RobotConstants.TICKS_PER_INCH;
    }

    public MotionMagicForward(double lTicks, double rTicks){

        //recives ticks from MM turn
        leftTicksToMove = lTicks;
        rightTicksToMove = rTicks;
    }

    @Override
    public void initialize() {
       
        SmartDashboard.putBoolean("End Method in MotionMagicForward called", false);

        Robot.drivetrainsubsystem.frontLeft.setSelectedSensorPosition(0);
        Robot.drivetrainsubsystem.backLeft.setSelectedSensorPosition(0);
        Robot.drivetrainsubsystem.frontRight.setSelectedSensorPosition(0);
        Robot.drivetrainsubsystem.backRight.setSelectedSensorPosition(0);
        
     
        
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
        SmartDashboard.putNumber("Current Left Ticks", Robot.drivetrainsubsystem.frontLeft.getSelectedSensorPosition());
        SmartDashboard.putNumber("Current Right Ticks", Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition());

    }

    @Override
    public boolean isFinished() {
        if(Robot.drivetrainsubsystem.frontLeft.getSelectedSensorPosition() >= leftTicksToMove - RobotConstants.TOLERANCE_TICKS){
            isFinished = true;
            return true;
        
        } else {
            return false;
        }


    }   

    @Override
    public void end(boolean interrupted) {
        SmartDashboard.putBoolean("End Method in MotionMagicForward called", true);
        Robot.drivetrainsubsystem.frontLeft.set(TalonFXControlMode.PercentOutput, 0);
        Robot.drivetrainsubsystem.backLeft.set(TalonFXControlMode.PercentOutput, 0);
        Robot.drivetrainsubsystem.frontRight.set(TalonFXControlMode.PercentOutput, 0);
        Robot.drivetrainsubsystem.backRight.set(TalonFXControlMode.PercentOutput, 0);

    }
}