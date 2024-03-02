package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Auto3 extends SequentialCommandGroup {

    private final ConveyorSubsystem m_conveyorSubsystem;
    private final DriveSubsystem m_driveSubsystem;

    public Auto3(ConveyorSubsystem cs, DriveSubsystem ds) {
        m_conveyorSubsystem = cs;
        m_driveSubsystem = ds;

    addCommands(
        new DriveToAmp(m_driveSubsystem, m_conveyorSubsystem)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
