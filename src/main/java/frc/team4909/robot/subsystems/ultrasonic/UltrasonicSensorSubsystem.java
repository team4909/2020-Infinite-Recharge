package frc.team4909.robot.subsystems.ultrasonic;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class UltrasonicSensorSubsystem extends SubsystemBase {

    Ultrasonic ultra;

    public UltrasonicSensorSubsystem() {
        // Ports: trig/ping, echo
        ultra = new Ultrasonic(0, 1);
        enable();
    }

    public void enable() {
        ultra.setAutomaticMode(true);
    }

    public void disable() {
        ultra.setAutomaticMode(false);
    }

    // Return distance from the sensor in a unit
    /**
     * 
     * @return The distance.
     */
    public int read() {
        ultra.setDistanceUnits(Unit.kInches);
        double range = ultra.getRangeInches();
        if (range > 0 && range != 0) {
            return (int) range;
        } else {
            // Distance is not okay
            System.out.println("Distance is invalid");
            return 0;
        }
    }

    /**
     * 
     * @param values The number of readings you want in the data set.
     * @return An average distance, for a more accurate reading.
     */
    public int readAverageDistance(int values) {
        int[] dataSet = new int[values];
        int distSum = 0;
        for (int i = 0; i < values; i++) {
            dataSet[i] = this.read();
            distSum += dataSet[i];
        }
        return distSum / values;
    } 
}