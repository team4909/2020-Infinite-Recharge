package frc.team4909.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team4909.robot.RobotConstants;

public class ShooterSubsystem extends SubsystemBase {

    WPI_TalonFX shooter1, shooter2;
    double speed = 0;
    public boolean isAtSpeed;

    CANSparkMax turnMotor;

    public double rawspeed, kP, kD, kI, kF;



    public ShooterSubsystem()
    {

        shooter1 = new WPI_TalonFX(6);
        shooter2 = new WPI_TalonFX(5);

        shooter1.configFactoryDefault();
        shooter2.configFactoryDefault();

        shooter1.follow(shooter2);
        shooter1.setInverted(true);

        shooter1.setNeutralMode(NeutralMode.Coast);
        shooter2.setNeutralMode(NeutralMode.Coast);

        turnMotor = new CANSparkMax(10, MotorType.kBrushless);
        turnMotor.setIdleMode(IdleMode.kBrake);

        shooter2.config_kP(0, RobotConstants.shooterkP);
        shooter2.config_kI(0, RobotConstants.shooterkI);
        shooter2.config_kD(0, RobotConstants.shooterkD);
        shooter2.config_kF(0, RobotConstants.shooterkF);

    }


    public void zeroTurret(){
        turnMotor.getEncoder().setPosition(0);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Speed", shooter2.getSelectedSensorVelocity());
        SmartDashboard.putBoolean("At Speed", isAtSpeed);
        if(Math.abs(speed-getRPM())<100.00){
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
        shooter2.set(ControlMode.Velocity, velocity);
        speed = velocity;
    }

    

    public double getRPM(){
        return shooter2.getSelectedSensorVelocity();
    }

}
