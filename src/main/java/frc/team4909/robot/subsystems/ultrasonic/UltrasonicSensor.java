package frc.team4909.robot.subsystems.ultrasonic;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//import edu.wpi.first.wpilibj.SendableBase;
///import edu.wpi.first.wpilibj.SensorBase;

public class UltrasonicSensor extends SubsystemBase {

    Ultrasonic ultra;

    public UltrasonicSensor() {
        // Ports: trig, echo
        ultra = new Ultrasonic(0, 1);
        ultra.setAutomaticMode(true);
    }

    // Return distance from the sensor in inches
    public double read()
    {
        ultra.setDistanceUnits(Unit.kInches);
        return ultra.getRangeInches();
    }

}