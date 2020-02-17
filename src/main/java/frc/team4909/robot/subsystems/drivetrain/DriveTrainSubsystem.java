package frc.team4909.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrainSubsystem extends SubsystemBase{
    WPI_TalonFX frontRight, frontLeft, backRight, backLeft;
    //CANSparkMax frontRight, frontLeft, backRight, backLeft;
    SpeedControllerGroup m_right, m_left;
    DifferentialDrive bionicDrive;
    boolean inverted;

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
        double speedOutput = Math.pow(speed, 3)*0.75;
        double turnOutput = turn*0.65;//Math.pow(rightSpeed, 3);

        if(inverted){
            speedOutput = speedOutput*-1;
            turnOutput = turnOutput*-1;
        }

        bionicDrive.arcadeDrive(speedOutput, turnOutput);
    }

    public void tankDrive(double leftSpeed, double rightSpeed){
        double leftOutput = leftSpeed;
        double rightOutput = rightSpeed;

        bionicDrive.tankDrive(leftOutput, rightOutput);
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