package frc.team4909.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    CANSparkMax shooter1;
    CANSparkMax shooter2;
    WPI_TalonSRX turnMotor;
    SpeedControllerGroup shooters;
    public CANEncoder encoder, encoder2;
    CANPIDController speedPID;
    double kP, kI, kD;
    
    public ShooterSubsystem()
    {
        shooter1 = new CANSparkMax(5, MotorType.kBrushless);
        shooter2 = new CANSparkMax(6, MotorType.kBrushless);
        shooters = new SpeedControllerGroup(shooter1, shooter2);

        turnMotor = new WPI_TalonSRX(0);

        kP = 5e-5; 
        kI = 1e-6;
        kD = 0;

        shooter1.restoreFactoryDefaults();
        shooter2.restoreFactoryDefaults();

        shooter1.follow(shooter2, true);

        encoder = shooter2.getEncoder();
        encoder2 = shooter1.getEncoder();

        speedPID = new CANPIDController(shooter2);

        speedPID.setP(kP);
        speedPID.setI(kI);
        speedPID.setD(kD);
        //speedPID.setIMaxAccum(1.5, 0);
        //speedPID.setOutputRange(-0.75, 0.75);
        //speedPID.setFeedbackDevice(encoder);

        //encoder.setPosition(0);

        //encoder.setVelocityConversionFactor(42);

    }

    public void setSpeed(double speed)
    {
        shooter2.set(speed);
    }

    public void setVelocity(double velocity){
        speedPID.setReference(velocity, ControlType.kVelocity);
    }

    public void setTurnSpeed(double speed){
        turnMotor.set(speed);
    }

}
