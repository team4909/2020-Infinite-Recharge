package frc.team4909.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team4909.robot.RobotConstants;

public class IntakeSubsystem extends SubsystemBase{
    
    CANSparkMax deployMotor;
    WPI_TalonSRX intakeMotor;
    CANPIDController deployPID;
    CANEncoder deployEncoder;
    public boolean intakeDeployed = false;
    double holdingPos;
    
    public IntakeSubsystem(){
        intakeMotor = new WPI_TalonSRX(12);
        intakeMotor.enableVoltageCompensation(true);
        intakeMotor.setInverted(true);
        intakeMotor.configContinuousCurrentLimit(20);
        intakeMotor.configPeakCurrentLimit(35, 1000);
        intakeMotor.enableCurrentLimit(true);

        deployMotor = new CANSparkMax(13, MotorType.kBrushless);
        deployMotor.restoreFactoryDefaults();

        deployPID = new CANPIDController(deployMotor);
        deployPID.setP(RobotConstants.intakekP);
        deployPID.setI(RobotConstants.intakekI);
        deployPID.setD(RobotConstants.intakekD);
        deployPID.setFF(RobotConstants.intakekF);
        deployPID.setOutputRange(-0.5, 0.5);

        deployEncoder = new CANEncoder(deployMotor);
        deployEncoder.setPosition(0);
    }

    public void setSpeed(double speed){
        intakeMotor.set(speed);
    }

    public void deployIntake(double pos){
        deployPID.setReference(pos, ControlType.kPosition);
    }

    public void zeroDeploy(){
        deployEncoder.setPosition(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Intake Pos", deployEncoder.getPosition());
        // SmartDashboard.putNumber("Intake Current", intakeMotor.);
        //deployPID.setReference(holdingPos, ControlType.kPosition);
    }
}