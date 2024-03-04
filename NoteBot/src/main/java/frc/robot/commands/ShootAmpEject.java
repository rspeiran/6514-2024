package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;

public class ShootAmpEject extends Command {

    private final ConveyorSubsystem m_conveyorSubsystem;
    private final Double m_power;

    public ShootAmpEject(ConveyorSubsystem subsystem, double power) {

        m_conveyorSubsystem = subsystem;
        m_power = power;
        addRequirements(m_conveyorSubsystem);      

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_conveyorSubsystem.ConveryorLowOn(0);
        m_conveyorSubsystem.ConveryorHighOn(0);
        m_conveyorSubsystem.ShooterOn(0);

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        boolean IsEjected = m_conveyorSubsystem.IsEjected();
        if(IsEjected)
        {
            m_conveyorSubsystem.AmpEject();
        }
        m_conveyorSubsystem.AmpEject();

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(m_conveyorSubsystem.IsEjected())
        {
            return true;
        }
        else {
            return false;
        }    
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}
