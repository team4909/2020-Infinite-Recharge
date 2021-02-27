package frc.team4909.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

public class TurnRobot extends CommandBase{
    //error is how much we have to turn
    //gain is the speed to turn at based on error
    double degrees;
    double currentPosition;
    double targetPosition;
    double startingPosition;
    PIDController turnPID;
    final double SPEED = 0; //TODO check value
    int numLoops = 0;

    public TurnRobot(double deg){
        super();
        addRequirements(Robot.drivetrainsubsystem);
        turnPID = new PIDController(RobotConstants.TURN_KP, RobotConstants.TURN_KI, RobotConstants.TURN_KD);
        this.degrees = deg;
        //TODO change the format of final constants to CAPS_AND_UNDERSCORES.
    }
    
    @Override
    public void initialize() {
        startingPosition = Robot.drivetrainsubsystem.getAngle();
        targetPosition = degrees + startingPosition; //Gets the abosolute position | TODO Find what get angle really means / gives back
        turnPID.setSetpoint(targetPosition);
        // turnRobot.setTolerance(3);//TODO Test Value and figure out units
        System.out.println("Begin TurnRobot with error " + (targetPosition - startingPosition));
    }

    @Override
    public void execute() {
        currentPosition = Robot.drivetrainsubsystem.getAngle();
        double output = turnPID.calculate(currentPosition);
        //NEGATIVE IS FOR SOME REASON???> SEE DRIVEFORWARD.JAVA
        double clampedOutput = MathUtil.clamp(Math.abs(output), 0.35, 1);
        Robot.drivetrainsubsystem.arcadeDrive(SPEED, output < 0 ? -clampedOutput : clampedOutput, true); //TODO where does the negative go???; 
        if(++numLoops == 10){
            System.out.println("Current Angle: " + currentPosition);
            System.out.println("Target Angle: " + targetPosition);
            System.out.println("Turn PID Output: " + output + "\n");
            System.out.println("             -               ");
            numLoops = 0;
        }
    }

    @Override
    public boolean isFinished() {
        if(Math.abs(targetPosition - currentPosition) < RobotConstants.TOLERANCE_DEGREES){ 
            System.out.println("Error is less than 3 units");
            return true;
        }
        
        return false;
    }

    @Override
    public void end(boolean interrupted){
        System.out.println("No more turning");
        Robot.drivetrainsubsystem.arcadeDrive(0, 0, false);
    }
    
}