package frc.team4909.robot.subsystems.leds;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Spark;

/*
The LEDs are controlled by the REV Blinkin LED Driver.
The driver is initialized as a Spark motor controller
and uses a PWM signal to follow preset patterns.
Patterns can be found here: http://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf
*/

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

    public void setBlueBlink(){
        LEDController.set(-0.09);
    }

    public void setAllianceColor(){
        switch (DriverStation.getInstance().getAlliance()) {
            case Red:
                setRed();
                break;

            case Blue:
                setBlue();
                break;
            default:
                break;
        }
    }
}