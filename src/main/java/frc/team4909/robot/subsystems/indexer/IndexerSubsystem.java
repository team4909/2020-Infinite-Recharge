package frc.team4909.robot.subsystems.indexer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexerSubsystem extends SubsystemBase{
    
    CANSparkMax lowerMotor, upperMotor, sorterMotor;
    
    public IndexerSubsystem(){
        lowerMotor = new CANSparkMax(7, MotorType.kBrushless);
        upperMotor = new CANSparkMax(8, MotorType.kBrushless);
        sorterMotor = new CANSparkMax(11, MotorType.kBrushless);
    }

    public void setSpeed(double speed){
        lowerMotor.set(speed);
        upperMotor.set(speed);
    }

    public void sorterOn(double speed){
        sorterMotor.set(speed);
    }
}
