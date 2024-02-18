package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;

import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;

public class Climb extends Command {

    private final ClimbSubsystem m_climbSubsystem;
    private final DoubleSupplier m_leftPower;
    private final DoubleSupplier m_rightPower;
 
    public Climb(ClimbSubsystem subsystem, DoubleSupplier leftPower, DoubleSupplier rightPower) {
        
        m_climbSubsystem = subsystem;
        
        m_leftPower = leftPower;
        m_rightPower = rightPower;

        addRequirements(m_climbSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //m_driveSubsystem.drive(m_left.getAsDouble(), m_right.getAsDouble());
        m_climbSubsystem.Climb(m_leftPower.getAsDouble(), m_rightPower.getAsDouble());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}
