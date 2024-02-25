package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DriveRotate extends Command {

    private final DriveSubsystem m_driveSubsystem;
    private double m_rotateToAngleRate;

    private double error;
    private double kP;
    private double minMove;
 
    public DriveRotate(double rotateToAngleRate, DriveSubsystem subsystem) {

        m_rotateToAngleRate = rotateToAngleRate;
        m_driveSubsystem = subsystem;
        addRequirements(m_driveSubsystem);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        kP = 0.05;
        minMove = 0.3; 
        
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        error = m_rotateToAngleRate - m_driveSubsystem.ahrs.getYaw();
        m_driveSubsystem.drive(minMove + kP * error, -1 * minMove + kP * error);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
         m_driveSubsystem.drive(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (Math.abs(error) < 2){
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
