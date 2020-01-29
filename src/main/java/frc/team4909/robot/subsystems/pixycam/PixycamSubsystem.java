package frc.team4909.robot.subsystems.pixycam;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

public class PixycamSubsystem extends SubsystemBase {

    public Pixy2 pixycam;
    double xcoord, ycoord;
    boolean isCamera = false;
    // private SPILink spi;
    int state = -1;

    public PixycamSubsystem() {
        super();
        pixycam = Pixy2.createInstance(Pixy2.LinkType.SPI);
        pixycam.init(1);
    }

    @Override
    public void periodic() {
        if (!isCamera){
            System.out.println("no Camera");
            state = pixycam.init(1);
        }
        isCamera = state >= 0;
        SmartDashboard.putBoolean("Camera", isCamera);
        pixycam.getCCC().getBlocks(false, 255, 255);
        ArrayList<Block> blocks = pixycam.getCCC().getBlocks();
        if (blocks.size() > 0) {
            xcoord = blocks.get(0).getX(); // x position of the largest

            ycoord = blocks.get(0).getY(); // y position of the largest

            SmartDashboard.putBoolean("present", true);

            SmartDashboard.putNumber("Xccord", xcoord);
            SmartDashboard.putNumber("Ycoord", ycoord);
        } else{
            SmartDashboard.putBoolean("present", false);
            SmartDashboard.putNumber("size", blocks.size());
        }
    

    }

    public double getX(){
    return xcoord;
    }

    public double getY(){
    return ycoord;
    }

}