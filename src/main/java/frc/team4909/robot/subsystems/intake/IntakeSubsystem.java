package frc.team4909.robot.subsystems.intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase{
    
    CANSparkMax intakeMotor;
    
    public IntakeSubsystem(){
        intakeMotor = new CANSparkMax(0, MotorType.kBrushless);
    }

    public void setSpeed(double speed){
        intakeMotor.set(speed);
    }
}