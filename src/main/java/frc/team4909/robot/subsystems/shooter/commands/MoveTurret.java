package frc.team4909.robot.subsystems.shooter.commands;

import java.text.DecimalFormat;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.Vision;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.subsystems.shooter.ShooterSubsystem;

public class MoveTurret extends CommandBase {

    private double speedTurret;
    private double lastSpeedTurret;
    Boolean atEndpoint = false;

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
        SmartDashboard.putBoolean("induction", Robot.shootersubsystem.endPoint.get());

        // if at endpoint
        if (!Robot.shootersubsystem.endPoint.get() == true) {

            // negative motion caused us to hit limit
            if (lastSpeedTurret < 0){
                if(speedTurret < 0){
                    speedTurret = 0;
                } 
            } else if (lastSpeedTurret > 0){
                if(speedTurret > 0){
                    speedTurret = 0;
                } 
            }
        }
        else { //if the metal is *NOT* above the sensor
            if (speedTurret != 0){
                lastSpeedTurret = speedTurret;
            }
        }
        SmartDashboard.putNumber("turret speed", speedTurret);
        SmartDashboard.putNumber("last turret speed", lastSpeedTurret);
        

        Robot.shootersubsystem.setTurnSpeed(speedTurret * RobotConstants.turretSpeedMultiplier);

        //check the direction correctness, like whether RS left actually moves the turret left.
    }
    @Override
    public void end(boolean interupted){
        Robot.shootersubsystem.setTurnSpeed(0);
    }
}