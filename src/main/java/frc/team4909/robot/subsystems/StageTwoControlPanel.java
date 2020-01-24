package frc.team4909.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class Color_sensor_sensing extends CommandBase {

    boolean colour6timespassed;
    int colourCounter;
    String initaialColour;
    char sixtynine_nice;
    String lastColour;




    public Color_sensor_sensing() {

        addRequirements(Robot.colorsensor);


    }

    @Override
    public void initialize(){

        initaialColour = Robot.colorsensor.colorString;


        Robot.drivetrainsubsystem.m_left.set(0.2);

        lastColour = initaialColour;

    }

    @Override
    public void execute(){
        System.out.println("initialcolor:"+initaialColour);
        Robot.drivetrainsubsystem.m_left.set(0.2);
        System.out.println(lastColour);
        if (lastColour != Robot.colorsensor.colorString) {

            if (Robot.colorsensor.colorString == initaialColour ) {


                colourCounter++;
                
                
            }

            lastColour = Robot.colorsensor.colorString;

            
        }


        


    }

        
    public boolean isFinished() {

        if (colourCounter == 6) {
            
            Robot.drivetrainsubsystem.m_left.set(0.0);
            return true;
        }

        return false;


    }
        

        


}
