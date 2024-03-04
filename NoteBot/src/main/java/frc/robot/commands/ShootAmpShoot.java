package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;

public class ShootAmpShoot extends Command {

    private final ConveyorSubsystem m_conveyorSubsystem;
    private double m_power;

    public ShootAmpShoot(ConveyorSubsystem subsystem, double power) {
        m_conveyorSubsystem = subsystem;
        m_power = power;
        addRequirements(m_conveyorSubsystem);      
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_conveyorSubsystem.m_state = 2;
        m_conveyorSubsystem.ConveyorMotorBrake();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_conveyorSubsystem.ConveyorUp();
        m_conveyorSubsystem.ConveryorLowOn(3.25);
        m_conveyorSubsystem.ConveryorHighOn(3.75);
        m_conveyorSubsystem.ShooterOn(1.50);

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(m_conveyorSubsystem.IsConveyorLoaded())
        {
            return false;
        }
        else {
            return true;    
        }
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}
