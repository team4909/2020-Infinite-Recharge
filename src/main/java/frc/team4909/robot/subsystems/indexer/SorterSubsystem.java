package frc.team4909.robot.subsystems.indexer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SorterSubsystem extends SubsystemBase{
    
    CANSparkMax sorterMotor;

    public SorterSubsystem(){
        sorterMotor = new CANSparkMax(14, MotorType.kBrushless);
        sorterMotor.setIdleMode(IdleMode.kBrake);
    }
    public void sorterOn(double speed){
        sorterMotor.set(speed);
    }
}