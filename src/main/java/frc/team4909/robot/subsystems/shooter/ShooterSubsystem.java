package frc.team4909.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team4909.robot.RobotConstants;

public class ShooterSubsystem extends SubsystemBase {

    CANSparkMax shooter1;
    CANSparkMax shooter2;
    CANSparkMax turnMotor;
    WPI_TalonSRX hoodControl;
    SpeedControllerGroup shooter;
    CANEncoder encoder;
    CANPIDController speedPID;
    
    public ShooterSubsystem()
    {
        shooter1 = new CANSparkMax(5, MotorType.kBrushless);
        shooter2 = new CANSparkMax(6, MotorType.kBrushless);
        shooter = new SpeedControllerGroup(shooter1, shooter2);

        turnMotor = new CANSparkMax(10, MotorType.kBrushless);

        hoodControl = new WPI_TalonSRX(9);

        shooter1.restoreFactoryDefaults();
        shooter2.restoreFactoryDefaults();

        shooter1.follow(shooter2, true);

        encoder = shooter2.getEncoder();

        speedPID = new CANPIDController(shooter2);

        speedPID.setP(RobotConstants.shooterkP);
        speedPID.setI(RobotConstants.shooterkI);
        speedPID.setD(RobotConstants.shooterkD);

        hoodControl.configFactoryDefault();

        hoodControl.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        hoodControl.config_kP(0, RobotConstants.hoodkP);
        hoodControl.config_kI(0, RobotConstants.hoodkI);
        hoodControl.config_kD(0, RobotConstants.hoodkD);

        hoodControl.setSelectedSensorPosition(0);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Speed", encoder.getVelocity());
    }

    public void setSpeed(double speed){
        shooter2.set(speed);
    }

    public void setVelocity(double velocity){
        speedPID.setReference(velocity, ControlType.kVelocity);
    }

    public void setTurnSpeed(double speed){
        turnMotor.set(speed);
    }

    public void setHoodPosition(int pos){
        hoodControl.set(ControlMode.Position, pos);
    }

    public double getRPM(){
        return encoder.getVelocity();
    }

}
