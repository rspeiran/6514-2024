package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;

import frc.robot.subsystems.DriveSubsystem;

public class DriveForwardMeters extends Command {

    private final DriveSubsystem m_driveSubsystem;
    private double m_inches;
    private double m_power;
 
    public DriveForwardMeters(double inches, double power, DriveSubsystem subsystem) {

        m_inches = inches;
        m_power = power;

        m_driveSubsystem = subsystem;
        addRequirements(m_driveSubsystem);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_driveSubsystem.DriveEncoderReset();
        m_driveSubsystem.SetMotorBrake();
        //m_driveSubsystem.driveArcade(m_power, 0);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_driveSubsystem.driveArcade(m_power, 0);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_driveSubsystem.driveArcade(0,0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return  Math.abs(m_driveSubsystem.getAverageDistance()) >= Math.abs(m_inches);
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
