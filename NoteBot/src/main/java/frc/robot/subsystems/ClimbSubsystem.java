package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Spark;


public class ClimbSubsystem extends SubsystemBase {
    
    private Spark climbLeftMotorController;
    private Spark climbRightMotorController;

    public ClimbSubsystem() {

        climbLeftMotorController = new Spark(2);
        climbLeftMotorController.setInverted(true);

        climbRightMotorController = new Spark(3);
        climbRightMotorController.setInverted(true);

    }

    @Override
    public void periodic() {
    }

    @Override
    public void simulationPeriodic() {
        
    }

    public void Climb(double left, double right) {
        climbLeftMotorController.set(left);
        climbRightMotorController.set(right);
        
    }
}

