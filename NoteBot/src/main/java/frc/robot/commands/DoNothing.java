package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import java.util.function.DoubleSupplier;

public class DoNothing extends InstantCommand {

    public DoNothing() {
        // m_subsystem = subsystem;
        // addRequirements(m_subsystem);    
    }

    // Called once when this command runs
    @Override
    public void initialize() {
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
