package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbRelease extends Command {

    private final ClimbSubsystem m_climbSubsystem;
    private final Double m_leftPower;
    private final Double m_rightPower;
 
    public ClimbRelease(ClimbSubsystem subsystem, Double leftPower, Double rightPower) {
        
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
        m_climbSubsystem.Climb(m_leftPower, m_rightPower);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
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
