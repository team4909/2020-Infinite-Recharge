package frc.team4909.robot.subsystems.intake;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team4909.robot.RobotConstants;

public class IntakeSubsystem extends SubsystemBase{
    
    CANSparkMax intakeMotor, deployMotor;
    CANPIDController deployPID;
    CANEncoder deployEncoder;
    public boolean intakeDeployed = false;
    
    public IntakeSubsystem(){
        intakeMotor = new CANSparkMax(12, MotorType.kBrushless);
        intakeMotor.setInverted(true);

        deployMotor = new CANSparkMax(13, MotorType.kBrushless);
        deployMotor.restoreFactoryDefaults();

        deployPID = new CANPIDController(deployMotor);
        deployPID.setP(RobotConstants.intakekP);
        deployPID.setI(RobotConstants.intakekI);
        deployPID.setD(RobotConstants.intakekD);
        deployPID.setFF(RobotConstants.intakekF);
        deployPID.setOutputRange(-0.75, 0.75);

        deployEncoder = new CANEncoder(deployMotor);
        deployEncoder.setPosition(0);
    }

    public void setSpeed(double speed){
        intakeMotor.set(speed);
    }

    public void deployIntake(int pos){
        deployPID.setReference(pos, ControlType.kPosition);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Intake Pos", deployEncoder.getPosition());
    }
}