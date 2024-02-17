package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class DriveSubsystem extends SubsystemBase {

    private WPI_TalonSRX driveRightMotorControler;
    private WPI_TalonSRX driveLeftMotorController;
    private DifferentialDrive differentialDrive;
    private WPI_VictorSPX driveRightMotorControllerFollower;
    private WPI_VictorSPX driveLeftMotorControllerFollower;
    private Encoder driveLeftQuadratureEncoder;
    private Encoder driveRightQuadratureEncoder;
    private AnalogGyro analogGyro;

    //private static final double cpr = 1024;
    //private static final double whd = 6; // for 6 inch wheel

    public DriveSubsystem() {

        driveRightMotorControler = new WPI_TalonSRX(30);
        //addChild("DriveRightMotorControler",driveRightMotorControler);
        
        Shuffleboard.getTab("NoteBot")
            .add("DriveRight", 0)
            .withWidget(BuiltInWidgets.kNumberBar) 
            .withSize(2, 1)
            .withPosition(3, 3);

        driveRightMotorControler.setInverted(false);
        driveRightMotorControler.setNeutralMode(NeutralMode.Brake);

        driveLeftMotorController = new WPI_TalonSRX(20);
        //addChild("DriveLeftMotorController",driveLeftMotorController);
        Shuffleboard.getTab("NoteBot")
            .add("DriveLeft", 0)
            .withWidget(BuiltInWidgets.kNumberBar) 
            .withSize(2, 1)
            .withPosition(0, 3);
        
        driveLeftMotorController.setInverted(false);
        driveLeftMotorController.setNeutralMode(NeutralMode.Brake);

        differentialDrive = new DifferentialDrive(driveRightMotorControler, driveLeftMotorController);
        addChild("DifferentialDrive",differentialDrive);
        differentialDrive.setSafetyEnabled(false);
        differentialDrive.setExpiration(0.1);
        differentialDrive.setMaxOutput(1.0);

        driveRightMotorControllerFollower = new WPI_VictorSPX(31);
        //addChild("DriveRightMotorControllerFollower",driveRightMotorControllerFollower);
        driveRightMotorControllerFollower.follow(driveRightMotorControler);
        driveRightMotorControllerFollower.setInverted(false);
        driveRightMotorControllerFollower.setNeutralMode(NeutralMode.Brake);

        driveLeftMotorControllerFollower = new WPI_VictorSPX(21);
        //addChild("DriveLeftMotorControllerFollower",driveLeftMotorControllerFollower);
        driveLeftMotorControllerFollower.follow(driveLeftMotorController);
        driveLeftMotorControllerFollower.setInverted(false);
        driveLeftMotorControllerFollower.setNeutralMode(NeutralMode.Brake);

        
        //driveLeftQuadratureEncoder = new Encoder(0, 1, false, EncodingType.k4X);
        addChild("DriveLeftQuadratureEncoder",driveLeftQuadratureEncoder);
        //driveLeftQuadratureEncoder.setDistancePerPulse(Math.PI*whd/cpr);

        //driveRightQuadratureEncoder = new Encoder(2, 3, false, EncodingType.k4X);
        addChild("DriveRightQuadratureEncoder",driveRightQuadratureEncoder);
        //driveRightQuadratureEncoder.setDistancePerPulse(Math.PI*whd/cpr);



        analogGyro = new AnalogGyro(0);
        addChild("AnalogGyro",analogGyro);
        analogGyro.setSensitivity(0.007);

    }

    @Override
    public void periodic() {
    }

    @Override
    public void simulationPeriodic() {
        //double distLeft = driveLeftQuadratureEncoder.getDistance();
        double distLeft = driveLeftMotorController.getSelectedSensorPosition();
        SmartDashboard.putNumber("Left Encoder", distLeft);
        
        //double distRight = driveLeftQuadratureEncoder.getDistance();
        double distRight = driveRightMotorControler.getSelectedSensorPosition();
        SmartDashboard.putNumber("Left Encoder", distRight);
        
    }

    public void drive(double left, double right) {
        differentialDrive.tankDrive(left, right);
        //Shuffleboard.getTab("NoteBot")
        //    .add("DriveLeft", left);
        //Shuffleboard.getTab("NoteBot")
        //    .add("DriveRight", right);


    }
}

