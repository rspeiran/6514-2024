package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;

public class ShootAmpRetract extends Command {

    private final ConveyorSubsystem m_conveyorSubsystem;
    private final Double m_power;

    public ShootAmpRetract(ConveyorSubsystem subsystem, double power) {

        m_conveyorSubsystem = subsystem;
        m_power = power;
        addRequirements(m_conveyorSubsystem);      

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_conveyorSubsystem.AmpRetract();

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_conveyorSubsystem.ConveyorDown();
        m_conveyorSubsystem.ConveryorLowOn(0);
        m_conveyorSubsystem.ConveryorHighOn(0);
        m_conveyorSubsystem.ShooterOn(0);
        m_conveyorSubsystem.m_state=0;   ///TEMP Should be 0
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;    
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}
