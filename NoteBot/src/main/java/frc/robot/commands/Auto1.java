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
        new WaitCommand(2),
        new ShootSpeakerHigh(m_conveyorSubsystem, 7.0),
        new WaitCommand(2),
        //  STEP 2:     Forward 6 * 12 = 72 (Pickup)
        new DriveStraight(72, 0.60, m_driveSubsystem),   //MOVED 95 INCHES
        new WaitCommand((0.25)),
        //  STEP 3:     Backwars 6 * 12 = 72
        new DriveStraight(72, -0.60, m_driveSubsystem),
        new WaitCommand(0.25),
        //  STEP 4:  Shoot
        new ShootSpeakerHigh(m_conveyorSubsystem, 7.0)

        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
