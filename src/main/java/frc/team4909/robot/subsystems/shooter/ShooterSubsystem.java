package frc.team4909.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team4909.robot.RobotConstants;

public class ShooterSubsystem extends SubsystemBase {

    CANSparkMax shooter1;
    CANSparkMax shooter2;
    CANSparkMax turnMotor;
    SpeedControllerGroup shooter;
    CANEncoder encoder;
    CANPIDController speedPID;
    DigitalInput endPoint;
    
    public ShooterSubsystem()
    {
        shooter1 = new CANSparkMax(5, MotorType.kBrushless);
        shooter2 = new CANSparkMax(6, MotorType.kBrushless);
        shooter = new SpeedControllerGroup(shooter1, shooter2);

        turnMotor = new CANSparkMax(10, MotorType.kBrushless);
        turnMotor.setIdleMode(IdleMode.kBrake);

        endPoint = new DigitalInput(2);

        shooter1.restoreFactoryDefaults();
        shooter2.restoreFactoryDefaults();

        shooter1.follow(shooter2, true);

        encoder = shooter2.getEncoder();

        speedPID = new CANPIDController(shooter2);

        speedPID.setP(0.01);//RobotConstants.shooterkP);
        speedPID.setI(0);//RobotConstants.shooterkI);
        speedPID.setD(0);//RobotConstants.shooterkD);
        // speedPID.setIMaxAccum(10, 0);

        //speedPID.setReference(0, ControlType.kVelocity);
    }

    public boolean turretAtZero(){
        return endPoint.get();
    }

    public void zeroTurret(){
        turnMotor.getEncoder().setPosition(0);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Speed", encoder.getVelocity());
    }

    public void setSpeed(double speed){
        shooter1.set(speed);
        shooter2.set(speed);
    }

    public void setVelocity(double velocity){
        speedPID.setReference(velocity, ControlType.kVelocity);
    }

    public void setTurnSpeed(double speed){
        turnMotor.set(speed);
    }

    public double getRPM(){
        return encoder.getVelocity();
    }

}
