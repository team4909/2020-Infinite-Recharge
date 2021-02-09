package frc.team4909.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.*;
import com.kauailabs.navx.frc.*;
import edu.wpi.first.wpilibj.*;
import frc.team4909.robot.subsystems.drivetrain.*;
import frc.team4909.robot.*;

public class TurnRobot extends CommandBase{
    //error is how much we have to turn
    //gain is the speed to turn at based on error
    public double error;
    public double targetState;
    public double GAIN = 0.3; //TODO check value | Gain is the speed at which the robot will spin
    public double SPEED = 0.5; //TODO check value
    public double degrees;

    public TurnRobot(double degrees){
        super();
        addRequirements(Robot.drivetrainsubsystem);
        this.degrees = degrees;
    }

    @Override
    public void initialize() {
        targetState = degrees + Robot.navX.getAngle(); //Gets the abosolute position | TODO Find what get angle really means / gives back
        System.out.println(Robot.navX.getAngle());
        error = targetState - Robot.navX.getAngle(); //Gets the absolute error
        Robot.drivetrainsubsystem.arcadeDrive(SPEED, error * GAIN);
    }


    
    @Override
    public void execute(){
        error = targetState - Robot.navX.getAngle();
        Robot.drivetrainsubsystem.arcadeDrive(SPEED, error * GAIN);
    }

    @Override
    public boolean isFinished() {
        if(error < 5){ //TODO test value
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted){
        Robot.drivetrainsubsystem.arcadeDrive(0, 0);
    }
    
}