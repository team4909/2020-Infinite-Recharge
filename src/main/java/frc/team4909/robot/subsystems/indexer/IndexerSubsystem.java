package frc.team4909.robot.subsystems.indexer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexerSubsystem extends SubsystemBase{
    
    CANSparkMax lowerMotor, upperMotor;
    
    public IndexerSubsystem(){
        lowerMotor = new CANSparkMax(7, MotorType.kBrushless);
        upperMotor = new CANSparkMax(8, MotorType.kBrushless);
    }

    public void setSpeed(double speed){
        lowerMotor.set(speed);
        upperMotor.set(speed);
    }
}