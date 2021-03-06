package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.BionicF310;

public class MoveHood extends CommandBase{


    public MoveHood(){
        super();
        addRequirements(Robot.hoodSubsystem);
        // i am default
    }

    @Override
    public void initialize() {
        
        // prevValue = Robot.hoodSubsystem.getPosition();
    }

    @Override
    public void execute() {
        Robot.hoodSubsystem.moveHood(-Robot.manipulatorGamepad.getThresholdAxis(BionicF310.LY));
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Turret End");
    }

}