import edu.wpi.first.wpilibj.I2C;

public class ultrasonic extends SubsystemBase {
    public I2C i2c;
    int slave_address;
    public ultrasonic() {
        i2c = new I2C(Port.kOnboard, 0);
    }

    public String read()
    {
        byte[] arduinodata;
        if (i2c.readOnly(arduinodata, 4)){
            String data = arduinodata;
        }
        else {
            String data = "No communication";
        }
        return arduinodata;
    }

}