package frc.team4909.robot.subsystems.leds;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

/**
 * Sets the LEDs to Green through a command. This is because the setting to
 * Green will happen through a button press. It is much easier to call a Command
 * over a ButtonPress, rather than calling a method of a subsystem. It's easier
 * than calling the method SetGreen() directly from the 'LEDs' subsystem.
 */
public class SetGreen extends CommandBase {

    public SetGreen() {
        super();
    }

    @Override
    public void initialize() {
        Robot.leds.setGreen();
    }
}