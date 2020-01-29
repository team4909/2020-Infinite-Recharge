package frc.team4909.robot.subsystems.leds;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDs extends SubsystemBase{
    public AddressableLED Strip;
    public AddressableLEDBuffer ledBuffer;

    public LEDs() {
        Strip = new AddressableLED(0);
        ledBuffer = new AddressableLEDBuffer(30);

        Strip.setLength(ledBuffer.getLength());
    }

    public void setBlue() {
        for (int i = 0; i <= ledBuffer.getLength()-1; i++) {
            ledBuffer.setRGB(i, 0, 255, 0);
        }
        Strip.setData(ledBuffer);
    }

    public void periodic(){
        setBlue();
        Strip.start();
    }
}