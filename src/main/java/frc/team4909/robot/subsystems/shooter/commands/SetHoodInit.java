package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class SetHoodInit extends CommandBase{

    public SetHoodInit(){
        super();
        addRequirements(Robot.hoodSubsystem);
    }

    @Override
    public void initialize() {
        Robot.hoodSubsystem.setHoodAngle(61);
    }
    @Override
    public boolean isFinished() {
        return true;
    }

}