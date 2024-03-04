package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class Auto1 extends SequentialCommandGroup {

    private final ConveyorSubsystem m_conveyorSubsystem;
    private final DriveSubsystem m_driveSubsystem;

    public Auto1(ConveyorSubsystem cs, DriveSubsystem ds) {
    //public Auto1()  {
        m_conveyorSubsystem = cs;
        m_driveSubsystem = ds;

        addCommands(
            new WaitCommand(1),
            new ShootSpeakerHigh(m_conveyorSubsystem, 8.0),
            new WaitCommand(0.5),
            //  STEP 2:     Forward 6 * 12 = 72 (Pickup)
            new DriveStraight(62, 0.65, m_driveSubsystem),
            new WaitCommand((1.00)),
            new DriveEncoderReset(m_driveSubsystem),
            new WaitCommand((1.00)),
            //  STEP 3:     Backwars 6 * 12 = 72
            new DriveStraight(65, -0.65, m_driveSubsystem),
            new WaitCommand((0.50)),
            new DriveEncoderReset(m_driveSubsystem),
            //  STEP 4:  Shoot
            new ShootSpeakerHigh(m_conveyorSubsystem, 7.0),
            new WaitCommand((1.00)),
            new DriveStraight(10, 0.65, m_driveSubsystem),
            new DriveRotate(m_driveSubsystem,-10),
            new DriveStraight(20, 0.65, m_driveSubsystem)
 
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
