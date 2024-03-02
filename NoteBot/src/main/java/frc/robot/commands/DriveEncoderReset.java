package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;

import frc.robot.subsystems.DriveSubsystem;


public class DriveEncoderReset extends Command {

    private final DriveSubsystem m_driveSubsystem;
    private int m_count = 0;
 
    public DriveEncoderReset(DriveSubsystem subsystem) {
 
        m_driveSubsystem = subsystem;
        addRequirements(m_driveSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_driveSubsystem.DriveEncoderReset();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_count = m_count + 1;
        m_driveSubsystem.driveArcade(0,0);

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (m_count >= 15)
        {
             return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
