package frc.team4909.robot.subsystems.camera;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.MjpegServer;


public class CameraSubsystem{

    boolean toggle = true;
    MjpegServer camera;
    UsbCamera camera0;
    UsbCamera camera1;
    //Thread stream1, stream2;
    //private volatile boolean exit1 = false;
    //private volatile boolean exit2 = false;
    public CameraSubsystem(){
        
    }

    public void toggleCamera(){
        toggle = !toggle;
        System.out.print("TOGGLED::::::     ");
        System.out.println(toggle);

        if(toggle){
            camera.setSource(camera0);
            SmartDashboard.putNumber("source", 0);
        }
        else{
            camera.setSource(camera1);
            SmartDashboard.putNumber("source", 1);
        }



    }

    public void Stream(){
        new Thread(() -> {

            camera = CameraServer.getInstance().addSwitchedCamera("Camera Feed");
            camera0 = CameraServer.getInstance().startAutomaticCapture(0);
            camera1 = CameraServer.getInstance().startAutomaticCapture(1);
            
            camera0.setFPS(10);
            camera1.setFPS(10);
            camera0.setResolution(240, 180);
            camera1.setResolution(240, 180);

            

        }).start();
    }
}