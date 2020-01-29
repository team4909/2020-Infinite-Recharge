package frc.team4909.robot.subsystems.leds;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDs extends SubsystemBase{
    public Spark LEDController;

    public LEDs() {
        LEDController = new Spark(0);
    }

    public void setBlue() {
        LEDController.set(0.85);
    }

    public void setRed(){
        LEDController.set(0.59);
    }

    public void periodic(){
        LEDController.set(0.49);
    }
}