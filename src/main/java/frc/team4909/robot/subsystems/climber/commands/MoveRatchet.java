package frc.team4909.robot.subsystems.climber.commands;

import com.revrobotics.CANPIDController;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class MoveRatchet extends CommandBase{

    double pos;

    public MoveRatchet(double position) {
        super();
        addRequirements(Robot.climberSubsystem);
        pos = position;
    }

    @Override
    public void initialize() {
        Robot.climberSubsystem.setRatchetAngle(pos);
    }

    // @Override
    // public void end(boolean interrupted) {
    //     Robot.climberSubsystem.setRatchetSpeed(0);
    }

