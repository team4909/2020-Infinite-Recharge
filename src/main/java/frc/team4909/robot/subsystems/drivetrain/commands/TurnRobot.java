package frc.team4909.robot.subsystems.drivetrain.commands;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class TurnRobot extends CommandBase{
    //error is how much we have to turn
    //gain is the speed to turn at based on error
    double degrees;
    double currentPosition;
    double targetPosition;
    PIDController turnPID;
    final double SPEED = 0; //TODO check value
    int numLoops = 0;

    public TurnRobot(double deg){
        super();
        addRequirements(Robot.drivetrainsubsystem);
        turnPID = new PIDController(RobotConstants.TURN_KP, RobotConstants.TURN_KI, RobotConstants.TURN_KD);
        this.degrees = deg; //Positive = Clockwise
    }
    
    @Override
    public void initialize() {
        Robot.drivetrainsubsystem.frontLeft.setInverted(TalonFXInvertType.CounterClockwise);
        Robot.drivetrainsubsystem.backLeft.setInverted(TalonFXInvertType.CounterClockwise);
        Robot.drivetrainsubsystem.frontRight.setInverted(TalonFXInvertType.CounterClockwise);
        Robot.drivetrainsubsystem.backRight.setInverted(TalonFXInvertType.CounterClockwise);

        
        Robot.drivetrainsubsystem.frontLeft.setSelectedSensorPosition(0);
        Robot.drivetrainsubsystem.backLeft.setSelectedSensorPosition(0);
        Robot.drivetrainsubsystem.frontRight.setSelectedSensorPosition(0);
        Robot.drivetrainsubsystem.backRight.setSelectedSensorPosition(0);
        

        currentPosition = Robot.drivetrainsubsystem.getAngle();
        targetPosition = degrees + currentPosition; //Gets the absolute position | TODO Find what get angle really means / gives back
        SmartDashboard.putNumber("TurnRobot Start Angle", currentPosition);
        SmartDashboard.putNumber("TurnRobot Current Angle", currentPosition);
        SmartDashboard.putNumber("TurnRobot Target Angle", targetPosition);
        turnPID.setSetpoint(targetPosition);
        // turnRobot.setTolerance(3);//TODO Test Value and figure out units
        System.out.println("Begin TurnRobot with error " + (targetPosition - currentPosition));
        SmartDashboard.putBoolean("Has TurnRobot Ended?", false);
    }

    @Override
    public void execute() {
        currentPosition = Robot.drivetrainsubsystem.getAngle();
        double output = turnPID.calculate(currentPosition);
        //NEGATIVE IS FOR SOME REASON???> SEE DRIVEFORWARD.JAVA
        double clampedOutput = MathUtil.clamp(Math.abs(output), 0.3, 0.31); //0.5, 0.9
        //double clampedOutput = MathUtil.clamp(output, -1, 1);
        Robot.drivetrainsubsystem.arcadeDrive(SPEED, output < 0 ? -clampedOutput : clampedOutput, true); //TODO where does the negative go???;
        //Option: Tank Drive to avoid unknown ramping-related issues.
        //Robot.drivetrainsubsystem.arcadeDrive(SPEED, clampedOutput, true);
        if(++numLoops == 1){
            SmartDashboard.putNumber("TurnRobot Current Angle", currentPosition);
            SmartDashboard.putNumber("TurnRobot Target Angle", targetPosition);
            SmartDashboard.putNumber("Turn PID Error", targetPosition - currentPosition);
            SmartDashboard.putNumber("Clamped Turn Output", clampedOutput);
            numLoops = 0;
        }

        SmartDashboard.putNumber("LEFT TURN TICKS", Robot.drivetrainsubsystem.frontLeft.getSelectedSensorPosition());
        SmartDashboard.putNumber("RIGHT TURN TICKS", Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition());
    }

    @Override
    public boolean isFinished() {
        if(Math.abs(targetPosition - currentPosition) < RobotConstants.TOLERANCE_DEGREES){ //COULD THIS BE USED TO FIX UNDER/OVERSHOOTING?
            System.out.println("Error is less than 3 units");
            return true;
        }
        
        return false;
    }

    @Override
    public void end(boolean interrupted){
        System.out.println("No more turning");

        Robot.drivetrainsubsystem.frontLeft.set(TalonFXControlMode.PercentOutput, 0);
        Robot.drivetrainsubsystem.backLeft.set(TalonFXControlMode.PercentOutput, 0);
        Robot.drivetrainsubsystem.frontRight.set(TalonFXControlMode.PercentOutput, 0);
        Robot.drivetrainsubsystem.backRight.set(TalonFXControlMode.PercentOutput, 0);
        
        Robot.drivetrainsubsystem.tankDrive(0, 0);
        Robot.drivetrainsubsystem.resetAngle();
        SmartDashboard.putBoolean("Has TurnRobot Ended?", true);
    }
    
}