package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.RobotConstants;
import frc.team4909.robot.operator.controllers.BionicF310;
import frc.team4909.robot.operator.controllers.FlightStick;
import frc.team4909.robot.operator.generic.BionicJoystick;

public class SwapShooterSpeed extends CommandBase{
    public SwapShooterSpeed(){
        super();
    }

    @Override
    public void initialize() {
        if(Robot.manipulatorGamepad.getThresholdAxis(FlightStick.Slider) > 0.8){
            Robot.shootersubsystem.shooterSetSpeed = 22000;
        }else if(Robot.manipulatorGamepad.getThresholdAxis(FlightStick.Slider) > -0.8){
            Robot.shootersubsystem.shooterSetSpeed = 17000;
        }
    }
}