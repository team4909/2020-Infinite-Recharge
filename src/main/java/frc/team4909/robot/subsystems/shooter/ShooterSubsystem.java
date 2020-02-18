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
    CANEncoder encoder;
    CANPIDController speedPID;
    double speed = 0;
    public boolean isAtSpeed;

    CANSparkMax turnMotor;

    public double rawspeed, kP, kD, kI, kF;



    public ShooterSubsystem()
    {

        shooter1 = new CANSparkMax(5, MotorType.kBrushless);
        shooter2 = new CANSparkMax(6, MotorType.kBrushless);

        shooter1.restoreFactoryDefaults();
        shooter2.restoreFactoryDefaults();

        shooter1.follow(shooter2, true);

        shooter1.setIdleMode(IdleMode.kCoast);
        shooter2.setIdleMode(IdleMode.kCoast);

        turnMotor = new CANSparkMax(10, MotorType.kBrushless);
        turnMotor.setIdleMode(IdleMode.kBrake);


        encoder = shooter2.getEncoder();

        speedPID = new CANPIDController(shooter2);
        speedPID.setOutputRange(0.2, 1);

        speedPID.setP(RobotConstants.shooterkP);
        speedPID.setI(0);
        speedPID.setD(0);
        // speedPID.setFF(0.09);
        speedPID.setFF(0);

        SmartDashboard.putNumber("kF", 0);
        SmartDashboard.putNumber("kI", 0);
        SmartDashboard.putNumber("kD", 0);
        SmartDashboard.putNumber("kP", 0);
        SmartDashboard.putNumber("% Speed", 0);

    }


    public void zeroTurret(){
        turnMotor.getEncoder().setPosition(0);
    }

    @Override
    public void periodic(){

        // kP = SmartDashboard.getNumber("kP", 0.005);
        // kD = SmartDashboard.getNumber("kI", 0);
        // speed = SmartDashboard.getNumber("% Speed", 0);
        // kF = SmartDashboard.getNumber("kF", 0);
        // speedPID.setP(kP);
        // speedPID.setI(kI);
        // speedPID.setD(kD);
        // SmartDashboard.putNumber("Error", speedPID.get);
        SmartDashboard.putNumber("Curr P", speedPID.getP());
        SmartDashboard.putNumber("Speed", encoder.getVelocity());
        SmartDashboard.putBoolean("At Speed", isAtSpeed);
        if(Math.abs(speed-encoder.getVelocity())<100.00){
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
        // speedPID.setFF(velocity/5000);
        speedPID.setReference(velocity, ControlType.kVelocity);
        speed = velocity;
    }

    

    public double getRPM(){
        return encoder.getVelocity();
    }

}
