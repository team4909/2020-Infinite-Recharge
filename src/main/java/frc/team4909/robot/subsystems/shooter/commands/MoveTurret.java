package frc.team4909.robot.subsystems.shooter.commands;

import java.text.DecimalFormat;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.Vision;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.operator.controllers.FlightStick;
import frc.team4909.robot.subsystems.shooter.ShooterSubsystem;

public class MoveTurret extends CommandBase {



    public MoveTurret(ShooterSubsystem subsystem) {
        super();
        addRequirements(subsystem);
    }
    
    @Override
    public void initialize() {
        // turn off limelight lights
        Robot.vision.setLights(0);
    }

    @Override
    public void execute(){
        
        Robot.turretSubsystem.setTurnSpeed(-Robot.manipulatorGamepad.getThresholdAxis(FlightStick.Z));

        //check the direction correctness, like whether RS left actually moves the turret left.
    }
    @Override
    public void end(boolean interupted){
        Robot.turretSubsystem.setTurnSpeed(0);
    }
}