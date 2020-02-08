import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Ultrasonic;
public class ultrasonic extends SubsystemBase {
    Ultrasonic ultra;
    public ultrasonic() {
        ultra = new Ultrasonic(0,1);
        ultra.setAutomaticMode(true);
    }

    public String read()
    {
        ultra.setDistanceUnits(inches);
        return ultra.getRangeInches();
    }

}