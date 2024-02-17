package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.wpilibj.AnalogInput;

import com.ctre.phoenix.led.ColorFlowAnimation.Direction;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class ConveyorSubsystem extends SubsystemBase {
    private Compressor compressor;
    private Solenoid solenoid1;
    private Relay solenoidRelay1;
    private DoubleSolenoid solenoidDouble1;
    private Spark intakeTopMotorController;
    private Spark intakeBottomMotorController;
    private WPI_VictorSPX shooterMotorController1;
    private WPI_VictorSPX shooterMotorController2;
    private CANSparkMax conveyorMotorController1;
    private CANSparkMax conveyorMotorController2;
    private CANSparkMax conveyorMotorController3;
    private CANSparkMax conveyorMotorController4;
    private Spark liftLeftMotorController;
    private Spark liftRightMotorController;
    private Ultrasonic ultrasonic1;
    private Ultrasonic ultrasonic2;
    private DigitalInput loaadedLs1;
    private DigitalInput limitSwitch2;
    private DigitalInput limitSwitch3;
    private PowerDistribution powerDistribution;
    private Servo servo1;
    private Servo servo2;
    private AnalogInput UltrasonicConveyor;
    //private MedianFilter;


    public ConveyorSubsystem() {
        compressor = new Compressor(2, PneumaticsModuleType.CTREPCM);
        //addChild("Compressor",compressor);

        //solenoid1 = new Solenoid(0, PneumaticsModuleType.CTREPCM, 0);
        //addChild("Solenoid1", solenoid1);

        //solenoidRelay1 = new Relay(0);
        //addChild("SolenoidRelay1", solenoidRelay1);

        //solenoidDouble1 = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, 1, 0);
        //addChild("SolenoidDouble1", solenoidDouble1);
 
        intakeTopMotorController = new Spark(0);
        //addChild("IntakeTopMotorController",intakeTopMotorController);
        intakeTopMotorController.setInverted(false);

        intakeBottomMotorController = new Spark(1);
        //addChild("IntakeBottomMotorController",intakeBottomMotorController);
        intakeBottomMotorController.setInverted(false);

        conveyorMotorController1 = new CANSparkMax(41, MotorType.kBrushed);
        //addChild("ConveyorMotorController1",conveyorMotorController1.);
        conveyorMotorController1.setInverted(false);

        conveyorMotorController2 = new CANSparkMax(42, MotorType.kBrushed);
        //addChild("ConveyorMotorController2",conveyorMotorController2);
        conveyorMotorController2.setInverted(false);

        conveyorMotorController3 = new CANSparkMax(43, MotorType.kBrushed);
        //addChild("ConveyorMotorController3",conveyorMotorController3);
        conveyorMotorController3.setInverted(true);

        conveyorMotorController4 = new CANSparkMax(44, MotorType.kBrushed);
        //addChild("ConveyorMotorController3",conveyorMotorController3);
        conveyorMotorController4.setInverted(true);

        shooterMotorController1 = new WPI_VictorSPX(45);
        //addChild("ShooterMotorController1",shooterMotorController1);
        shooterMotorController1.setInverted(false);

        shooterMotorController2 = new WPI_VictorSPX(46);
        //addChild("ShooterMotorController2",shooterMotorController2);
        shooterMotorController2.setInverted(false);

        //liftLeftMotorController = new Spark(11);
        //addChild("LiftLeftMotorController",liftLeftMotorController);
        //liftLeftMotorController.setInverted(false);

        //liftRightMotorController = new Spark(12);
        //addChild("LiftRightMotorController",liftRightMotorController);
        //liftRightMotorController.setInverted(false);

        ultrasonic1 = new Ultrasonic(4,5 );
        addChild("Ultrasonic1", ultrasonic1);
        MedianFilter m_filter = new MedianFilter(5);
        //ultrasonic2 = new Ultrasonic(6, 7);
        //addChild("Ultrasonic2", ultrasonic2);

        UltrasonicConveyor = new AnalogInput(1);
        addChild("UltrasonicConveyor", UltrasonicConveyor);

        loaadedLs1 = new DigitalInput(10);
        addChild("loaadedLs1", loaadedLs1);

        Shuffleboard.getTab("NoteBot")
            .add("Loaded", false)
            .withWidget(BuiltInWidgets.kBooleanBox)
            .withSize(1, 1)
            .withPosition(4, 5)
            .getEntry();


        //limitSwitch2 = new DigitalInput(9);
        //addChild("LimitSwitch2", limitSwitch2);

        //limitSwitch3 = new DigitalInput(8);
        //addChild("LimitSwitch3", limitSwitch3);

        powerDistribution = new PowerDistribution();
        addChild("PowerDistribution",powerDistribution);

        //servo1 = new Servo(13);
        //addChild("Servo1", servo1);

        //servo2 = new Servo(14);
        //addChild("Servo2", servo2);


        Shuffleboard.getTab("NoteBot")
            .add("ShooterMotor Left", 0)
            .withWidget(BuiltInWidgets.kNumberSlider) // specify the widget here
            .withSize(2, 1) // make the widget 2x1
            .withPosition(0, 0) // place it in the top-left corner
            .getEntry();

        Shuffleboard.getTab("NoteBot")
            .add("ShooterMotor Right", 0)
            .withWidget(BuiltInWidgets.kNumberSlider)
            .withSize(2, 1)
            .withPosition(3, 0)
            .getEntry();

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        //IntakeOn();

        //SmartDashboard.putNumber("ShooterMotor Left", shooterMotorController1.getMotorOutputVoltage());
        //SmartDashboard.putNumber("ShooterMotor Right", shooterMotorController2.getMotorOutputVoltage());
        //compressor.enableDigital();
        compressor.disable();

        //shooterMotorController1.set(0.10);
        //shooterMotorController2.set(0.10);
        
        //conveyorMotorController1.set(0.10);  //LEFT LOWER
        //conveyorMotorController3.set(0.10);   //RIGHT LOWER

        //conveyorMotorController2.set(0.10);  //LEFT UPPER
        //conveyorMotorController4.set(0.10);      //RIGHT UPPER
        
        //intakeTopMotorController.set(0.20);
        //intakeBottomMotorController.set(0.30);
        //double noteDistance = UltrasonicConveyor.getAverageVoltage();
        
        //System.out.println("U1 " + ultrasonic1.getRangeMM());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public double GetConveyorLoad() {
        return UltrasonicConveyor.getAverageVoltage();
    }

    public double GetUltrasonicLoad() {
        double measurement = ultrasonic1.getRangeMM();
        //double filteredMeasurement = m_filter.calculate(measurement);
        //double pidOutput = m_pidController.calculate(filteredMeasurement);
        return measurement;
    }


    public void IntakeOn() {
        intakeTopMotorController.set(0.90);
        intakeBottomMotorController.set(0.90);
    }

    public void IntakeOff() {
        intakeTopMotorController.set(0);
        intakeBottomMotorController.set(0);
       
    }

    public void ShooterOn(double speed) {
        shooterMotorController1.setVoltage(12.2);
        shooterMotorController2.setVoltage(12.2);
    }

    public void ShooterOff() {
        shooterMotorController1.set(0);
        shooterMotorController2.set(0);
    }

    public void ConveryorHighOn(double volts) {
        conveyorMotorController2.setVoltage(volts);  //LEFT UPPER
        conveyorMotorController4.setVoltage(volts);
    }

    public void ConveryorLowOn(double volts) {
        conveyorMotorController1.setVoltage(volts); //LEFT LOWER
        conveyorMotorController3.setVoltage(volts);

    }

    public void ConveryorHighOff(double speed) {
        conveyorMotorController1.setVoltage(12.2);
        conveyorMotorController2.setVoltage(12.2);
    }

    public void ConveryorLowoff(double speed) {
        conveyorMotorController3.setVoltage(12.2);
        conveyorMotorController4.setVoltage(12.2);
    }


}