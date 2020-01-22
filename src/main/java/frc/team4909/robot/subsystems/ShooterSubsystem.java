package frc.team4909.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    CANSparkMax shooter1;
    CANSparkMax shooter2;
    SpeedControllerGroup shooters;
    public CANEncoder encoder;
    CANPIDController speedPID;
    double kP, kI, kD;
    
    public ShooterSubsystem()
    {
        shooter1 = new CANSparkMax(7, MotorType.kBrushless);
        //shooter2 = new CANSparkMax(8, MotorType.kBrushless);
        //shooters = new SpeedControllerGroup(shooter1, shooter2);

        kP = 1;
        kI = 0;
        kD = 0;

        shooter1.restoreFactoryDefaults();
        //shooter2.restoreFactoryDefaults();

        //shooter2.setInverted(true);

        //shooter2.follow(shooter1);

        encoder = new CANEncoder(shooter1);

        speedPID = new CANPIDController(shooter1);

        speedPID.setP(kP);
        speedPID.setD(kD);
        speedPID.setIMaxAccum(1.5, 0);
        speedPID.setOutputRange(-0.75, 0.75);

        encoder.setPosition(0);

        encoder.setVelocityConversionFactor(42);

    }

    public void setSpeed(double speed)
    {
        shooter1.set(speed);
    }

    public void setVelocity(double velocity){
        speedPID.setReference(velocity, ControlType.kVelocity);
    }

}
