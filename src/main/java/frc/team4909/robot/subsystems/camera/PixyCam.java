package frc.team4909.robot.subsystems.camera;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.links.SPILink;

public class PixyCam extends SubsystemBase{
    
    private Pixy2 pixy;
    //Don't remove/rename the variables under
    public boolean detected;
    public int deviationFromCenter;
    
    public void initialize() {
        pixy = Pixy2.createInstance(new SPILink());
        pixy.init();
        // Enable both LEDS
        pixy.setLamp((byte) 1, (byte) 1);
    }

    // Returns object closest in distance to PixyCam
    public Block getBiggestBlock() {

        // Checks if blocks are on the screen
        int blockCount = pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 2);
        if (blockCount <= 0) { 
            detected = false;
            return null;
        }

        detected = true;
        // Get all blocks
        pixy.getCCC().getBlocks();
        ArrayList<Block> blocks = pixy.getCCC().getBlockCache();
        
        blocks.sort((Block b1, Block b2) -> b1.getWidth() - b2.getWidth());
        
        return blocks.get(0);
    }

    public PixyCam() {
        //TODO: Make a boolean that senses if the PixyCam sesnses something, also left and right of the center of the cam. 
    }

    @Override
    public void periodic(){
        Block bigBlock = this.getBiggestBlock();
        int x = bigBlock.getX();
        //TODO figure out where the xero for the x-axis is.
        deviationFromCenter = x;
    }

    public int getDeviation() {
        return deviationFromCenter;
    }

    public boolean getDetected() {
        return detected;
    }

}