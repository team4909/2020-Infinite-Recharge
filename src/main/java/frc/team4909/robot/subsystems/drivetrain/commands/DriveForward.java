package frc.team4909.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainRamp;

public class DriveForward extends CommandBase{

    //Amount of encoder ticks in a revolution
    //Creates new PID object
    PIDController distancePID;
    double inches;
    //Sets the position for the robot when it starts; This is set because we can ensure that we will be at 0 ticks everytime we start the robot
    int startingPos;
    double currentPos;
    double targetPos;
    public int numLoops = 0;


    public DriveForward(double in){
        super();
        addRequirements(Robot.drivetrainsubsystem);
        //Creates new PID controller; See RobotConstants for values
        distancePID = new PIDController(RobotConstants.distancekP, RobotConstants.distancekI, RobotConstants.distancekD);
        //Sets the setpoint for the PID
        this.inches = in;
    }

    @Override
    public void initialize() {
        startingPos = Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition();
        targetPos = startingPos + RobotConstants.TICKS_PER_INCH * inches;
        distancePID.setSetpoint(targetPos);
        System.out.println("Begin DriveForward with error " + (targetPos - startingPos));
        //TODO Test Value and figure out units
    }

    @Override
    public void execute() {
        //This sets the current position of the robot
        currentPos = Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition(); //TODO test wether STARTING_POS needs to be added to this value
        //This takes the pid calculate method and gives it as speed to the arcade drive
        //IMPORTANT: THE NEGATIVE IS NEEDED BECAUSE ON THE JOYSTICK, UP IS NEGATIVE. THE NEGATIVE EMULATES THE STICK'S DIRECTIONS. 
        Robot.drivetrainsubsystem.arcadeDrive(-MathUtil.clamp(distancePID.calculate(currentPos), -0.4, 0.4), 0, false); //TODO we might have to set the speed or voltage of the motors to the pid not arcaed drive
        if(++numLoops == 10){
            // System.out.println("Current Position: " + currentPos);
            // System.out.println("Target Position: " + targetPos);
            // System.out.println("Drive PID Output: " + distancePID.calculate(currentPos));
            // System.out.println("             -               ");
            numLoops = 0;
        }
    }
    
    @Override
    public boolean isFinished() {

        if (Math.abs(currentPos - targetPos) < RobotConstants.TOLERANCE_INCHES){
            System.out.println("Error command in Drive Forward has been called");
            return true;
        }
        return false;

    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("No more moving translationally.");
        // Robot.drivetrainsubsystem.arcadeDrive(0, 0, false);
        // NOTE: we are using tank drive because arcade drive ramping IS VERY BAD, we will use tank drive instead.
        // @TODO fix arcade drive ramping.
        Robot.drivetrainsubsystem.tankDrive(0, 0);
        DriveTrainRamp.zeroLastValue();
        System.out.println("End command in DriveForward has ended");
    }
}