package frc.team4909.robot.subsystems.leds;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class LEDSetter extends CommandBase {

    // LED Subsystem
    LEDs leds = Robot.leds;

    public LEDSetter() {
        super();
    } 

    /**
     * Checks different subsystems and what they're doing.
     * Sets the color of the Robot's LEDs Accordingly.
     */
    @Override
    public void execute() {
        // When a ball is detected in the upper indexer, set Yellow.
        if (Robot.indexerSubsystem.hasBallUpper()) {
            if (Robot.shootersubsystem.isAtSpeed) {
                if (Robot.shootersubsystem.isAligned) {
                    leds.setBlue();
                } else {
                    leds.setBlueBlink();
                }
            } else {
                leds.setYellow();
            }
        } 
    }
    

}