package frc.team4909.robot.autos;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;
import frc.team4909.robot.Vision;
import frc.team4909.robot.subsystems.shooter.TurretSubsystem;

public class Distance extends CommandBase{

        public double distance;
        public double constantDistance;
        public double constantAngle;
        public double distanceToTarget;

    public Distance(Vision subsystem){

        addRequirements(subsystem);

        

        

    }
  
    public double distanceToWall(){

        distanceToTarget = Robot.vision.calculateDistanceFromCameraHeight(Robot.vision.targetHeight,
         Robot.vision.cameraHeight, Robot.vision.cameraAngle);

       // distanceToTarget = Math.sqrt( Math.pow(distance, 2) + Math.pow( 152.845, 2) - distance * 152.845 + Math.cos(51.73));
        distance = (189.335 + Math.sqrt(Math.pow(189.335,2))-(4*233616-Math.pow(distanceToTarget, 2)))/2;




        SmartDashboard.putNumber( "Distance to wall", distance);

    

}