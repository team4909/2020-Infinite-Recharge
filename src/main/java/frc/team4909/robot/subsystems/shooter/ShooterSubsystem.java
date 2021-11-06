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

    WPI_TalonFX shooterRight, shooterLeft;
    double speed = -500000;
    public boolean isAtSpeed = false;
    public boolean isAligned = false;
    public boolean isReving = false;
    public double shooterSetSpeed = 17000;

    
    CANSparkMax turnMotor;

    public double rawspeed, kP, kD, kI, kF;

    public ShooterSubsystem() {

        shooterRight = new WPI_TalonFX(5);
        shooterLeft = new WPI_TalonFX(6); //leader

        shooterRight.configFactoryDefault();
        shooterLeft.configFactoryDefault();

        shooterLeft.setInverted(true);
        shooterRight.follow(shooterLeft);
        

        shooterLeft.setNeutralMode(NeutralMode.Coast);
        shooterRight.setNeutralMode(NeutralMode.Coast);

        shooterLeft.config_kP(0, RobotConstants.shooterkP);
        shooterLeft.config_kI(0, RobotConstants.shooterkI);
        shooterLeft.config_kD(0, RobotConstants.shooterkD);
        shooterLeft.config_kF(0, RobotConstants.shooterkF);

        // shooterRight.configPeakOutputReverse(0);


        turnMotor = new CANSparkMax(10, MotorType.kBrushless);
        turnMotor.setIdleMode(IdleMode.kBrake);

    }

    public void zeroTurret() {
        turnMotor.getEncoder().setPosition(0);
    }


    @Override
    public void periodic() {
        SmartDashboard.putNumber("Speed", getRPM());
        SmartDashboard.putBoolean("At Speed", isAtSpeed);
        SmartDashboard.putNumber("Shooter/Speed", shooterLeft.getSelectedSensorVelocity());
        SmartDashboard.putNumber("Shooter/Speed RPM", getRPM());
        SmartDashboard.putNumber("Current Speed Setpoint", speed);
        SmartDashboard.putNumber("Shooter Speed Output", shooterLeft.get());
        SmartDashboard.putNumber("Shooter/error", shooterLeft.getClosedLoopError());
        SmartDashboard.putNumber("Shooter/error expected", shooterLeft.getClosedLoopTarget()-getRPM() );

        SmartDashboard.putNumber("Shooter/target", shooterLeft.getClosedLoopTarget());

        

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
        SmartDashboard.putNumber("shooter2 current", shooterLeft.getSupplyCurrent());
        SmartDashboard.putBoolean("Shooter Reving", isReving);

    }

    public void setSpeed(double speed) {
        // shooter1.set(speed);
        shooterLeft.set(ControlMode.PercentOutput, speed);
        shooterRight.set(ControlMode.PercentOutput, speed);
        speed = 0;
    }

    public void setVelocity(double velocityInRPM) {
        if (velocityInRPM == 0) {
            setSpeed(0);
        }
        shooterLeft.set(ControlMode.Velocity, (velocityInRPM / 600.0) * 2048.0);
        speed = (velocityInRPM / 600.0) * 2048.0;
    }

    //NOT RPM DUKMMY
    public double getRPM() {
        // return Util.map(shooter2.getSelectedSensorVelocity(), 0.0, 21777.06, 0.0, 6380.0);
        return 600*((double)shooterLeft.getSelectedSensorVelocity() / 2048.0);
    }

}
