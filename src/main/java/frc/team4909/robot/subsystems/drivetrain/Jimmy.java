package frc.team4909.robot.subsystems.drivetrain;


public class Jimmy extends Subsystems {
CANSparkMax frontRight, frontLeft, backRight, backLeft;
SpeedControllerGroup m_right, m_left;  
DifferentialDrive bionicDrive;  
    public Jimmy() {
        frontRight = new CANSparkMax(1);
        backRight = new CANSparkMax(2);
        frontLeft = new CANSparkMax(3);
        backLeft = new CANSparkMax(4);

        m_right = new SpeedControllerGroup(frontRight, backRight);
        m_left = new SpeedControllerGroup(frontLeft, backLeft);

        bionicDrive = new DifferentialDrive(m_left, m_right);
    }
    public void arcadeDrive(double speed, double rotation) {
        bionicDrive.arcadeDrive(speed, rotation);
    }


    
}