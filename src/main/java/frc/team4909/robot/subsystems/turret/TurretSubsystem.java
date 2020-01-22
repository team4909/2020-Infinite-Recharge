package frc.team4909.robot.subsystems.turret;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase{
    Spark turnMotor;
    double lastError;

    public TurretSubsystem(){
        super();
        turnMotor = new Spark(0);
    }

    public void setSpeed(double speed){
        turnMotor.set(speed);
    }
}