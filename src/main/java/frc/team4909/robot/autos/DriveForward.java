package frc.team4909.robot.autos;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class DriveForward extends CommandBase{

    //Amount of encoder ticks in a revolution
    int TICKS_PER_REV = 1440; //TODO test value
    //Creates new PID object
    PIDController distancePID;
    //Sets the position for the robot when it starts; This is set because we can ensure that we will be at 0 ticks everytime we start the robot
    int STARTING_POS = Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition();
    int currentPos;

    public DriveForward(int feet){
        //Converts the feet to move argument into ticks for a setpoint
        int feetInTicks = (feet * 2880) + STARTING_POS;
        //Creates new PID controller; See RobotConstants for values
        distancePID = new PIDController(RobotConstants.distancekP, RobotConstants.distancekI, RobotConstants.distancekD);
        //Sets the setpoint for the PID
        distancePID.setSetpoint(feetInTicks);
    }

    @Override
    public void execute() {
        //This sets the current position of the robot
        currentPos = Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition(); //TODO test wether STARTING_POS needs to be added to this value
        //This takes the pid calculate method and gives it as speed to the arcade drive
        Robot.drivetrainsubsystem.arcadeDrive(MathUtil.clamp(distancePID.calculate(currentPos), 50, 400), 0); //TODO we might have to set the speed or voltage of the motors to the pid not arcaed drive
    }
}