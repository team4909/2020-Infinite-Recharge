package frc.team4909.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;

/**
 * IntakeDown
 */
public class IntakeDown extends CommandBase{

    @Override
    public void initialize() {
        Robot.intakeSubsystem.deployIntake(RobotConstants.deploySetpoint);
        SmartDashboard.putBoolean("Intake Down Initially", false);
    }

    @Override
    public void execute() {
        SmartDashboard.putNumber("intakePos", Robot.intakeSubsystem.deployEncoder.getPosition());
    }

    @Override
    public boolean isFinished() {
        if(Robot.intakeSubsystem.deployEncoder.getPosition() >= RobotConstants.deploySetpoint){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.intakeSubsystem.setSpeed(0);
        SmartDashboard.putBoolean("Intake Down Initially", true);
    }
    
}