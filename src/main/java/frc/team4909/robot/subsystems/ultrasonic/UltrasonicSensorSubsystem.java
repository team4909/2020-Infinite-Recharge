package frc.team4909.robot.subsystems.ultrasonic;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class UltrasonicSensorSubsystem extends SubsystemBase {

    Ultrasonic ultra;

    public UltrasonicSensorSubsystem() {
        // Ports: trig/ping, echo
        ultra = new Ultrasonic(0, 1);
        ultra.setAutomaticMode(true);
    }

    // Return distance from the sensor in inches
    public int read()
    {
        ultra.setDistanceUnits(Unit.kInches);
        double range  = ultra.getRangeInches();
        if (range > 0 && range != 0) {
            return (int) range;
        } else {
            //Distance is not okay
            System.out.println("Distance is invalid");
            return 0;
        }

    }

}