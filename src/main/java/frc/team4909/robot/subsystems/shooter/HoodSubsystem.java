package frc.team4909.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team4909.robot.RobotConstants;

public class HoodSubsystem extends SubsystemBase{

    WPI_TalonSRX hoodControl;
    int hoodPos;


    public HoodSubsystem(){        
        hoodControl = new WPI_TalonSRX(9);
        hoodControl.setNeutralMode(NeutralMode.Brake);

        
        hoodControl.configFactoryDefault();

        hoodControl.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        hoodControl.config_kP(0, RobotConstants.hoodkP);
        hoodControl.config_kI(0, RobotConstants.hoodkI);
        hoodControl.config_kD(0, RobotConstants.hoodkD);

        hoodControl.setSelectedSensorPosition(0);
        hoodControl.setInverted(true);

    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Hood Position", hoodControl.getSelectedSensorPosition());
        hoodControl.set(ControlMode.Position, hoodPos);
    }
    
    public void setHoodPosition(int pos){
        hoodPos = pos;
    }

    public void moveHood(int pos){
        hoodPos += pos;
    }

    public void zeroHood(){
        hoodPos = 0;
        hoodControl.setSelectedSensorPosition(0);
    }

}