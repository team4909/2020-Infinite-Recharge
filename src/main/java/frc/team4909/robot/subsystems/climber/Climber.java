package frc.team4909.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANEncoder;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class Climber extends SubsystemBase {
    WPI_VictorSPX climbermotor;
    WPI_VictorSPX hookset;
    CANEncoder climbcoder;
    private double climberposition;
    double climberP;
    double climberI;
    double climberD;
    public Climber() {
        climbermotor = new WPI_VictorSPX(0);
        hookset = new WPI_VictorSPX(1);

        climberposition=0;
        climbermotor.configFactoryDefault();
        hookset.configFactoryDefault();
        climberP=0;
        climberI=0;
        climberD=0;

        climbcoder.setPosition(0.0);
    }

    public void setNewPosition()
    {
        climberposition = climbcoder.getPosition();
    }

    public void setHookSpeed(double speed){
        hookset.set(speed);
    }

    public void setSpeed(double speed)
    {
        climbermotor.set(speed);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("position", climbcoder.getPosition());
    }
    
}
