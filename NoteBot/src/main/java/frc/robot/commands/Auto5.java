package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class Auto5 extends SequentialCommandGroup {
    private final ConveyorSubsystem m_conveyorSubsystem;
    private final DriveSubsystem m_driveSubsystem;

    public Auto5(ConveyorSubsystem cs, DriveSubsystem ds){
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

        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}
