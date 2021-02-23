package frc.team4909.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.util.Util;

public class DriveTrainSubsystem extends SubsystemBase{
    public WPI_TalonFX frontRight, frontLeft, backRight, backLeft;
    //CANSparkMax frontRight, frontLeft, backRight, backLeft;
    SpeedControllerGroup m_right, m_left;
    public DifferentialDrive bionicDrive;
    boolean inverted, preciseMode = false;
    AHRS navX;
    double angle = 0;
    PIDController pid;
    double speedMultiplier, turnMultiplier, lastAngle;

    public DriveTrainSubsystem() {

        //RIGHT SIDE NOT INVERTED: 

        frontRight = new WPI_TalonFX(1);
        backRight = new WPI_TalonFX(2);
        frontLeft = new WPI_TalonFX(3);
        backLeft = new WPI_TalonFX(4);

        frontLeft.configFactoryDefault();
        backLeft.configFactoryDefault();
        frontRight.configFactoryDefault();
        backRight.configFactoryDefault();

        m_right = new SpeedControllerGroup(frontRight, backRight);

        // frontRight.configClosedloopRamp(2);
        // frontLeft.configClosedloopRamp(2);
        // backRight.configClosedloopRamp(2);
        // backLeft.configClosedloopRamp(2);

        frontLeft.setNeutralMode(NeutralMode.Brake);
        frontRight.setNeutralMode(NeutralMode.Brake);
        backLeft.setNeutralMode(NeutralMode.Brake);
        backRight.setNeutralMode(NeutralMode.Brake);

        m_left = new SpeedControllerGroup(frontLeft, backLeft);

        pid = new PIDController(RobotConstants.drivekP, RobotConstants.drivekI, RobotConstants.drivekD);

        navX = new AHRS(SerialPort.Port.kMXP);
        navX.reset();

        bionicDrive = new DifferentialDrive(m_left, m_right);
        //bionicDrive.setRightSideInverted(true);

    }


    public void arcadeDrive(final double speed, final double turn, final boolean decelerationRamped) {

        double speedOutput = speed;
        double turnOutput = turn;

        // Since the robot doesn't move at speeds less than .3, this map function 
        // takes the full range of the joystick and converts it to the full range of the robot
        
        // if (speed != 0) {
        //     speedOutput = Util.map(Math.abs(speed), 0.0, 1, .3, speedMultiplier); 
        //     speedOutput = Math.copySign(speedOutput, speed);
        //     }

        if (turn != 0){
            turnOutput = Util.map(Math.abs(turn), 0.05, 1, .3, turnMultiplier); 
            turnOutput = Math.copySign(turnOutput, turn);
        }

        // angle += turn;

        SmartDashboard.putNumber("SpeedOutput", speedOutput);
        SmartDashboard.putNumber("TurnOutoput", turnOutput);

        if(inverted){
            speedOutput = speedOutput*-1;
        }

        //Drive Ramping Up
        if (decelerationRamped) speedOutput = DriveTrainRamp.getRampedOutput(speedOutput);
        else speedOutput = DriveTrainRamp.getAccelerationRampedOutput(speedOutput);



        if(Math.abs(turnOutput) != 0 && speedOutput != 0){
            bionicDrive.arcadeDrive(speedOutput, turnOutput * 0.5);
        }else{
            if(speedOutput != 0){
                bionicDrive.arcadeDrive(speedOutput, MathUtil.clamp(pid.calculate(navX.getAngle(), angle),-0.5, 0.5));    
            }else{
                bionicDrive.arcadeDrive(speedOutput, turnOutput);
            }
        }
    }

    public void tankDrive(final double leftSpeed, final double rightSpeed){
        final double leftOutput = leftSpeed;
        final double rightOutput = rightSpeed;

        bionicDrive.tankDrive(leftOutput, rightOutput);
    }

    public void togglePreciseMode() {
        preciseMode = !preciseMode;
    }
    
    public void invertDriveDirection(){
        inverted = !inverted;
    }

    public void zeroGyro(){
        angle = 0;
        navX.reset();
    }

    @Override
    public void periodic(){
        bionicDrive.feedWatchdog();
        SmartDashboard.putNumber("Robot Angle", navX.getAngle());   
        SmartDashboard.putNumber("Target Angle", angle);
        SmartDashboard.putNumber("Is Turning?", navX.getRawAccelZ());
       
        if(Math.abs(navX.getAngle()-lastAngle)>1){                        
            angle = navX.getAngle();
        }

        lastAngle = navX.getAngle();

        if(preciseMode){
            speedMultiplier = 0.5;
            turnMultiplier = 0.5;
        }else{
            speedMultiplier = 1;
            turnMultiplier = 0.75;
        }

        //System.out.println(frontRight.getSelectedSensorPosition());
    } 
    

}