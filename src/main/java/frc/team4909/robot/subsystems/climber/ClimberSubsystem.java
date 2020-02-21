package frc.team4909.robot.subsystems.climber;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
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

    public ClimberSubsystem() {

        climberMotor1 = new CANSparkMax(14, MotorType.kBrushless);
        climberMotor2 = new CANSparkMax(15, MotorType.kBrushless);
        rachet = new PWM(1);
        hookMotor = new CANSparkMax(16, MotorType.kBrushless);

        climberMotor1.restoreFactoryDefaults();
        climberMotor2.restoreFactoryDefaults();

        climbEncoder1 = new CANEncoder(climberMotor1);
        climbEncoder2 = new CANEncoder(climberMotor2);
        hookEncoder = new CANEncoder(hookMotor);

        climberPID = new CANPIDController(climberMotor1);
        hookPID = new CANPIDController(hookMotor);

        climberMotor2.follow(climberMotor1);


        climberPID.setP(RobotConstants.climberkP);
        climberPID.setI(RobotConstants.climberkI);
        climberPID.setD(RobotConstants.climberkD);
        climberPID.setFF(RobotConstants.climberkF);

        hookPID.setP(RobotConstants.hookkP);
        hookPID.setP(RobotConstants.hookkI);
        hookPID.setP(RobotConstants.hookkD);
        hookPID.setP(RobotConstants.hookkF);
    }

    public void setRatchetSpeed(int speed){
        rachet.setRaw(speed);
    }

    public void setSpeed(double speed)
    {
        climberMotor1.set(speed);
    
    }

    public void setHookSpeed(double speed){
        hookMotor.set(speed);
    }

    public void setHookPosition(double Hpos){
        hookPID.setReference(Hpos, ControlType.kPosition);

    }

    public void setClimberPosition(double pos){
        climberPID.setReference(pos, ControlType.kPosition);

    }

    public double getClimbPos(){
        return climbEncoder1.getPosition();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("climber position", climbEncoder1.getPosition());
        SmartDashboard.putNumber("Hook position", hookEncoder.getPosition());
    }
    
}
