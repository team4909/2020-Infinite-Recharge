package frc.team4909.robot.subsystems.shooter.commands;

import java.text.DecimalFormat;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.Vision;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.subsystems.shooter.ShooterSubsystem;

public class MoveTurret extends CommandBase {

    private double speedTurret;

    public MoveTurret(ShooterSubsystem subsystem) {
        super();
        addRequirements(subsystem);
    }
    
    @Override
    public void initialize() {
        Robot.vision.setLights(0);
    }

    @Override
    public void execute(){
        speedTurret = Robot.manipulatorGamepad.getThresholdAxis(BionicF310.RX);
        Robot.shootersubsystem.setTurnSpeed(speedTurret * RobotConstants.turretSpeedMultiplier);
        //check the direction correctness, like whether RS left actually moves the turret left.
    }
    @Override
    public void end(boolean interupted){
        Robot.shootersubsystem.setTurnSpeed(0);
    }
}