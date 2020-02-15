package frc.team4909.robot.subsystems.indexer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class IndexerSubsystem extends SubsystemBase{
    
    CANSparkMax lowerMotor, upperMotor;
    DigitalInput upperPhotoelectric, lowerPhotoelectric;
    int ballTimer;
    final int timeForBall = 35;
    
    public IndexerSubsystem(){
        lowerMotor = new CANSparkMax(7, MotorType.kBrushless);
        upperMotor = new CANSparkMax(8, MotorType.kBrushless);

        lowerMotor.setIdleMode(IdleMode.kBrake);
        upperMotor.setIdleMode(IdleMode.kBrake);

        upperPhotoelectric = new DigitalInput(0);
        lowerPhotoelectric = new DigitalInput(1);
    }

    public boolean hasBallUpper(){
        //System.out.println(upperPhotoelectric.get());
        return !upperPhotoelectric.get();
    }

    public boolean hasBallLower(){
        return !lowerPhotoelectric.get();
    }

    public void 
    setSpeed(double speed){
        upperMotor.set(speed);
        lowerMotor.set(speed);
    }

    public void setSmartSpeed(double speed){
        if(!hasBallUpper()){
            if(hasBallLower()){
                setSpeed(speed);
            }else{setSpeed(0);}
        }else{setSpeed(0);}


        // if(!hasBallUpper()){
        //     upperMotor.set(speed);
        // }else{upperMotor.set(-speed*0.15);}
        // if(ballTimer < timeForBall){
        //     lowerMotor.set(speed);
        //     if(hasBallLower()){
        //         ballTimer++;
        //     }
        // }else{lowerMotor.set(0);}
        
    }

    @Override
    public void periodic() {

        
        SmartDashboard.putNumber("Ball Timer", ballTimer);

        if(!hasBallLower()){
        ballTimer = 0;
        }
    }
}
