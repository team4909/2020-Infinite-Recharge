package frc.team4909.robot.subsystems.intake;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team4909.robot.RobotConstants;

public class IntakeSubsystem extends SubsystemBase{
    
    CANSparkMax intakeMotor, deployMotor;
    CANPIDController deployPID;
    
    public IntakeSubsystem(){
        intakeMotor = new CANSparkMax(12, MotorType.kBrushless);
        intakeMotor.setInverted(true);

        deployMotor = new CANSparkMax(13, MotorType.kBrushless);

        deployPID = new CANPIDController(deployMotor);
        deployPID.setP(RobotConstants.intakeP);
        deployPID.setI(RobotConstants.intakeI);
        deployPID.setD(RobotConstants.intakeD);
        deployPID.setFF(RobotConstants.intakeFF);
    }

    public void setSpeed(double speed){
        intakeMotor.set(speed);
    }

    public void deployIntake(int pos){
        deployPID.setReference(pos, ControlType.kPosition);
    }
}