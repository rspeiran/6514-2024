package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;

import java.util.function.DoubleSupplier;

public class ShootAmp extends Command {

    private final ConveyorSubsystem m_conveyorSubsystem;
    private final Double m_power;
    private int counter = 0;
    private int pushcount = 0;

    public ShootAmp(ConveyorSubsystem subsystem, double power) {

        m_conveyorSubsystem = subsystem;
        m_power = power;
        addRequirements(m_conveyorSubsystem);      

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        counter = 0;
        pushcount = 0;
        m_conveyorSubsystem.m_state = 2;
        m_conveyorSubsystem.ConveyorUp();
        m_conveyorSubsystem.ConveyorMotorBrake();
        

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //lift the conveyor
        m_conveyorSubsystem.ConveyorUp();

        counter = counter +1;

        //STEP 1 - Get the Conveyor UP
        if (counter < 100)
        {
            //Give cycles for conveyor to position
        }
        //STEP 2 - Move Note to Shooter
        else if (m_conveyorSubsystem.IsConveyorLoaded() ) {
            m_conveyorSubsystem.ConveryorLowOn(3.00);
            m_conveyorSubsystem.ConveryorHighOn(2.75);
            m_conveyorSubsystem.ShooterOn(0.0);

        }
        //STEP 3 - Stop Conveyor and Push 
        else if (!m_conveyorSubsystem.IsConveyorLoaded() && pushcount < 60) {
            m_conveyorSubsystem.ConveryorLowOn(0);
            m_conveyorSubsystem.ConveryorHighOn(1.25);
            m_conveyorSubsystem.ShooterOn(0);
            pushcount = pushcount + 1;
        }
        else if (pushcount >= 60 && pushcount < 100)
        {
            m_conveyorSubsystem.ConveryorHighOn(1.75);             
            m_conveyorSubsystem.ShooterOn(1.75);
            pushcount = pushcount + 1;
        }
        
        if (counter > 270 && counter < 470)
        {
            m_conveyorSubsystem.AmpEject();
            pushcount = pushcount + 1;

        }
        if (counter > 470 && counter < 500)
        {
            pushcount = pushcount + 1;
            m_conveyorSubsystem.AmpRetract();
        }
        if (counter > 415 && counter < 600)
        {
            m_conveyorSubsystem.ConveyorDown();
            pushcount = pushcount + 1;

        }





        //STEP 5 - Shoting Must have failed... Get it out!
        
        if (counter > 1000){
            m_conveyorSubsystem.ConveryorLowOn(2);
            m_conveyorSubsystem.ConveryorHighOn(2.6);
            m_conveyorSubsystem.ShooterOn(3.0);  

        }


    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_conveyorSubsystem.ConveyorDown();
        m_conveyorSubsystem.ConveryorLowOn(0);
        m_conveyorSubsystem.ConveryorHighOn(0);
        m_conveyorSubsystem.ShooterOn(0);
        m_conveyorSubsystem.m_state=0;   ///TEMP Should be 0
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(m_conveyorSubsystem.IsConveyorLoaded() || counter < 1000)
        {
            return false;
        }
        else {
            return true;    
        }
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}
