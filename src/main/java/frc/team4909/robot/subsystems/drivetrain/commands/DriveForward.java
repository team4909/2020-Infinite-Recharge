package frc.team4909.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    boolean retrieveDistance = true;
    public int numLoops = 0;
    

    public DriveForward(double in){
        this();
        this.inches = in;
        this.retrieveDistance = false;
    }

    public DriveForward(){
        super();
        SmartDashboard.putNumber("DriveForward Distance", 0);
        SmartDashboard.putString("Position Determining Method", "default");
        addRequirements(Robot.drivetrainsubsystem);
        //Creates new PID controller; See RobotConstants for values
        distancePID = new PIDController(RobotConstants.distancekP, RobotConstants.distancekI, RobotConstants.distancekD);
        //Sets the setpoint for the PID
    }

    public int getSensorPosition(String method) {
        int rightPos, leftPos;
        switch (method) {
            
            case "front right":
                return Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition();           
            case "front left":
                return Robot.drivetrainsubsystem.frontLeft.getSelectedSensorPosition();
            case "back right":
                return Robot.drivetrainsubsystem.backRight.getSelectedSensorPosition();
            case "back left":
                return Robot.drivetrainsubsystem.backLeft.getSelectedSensorPosition();
            case "mean":
                rightPos = (Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition()
                                    + Robot.drivetrainsubsystem.backRight.getSelectedSensorPosition()) / 2;
                leftPos = -1 * (Robot.drivetrainsubsystem.frontLeft.getSelectedSensorPosition() 
                                    + Robot.drivetrainsubsystem.backLeft.getSelectedSensorPosition()) / 2;
                return (rightPos + leftPos) / 2;
            default:
                rightPos = (Robot.drivetrainsubsystem.frontRight.getSelectedSensorPosition()
                                    + Robot.drivetrainsubsystem.backRight.getSelectedSensorPosition()) / 2;
                leftPos = -1 * (Robot.drivetrainsubsystem.frontLeft.getSelectedSensorPosition() 
                                    + Robot.drivetrainsubsystem.backLeft.getSelectedSensorPosition()) / 2;
                return (rightPos + leftPos) / 2;
            
        }
    }


    @Override
    public void initialize() {
        startingPos = getSensorPosition(SmartDashboard.getString("Position Determining Method", "default"));
        if (this.retrieveDistance) {
            this.inches = SmartDashboard.getNumber("DriveForward Distance", 0);
        } 
        
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
        double output = -1 * MathUtil.clamp(
            distancePID.calculate(currentPos), 
            -0.4, 
            0.4);
        Robot.drivetrainsubsystem.arcadeDrive(
            output, 
            0, 
            false); //TODO we might have to set the speed or voltage of the motors to the pid not arcaed drive
        if(++numLoops == 1){
            // System.out.println("Current Position: " + currentPos);
            // System.out.println("Target Position: " + targetPos);
            // System.out.println("Drive PID Output: " + distancePID.calculate(currentPos));
            // System.out.println("             -               ");
            SmartDashboard.putNumber("DriveForward - Current Position", currentPos);
            SmartDashboard.putNumber("DriveForward - Target Position", targetPos);
            SmartDashboard.putNumber("DriveForward - Error", targetPos - currentPos);
            SmartDashboard.putNumber("DriveForward - PID Output", output);
            numLoops = 0;
        }
    }
    
    @Override
    public boolean isFinished() {

        if ((currentPos - targetPos) > 0){
            System.out.println("isFinished has returned true");
            return true;
        }
        return false;

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
        DriveTrainRamp.zeroLastValue();
        System.out.println("End command in DriveForward has ended");
    }
}