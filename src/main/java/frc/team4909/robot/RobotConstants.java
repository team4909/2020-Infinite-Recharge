package frc.team4909.robot;

public class RobotConstants {

    // Limlight Constants
    public static final double limelightHeight = 34;
    public static final double powerPortHeight = 90;
    public static final double limelightAngle = 15;
    public static final double limelightPowerPortDiff = powerPortHeight - limelightHeight;

    //Shooter Constants
    public static final double shooterkP = 2; //1000 *x = 1;  where 1000 is worst case error
    public static final double shooterkI = 0;
    public static final double shooterkD = 100;
    public static final double shooterkF = 0.05; //setpoint * kf = motorOutput 

    // OLD Quadratic hood values
    public static final double hoodCoefA = -0.000673493;
    public static final double hoodCoefB = 0.291947;
    public static final double hoodCoefC = 36.9933;

    // NEW Quartic hood values
    // public static final double hoodCoef4A = -1.4058e-8;
    // public static final double hoodCoef4B = 1.282e-5;
    // public static final double hoodCoef4C = -5.06999e-3;
    // public static final double hoodCoef4D = 0.95704;
    public static final double hoodCoef4A = -6.5612e-9;
    public static final double hoodCoef4B = 0.00001292;
    public static final double hoodCoef4C = -0.00570245;
    public static final double hoodCoef4D = 0.962744;

    public static final double turretSpeedMultiplier = 0.3;

    public static final double hoodkP = 24;
    public static final double hoodkI = 0;
    public static final double hoodkD = 0;

    // Climber Constants
    public static final double climberkP = 1;
    public static final double climberkI = 0;
    public static final double climberkD = 0;
    public static final double climberkF = 0;
    public static final double climberTop = 0;

    public static final double hookkP = 0;
    public static final double hookkI = 0;
    public static final double hookkD = 0;
    public static final double hookkF = 0;

    // Intake Constants
    public static final double intakekP = 0.5;
    public static final double intakekI = 0;
    public static final double intakekD = 0;
    public static final double intakekF = 0;
    public static final double deploySetpoint = 33.4;

    // Drive Constantsft
    public static final double drivekP = 0.09;
    public static final double drivekI = 0.0;
    public static final double drivekD = 0.0;

    public static final double distancekP = 0.025;
    public static final double distancekI = 0.0;
    public static final double distancekD = 0.0;

    public static final double TICKS_PER_INCH = 20_472 / (6.125 * Math.PI);
}