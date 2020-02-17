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
    SpeedControllerGroup shooter;
    CANEncoder encoder;
    CANPIDController speedPID;
    double speed = 0;
    public boolean isAtSpeed;
    public DigitalInput endPoint;
    private double speedTurret;
    private double lastSpeedTurret;
    private double lastSpeed;

    CANSparkMax turnMotor;
    
    public ShooterSubsystem()
    {
        shooter1 = new CANSparkMax(5, MotorType.kBrushless);
        shooter2 = new CANSparkMax(6, MotorType.kBrushless);
        shooter = new SpeedControllerGroup(shooter1, shooter2);

        turnMotor = new CANSparkMax(10, MotorType.kBrushless);
        turnMotor.setIdleMode(IdleMode.kBrake);
        SmartDashboard.putBoolean("induction", true);
        endPoint = new DigitalInput(2);

        shooter1.restoreFactoryDefaults();
        shooter2.restoreFactoryDefaults();

        shooter1.follow(shooter2, true);

        encoder = shooter2.getEncoder();

        speedPID = new CANPIDController(shooter2);

        speedPID.setP(RobotConstants.shooterkP);
        speedPID.setI(RobotConstants.shooterkI);
        speedPID.setD(RobotConstants.shooterkD);
        speedPID.setFF(0.0001);
        // speedPID.setIMaxAccum(10, 0);

        //speedPID.setReference(0, ControlType.kVelocity);
    }

    public void zeroTurret(){
        turnMotor.getEncoder().setPosition(0);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Speed", encoder.getVelocity());
        SmartDashboard.putBoolean("At Speed", isAtSpeed);
        if(Math.abs(speed = 0-encoder.getVelocity())<100){
            isAtSpeed = true;
        }
        else {
            isAtSpeed = false;
        }

    }

    public void setSpeed(double speed){
        shooter1.set(speed);
        shooter2.set(speed);
        speed = 0;
    }

    public void setVelocity(double velocity){
        speedPID.setReference(velocity, ControlType.kVelocity);
        speed = velocity;
    }

    public void setTurnSpeed(double speed){

        // if at endpoint
        if (!endPoint.get() == true) {

            // negative motion caused us to hit limit
            if (lastSpeed < 0){
                if(speed < 0){
                    speed = 0;
                } 
            } else if (lastSpeed > 0){
                if(speed > 0){
                    speed = 0;
                } 
            }
        }
        else { //if the metal is *NOT* above the sensor
            if (speed != 0){
                lastSpeed = speed;
            }
        }
        SmartDashboard.putNumber("turret speed", speed);
        SmartDashboard.putNumber("last turret speed", lastSpeed);
    
        turnMotor.set(speed * RobotConstants.turretSpeedMultiplier);

    }

    public double getRPM(){
        return encoder.getVelocity();
    }

}
