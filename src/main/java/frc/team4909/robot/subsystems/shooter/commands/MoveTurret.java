package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.operator.controllers.FlightStick;

public class MoveTurret extends CommandBase {



    public MoveTurret() {
        super();
    }
    
    @Override
    public void initialize() {
        // turn off limelight lights
    }

    @Override
    public void execute(){
        
        Robot.turretSubsystem.setTurnSpeed(-Robot.manipulatorGamepad.getThresholdAxis(FlightStick.Z));

        //check the direction correctness, like whether RS left actually moves the turret left.
    }
    @Override
    public void end(boolean interrupted){
        Robot.turretSubsystem.setTurnSpeed(0);
    }
}