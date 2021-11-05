package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class SetHoodFar extends CommandBase{

    public SetHoodFar(){
        super();
        addRequirements(Robot.hoodSubsystem);
    }

    @Override
    public void initialize() {
        Robot.hoodSubsystem.setHoodAngle(60);
    }
    @Override
    public boolean isFinished() {
        return true;
    }

}