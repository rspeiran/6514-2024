package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;

import java.util.function.DoubleSupplier;

public class ShootAmp extends Command {

    private final ConveyorSubsystem m_conveyorSubsystem;
    private final Double m_power;
    private int counter = 0;

    public ShootAmp(ConveyorSubsystem subsystem, double power) {

        m_conveyorSubsystem = subsystem;
        m_power = power;
        addRequirements(m_conveyorSubsystem);      

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        counter = 0;
        m_conveyorSubsystem.m_state = 2;
        m_conveyorSubsystem.ConveyorUp();
        

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //lift the conveyor
        counter = counter +1;
        if (counter > 400)
        {
            m_conveyorSubsystem.ConveyorUp();
            m_conveyorSubsystem.ConveryorLowOn(2);
            m_conveyorSubsystem.ConveryorHighOn(2.6);
            m_conveyorSubsystem.ShooterOn(0.0);

        }

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_conveyorSubsystem.ConveyorDown();
        m_conveyorSubsystem.m_state=0;
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(m_conveyorSubsystem.IsConveyorLoaded() && counter < 600)
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
