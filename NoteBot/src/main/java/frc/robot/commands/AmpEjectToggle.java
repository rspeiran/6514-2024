package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ConveyorSubsystem;

import java.util.function.DoubleSupplier;

public class AmpEjectToggle extends InstantCommand {

    private final ConveyorSubsystem m_conveyorSubsystem;


    public AmpEjectToggle(ConveyorSubsystem subsystem) {
        m_conveyorSubsystem = subsystem;
        addRequirements(m_conveyorSubsystem);    
    }

    // Called once when this command runs
    @Override
    public void initialize() {
        boolean IsEjected = m_conveyorSubsystem.IsEjected();
        if(IsEjected)
        {
            m_conveyorSubsystem.AmpRetract();
        }
        else {
            m_conveyorSubsystem.AmpEject();
        }

        if (m_conveyorSubsystem.IsConveyorLoaded())
        {
            //m_conveyorSubsystem.ConveyorUp();
            //m_conveyorSubsystem.AmpEject();
        }
        else {
            //m_conveyorSubsystem.ConveyorDown();
        }


    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
