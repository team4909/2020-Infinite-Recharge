package frc.team4909.robot.operator.controllers;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.team4909.robot.operator.generic.BionicPOV;

public class FlightStick extends edu.wpi.first.wpilibj.Joystick {
    
    public static BionicPOV Top = new BionicPOV(0);
    public static BionicPOV Bottom = new BionicPOV(180);
    public static BionicPOV TopRight = new BionicPOV(45);
    public static BionicPOV Right = new BionicPOV(90);
    public static BionicPOV BottomRight = new BionicPOV(135);
    public static BionicPOV TopLeft = new BionicPOV(315);
    public static BionicPOV Left = new BionicPOV(270);
    public static BionicPOV BottomLeft = new BionicPOV(225);
    
    public FlightStick(int port) {
		super(port);
	}

	public double getThresholdAxis(int axis, double deadzone){
		if(Math.abs(this.getRawAxis(axis)) > Math.abs(deadzone)){
			return this.getRawAxis(axis);
		}
		else
			return 0.0;
	}
	
	public void buttonPressed(int button, Command command){
		JoystickButton newButton = new JoystickButton(this, button);
		
		newButton.whenPressed(command);
	}
	
	public void buttonHeld(int button, Command command){
		JoystickButton newButton = new JoystickButton(this, button);
		
		newButton.whileHeld(command);
	}
	
	public void buttonToggled(int button, Command command){
		JoystickButton newButton = new JoystickButton(this, button);
		
		newButton.toggleWhenPressed(command);
	}
}