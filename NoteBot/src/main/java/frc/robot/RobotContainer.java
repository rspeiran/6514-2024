package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

public class RobotContainer {

  private static RobotContainer m_robotContainer = new RobotContainer();

  public final ConveyorSubsystem m_conveyorSubsystem = new ConveyorSubsystem();
  public final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  public final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();

  // Joysticks
  //private final PS4Controller operatorPS4Controller = new PS4Controller(1);
  private final PS4Controller driverPS4Controller = new PS4Controller(0);

  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();


  private RobotContainer() {
    
    Shuffleboard.selectTab("NoteBot");

    Shuffleboard.selectTab("NoteBot Commands");

    SmartDashboard.putData("AutoCommand", new AutoCommand());
    SmartDashboard.putData("Auto1", new Auto1());
    SmartDashboard.putData("Auto2", new Auto2());
    SmartDashboard.putData("Auto3", new Auto3());
    SmartDashboard.putData("Auto4", new Auto4());
    SmartDashboard.putData("Auto5", new Auto5());
    //SmartDashboard.putData("Climb", new Climb( m_conveyorSubsystem, 1,1 ));
    SmartDashboard.putData("DriveToAmp", new DriveToAmp( m_driveSubsystem ));
    SmartDashboard.putData("Pickup", new Pickup( m_conveyorSubsystem ));
    SmartDashboard.putData("PickUpAuto", new PickUpAuto());
    SmartDashboard.putData("ShootAmp", new ShootAmp(m_conveyorSubsystem, 1));
    SmartDashboard.putData("ShootSpeakerHigh", new ShootSpeakerHigh(m_conveyorSubsystem, 5));
    SmartDashboard.putData("ShootSpeekerLong", new ShootSpeekerLong());
    //SmartDashboard.putData("DriveForwardMeters", new DriveForwardMeters( m_driveSubsystem ));
    //SmartDashboard.putData("DriveRotateMeters", new DriveRotateMeters( m_driveSubsystem ));

    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    m_driveSubsystem.setDefaultCommand(
      new DriveTeleop(m_driveSubsystem,
      () -> driverPS4Controller.getLeftY(), 
      () -> driverPS4Controller.getRightY())
    );

    m_climbSubsystem.setDefaultCommand(
      new Climb(m_climbSubsystem,
      () -> driverPS4Controller.getRawAxis(2), 
      () -> driverPS4Controller.getRawAxis(3))
    );


    // Configure autonomous sendable chooser
    m_chooser.addOption("Auto1", new Auto1());
    m_chooser.addOption("Auto2", new Auto2());
    m_chooser.addOption("Auto3", new Auto3());
    m_chooser.addOption("Auto4", new Auto4());
    m_chooser.addOption("Auto5", new Auto5());
    m_chooser.addOption("AutoCommand", new AutoCommand());
    m_chooser.setDefaultOption("AutoCommand", new AutoCommand());


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

    final JoystickButton btnDrive1 = new JoystickButton(driverPS4Controller, PS4Controller.Button.kSquare.value);        
    btnDrive1.onTrue(new ShootSpeakerHigh(m_conveyorSubsystem, 7.0).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
    final JoystickButton btnDrive2 = new JoystickButton(driverPS4Controller, PS4Controller.Button.kCircle.value);        
    btnDrive2.onTrue(new ShootAmp(m_conveyorSubsystem, 2).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                            
    //final JoystickButton btnDrive3 = new JoystickButton(driverPS4Controller, PS4Controller.Button.kSquare.value);        
    //btnDrive3.onTrue(new DoNothing().withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                            
    //final JoystickButton btnDrive4 = new JoystickButton(driverPS4Controller, PS4Controller.Button.kSquare.value);        
    //btnDrive4.onTrue(new DoNothing().withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                            
    //final JoystickButton btnOperator1 = new JoystickButton(operatorPS4Controller, PS4Controller.Button.kSquare.value);        
    //btnOperator1.onTrue(new DoNothing().withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                            
    //final JoystickButton btnOperator2 = new JoystickButton(operatorPS4Controller, PS4Controller.Button.kSquare.value);        
    //btnOperator2.onTrue(new DoNothing().withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                            
    //final JoystickButton btnOperator3 = new JoystickButton(operatorPS4Controller, PS4Controller.Button.kSquare.value);        
    //btnOperator3.onTrue(new DoNothing().withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                            
    //final JoystickButton btnOperator4 = new JoystickButton(operatorPS4Controller, PS4Controller.Button.kSquare.value);        
    //btnOperator4.onTrue(new DoNothing().withInterruptBehavior(InterruptionBehavior.kCancelSelf));
    

  }


  public PS4Controller getDrivePS4Controller() {
    return driverPS4Controller;
  }

  //public PS4Controller getOperatorPS4Controller() {
  //  return operatorPS4Controller;
  //}

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

