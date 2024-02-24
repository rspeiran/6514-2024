package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveSubsystem;


public class Auto1 extends SequentialCommandGroup {

    //private final ConveyorSubsystem m_conveyorSubsystem;
    //private final DriveSubsystem m_driveSubsystem;

    public Auto1() {

        //ConveyorSubsystem conveyorSubsystem, DriveSubsystem driveSubsystem
       // m_conveyorSubsystem = conveyorSubsystem;
       // m_driveSubsystem = driveSubsystem;


    addCommands(
        // Add Commands here:
        // Also add parallel commands using the
        //
        // addCommands(
        //      new command1(argsN, subsystem),
        //      Commands.parallel(
        //          new command2(argsN, subsystem),
        //          new command3(argsN, subsystem)
        //      )    
        //  );
        //  STEP 1:   Shoot
        //new ShootSpeakerHigh(m_conveyorSubsystem, 7.0),
        //  STEP 2:     Forward 6 * 12 = 72 (Pickup)
        //new DriveForwardMeters(72, 0.40, m_driveSubsystem),
        //  STEP 3:     Backwars 6 * 12 = 72
        //new DriveForwardMeters(-72, 0.40, m_driveSubsystem),
        //  STEP 4:  Shoot
        //new ShootSpeakerHigh(m_conveyorSubsystem, 7.0)

        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
