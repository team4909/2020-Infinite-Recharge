package frc.team4909.robot.subsystems.shooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team4909.robot.RobotConstants;

public class TurretSubsystem extends SubsystemBase{

    public DigitalInput endPoint;
    private double lastSpeed;

    CANSparkMax turnMotor;

    public TurretSubsystem() {
        turnMotor = new CANSparkMax(10, MotorType.kBrushless);
        turnMotor.setIdleMode(IdleMode.kBrake);
        SmartDashboard.putBoolean("induction", true);
        endPoint = new DigitalInput(2);
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




}

