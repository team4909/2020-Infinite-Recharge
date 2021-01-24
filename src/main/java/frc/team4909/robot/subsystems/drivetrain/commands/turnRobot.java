package frc.team4909.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.*;
import com.kauailabs.navx.frc.*;
import edu.wpi.first.wpilibj.*;
import frc.team4909.robot.subsystems.drivetrain.*;

public class turnRobot extends CommandBase{
    //error is how much we have to turn
    //gain is the speed to turn at based on error
    public AHRS navX = new AHRS(SPI.Port.kMXP);
    public double error;
    public double gain;

    public turnRobot(double degrees, DriveTrainSubsystem subsystem){
        super();
        addRequirements(subsystem);
        error = degrees - navX.getAngle();
        gain = 0.3; //TODO:check value
        subsystem.arcadeDrive(speed, error * gain);

    }
}