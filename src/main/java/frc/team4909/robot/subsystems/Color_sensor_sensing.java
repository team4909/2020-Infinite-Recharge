package frc.team4909.robot.subsystems;

import java.util.Set;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team4909.robot.Robot;

public class Color_sensor_sensing extends CommandBase {

    boolean colour6timespassed;
    int colourCounter;
    String initaialColour;

    public Color_sensor_sensing(ColorSensor colorsensor) {

        addRequirements(colorsensor);


    }

    @Override
    public void initialize(){

        initaialColour = Robot.colorsensor.colorString;


        while (colourCounter < 6) {

            Robot.drivetrainsubsystem.m_left.set(0.5);
            
        }

        Robot.drivetrainsubsystem.m_left.set(0.0);


    }

    @Override
    public void execute(){

        Robot.drivetrainsubsystem.m_left.set(0.5);

        if (Robot.colorsensor.colorString == initaialColour ) {

            colourCounter++;
            
        }
        
        System.out.println(colourCounter);

    }

        
    public boolean isFinished() {

        if (colourCounter == 6) {
            
            return true;
        }

        return false;


    }
        

        


}
