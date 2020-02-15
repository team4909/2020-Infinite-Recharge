package frc.team4909.robot.subsystems.intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase{
    
    CANSparkMax intakeMotor, deployMotor;
    
    public IntakeSubsystem(){
        intakeMotor = new CANSparkMax(12, MotorType.kBrushless);
        intakeMotor.setInverted(true);
        deployMotor = new CANSparkMax(13, MotorType.kBrushless);
    }

    public void setSpeed(double speed){
        intakeMotor.set(speed);
    }
}