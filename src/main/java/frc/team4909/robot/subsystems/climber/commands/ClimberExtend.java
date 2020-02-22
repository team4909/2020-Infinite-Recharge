package frc.team4909.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.team4909.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimberExtend extends CommandBase {

    double pos;

    public ClimberExtend(double setpoint){
        super();
        addRequirements(Robot.climberSubsystem);
        pos = setpoint;
    }

    @Override
    public void initialize() {
        Robot.climberSubsystem.setClimberPosition(pos);
    }

    @Override
    public boolean isFinished() {
        if(Math.abs(Robot.climberSubsystem.getClimbPos()-pos) <= 10){
            return true;
        }
        return false;
    }
}