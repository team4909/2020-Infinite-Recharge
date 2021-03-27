package frc.team4909.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
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

        frontLeft.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, RobotConstants.PID_LOOP_IDX, RobotConstants.TIMEOUT);
        frontRight.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, RobotConstants.PID_LOOP_IDX, RobotConstants.TIMEOUT);
        backLeft.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, RobotConstants.PID_LOOP_IDX, RobotConstants.TIMEOUT);
        backRight.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, RobotConstants.PID_LOOP_IDX, RobotConstants.TIMEOUT);

        // frontLeft.configNeutralDeadband(0.001);
        // frontRight.configNeutralDeadband(0.001);
        // backLeft.configNeutralDeadband(0.001);
        // backRight.configNeutralDeadband(0.001);

        frontLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, RobotConstants.TIMEOUT);
        frontLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, RobotConstants.TIMEOUT);

        frontRight.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, RobotConstants.TIMEOUT);
        frontRight.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, RobotConstants.TIMEOUT);

        backLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, RobotConstants.TIMEOUT);
        backLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, RobotConstants.TIMEOUT);

        backRight.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, RobotConstants.TIMEOUT);
        backRight.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, RobotConstants.TIMEOUT);

        frontLeft.selectProfileSlot(RobotConstants.PID_SLOT_IDX, RobotConstants.PID_LOOP_IDX);
        frontLeft.config_kP(RobotConstants.PID_SLOT_IDX, RobotConstants.MAGIC_KP);
        frontLeft.config_kI(RobotConstants.PID_SLOT_IDX, RobotConstants.MAGIC_KI);
        frontLeft.config_kD(RobotConstants.PID_SLOT_IDX, RobotConstants.MAGIC_KD);
        frontLeft.config_kF(RobotConstants.PID_SLOT_IDX, RobotConstants.MAGIC_KF);

        frontRight.selectProfileSlot(RobotConstants.PID_SLOT_IDX, RobotConstants.PID_LOOP_IDX);
        frontRight.config_kP(RobotConstants.PID_SLOT_IDX, RobotConstants.MAGIC_KP);
        frontRight.config_kI(RobotConstants.PID_SLOT_IDX, RobotConstants.MAGIC_KI);
        frontRight.config_kD(RobotConstants.PID_SLOT_IDX, RobotConstants.MAGIC_KD);
        frontRight.config_kF(RobotConstants.PID_SLOT_IDX, RobotConstants.MAGIC_KF);
        
        backLeft.selectProfileSlot(RobotConstants.PID_SLOT_IDX, RobotConstants.PID_LOOP_IDX);
        backLeft.config_kP(RobotConstants.PID_SLOT_IDX, RobotConstants.MAGIC_KP);
        backLeft.config_kI(RobotConstants.PID_SLOT_IDX, RobotConstants.MAGIC_KI);
        backLeft.config_kD(RobotConstants.PID_SLOT_IDX, RobotConstants.MAGIC_KD);
        backLeft.config_kF(RobotConstants.PID_SLOT_IDX, RobotConstants.MAGIC_KF);
        
        backRight.selectProfileSlot(RobotConstants.PID_SLOT_IDX, RobotConstants.PID_LOOP_IDX);
        backRight.config_kP(RobotConstants.PID_SLOT_IDX, RobotConstants.MAGIC_KP);
        backRight.config_kI(RobotConstants.PID_SLOT_IDX, RobotConstants.MAGIC_KI);
        backRight.config_kD(RobotConstants.PID_SLOT_IDX, RobotConstants.MAGIC_KD);
        backRight.config_kF(RobotConstants.PID_SLOT_IDX, RobotConstants.MAGIC_KF);

        frontLeft.configMotionCruiseVelocity(RobotConstants.MOTION_CRUISE_VELOCITY);
        frontLeft.configMotionAcceleration(RobotConstants.MOTION_ACCELERATION);

        frontRight.configMotionCruiseVelocity(RobotConstants.MOTION_CRUISE_VELOCITY);
        frontRight.configMotionAcceleration(RobotConstants.MOTION_ACCELERATION);

        backLeft.configMotionCruiseVelocity(RobotConstants.MOTION_CRUISE_VELOCITY);
        backLeft.configMotionAcceleration(RobotConstants.MOTION_ACCELERATION);

        backRight.configMotionCruiseVelocity(RobotConstants.MOTION_CRUISE_VELOCITY);
        backRight.configMotionAcceleration(RobotConstants.MOTION_ACCELERATION);



        m_left = new SpeedControllerGroup(frontLeft, backLeft);

        pid = new PIDController(RobotConstants.drivekP, RobotConstants.drivekI, RobotConstants.drivekD);

        navX = new AHRS(SerialPort.Port.kMXP);
        zeroGyro();

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

        

        if(inverted){
            speedOutput = speedOutput*-1;
        }

        //Drive Ramping Up 
        if (decelerationRamped){ 
            speedOutput = DriveTrainRamp.getRampedOutput(speedOutput);
        } else{
            speedOutput = DriveTrainRamp.getAccelerationRampedOutput(speedOutput);
        }

        SmartDashboard.putNumber("SpeedOutput", speedOutput);
        SmartDashboard.putNumber("TurnOutoput", turnOutput);

        if(Math.abs(turnOutput) != 0 && speedOutput != 0){
            bionicDrive.arcadeDrive(speedOutput, turnOutput);
        }else{
            if(speedOutput != 0){
                bionicDrive.arcadeDrive(speedOutput, MathUtil.clamp(pid.calculate(navX.getAngle(), angle),-0.5, 0.5));    
            }else{
                bionicDrive.arcadeDrive(0, turnOutput);
            }
        }
    }

    public void tankDrive(final double leftSpeed, final double rightSpeed){
        double leftOutput = leftSpeed;
        double rightOutput = rightSpeed;

        if(leftOutput != 0){
            leftOutput = MathUtil.clamp(Math.abs(leftOutput), 0.3, RobotConstants.MAX_VELOCITY);
            if(leftOutput < 0){
                leftOutput *= -1;
            }
        }

        if(rightOutput != 0){
            rightOutput = MathUtil.clamp(Math.abs(rightOutput), 0.3, RobotConstants.MAX_VELOCITY);
            if(rightOutput < 0){
                rightOutput *= -1;
            }

        } 

        bionicDrive.tankDrive( -leftOutput, -rightOutput);
    }

    // public void PIDTankDrive(double leftVelocity, double rightVelocity){
    //     frontLeft.set(TalonFXControlMode.Position, leftVelocity);
    //     frontRight.set(TalonFXControlMode.Position, rightVelocity);
    //     backLeft.set(TalonFXControlMode.Position, leftVelocity);
    //     backRight.set(TalonFXControlMode.Position, rightVelocity);

    // }
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

    public double getAngle(){
        return navX.getAngle();
    }

    public void resetAngle(){
        lastAngle = angle = navX.getAngle();
    }

    public void getDisplacementX(){
        navX.getDisplacementX();
    }

    public void getDisplacementY(){
        navX.getDisplacementY();
    }

    @Override
    public void periodic(){
        bionicDrive.feedWatchdog();

        //Useful for Debugging
        //SmartDashboard.putNumber("Robot Angle", navX.getAngle());   
        //SmartDashboard.putNumber("Target Angle", angle);
        //SmartDashboard.putNumber("Is Turning?", navX.getRawAccelZ());
       
        if(Math.abs(navX.getAngle()-lastAngle) > 0.5){                        
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