package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;

import frc.robot.subsystems.DriveSubsystem;

public class DriveTeleop extends Command {

    private final DriveSubsystem m_driveSubsystem;
    private final double m_left;
    private final double m_right;

    public DriveTeleop(DriveSubsystem subsystem, DoubleSupplier left, DoubleSupplier right) {


        m_driveSubsystem = subsystem;
        m_left = left.getAsDouble();
        m_right = right.getAsDouble();
        addRequirements(m_driveSubsystem);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_driveSubsystem.drive(m_left, m_right);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_driveSubsystem.drive(0, 0);
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
