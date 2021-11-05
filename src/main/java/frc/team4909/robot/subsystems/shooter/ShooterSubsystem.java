package frc.team4909.robot.subsystems.shooter;

import java.util.Map;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.music.Orchestra;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.operator.controllers.FlightStick;
import frc.team4909.robot.util.Util;

public class ShooterSubsystem extends SubsystemBase {

    WPI_TalonFX shooter1, shooter2;
    double speed = -500000;
    public boolean isAtSpeed = false;
    public boolean isAligned = false;
    public boolean isReving = false;
    public double shooterSetSpeed = 17000;
    Orchestra orchestra = new Orchestra();
    
    CANSparkMax turnMotor;

    public double rawspeed, kP, kD, kI, kF;

    public ShooterSubsystem() {

        shooter1 = new WPI_TalonFX(6);
        shooter2 = new WPI_TalonFX(5);

        shooter1.configFactoryDefault();
        shooter2.configFactoryDefault();

        shooter1.follow(shooter2);
        shooter1.setInverted(true);

        shooter1.setNeutralMode(NeutralMode.Coast);
        shooter2.setNeutralMode(NeutralMode.Coast);

        turnMotor = new CANSparkMax(10, MotorType.kBrushless);
        turnMotor.setIdleMode(IdleMode.kBrake);

        shooter2.config_kP(0, RobotConstants.shooterkP);
        shooter2.config_kI(0, RobotConstants.shooterkI);
        shooter2.config_kD(0, RobotConstants.shooterkD);
        shooter2.config_kF(0, RobotConstants.shooterkF);

        shooter2.configPeakOutputReverse(0);

        orchestra.addInstrument(shooter1);

    }

    public void zeroTurret() {
        turnMotor.getEncoder().setPosition(0);
    }


    @Override
    public void periodic() {
        SmartDashboard.putNumber("Speed", getRPM());
        SmartDashboard.putBoolean("At Speed", isAtSpeed);
        SmartDashboard.putNumber("Current Speed Setpoint", speed);
        SmartDashboard.putNumber("Shooter Speed Output", shooter2.get());
        if (Math.abs(speed - getRPM()) < 250.00) {
            isAtSpeed = true;
        } else {
            isAtSpeed = false;
        }

        // if(Robot.manipulatorGamepad.getThresholdAxis(FlightStick.Slider) < -0.8){
        //     Robot.shootersubsystem.shooterSetSpeed = 22000;
        // }else if(Robot.manipulatorGamepad.getThresholdAxis(FlightStick.Slider) > 0.8){
        //     Robot.shootersubsystem.shooterSetSpeed = 17000;
        // }

        // SmartDashboard.putNumber("shooter1 current", shooter1.getSupplyCurrent());
        SmartDashboard.putNumber("shooter2 current", shooter2.getSupplyCurrent());
        SmartDashboard.putBoolean("Shooter Reving", isReving);

    }

    public void setSpeed(double speed) {
        // shooter1.set(speed);
        shooter2.set(speed);
        speed = 0;
    }

    public void setVelocity(double velocity) {
        shooter2.set(ControlMode.Velocity, velocity);
        speed = velocity;
    }

    //NOT RPM DUKMMY
    public double getRPM() {
        // return Util.map(shooter2.getSelectedSensorVelocity(), 0.0, 21777.06, 0.0, 6380.0);
        return 600*(shooter2.getSelectedSensorVelocity() / 2048);
    }

}
