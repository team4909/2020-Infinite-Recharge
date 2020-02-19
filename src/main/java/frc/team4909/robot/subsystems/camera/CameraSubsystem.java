package frc.team4909.robot.subsystems.camera;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.CvSink;

import org.opencv.core.Core;
import org.opencv.core.*;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

public class CameraSubsystem {

    boolean toggle = true;
boolean climb = true; // set to F
    MjpegServer camera;
    UsbCamera camera0;
    UsbCamera camera1;
    UsbCamera climbCamera;
    CvSink inSink;
    Mat imgWithLine;
    CvSource outImg;

    public CameraSubsystem() {

    }

    public void isClimbing() {
        climb = true;
    }

    public void toggleCamera() {
        toggle = !toggle; // rename to the correct direction (e.g. front/back) after testing.
        System.out.print("TOGGLED::::::     ");
        System.out.println(toggle);

        if (climb) {
            camera.setSource(climbCamera);
            SmartDashboard.putNumber("source", 2);
        } else if (toggle) {
            camera.setSource(camera0);
            SmartDashboard.putNumber("source", 0);
        } else {
            camera.setSource(camera1);
            SmartDashboard.putNumber("source", 1);
        }
    }

    public void Stream() {
        new Thread(() -> {

            camera = CameraServer.getInstance().addSwitchedCamera("Camera Feed");
            camera0 = CameraServer.getInstance().startAutomaticCapture(0);
            camera1 = CameraServer.getInstance().startAutomaticCapture(1);
            climbCamera = CameraServer.getInstance().startAutomaticCapture(2);
            // add view of lielight for jeff...
            camera0.setFPS(10);
            camera1.setFPS(10);
            climbCamera.setFPS(20);
            camera0.setResolution(240, 180);
            camera1.setResolution(240, 180);
            climbCamera.setResolution(240, 180);


            // Add BIG RED LIINNNNEEEE to matt's climb cam

            inSink = new CvSink("Climb Camera");
            inSink.setSource(climbCamera);
            inSink.grabFrame(imgWithLine);


            Imgproc.line(imgWithLine, new Point(0, 120), new Point(239, 120), new Scalar(0.3), 3);

            outImg = new CvSource("Blur", PixelFormat.kMJPEG, 240, 180, 20);
            outImg.putFrame(imgWithLine);

        }).start();
    }
}