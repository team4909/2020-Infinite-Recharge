package frc.team4909.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class DriveForward extends CommandBase{

    //Amount of encoder ticks in a revolution
    //Creates new PID object
    PIDController distancePID;
    //Sets the position for the robot when it starts; This is set because we can ensure that we will be at 0 ticks everytime we start the robot
    int STARTING_POS = Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition();
    double currentPos;
    double targetPos;
    public int numLoops = 0;


    public DriveForward(int inches){
        super();
        addRequirements(Robot.drivetrainsubsystem);
        //Creates new PID controller; See RobotConstants for values
        distancePID = new PIDController(RobotConstants.distancekP, RobotConstants.distancekI, RobotConstants.distancekD);
        //Sets the setpoint for the PID
        targetPos = STARTING_POS + RobotConstants.TICKS_PER_INCH * inches;
        distancePID.setSetpoint(targetPos);
    }

    @Override
    public void execute() {
        //This sets the current position of the robot
        currentPos = Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition(); //TODO test wether STARTING_POS needs to be added to this value
        //This takes the pid calculate method and gives it as speed to the arcade drive
        //IMPORTANT: THE NEGATIVE IS NEEDED BECAUSE ON THE JOYSTICK, UP IS NEGATIVE. THE NEGATIVE EMULATES THE STICK'S DIRECTIONS. 
        Robot.drivetrainsubsystem.arcadeDrive( - MathUtil.clamp(distancePID.calculate(currentPos), 0, 0.6), 0, false); //TODO we might have to set the speed or voltage of the motors to the pid not arcaed drive
        if(++numLoops == 10){
            System.out.println("Current Position: " + currentPos);
            System.out.println("Target Position: " + targetPos);
            System.out.println("PID Output: " + distancePID.calculate(Robot.navX.getAngle()));
            numLoops = 0;
        }
    }
    
}