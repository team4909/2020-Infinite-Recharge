package frc.team4909.robot;

public class RobotConstants {

    // Limlight Constants
    public static final double limelightHeight = 34;
    public static final double powerPortHeight = 90;
    public static final double limelightAngle = 15;
    public static final double limelightPowerPortDiff = powerPortHeight - limelightHeight;

    //Shooter Constants
    public static final double shooterkP = 0.00139;
    public static final double shooterkI = 0;
    public static final double shooterkD = 0;
    public static final double shooterkF = 0.05815;

    public static final double hoodCoefA = -0.0015916;
    public static final double hoodCoefB = 0.5017;
    public static final double hoodCoefC = 27.1368;

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
    public static final double deploySetpoint = 28;//33.4 for any non GalacticSeach purposes

    // Drive Constantsft
    public static final double drivekP = 0.09;
    public static final double drivekI = 0.0;
    public static final double drivekD = 0.0;
    
    public static final double distancekP = 0.002;
    public static final double distancekI = 0.0;
    public static final double distancekD = 0.0002;

    public static final double TURN_KP = 0.045;
    public static final double TURN_KI = 0.0;
    public static final double TURN_KD = 0.006;

    //THIS IS ALL IMPERICAL: we rotated the wheel 360(degrees) and subtracted the starting encoder position... 
    //...from the ending encoder position to get 20,472. - all for TICKS_PER_INCH
    //The 13.2 in the commented out "calculation" is the actual gear ratio from cad.
    //6.125 is the wheel diameter including the tread
    public static final double TICKS_PER_INCH = 20_472 / (6.125 * Math.PI); //13.2 * 2048 / (6.125 * Math.PI);
    public static final double TOLERANCE_INCHES = 0.2 * TICKS_PER_INCH; //0.5 prev
    public static final double TOLERANCE_DEGREES = 0.25;
    
    //Pixy Refresh Constant
    public static final int PIXY_REFRESH_DIVISOR = 10;

    //Motion Magic Constants
    public static final int PID_LOOP_IDX = 0;
    public static final int PID_SLOT_IDX = 0;
    public static final int TIMEOUT = 30;

    public static final int MOTION_CRUISE_VELOCITY = 20000;
    public static final int MOTION_ACCELERATION = 10000;

    public static final double MAGIC_KF = 0.0;
    public static final double MAGIC_KP = 0.2;
    public static final double MAGIC_KI = 0.0;
    public static final double MAGIC_KD = 0.0;

    public static final double TOLERANCE_TICKS = TICKS_PER_INCH;
}