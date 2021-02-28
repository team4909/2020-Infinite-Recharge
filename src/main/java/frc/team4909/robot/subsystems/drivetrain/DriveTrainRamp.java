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
    private static double accelThreshold = 0.03; //TODO test value
    private static double lastSetSpeed = 0; 

    public static double getRampedOutput(double targetSpeed){

        //TODO Functionize the below line.
        accelThreshold = 0.03 - Math.abs(lastSetSpeed) * 0.015;
        //If the speed is trying to acellerate too fast
        if(Math.abs(targetSpeed - lastSetSpeed) > accelThreshold){
            //Sets speedOutPut equal to its last value plus the limit 
            targetSpeed = lastSetSpeed + Math.copySign(accelThreshold, targetSpeed - lastSetSpeed); //CopySign handles accel and deccel
        }

        lastSetSpeed = targetSpeed;
        return targetSpeed;

    }
    
    //OLD VERSION OF METHOD BELOW
    
    public static double getAccelerationRampedOutputOld(double targetSpeed){
        //TODO Functionize the below line.
        accelThreshold = 0.02;
        //If the speed is trying to acellerate too fast
        if( (targetSpeed - lastSetSpeed) < (-1 * accelThreshold) ){
            //Sets speedOutPut equal to its last value plus the limit 
            targetSpeed = lastSetSpeed + Math.copySign(accelThreshold, targetSpeed - lastSetSpeed); //CopySign handles accel and deccel
        }

        lastSetSpeed = targetSpeed;

        return targetSpeed;

    }

    public static double getAccelerationRampedOutput(double targetSpeed){
        //TODO Functionize the below line.
        accelThreshold = 0.02;
        //Checks if the speed is trying to increase, and by too much. Misses the case where the speed isn't changing, but the direction is.
        if( Math.abs(targetSpeed) > Math.abs(lastSetSpeed) && Math.abs(targetSpeed - lastSetSpeed) > accelThreshold) {
            //Sets the new speed equal to its last value plus the limit. 
            targetSpeed = lastSetSpeed + Math.copySign(accelThreshold, targetSpeed - lastSetSpeed);
        } else if (targetSpeed * lastSetSpeed < 0 && Math.abs(targetSpeed - lastSetSpeed) > accelThreshold) {
            targetSpeed = 0; //CopySign handles accel
        }

        lastSetSpeed = targetSpeed;

        return targetSpeed;
    }

    public static void zeroLastValue() {
        lastSetSpeed = 0;
    }
    

    

}