// package frc.team4909.robot.subsystems.climber.commands;

// import com.revrobotics.CANPIDController;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.team4909.robot.Robot;

// public class HookIn extends CommandBase{

//     CANPIDController hookPID;

//     public HookIn() {
//         super();
//         addRequirements(Robot.climberSubsystem);
//     }

//     @Override
//     public void execute() {
//         Robot.climberSubsystem.setHookSpeed(-0.2);
//     }

//     @Override
//     public void end(boolean interupted){
//         Robot.climberSubsystem.setHookSpeed(0);
//     }
// }
