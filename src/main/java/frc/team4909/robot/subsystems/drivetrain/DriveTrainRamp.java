package frc.team4909.robot.subsystems.drivetrain;

public class DriveTrainRamp {
    
    //Class Variables/attributes
    
        /*  
        *   Values we'll need
        *   lastOutput
        *   joystickOutput
        *   acceleration threshold (could be its own function with related constants, or could be a constant)
        */

    //Class Variables :
   //The maximum rate of change of the "speedoutput" in the drivetrain arcade drive.  
    private static double accelThreshold = 0.1; //TODO test value
    private static double lastOutput = 0; 

    public static double getRampedOutput(double speedOutput){

        //If the speed is trying to acellerate too fast
        if(Math.abs(speedOutput - lastOutput) > accelThreshold){
            //Sets speedOutPut equal to its last value plus the limit 
            speedOutput = lastOutput + Math.copySign(accelThreshold, (speedOutput - lastOutput)); //CopySign handles accel and deccel
        }

        lastOutput = speedOutput;
        return speedOutput;

    } 
}