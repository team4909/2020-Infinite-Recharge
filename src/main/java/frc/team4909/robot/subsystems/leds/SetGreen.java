package frc.team4909.robot.subsystems.leds;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class SetGreen extends CommandBase {

    public SetGreen() {
        super();
    }

    @Override
    public void initialize() {
        Robot.leds.setGreen();
    }
}