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
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.util.Util;

public class DriveTrainSubsystem extends SubsystemBase{
    WPI_TalonFX frontRight, frontLeft, backRight, backLeft;
    //CANSparkMax frontRight, frontLeft, backRight, backLeft;
    SpeedControllerGroup m_right, m_left;
    DifferentialDrive bionicDrive;
    boolean inverted = false;
    AHRS navX;
    double angle = 0;
    PIDController pid;

    public DriveTrainSubsystem() {
        frontRight = new WPI_TalonFX(1);
        backRight = new WPI_TalonFX(2);
        // frontRight = new CANSparkMax(1, MotorType.kBrushless);
        // backRight = new CANSparkMax(2, MotorType.kBrushless);
        m_right = new SpeedControllerGroup(frontRight, backRight);

        frontLeft = new WPI_TalonFX(3);
        backLeft = new WPI_TalonFX(4);

        frontLeft.setNeutralMode(NeutralMode.Coast);
        frontRight.setNeutralMode(NeutralMode.Coast);
        backLeft.setNeutralMode(NeutralMode.Coast);
        backRight.setNeutralMode(NeutralMode.Coast);
        // frontLeft = new CANSparkMax(3, MotorType.kBrushless);
        // backLeft = new CANSparkMax(4, MotorType.kBrushless);
        m_left = new SpeedControllerGroup(frontLeft, backLeft);

        pid = new PIDController(RobotConstants.drivekP, RobotConstants.drivekI, RobotConstants.drivekD);

        navX = new AHRS(SerialPort.Port.kMXP);
        navX.reset();


        bionicDrive = new DifferentialDrive(m_left, m_right);

    }




    public void arcadeDrive(double speed, double turn) {

        double speedOutput = speed;
        double turnOutput = turn * 0.7;

        // Since the robot doesn't move at speeds less than .3, this map function 
        // takes the full range of the joystick and converts it to the full range of the robot
        if (speed != 0) {
            speedOutput = Util.map(Math.abs(speed), 0.0, 1, .3, .75); 
            speedOutput = Math.copySign(speedOutput, speed);
            }

        // angle += turn;

        SmartDashboard.putNumber("SpeedOutput", speedOutput);
        SmartDashboard.putNumber("TurnOutoput", turnOutput);

        if(inverted){
            speedOutput = speedOutput*-1;
        }
        if(Math.abs(turnOutput) != 0){
            bionicDrive.arcadeDrive(speedOutput, turnOutput);
            angle = navX.getAngle();
        }else{
            if(speedOutput != 0){
                bionicDrive.arcadeDrive(speedOutput, MathUtil.clamp(pid.calculate(navX.getAngle(), angle),-0.75, 0.75));    
            }else{bionicDrive.arcadeDrive(0, 0);}
        }
    }

    public void tankDrive(double leftSpeed, double rightSpeed){
        double leftOutput = leftSpeed;
        double rightOutput = rightSpeed;

        bionicDrive.tankDrive(leftOutput, rightOutput);
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
       
    } 
    

}