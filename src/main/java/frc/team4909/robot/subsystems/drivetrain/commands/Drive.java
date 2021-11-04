package frc.team4909.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class Drive extends CommandBase{

    //Amount of encoder ticks in a revolution
    //Creates new PID object
    PIDController distancePID;
    double inches;
    //Sets the position for the robot when it starts; This is set because we can ensure that we will be at 0 ticks everytime we start the robot
    double currentPos;
    double targetPos;


    public Drive(double in){
        super();
        this.inches = in;
 
        addRequirements(Robot.drivetrainsubsystem);
        //Creates new PID controller; See RobotConstants for values
        distancePID = new PIDController(RobotConstants.distancekP, RobotConstants.distancekI, RobotConstants.distancekD);
    }


    @Override
    public void initialize() {

        Robot.drivetrainsubsystem.frontRight.setSelectedSensorPosition(0); //reset the encoder

        currentPos = Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition();

        targetPos = RobotConstants.TICKS_PER_INCH * inches;


        distancePID.setSetpoint(targetPos);
    }

    @Override
    public void execute() {
        //This sets the current position of the robot
        currentPos = Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition();
        //This takes the pid calculate method and gives it as speed to the arcade drive
        //IMPORTANT: THE NEGATIVE IS NEEDED BECAUSE ON THE JOYSTICK, UP IS NEGATIVE. THE NEGATIVE EMULATES THE STICK'S DIRECTIONS.

        double output = distancePID.calculate(currentPos);

        double clampedOutput = MathUtil.clamp(Math.abs(output), 0.2, 0.7);

        final double JOYSTICK_IS_NEGATIVE = -1;
        clampedOutput = JOYSTICK_IS_NEGATIVE * Math.copySign(clampedOutput, targetPos);

        Robot.drivetrainsubsystem.arcadeDrive(clampedOutput, 0);
   
        

        SmartDashboard.putNumber("DriveForward - Current Position", currentPos/RobotConstants.TICKS_PER_INCH);
        SmartDashboard.putNumber("DriveForward - Target Position", targetPos/RobotConstants.TICKS_PER_INCH);
        SmartDashboard.putNumber("DriveForward - Error", distancePID.getPositionError()/RobotConstants.TICKS_PER_INCH);
        SmartDashboard.putNumber("DriveForward - PID Output", output);
   
    }
    
    @Override
    public boolean isFinished() {
        return Math.abs(distancePID.getPositionError()) < (20.0 * RobotConstants.TICKS_PER_INCH);

        // if (inches < 0) {

        //     if ((currentPos - targetPos) < 0){
        //         System.out.println("isFinished has returned true (-)");
        //         return true;
        //     }
        // } else {
        //     if ((currentPos - targetPos) > 0){
        //         System.out.println("isFinished has returned true (+)");
        //         return true;
        //     }
        // }

        // return false;

    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            System.out.println("DRIVE FORWARD WAS INTERRUPTED");
            System.exit(1);
        }
        System.out.println("No more moving translationally.");
        // Robot.drivetrainsubsystem.arcadeDrive(0, 0, false);
        // NOTE: we are using tank drive because arcade drive ramping IS VERY BAD, we will use tank drive instead.
        // @TODO fix arcade drive ramping.
        Robot.drivetrainsubsystem.tankDrive(0, 0);
        SmartDashboard.putBoolean("Has Driveforward Ended?", true);
        System.out.println("End command in DriveForward has ended");
    }
}