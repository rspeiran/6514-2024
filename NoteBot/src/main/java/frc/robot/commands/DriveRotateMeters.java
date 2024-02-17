package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;
import frc.robot.subsystems.DriveSubsystem;

public class DriveRotateMeters extends Command {

    private final DriveSubsystem m_driveSubsystem;
    private double m_LeftMeters;
    private double m_RightMeters;
 
    public DriveRotateMeters(double LeftMeters, double RightMeters, DriveSubsystem subsystem) {

        m_LeftMeters = LeftMeters;
        m_RightMeters = RightMeters;

        m_driveSubsystem = subsystem;
        addRequirements(m_driveSubsystem);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
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
