package frc.team4909.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team4909.robot.util.Util;

public class DriveTrainSubsystem extends SubsystemBase{
    WPI_TalonFX frontRight, frontLeft, backRight, backLeft;
    //CANSparkMax frontRight, frontLeft, backRight, backLeft;
    SpeedControllerGroup m_right, m_left;
    DifferentialDrive bionicDrive;
    boolean inverted = false;

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



        bionicDrive = new DifferentialDrive(m_left, m_right);
    }




    public void arcadeDrive(double speed, double turn) {

        double speedOutput = speed;
        double turnOutput = turn;

        // Since the robot doesn't move at speeds less than .3, this map function 
        // takes the full range of the joystick and converts it to the full range of the robot
        if (speed != 0) {
            speedOutput = Util.map(Math.abs(speed), 0.0, 1, .3, .75); 
            speedOutput = Math.copySign(speedOutput, speed);
            }

        // double turnOutput = turn*0.65;//Math.pow(rightSpeed, 3);
        SmartDashboard.putNumber("SpeedOutput", speedOutput);
        SmartDashboard.putNumber("TurnOutoput", turnOutput);

        if(inverted){
            speedOutput = speedOutput*-1;
            turnOutput = turnOutput*1;
        }


        bionicDrive.arcadeDrive(speedOutput, turnOutput);
    }

    public void tankDrive(double leftSpeed, double rightSpeed){
        double leftOutput = leftSpeed;
        double rightOutput = rightSpeed;

        bionicDrive.tankDrive(leftOutput, rightOutput);
    }

    public void drivetrainPID(){
        
        

}
    public void invertDriveDirection(){
        inverted = !inverted;
    }

    public void initialize(){
    }

    @Override
    public void periodic(){
        bionicDrive.feedWatchdog();
       
    } 
    

}