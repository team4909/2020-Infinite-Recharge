package frc.team4909.robot.autos;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.subsystems.drivetrain.SetDriveSpeed;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.camera.PixyCam;
import frc.team4909.robot.subsystems.intake.commands.IntakeDeploy;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterUp;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;

public class GalacticSearch extends CommandBase {

    public String selectedPath;
    
    public GalacticSearch(){

        boolean detected = Robot.pixyCam.getDetected();
        int ballCount; 
        final int CENTERX_PIXY = 0;
        // In the form of Path, Color "AR" "AB" "BR" "BB"

        
        //TODO: Can implement gyro instead of timeout

                //First Step when Robot is at C1
                if(detected == true){//Check if we can see something
                    if (Robot.pixyCam.getDeviationX() < CENTERX_PIXY){ //Checks if there is a blob on the left
                       selectedPath = "BR";
                        //TODO Finish Path w/ PathWeaver* or Gyro + Pixy
                    } 
                    else if(Robot.pixyCam.getDeviationX() > CENTERX_PIXY ){ //Checks if there is a blob on the right
                        selectedPath = "AR";
                        //TODO Finish Path w/ PathWeaver* or Gyro + Pixy
                    }  
                }
                else if(detected == false){//We cannot see anything
                    //TODO write code to drive to middle of D5 & E5
                    if(detected == true){//Check if we can see something
                        if (Robot.pixyCam.getDeviationX() < CENTERX_PIXY){ //Checks if there is a blob on the left
                           selectedPath = "BB";
                            //TODO Finish Path w/ PathWeaver* or Gyro + Pixy
                        } 
                        else if(Robot.pixyCam.getDeviationX() > CENTERX_PIXY ){ //Checks if there is a blob on the right
                            selectedPath = "AB";
                            //TODO Finish Path w/ PathWeaver* or Gyro + Pixy
                        }  
                    }
                }

                switch(selectedPath){//Testing selected Path with each of the cases
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

    }
} 