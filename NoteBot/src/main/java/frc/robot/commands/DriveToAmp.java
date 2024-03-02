package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;

import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class DriveToAmp extends Command {

    private final DriveSubsystem m_driveSubsystem;
    private final ConveyorSubsystem m_conveyorSubsystem;

    private double m_minForward = 0.25;
    private double m_maxForward = 0.75;
    private double m_distanceLoss = 0.00025;
    private double m_rotationGain = 0.00025;
    
    private double m_forward;
    private double m_rotate;



    public DriveToAmp(DriveSubsystem drivesubsystem, ConveyorSubsystem conveyorSubsystem) {

        m_driveSubsystem = drivesubsystem;
        m_conveyorSubsystem = conveyorSubsystem;
        addRequirements(m_driveSubsystem);
        addRequirements(m_driveSubsystem);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //As target gets larger - slow down
        m_forward = m_maxForward - (m_driveSubsystem.m_area * m_distanceLoss);
        if (m_forward < 0) {
            m_forward = 0;
        }  
        else if (m_forward < m_minForward)  {
            m_forward = m_minForward;
        }
        else if (m_forward > m_maxForward) {
            m_forward = m_maxForward;
        }

        //Calculate rotation
        m_rotate = m_driveSubsystem.m_pitch * m_rotationGain;
        if (m_rotate < - 0.25) {
            m_rotate = -0.25;
        }
        else if (m_rotate > 0.25) {
            m_rotate = 0.25;
        }

        m_driveSubsystem.driveArcade(m_forward, m_rotate);
    
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_driveSubsystem.drive(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (m_driveSubsystem.m_hasTargets)
        {
            return false;
        }
        else if (Math.abs(m_rotationGain) < 3  && m_driveSubsystem.m_area > 500 ) {
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
