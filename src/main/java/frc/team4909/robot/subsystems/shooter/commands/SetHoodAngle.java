package frc.team4909.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team4909.robot.Robot;

public class SetHoodAngle extends CommandBase{

    double distance;
    double interpola;
    double interpolb;
    double interpolc;

    public SetHoodAngle(double d, double a, double b, double c){
        distance = d;
        interpola = a;
        interpolb = b;
        interpolc = c;
    }

    public double geta()
    {
        return interpola;
    }

    public double getb()
    {
        return interpolb;
    }

    public double getc()
    {
        return interpolc;
    }

    public void initialize(){
        double position = interpola*Math.pow(distance, 2)+interpolb*Math.pow(distance, 1)+interpolc;
        Robot.hoodSubsystem.setHoodPosition((int)(position));
    }
}