package frc.team4909.robot.subsystems.climber;

import java.util.concurrent.atomic.DoubleAdder;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team4909.robot.RobotConstants;

public class ClimberSubsystem extends SubsystemBase {
    CANSparkMax climberMotor1, climberMotor2, hookMotor;
    CANEncoder climbEncoder1, climbEncoder2, hookEncoder;
    CANPIDController climberPID, hookPID;
    PWM rachet;
    double climbHoldPos = 0;

    public ClimberSubsystem() {

        climberMotor1 = new CANSparkMax(14, MotorType.kBrushless);
        climberMotor2 = new CANSparkMax(15, MotorType.kBrushless);
        rachet = new PWM(1);
        hookMotor = new CANSparkMax(16, MotorType.kBrushless);

        climberMotor1.restoreFactoryDefaults();
        climberMotor2.restoreFactoryDefaults();

        climberMotor1.setIdleMode(IdleMode.kBrake);
        climberMotor2.setIdleMode(IdleMode.kBrake);

        climberMotor2.follow(climberMotor1);

        climbEncoder1 = new CANEncoder(climberMotor1);
        hookEncoder = new CANEncoder(hookMotor);

        // climberPID = new CANPIDController(climberMotor1);
        hookPID = new CANPIDController(hookMotor);

        // climberPID.setP(RobotConstants.climberkP);
        // climberPID.setI(RobotConstants.climberkI);
        // climberPID.setD(RobotConstants.climberkD);
        // climberPID.setFF(RobotConstants.climberkF);

        hookPID.setP(RobotConstants.hookkP);
        hookPID.setP(RobotConstants.hookkI);
        hookPID.setP(RobotConstants.hookkD);
        hookPID.setP(RobotConstants.hookkF);

        // climbEncoder1.setPosition(0);
    }

    public void setRatchetSpeed(int speed){
        rachet.setRaw(speed);
    }

    public void setHookSpeed(double speed){
        hookMotor.set(speed);
    }

    public void setHookPosition(double Hpos){
        hookPID.setReference(Hpos, ControlType.kPosition);

    }

    public void setClimberPosition(double pos){
        // climberPID.setReference(pos, ControlType.kPosition);

    }

    public void setClimberSpeed(double speed){
        // climbHoldPos += speed*1;
        climberMotor1.set(speed);
        System.out.println("Elevator Moving" + speed);
    }

    public double getClimbPos(){
        return climbEncoder1.getPosition();
    }

    public void resetClimbEncoder(){
        climbEncoder1.setPosition(0);
        climbHoldPos=0;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("climber position", climbEncoder1.getPosition());
        SmartDashboard.putNumber("Hook position", hookEncoder.getPosition());
        // setClimberPosition(climbHoldPos);
    }
    
}
