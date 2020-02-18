package frc.team4909.robot.subsystems.climber;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team4909.robot.RobotConstants;

public class ClimberSubsystem extends SubsystemBase {
    CANSparkMax climberMotor;
    CANEncoder climbEncoder;
    CANPIDController climberPID;
    PWM hook;

    public ClimberSubsystem() {
        climberMotor = new CANSparkMax(0, MotorType.kBrushless);
        hook = new PWM(1);

        climbEncoder = new CANEncoder(climberMotor);
        climberPID = new CANPIDController(climberMotor);

        climberPID.setP(RobotConstants.climberkP);
        climberPID.setI(RobotConstants.climberkI);
        climberPID.setD(RobotConstants.climberkD);
        climberPID.setFF(RobotConstants.climberkF);

        climberMotor.restoreFactoryDefaults();

    }

    public void setHookAngle(int speed){
        hook.setRaw(speed);
    }

    public void setSpeed(double speed)
    {
        climberMotor.set(speed);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("position", climbEncoder.getPosition());
    }
    
}
