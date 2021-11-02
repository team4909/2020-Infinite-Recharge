package frc.team4909.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.subsystems.shooter.commands.ZeroHoodInit;

public class HoodSubsystem extends SubsystemBase{

    public WPI_TalonSRX hoodControl;
    int hoodPos;
    public boolean zeroing = false;

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
        hoodControl.configForwardSoftLimitThreshold(191, 10);

    }

    
/**
 * map a number from one range to another
 * @param  {num} value   the value to be mapped
 * @param  {num} old_min the minimum of value
 * @param  {num} old_max the maximum of value
 * @param  {num} new_min the new minimum value
 * @param  {num} new_max the new maximum value
 * @return {num}         the value remaped on the range [new_min new_max]
 */
public double map(double value, double old_min, double old_max, double new_min, double new_max) {
	return (value - old_min) / (old_max - old_min) * (new_max - new_min) + new_min;
}

    public double getAngle()
    {
        return map(hoodControl.getSelectedSensorPosition(), 0, 214, 23, 74);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Hood Position", getAngle()  );
        SmartDashboard.putNumber("Hood Setpoint", hoodPos);
        SmartDashboard.putNumber("Hood Current", getHoodCurrent());
        if (!zeroing){
            // System.out.println(getAngle());
            // if(getAngle() < 72.5 && getAngle() >= 23){
                hoodControl.set(ControlMode.Position, hoodPos);
            // } else {
                // setHoodAngle(23);
            // }

        }
        
    }
    
    public void setHoodPosition(int pos){
        hoodPos = pos;
    }

    public void setHoodAngle(int angle){
        if(angle < 23){
            angle = 23;
        }
        hoodPos = (int) map(angle, 23, 74, 0, 214);
    }

    public void setSpeed(double speed){
        hoodControl.set(speed);
    }

    public double getHoodCurrent(){
        return hoodControl.getStatorCurrent();
    }

    public void moveHood(double pos){
        // System.out.println(pos);
        if (getAngle() > 72.5) {
            if (pos > 0) {
                // do nothing
            } else {
                hoodPos += pos;
            }
        } else if (getAngle() < 23.5) {
            if (pos < 0) {
                // do nothing
            } else {
                hoodPos += pos;
            }
        } else {
            hoodPos += pos;
        }
        
    }

    public void zeroHood(){
        hoodControl.setSelectedSensorPosition(0);
        hoodPos = 0;
    }

}