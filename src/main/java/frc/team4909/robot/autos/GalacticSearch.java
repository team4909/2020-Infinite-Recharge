package frc.team4909.robot.autos;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.camera.PixyCam;
import frc.team4909.robot.subsystems.intake.commands.IntakeDeploy;

public class GalacticSearch extends CommandBase {

    public String selectedPath;
    // In the form of Path, Color "AR" "AB" "BR" "BB"
    boolean detected;
    boolean leftPixy;
    boolean rightPixy;
    final int CENTERX_PIXY = 0;
    boolean centerPixy;

    public GalacticSearch(){

                //First Step when Robot is at C1
                if(detected){//Check if we can see something
                    if (leftPixy){ //Checks if there is a blob on the left
                       selectedPath = "BR";
                        //TODO Finish Path w/ PathWeaver or Gyro + Pixy
                    } 
                    else if(rightPixy){ //Checks if there is a blob on the right
                        selectedPath = "AR";
                        //TODO Finish Path w/ PathWeaver or Gyro + Pixy
                    }  
                }
                else if(!detected){//We cannot see anything
                    //TODO write code to drive to middle of D5 & E5

                    if(detected){//Check if we can see something
                        if (leftPixy){ //Checks if there is a blob on the left
                           selectedPath = "BB";
                            //TODO Finish Path w/ PathWeaver or Gyro + Pixy
                        } 
                        else if(rightPixy){ //Checks if there is a blob on the right
                            selectedPath = "AB";
                            //TODO Finish Path w/ PathWeaver or Gyro + Pixy
                        }  
                    }
                }

                switch(selectedPath){ //Testing selected Path with each of the cases
                    case "AR": //If path is A Red, follow this trajecotry
        
                    break; //Skip the rest of the statement
        
                    case "BR": //If path is B Red, follow this trajecotry
        
                    break;
        
                    case "AB": //If path is A Blue, follow this trajectory
        
                    break;
        
                    case "BB":// If path is B Ble, follow this trajecotry
        
                    break;
        
                    default:
                    System.out.println("No Path Detected"); //Can recurse this command
                    break;
                }
    }

    @Override
    public void execute() {
        new IntakeDeploy();  //Deploying the Intake, because we want it to be running always
        detected = Robot.pixyCam.getDetected();

        if(Robot.pixyCam.getDeviationX() < CENTERX_PIXY){
            leftPixy = true;
        } else if(Robot.pixyCam.getDeviationX() > CENTERX_PIXY){
            rightPixy = true;
        } else if(Robot.pixyCam.getDeviationX() == CENTERX_PIXY){
            centerPixy = true;
        }
    }
} 