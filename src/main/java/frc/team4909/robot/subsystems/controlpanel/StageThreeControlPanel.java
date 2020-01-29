package frc.team4909.robot.subsystems.controlpanel;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class StageThreeControlPanel extends CommandBase {

    boolean colour6timespassed;
    int colourCounter;
    String givenColor;
    char sixtynine_nice;
    String lastColour;

    public StageThreeControlPanel() {
        addRequirements(Robot.colorsensor);
        String gameData = DriverStation.getInstance().getGameSpecificMessage(); //fms stuff;
        if(gameData.length() > 0){
            switch(gameData.charAt(0)){
                case 'B':
                    givenColor = "Red";
                    break;
                case 'Y':
                    givenColor = "Green";
                    break;
                case 'R':
                    givenColor = "Blue";
                    break;
                case 'G':
                    givenColor = "Yellow";
                    break;
                default:
                    givenColor = "";
                    break;
            }
            // SmartDashboard.putString(key, value)
            Robot.drivetrainsubsystem.m_left.set(0.2);
        }
    }

        
    public boolean isFinished() {

        if (givenColor == Robot.colorsensor.colorString) {
            
            Robot.drivetrainsubsystem.m_left.set(0.0);
            return true;
        }
        return false;
    }
}
