package frc.team4909.robot.operator.controllers;

import frc.team4909.robot.operator.generic.BionicAxis;
import frc.team4909.robot.operator.generic.BionicButton;
import frc.team4909.robot.operator.generic.BionicJoystick;
import frc.team4909.robot.operator.generic.BionicPOV;

public class FlightStick extends BionicJoystick {
    
    public static BionicPOV Top = new BionicPOV(0);
    public static BionicPOV Bottom = new BionicPOV(180);
    public static BionicPOV TopRight = new BionicPOV(45);
    public static BionicPOV Right = new BionicPOV(90);
    public static BionicPOV BottomRight = new BionicPOV(135);
    public static BionicPOV TopLeft = new BionicPOV(315);
    public static BionicPOV Left = new BionicPOV(270);
	public static BionicPOV BottomLeft = new BionicPOV(225);
	
	public static BionicButton One = new BionicButton(1);
    public static BionicButton Two = new BionicButton(2);
    public static BionicButton Three = new BionicButton(3);
	public static BionicButton Four = new BionicButton(4);
	public static BionicButton Five = new BionicButton(5);
    public static BionicButton Six = new BionicButton(6);
    public static BionicButton Seven = new BionicButton(7);
	public static BionicButton Eight = new BionicButton(8);
	public static BionicButton Nine = new BionicButton(9);
    public static BionicButton Ten = new BionicButton(10);
    public static BionicButton Eleven = new BionicButton(11);
    public static BionicButton Twelve = new BionicButton(12);
	
    public static BionicAxis Z = new BionicAxis(2);
    public static BionicAxis Slider = new BionicAxis(3);

    public FlightStick(int port, double deadzone, double sensitivity) {
		super(port, deadzone, sensitivity);
	}
}