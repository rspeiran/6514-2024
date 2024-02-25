package frc.robot.subsystems;

import frc.robot.commands.*;
//import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj.shuffleboard.WidgetType;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import java.util.Map;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

public class DriveSubsystem extends SubsystemBase {

    private WPI_TalonSRX driveRightMotorController;
    private WPI_TalonSRX driveLeftMotorController;
    private DifferentialDrive differentialDrive;
    private WPI_VictorSPX driveRightMotorControllerFollower;
    private WPI_VictorSPX driveLeftMotorControllerFollower;
    //private Encoder driveLeftQuadratureEncoder;
    //private Encoder driveRightQuadratureEncoder;
    //private AnalogGyro analogGyro;

    //private static final double cpr = 1024;
    //private static final double whd = 6; // for 6 inch wheel

    private AHRS ahrs;
    private double compassheading;

    //Shuffleboard Configuration
    private ShuffleboardTab NoteBotTab = Shuffleboard.getTab("NoteBot");
   
    private GenericEntry LeftEncoderEntry = NoteBotTab.add("Left Encoder", 0)
        .withPosition(4, 1)
        .getEntry();

    private GenericEntry RightEncoderEntry = NoteBotTab.add("Right Encoder", 0)
        .withPosition(5, 1)
        .getEntry();

    private GenericEntry LeftEncoderDistanceEntry = NoteBotTab.add("Left Distance", 0)
        .withPosition(4, 2)
        .getEntry();

    private GenericEntry RightEncoderDistanceEntry = NoteBotTab.add("Right Distance", 0)
        .withPosition(5, 2)
        .getEntry();

    private GenericEntry CompassHeadingEntry = NoteBotTab.add("Compass", 0)
        .withPosition(7, 1)
        .getEntry();


        //Shuffleboard Configuration End

    public DriveSubsystem() {

        driveRightMotorController = new WPI_TalonSRX(30);
        driveRightMotorController.setInverted(false);
        driveRightMotorController.setNeutralMode(NeutralMode.Coast);
        driveRightMotorController.setSelectedSensorPosition(0);

        driveLeftMotorController = new WPI_TalonSRX(20);
        driveLeftMotorController.setInverted(false);
        driveLeftMotorController.setNeutralMode(NeutralMode.Coast);
        driveLeftMotorController.setSelectedSensorPosition(0);

        differentialDrive = new DifferentialDrive(driveRightMotorController, driveLeftMotorController);
        differentialDrive.setSafetyEnabled(false);
        differentialDrive.setExpiration(0.1);
        differentialDrive.setMaxOutput(1.0);

        driveRightMotorControllerFollower = new WPI_VictorSPX(31);
        driveRightMotorControllerFollower.follow(driveRightMotorController);
        driveRightMotorControllerFollower.setInverted(false);
        driveRightMotorControllerFollower.setNeutralMode(NeutralMode.Coast);

        driveLeftMotorControllerFollower = new WPI_VictorSPX(21);
        driveLeftMotorControllerFollower.follow(driveLeftMotorController);
        driveLeftMotorControllerFollower.setInverted(false);
        driveLeftMotorControllerFollower.setNeutralMode(NeutralMode.Coast);

        ahrs = new AHRS(SerialPort.Port.kMXP); /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */
        ahrs.zeroYaw(); //
        //analogGyro = new AnalogGyro(0);
        //analogGyro.setSensitivity(0.007);

    }

    @Override
    public void periodic() {
        compassheading = ahrs.getAngle(); //ahrs.getCompassHeading();

        
        
        //UPDATE SHUFFLEBOARD START

        LeftEncoderEntry.setDouble(GetLeftEncoder());
        RightEncoderEntry.setDouble(GetRightEncoder());
        
        LeftEncoderDistanceEntry.setDouble(GetLeftEncoderDistance());
        RightEncoderDistanceEntry.setDouble(GetRightEncoderDistance());
        
        CompassHeadingEntry.setDouble(compassheading);
        //UPDATE SHUFFLEBOARD END

        

    }

    @Override
    public void simulationPeriodic() {
        //double distLeft = driveLeftQuadratureEncoder.getDistance();
        //double distLeft = driveLeftMotorController.getSelectedSensorPosition();
        //SmartDashboard.putNumber("Left Encoder", distLeft);
        
        //double distRight = driveLeftQuadratureEncoder.getDistance();
        //double distRight = driveRightMotorController.getSelectedSensorPosition();
        //SmartDashboard.putNumber("Left Encoder", distRight);
        
    }

    public void drive(double left, double right) {
        differentialDrive.tankDrive(left, right);
    }

    public void driveArcade(double power, double rotation) {
        differentialDrive.arcadeDrive(-power, rotation);
      }

    public double GetLeftEncoder() {
        return driveRightMotorController.getSensorCollection().getQuadraturePosition() * -1;
    }
    public double GetRightEncoder() {
        return driveLeftMotorController.getSensorCollection().getQuadraturePosition();

    }
    public double GetLeftEncoderDistance() {
        double distance = GetLeftEncoder() / (4096 / 18.849556); //6Xpi
        return distance;
    }
    public double GetRightEncoderDistance() {
        double distance = GetRightEncoder() / (4096 / 18.849556); //6Xpi
        return distance;
    }

    public double getAverageDistance() {
        return (GetLeftEncoderDistance() + GetRightEncoderDistance()) / 2.0;
      }

    public void DriveEncoderReset() {
        driveRightMotorController.setSelectedSensorPosition(0);
        driveLeftMotorController.setSelectedSensorPosition(0);
    }

    public void SetMotorBrake() {
        driveRightMotorController.setNeutralMode(NeutralMode.Brake);
        driveRightMotorControllerFollower.setNeutralMode(NeutralMode.Brake);
        driveLeftMotorController.setNeutralMode(NeutralMode.Brake);
        driveLeftMotorControllerFollower.setNeutralMode(NeutralMode.Brake);

    }
}

