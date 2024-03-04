package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ConveyorSubsystem;

public class AutoAmpShoot extends SequentialCommandGroup {
    private final ConveyorSubsystem m_conveyorSubsystem;
    private final Double m_power;

    public AutoAmpShoot(ConveyorSubsystem cs, double power) {
        m_conveyorSubsystem = cs;
        m_power = power;

        addCommands(
            new ShootAmpShoot(m_conveyorSubsystem, m_power),
            new WaitCommand(0.50),
            new ShootAmpEject(m_conveyorSubsystem, m_power),
            new WaitCommand(0.50),
            new ShootAmpRetract(m_conveyorSubsystem, m_power),
            new WaitCommand(0.50)
        );
    }


    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}
