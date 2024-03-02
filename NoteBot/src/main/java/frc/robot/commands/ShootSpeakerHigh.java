package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;

public class ShootSpeakerHigh extends Command {

    private final ConveyorSubsystem m_conveyorSubsystem;
    private final Double m_power;
    private int m_shootCount;

    public ShootSpeakerHigh(ConveyorSubsystem subsystem, double power) {

        m_conveyorSubsystem = subsystem;
        m_power = power;
        addRequirements(m_conveyorSubsystem);    

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_shootCount = 0;
        m_conveyorSubsystem.m_state = 2;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_conveyorSubsystem.ConveryorLowOn(m_power);
        m_conveyorSubsystem.ConveryorHighOn(m_power);
        m_conveyorSubsystem.ShooterOn(m_power + 1);
        m_shootCount = m_shootCount +1;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_conveyorSubsystem.m_state=0;
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        //if(m_conveyorSubsystem.IsConveyorLoaded()  && m_shootCount > 50)
        if(!m_conveyorSubsystem.IsConveyorLoaded()  && m_shootCount > 35)

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
