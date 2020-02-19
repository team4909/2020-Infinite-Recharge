package frc.team4909.robot.subsystems.climber;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team4909.robot.RobotConstants;

public class ClimberSubsystem extends SubsystemBase {
    public CANSparkMax climberMotor1;
    CANSparkMax climberMotor2;
    public CANSparkMax hookMotor;
    public CANEncoder climbEncoder1;
    CANEncoder climbEncoder2;
    CANEncoder hookEncoder;
    public CANPIDController climberPID1;
    CANPIDController climberPID2;
    CANPIDController hookPID;

    PWM rachet;

    public ClimberSubsystem() {

        climberMotor1 = new CANSparkMax(0, MotorType.kBrushless);
        climberMotor2 = new CANSparkMax(1, MotorType.kBrushless);
        rachet = new PWM(1);
        hookMotor = new CANSparkMax(2, MotorType.kBrushless);

        climberMotor1.restoreFactoryDefaults();
        climberMotor2.restoreFactoryDefaults();

        climbEncoder1 = new CANEncoder(climberMotor1);
        climbEncoder2 = new CANEncoder(climberMotor2);
        hookEncoder = new CANEncoder(hookMotor);

        climberPID1 = new CANPIDController(climberMotor1);
        climberPID2 = new CANPIDController(climberMotor2);
        hookPID = new CANPIDController(hookMotor);

        climberMotor2.follow(climberMotor1);


        climberPID1.setP(RobotConstants.climberkP);
        climberPID1.setI(RobotConstants.climberkI);
        climberPID1.setD(RobotConstants.climberkD);
        climberPID1.setFF(RobotConstants.climberkF);

        hookPID.setP(RobotConstants.hookkP);
        hookPID.setP(RobotConstants.hookkI);
        hookPID.setP(RobotConstants.hookkD);
        hookPID.setP(RobotConstants.hookkF);
    

    }

    public void setrachetAngle(int speed){
        rachet.setRaw(speed);
    }

    public void setSpeed(double speed)
    {
        climberMotor1.set(speed);
    
    }

    public void setHSpeed(double hSpeed){

        hookMotor.set(hSpeed);
    }

    public void setHookPosition(double Hpos){

        hookPID.setReference(Hpos, ControlType.kPosition);

    }

    public void setClimberPosition(double pos){

        climberPID1.setReference(pos, ControlType.kPosition);

    }

    public boolean TooMuchCurrent()
    {
        return climberMotor1.getOutputCurrent()>100;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("climber position", climbEncoder1.getPosition());
        SmartDashboard.putNumber("Hook position", hookEncoder.getPosition());
        SmartDashboard.putBoolean("Too Much Current?", TooMuchCurrent());
    }
    
}
