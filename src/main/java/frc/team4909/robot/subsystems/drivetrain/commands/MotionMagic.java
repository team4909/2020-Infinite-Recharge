package frc.team4909.robot.subsystems.drivetrain.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

/**
 * MotionMagic
 */
public class MotionMagic extends CommandBase{

    public double inches;
    public double LStartingDistance;
    public double RStartingDistance;

    public MotionMagic(double distance){

        this.inches = distance;
    
    }
    

    @Override
    public void initialize() {

        Robot.drivetrainsubsystem.frontLeft.configFactoryDefault();
        Robot.drivetrainsubsystem.frontRight.configFactoryDefault();
        Robot.drivetrainsubsystem.backLeft.configFactoryDefault();
        Robot.drivetrainsubsystem.backRight.configFactoryDefault();

        RStartingDistance = Robot.drivetrainsubsystem.backRight.getSelectedSensorPosition();
        LStartingDistance = Robot.drivetrainsubsystem.backLeft.getSelectedSensorPosition();
        inches *= RobotConstants.TICKS_PER_INCH;
        inches += LStartingDistance;

    


        Robot.drivetrainsubsystem.frontLeft.setInverted(TalonFXInvertType.Clockwise);
        Robot.drivetrainsubsystem.backLeft.setInverted(TalonFXInvertType.Clockwise);
        Robot.drivetrainsubsystem.frontRight.setInverted(TalonFXInvertType.CounterClockwise);
        Robot.drivetrainsubsystem.backRight.setInverted(TalonFXInvertType.CounterClockwise);


        
        Robot.drivetrainsubsystem.frontRight.set(ControlMode.MotionMagic, 7000 + Math.abs(RStartingDistance));
        Robot.drivetrainsubsystem.frontLeft.set(ControlMode.MotionMagic, 7000 + Math.abs(LStartingDistance));
        Robot.drivetrainsubsystem.backRight.set(ControlMode.MotionMagic, 7000 + Math.abs(RStartingDistance));
        Robot.drivetrainsubsystem.backLeft.set(ControlMode.MotionMagic, 7000 + Math.abs(LStartingDistance));
        
        // Robot.drivetrainsubsystem.frontLeft.set(ControlMode.PercentOutput, 0.5);
        // Robot.drivetrainsubsystem.frontRight.set(ControlMode.PercentOutput, 0.5);
        // Robot.drivetrainsubsystem.backLeft.set(ControlMode.PercentOutput, 0.5);
        // Robot.drivetrainsubsystem.backRight.set(ControlMode.PercentOutput, 0.5);

        SmartDashboard.putBoolean("MM - ENDED", false);
        SmartDashboard.putNumber("MM - Target Position", inches);
        SmartDashboard.putNumber("MM - Current Position", Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition());
        SmartDashboard.putNumber("MM - L Starting Postion", LStartingDistance);
        SmartDashboard.putNumber("MM - R starting Position", RStartingDistance);

    }

    @Override
    public void execute() {
    
        SmartDashboard.putNumber("MM - Target Position", inches);
        SmartDashboard.putNumber("MM - Current Position", Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition());
        SmartDashboard.putNumber("MM - FR POS", Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition());
        SmartDashboard.putNumber("MM - FL POS", Robot.drivetrainsubsystem.frontLeft.getSelectedSensorPosition());

        System.out.println("runningZ");
    }


    @Override
    public boolean isFinished() {
        // TODO Auto-generated method stub
        // if(Math.abs(Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition(0)) >= Math.abs(inches)){
            
        //     SmartDashboard.putBoolean("MM - ENDED", true);
        //     return true;
        // }

        

        return false;

    }

    @Override
    public void end(boolean interrupted) {
        Robot.drivetrainsubsystem.tankDrive(0, 0);
    }
}

