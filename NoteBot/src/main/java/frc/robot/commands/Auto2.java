package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Auto2 extends SequentialCommandGroup {

    private final ConveyorSubsystem m_conveyorSubsystem;
    private final DriveSubsystem m_driveSubsystem;

    public Auto2(ConveyorSubsystem cs, DriveSubsystem ds) {
    //public Auto1()  {
        m_conveyorSubsystem = cs;
        m_driveSubsystem = ds;

    addCommands(
        new DriveStraight(72, 0.30, m_driveSubsystem),
        new WaitCommand(0.50),
        new DriveEncoderReset(m_driveSubsystem),
        new DriveStraight(72, -0.40, m_driveSubsystem),
        new WaitCommand(0.50)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
