package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class SetHoodClose extends CommandBase{

    public SetHoodClose(){
        super();
        addRequirements(Robot.hoodSubsystem);
    }

    @Override
    public void initialize() {
        Robot.hoodSubsystem.setHoodAngle(23); //@TODO Test and find this value
    }
    @Override
    public boolean isFinished() {
        return true;
    }

}