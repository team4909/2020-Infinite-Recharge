package frc.team4909.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

/**
 * TrapDrive
 */
public class TrapDrive extends CommandBase{

    public double inches;
    public double rUpDistance;
    public double rDownDistance;
    public double cruiseDistance;
    public double startingDistance;
    public double currentDistance;

    public double rUpAccel;
    public double rDownAccel;


    public double startTime;
    public double currentTime;
    public double rampUpTime;
    public double rampDownTime;



    public TrapDrive(double distance){

        this.inches = distance;
    }

    @Override
    public void initialize() {

        startingDistance = Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition() * RobotConstants.TICKS_PER_INCH;
        inches += startingDistance;
        rUpDistance = RobotConstants.RAMP_UP_TIME * RobotConstants.MAX_VELOCITY * 0.5;
        rDownDistance = RobotConstants.RAMP_DOWN_TIME * RobotConstants.MAX_VELOCITY * 0.5;
        cruiseDistance = inches - (rUpDistance + rDownDistance);
        

        rUpAccel = RobotConstants.MAX_VELOCITY / RobotConstants.RAMP_UP_TIME;
        rDownAccel = RobotConstants.MAX_VELOCITY / RobotConstants.RAMP_DOWN_TIME;
        
        startTime = Timer.getFPGATimestamp();
        rampUpTime = startTime + RobotConstants.RAMP_UP_TIME;
        //rampDownTime = startTime + RobotConstants.RAMP_DOWN_TIME;

        

        SmartDashboard.putBoolean("TRAP HAS ENDED", false);

        SmartDashboard.putBoolean("TRAP - RAMP UP", false);
        SmartDashboard.putBoolean("TRAP - RAMP DOWN", false);
        SmartDashboard.putBoolean("TRAP - CRUISE", false);

        SmartDashboard.putNumber("TRAP - TARGET POSITION", inches);

    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        currentDistance = Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition() * RobotConstants.TICKS_PER_INCH;
        
        currentDistance -= startingDistance;
        currentTime = Timer.getFPGATimestamp() - startTime; //milliseconds to seconds

        double cruiseTime;
        System.out.println("inches" + inches);

        
        SmartDashboard.putNumber("TRAP - POSITION", currentDistance);
        
        SmartDashboard.putNumber("TRAP - CURRSNT TIME", currentTime);

        if(currentDistance <= rUpDistance) {
            Robot.drivetrainsubsystem.tankDrive(rUpAccel * currentTime, rUpAccel * currentTime);
            SmartDashboard.putBoolean("TRAP - RAMP UP", true);

        } else if( currentDistance <= rUpDistance + cruiseDistance && currentDistance > rUpDistance) {
            Robot.drivetrainsubsystem.tankDrive(RobotConstants.MAX_VELOCITY, RobotConstants.MAX_VELOCITY);
            SmartDashboard.putBoolean("TRAP - RAMP UP", false);
            SmartDashboard.putBoolean("TRAP - CRUISE", true);
            cruiseTime = currentTime;



        } else if(currentDistance <= inches && currentDistance > rUpDistance + cruiseDistance) {
            // y= -(acc) (time isolated) + MAx Vell
            double downVelocity = RobotConstants.MAX_VELOCITY - ( rDownAccel * currentTime);
            Robot.drivetrainsubsystem.tankDrive(downVelocity, downVelocity);
            SmartDashboard.putBoolean("TRAP - CRUISE", false);
            SmartDashboard.putBoolean("TRAP - RAMP DOWN", true);

        }

        super.execute();
    }

    @Override
    public boolean isFinished() {
        // TODO Auto-generated method stub
        //if output is less than or equal to .1
        SmartDashboard.putBoolean("TRAP - CRUISE", false);

        if(currentDistance >= inches){
             return true;
        }

        return false;
    }

    @Override
    public void end(boolean interrupted) {

        Robot.drivetrainsubsystem.tankDrive(0, 0);
        SmartDashboard.putBoolean("TRAP HAS ENDED", true);
    }

}