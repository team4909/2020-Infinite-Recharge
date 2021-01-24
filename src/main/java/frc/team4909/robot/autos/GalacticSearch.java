package frc.team4909.robot.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team4909.robot.subsystems.drivetrain.SetDriveSpeed;
import frc.team4909.robot.Robot;
import frc.team4909.robot.subsystems.camera.PixyCam;
import frc.team4909.robot.subsystems.intake.commands.IntakeDeploy;
import frc.team4909.robot.subsystems.indexer.commands.IndexerAndSorterUp;
import frc.team4909.robot.subsystems.drivetrain.DriveTrainSubsystem;

public class GalacticSearch extends SequentialCommandGroup { //Sequential: One after another, Parallel: Everything happens at once
    public GalacticSearch(){

        private boolean detected = Robot.pixyCam.getDetected();
        private boolean left = PixyCam.left; 
        private boolean right = PixyCam.right;
        private AHRS navx = new AHRS(SPI.Port.kMPX);
        private int ballCount = 0;
        
        //TODO: Can implement gyro instead of timeout

        addCommands(
            new SequentialCommandGroup(
                //Deploying the Intake, because we want it to be running always
                new IntakeDeploy();
                //First Step when Robot is at C1
                if(detected == true){//we can see something at either B3 & C3
                    if(left == true){
                        //Following B Red path
                        new SetDriveSpeed(0.5).withTimeout(0.75); //TODO: Test these numbers
                        new IndexerAndSorterUp(); //We could also use SmartIndexerand SorterUp
                        
                       
                    } else if(right == true){
                        //Following A Red path
                    }
                }
                else if(detected == false){//We cannot see anything

                }
        );
    }
} 