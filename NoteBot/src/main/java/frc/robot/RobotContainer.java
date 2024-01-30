// RobotBuilder Version: 6.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: RobotContainer.

package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private static RobotContainer m_robotContainer = new RobotContainer();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
// The robot's subsystems
    public final ConveyorSubsystem m_conveyorSubsystem = new ConveyorSubsystem();
    public final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

// Joysticks
private final PS4Controller operatorPS4Controller = new PS4Controller(1);
private final PS4Controller drivePS4Controller = new PS4Controller(0);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Smartdashboard Subsystems


    // SmartDashboard Buttons
    SmartDashboard.putData("AutoCommand", new AutoCommand());
    SmartDashboard.putData("Auto1", new Auto1());
    SmartDashboard.putData("Auto2", new Auto2());
    SmartDashboard.putData("Auto3", new Auto3());
    SmartDashboard.putData("Auto4", new Auto4());
    SmartDashboard.putData("Auto5", new Auto5());
    SmartDashboard.putData("Climb", new Climb( m_conveyorSubsystem ));
    SmartDashboard.putData("DriveToAmp", new DriveToAmp( m_driveSubsystem ));
    SmartDashboard.putData("Pickup", new Pickup( m_conveyorSubsystem ));
    SmartDashboard.putData("PickUpAuto", new PickUpAuto());
    SmartDashboard.putData("ShootAmp", new ShootAmp());
    SmartDashboard.putData("ShootSpeakerHigh", new ShootSpeakerHigh());
    SmartDashboard.putData("ShootSpeekerLong", new ShootSpeekerLong());
    //SmartDashboard.putData("DriveForwardMeters", new DriveForwardMeters( m_driveSubsystem ));
    //SmartDashboard.putData("DriveRotateMeters", new DriveRotateMeters( m_driveSubsystem ));

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    m_chooser.addOption("Auto1", new Auto1());
    m_chooser.addOption("Auto2", new Auto2());
    m_chooser.addOption("Auto3", new Auto3());
    m_chooser.addOption("Auto4", new Auto4());
    m_chooser.addOption("Auto5", new Auto5());
    m_chooser.setDefaultOption("AutoCommand", new AutoCommand());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    SmartDashboard.putData("Auto Mode", m_chooser);
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
// Create some buttons
final JoystickButton btnDrive1 = new JoystickButton(drivePS4Controller, PS4Controller.Button.kSquare.value);        
btnDrive1.onTrue(new DoNothing().withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton btnDrive2 = new JoystickButton(drivePS4Controller, PS4Controller.Button.kSquare.value);        
btnDrive2.onTrue(new DoNothing().withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton btnDrive3 = new JoystickButton(drivePS4Controller, PS4Controller.Button.kSquare.value);        
btnDrive3.onTrue(new DoNothing().withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton btnDrive4 = new JoystickButton(drivePS4Controller, PS4Controller.Button.kSquare.value);        
btnDrive4.onTrue(new DoNothing().withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton btnOperator1 = new JoystickButton(operatorPS4Controller, PS4Controller.Button.kSquare.value);        
btnOperator1.onTrue(new DoNothing().withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton btnOperator2 = new JoystickButton(operatorPS4Controller, PS4Controller.Button.kSquare.value);        
btnOperator2.onTrue(new DoNothing().withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton btnOperator3 = new JoystickButton(operatorPS4Controller, PS4Controller.Button.kSquare.value);        
btnOperator3.onTrue(new DoNothing().withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton btnOperator4 = new JoystickButton(operatorPS4Controller, PS4Controller.Button.kSquare.value);        
btnOperator4.onTrue(new DoNothing().withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
  }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public PS4Controller getDrivePS4Controller() {
      return drivePS4Controller;
    }

public PS4Controller getOperatorPS4Controller() {
      return operatorPS4Controller;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }
  

}
