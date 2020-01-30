package frc.team4909.robot.subsystems.leds;

import edu.wpi.first.wpilibj.Spark;

public class LEDs{
    public Spark LEDController;

    public LEDs() {
        LEDController = new Spark(0);
    }

    public void setRed(){
        LEDController.set(0.59);
    }

    public void setGreen(){
        LEDController.set(0.77);
    }

    public void setBlue(){
        LEDController.set(0.85);
    }

    public void setBlack(){
        LEDController.set(0.99);
    }

    public void setRedBlink(){
        LEDController.set(-0.11);
    }

    public void setDisabled(){
        LEDController.set(-0.71);
    }

    public void periodic(){
        LEDController.set(0.49);
    }
}