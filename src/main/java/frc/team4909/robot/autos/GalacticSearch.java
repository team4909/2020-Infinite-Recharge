package frc.team4909.robot.autos;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.Robot;
import frc.team4909.robot.autos.galacticsearch.A_Blue;
import frc.team4909.robot.autos.galacticsearch.A_Red;
import frc.team4909.robot.autos.galacticsearch.B_Blue;
import frc.team4909.robot.autos.galacticsearch.B_Red;
import frc.team4909.robot.autos.galacticsearch.BlueStart;
import frc.team4909.robot.subsystems.camera.PixyCam;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterUp;
import frc.team4909.robot.subsystems.indexer.commands.SmartIndexerAndSorterUp;
import frc.team4909.robot.subsystems.intake.commands.IntakeDeploy;

public class GalacticSearch extends SequentialCommandGroup{

    public String selectedPath;
    // In the form of Path, Color "AR" "AB" "BR" "BB"
    boolean detected;
    boolean leftPixy;
    boolean rightPixy;
    final int CENTERX_PIXY = 0;
    boolean centerPixy;

    public GalacticSearch(){
        
        addCommands(
            new ParallelCommandGroup(
                new ConditionalCommand( //Can we see a ball?
                    new ConditionalCommand( // Yes, A or B?
                        new A_Red(), // Is in the middle
                        new B_Red(), // Is on the left
                        new RedChoice()), 
                    new SequentialCommandGroup( //No
                        new BlueStart(), // Goes to D5 
                        new ConditionalCommand( // A or B?
                            new A_Blue(), // A Blue, is on the right
                            new B_Blue(), // B Blue, is on the left
                            new BlueChoice())), //Can also do this: BooleanSupplier sup = () -> true;
                    new FirstChoice()),
                //new IntakeDeploy(), //TODO make sure this works.
                new SmartIndexerAndSorterUp()
                //new IndexerAndSorterUp()

            )
        );

    {

    //DriveForward driveForward = new DriveForward();

    //                 //First Step when Robot is at C1
    //                 if(detected){//Check if we can see something
    //                     if (leftPixy){ //Checks if there is a blob on the left
    //                        selectedPath = "BR";
    //                         //TODO Finish Path w/ PathWeaver or Gyro + Pixy
    //                     } 
    //                     else if(rightPixy){ //Checks if there is a blob on the right
    //                         selectedPath = "AR";
    //                         //TODO Finish Path w/ PathWeaver or Gyro + Pixy
    //                     }  
    //                 }
    //                 else if(!detected){//We cannot see anything
    //                     //TODO write code to drive to middle of D5 & E5

    //                     if(detected){//Check if we can see something
    //                         if (leftPixy){ //Checks if there is a blob on the left
    //                            selectedPath = "BB";
    //                             //TODO Finish Path w/ PathWeaver or Gyro + Pixy
    //                         } 
    //                         else if(rightPixy){ //Checks if there is a blob on the right
    //                             selectedPath = "AB";
    //                             //TODO Finish Path w/ PathWeaver or Gyro + Pixy
    //                         }  
    //                     }
    //                 }

    //                 switch(selectedPath){ //Testing selected Path with each of the cases
    //                 case "AR": //If path is A Red, follow this trajecotry
    //                     driveForward.DriveForwardInches(60);

    //                     break; //Skip the rest of the statement

    //                 case "BR": //If path is B Red, follow this trajecotry
                        

    //                     break;

    //                 case "AB": //If path is A Blue, follow this trajectory

    //                     break;
            
    //                 case "BB":// If path is B Ble, follow this trajecotry

    //                     break;
            
    //                 default:
    //                     System.out.println("No Path Detected"); //Can recurse this command
    //                     break;
    //                 }
    //     }



    //     @Override
    //     public void execute() {
    //         new IntakeDeploy();  //Deploying the Intake, because we want it to be running always
    //         detected = Robot.pixyCam.getDetected();

    //         if(Robot.pixyCam.getDeviationX() < CENTERX_PIXY){
    //             leftPixy = true;
    //         } else if(Robot.pixyCam.getDeviationX() > CENTERX_PIXY){
    //             rightPixy = true;
    //         } else if(Robot.pixyCam.getDeviationX() == CENTERX_PIXY){
    //             centerPixy = true;
    //         }
    }
    }

    //TODO figure which value of deviation helps us determine left or right

    private class FirstChoice implements BooleanSupplier{

        @Override
        public boolean getAsBoolean() {
            if(Robot.pixyCam.getDetected() &&  Robot.pixyCam.getDeviationX() <= 68 ) { //In the middle or left
                SmartDashboard.putString("path", "RED");
                return true;
            } else if (Robot.pixyCam.getDetected() && Robot.pixyCam.getDeviationX() > 68 ) { //To the right 
                SmartDashboard.putString("path", "BLUE");
                return false;
            } else { //Not seen
                System.out.println("Not seen @ First Choice");
                System.out.println(Robot.pixyCam.getDetected());
                SmartDashboard.putString("path", "BLUE (NOTHING SEEN)");
                return false;
            }
        }
    }

    private class RedChoice implements BooleanSupplier{

        @Override
        public boolean getAsBoolean() {
            if (Robot.pixyCam.getDetected() && Robot.pixyCam.getDeviationX() < 30 && Robot.pixyCam.getDeviationX() > -40){ //In the Middle
                SmartDashboard.putString("path", "A_Red");
                return true;
            } else if (Robot.pixyCam.getDetected() && Robot.pixyCam.getDeviationX() <= -40){ //On the left
                SmartDashboard.putString("path", "B_Red");
                return false;
            } else {
                SmartDashboard.putString("path", "Uhhhh");
                System.out.println("Not seen/On the Right @ Red Choice");
                return false;
            }
        }
    }

    private class BlueChoice implements BooleanSupplier{
        
        @Override
        public boolean getAsBoolean() {
            if(Robot.pixyCam.getDetected() && Robot.pixyCam.getDeviationX() > 0){ //On the right
                SmartDashboard.putString("path", "A_Blue");
                return true;
            } else if(Robot.pixyCam.getDetected() && Robot.pixyCam.getDeviationX() <= 0){ //On the left
                SmartDashboard.putString("path", "B_Blue");
                return false;
            } else { //Not seen or On the right
                System.out.println("Not seen @ Blue Choice");
                SmartDashboard.putString("path", "Uhhhh (Blue Edition)");
                return false;
            } 
        }
    }
    
} 
