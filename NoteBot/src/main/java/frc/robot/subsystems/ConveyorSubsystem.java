package frc.robot.subsystems;

import frc.robot.commands.*;
//import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
//import edu.wpi.first.wpilibj.Relay;
//import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardComponent;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.networktables.BooleanEntry;
import edu.wpi.first.networktables.GenericEntry;

import java.util.ArrayList;

//import edu.wpi.first.wpilibj.AnalogInput;

import com.ctre.phoenix.led.ColorFlowAnimation.Direction;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.links.*;

public class ConveyorSubsystem extends SubsystemBase {
    private Compressor compressor;
    private Solenoid solenoid1;
    //private Relay solenoidRelay1;
    //private DoubleSolenoid solenoidDouble1;
    private Spark intakeTopMotorController;
    private Spark intakeBottomMotorController;
    private WPI_VictorSPX shooterMotorController1;
    private WPI_VictorSPX shooterMotorController2;
    private CANSparkMax conveyorMotorController1;
    private CANSparkMax conveyorMotorController2;
    private CANSparkMax conveyorMotorController3;
    private CANSparkMax conveyorMotorController4;
    //private Spark liftLeftMotorController;
    //private Spark liftRightMotorController;
    //private Ultrasonic ultrasonic1;
    //private Ultrasonic ultrasonic2;
    private DigitalInput loadedLs1;
    //private DigitalInput limitSwitch2;
    //private DigitalInput limitSwitch3;
    private PowerDistribution powerDistribution;
    //private Servo servo1;
    //private Servo servo2;
    //private AnalogInput ultrasonicConveyor;

    private final MedianFilter m_filter = new MedianFilter(5);
    
    // private final double kHoldDistanceMillimeters = 1.0e3;
    // proportional speed constant
    private static final double kP = 0.001;
    // integral speed constant
    private static final double kI = 0.0;
    // derivative speed constant
    private static final double kD = 0.0;

    private final PIDController m_pidController = new PIDController(kP, kI, kD);

    public int m_state = 1;     //0 ==Intake, 1 == loaded, 2 == shooting, 3 Conveyor Off

    private double counter = 0;

    private final Pixy2 pixy = Pixy2.createInstance(new SPILink());

    public int noteX;
    public int noteY;
    public int noteAngle;
    public int noteWidth;
    public int noteHeight;
    public boolean detected;

    //Shuffleboard Configuration
    private ShuffleboardTab NoteBotTab = Shuffleboard.getTab("NoteBot");
   
    private GenericEntry CountEntry =
       NoteBotTab.add("Conveyor Count", 0)
        .withPosition(9, 4)
          .getEntry();


    private GenericEntry LoadedEntry  = 
        NoteBotTab.add("Loaded", false)
        .withWidget(BuiltInWidgets.kBooleanBox)
        .withSize(1, 1)
        .withPosition(9, 0)
        .getEntry();


    private GenericEntry NoteXEntry  = 
        NoteBotTab.add("Note X", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withSize(1, 1)
        .withPosition(0, 3)
        .getEntry();

    private GenericEntry NoteYEntry  = 
        NoteBotTab.add("Note Y", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withSize(1, 1)
        .withPosition(1, 3)
        .getEntry();

    private GenericEntry NoteAngleEntry  = 
        NoteBotTab.add("Note Angle", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withSize(1, 1)
        .withPosition(2, 3)
        .getEntry();

    private GenericEntry NoteWidthEntry  = 
        NoteBotTab.add("Note Width", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withSize(1, 1)
        .withPosition(3, 3)
        .getEntry();

    private GenericEntry NoteHeightEntry  = 
        NoteBotTab.add("Note Height", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withSize(1, 1)
        .withPosition(4, 3)
        .getEntry();

    private GenericEntry BlockCountEntry  = 
        NoteBotTab.add("Block Count", 0)
        .withWidget(BuiltInWidgets.kTextView)
        .withSize(1, 1)
        .withPosition(7, 0)
        .getEntry();

    //Shuffleboard Configuration END


    public ConveyorSubsystem() {
        compressor = new Compressor(2, PneumaticsModuleType.CTREPCM);
        //addChild("Compressor",compressor);

        solenoid1 = new Solenoid(2, PneumaticsModuleType.CTREPCM, 0);
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

        //ultrasonic1 = new Ultrasonic(4,5 );
        //addChild("Ultrasonic1", ultrasonic1);
        MedianFilter m_filter = new MedianFilter(5);
        //ultrasonic2 = new Ultrasonic(6, 7);
        //addChild("Ultrasonic2", ultrasonic2);

        //ultrasonicConveyor = new AnalogInput(1);
        //addChild("UltrasonicConveyor", ultrasonicConveyor);

        loadedLs1 = new DigitalInput(0);
        //addChild("loaadedLs1", loadedLs1);

        


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


        //NotebotTab.add("ShooterMotor Left", 0)
        //    .withWidget(BuiltInWidgets.kNumberSlider) // specify the widget here
        //    .withSize(2, 1) // make the widget 2x1
        //    .withPosition(0, 0) // place it in the top-left corner
        //    .getEntry();

        //NotebotTab.add("ShooterMotor Right", 0)
        //    .withWidget(BuiltInWidgets.kNumberSlider)
        //    .withSize(2, 1)
        //    .withPosition(3, 0)
        //    .getEntry();

        try {
            pixy.init();
        }
        catch(Exception e)
        {
            System.out.println("Pixy Init Error:" + e);
        }

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

        //SmartDashboard.putNumber("ShooterMotor Left", shooterMotorController1.getMotorOutputVoltage());
        //SmartDashboard.putNumber("ShooterMotor Right", shooterMotorController2.getMotorOutputVoltage());

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
        //SmartDashboard.putBoolean("Loaded", IsConveyorLoaded());

        //LoadedEntry = IsConveyorLoaded();
                        
        counter = counter +1;
        


        if (m_state == 0) { //Intake
            ShooterOff();
            
            if (IsConveyorLoaded()){
                IntakeOff();
                ConveryorLowoff();
                ConveryorHighOff();
                compressor.enableDigital(); 
                m_state = 1;
            } else {
                if(noteHeight > 0 || detected)
                {
                    IntakeOn(7.0);  //TESTING 4
                    ConveryorLowOn(9.0);  //TESTIN  4 
                    ConveryorHighOn(-3.0);
                    ShooterOff();
                    compressor.disable();
                    detected = true;
                } else {
                    IntakeOff();
                    ConveryorLowoff();
                    ConveryorHighOff();
                    compressor.enableDigital(); 
                }

            }

        } 
        else if (m_state == 1) { //Loaded
            IntakeOff();
            ConveryorLowoff();
            ConveryorHighOff();
            if (!IsConveyorLoaded()){
                m_state = 0;
            }
            detected = false;
        }

        else if (m_state == 2) {  //Shooting
            IntakeOff();
            compressor.disable();
        }
        else if (m_state == 3) {  //Conveyor Off
            IntakeOff();
            ConveryorLowoff();
            ConveryorHighOff();
            ShooterOff();
            compressor.enableDigital();
            
        }
        else {
            //Something bad happened to hit here.
        }

        int blockCount = 0;
        try {
            blockCount = pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 25);
            if(blockCount <= 0){
                noteX = 0;
                noteY = 0;
                noteAngle = 0;
                noteWidth = 0;
                noteHeight = 0;
            }else{
                ArrayList<Block> noteList = pixy.getCCC().getBlockCache();

                if( noteList.get(0).getHeight() > 35){
                    noteX = noteList.get(0).getX();
                    noteY = noteList.get(0).getY();
                    noteAngle = noteList.get(0).getAngle();
                    noteWidth = noteList.get(0).getWidth();
                    noteHeight = noteList.get(0).getHeight();
                }
                else {
                    noteX = 0;
                    noteY = 0;
                    noteAngle = 0;
                    noteWidth = 0;
                    noteHeight = 0;
                }
                NoteXEntry.setDouble(noteX);
                NoteYEntry.setDouble(noteY);
                NoteAngleEntry.setDouble(noteAngle);
                NoteWidthEntry.setDouble(noteWidth);
                NoteHeightEntry.setDouble(noteHeight);
                BlockCountEntry.setInteger(blockCount);

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            //System.out.println(e.getLocalizedMessage());
            //Is Camera attached?
        }

        //UPDATE SHUFFLEBOARD START

        CountEntry.setDouble(counter);
        LoadedEntry.setBoolean(IsConveyorLoaded());
        
        

        //UPDATE SHUFFLEBOARD END



    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public double GetConveyorLoad() {
        //return ultrasonicConveyor.getAverageVoltage();
        return 0;
    }

    public void SetDistanceToMaintain(double distanceMM) {
        m_pidController.setSetpoint(distanceMM);
    }

    public double GetUltrasonic1Distance() {
        double measurement = 100; //ultrasonic1.getRangeMM();
        double filteredMeasurement = m_filter.calculate(measurement);
        return filteredMeasurement;
    }

    public double MaintainDistance() {
        double filteredMeasurement = GetUltrasonic1Distance();
        double pidOutput = m_pidController.calculate(filteredMeasurement);
        //m_robotDrive.arcadeDrive(pidOutput, 0, false);
        return pidOutput;
    }


    public void IntakeOn(double voltage) {
        intakeTopMotorController.setVoltage(voltage);
        intakeBottomMotorController.setVoltage(voltage);
    }

    public void IntakeOff() {
        intakeTopMotorController.set(0);
        intakeBottomMotorController.set(0);
    }

    public void ShooterOn(double voltage) {
        shooterMotorController1.setVoltage(voltage);
        shooterMotorController2.setVoltage(voltage);
    }

    public void ShooterOff() {
        shooterMotorController1.set(0);
        shooterMotorController2.set(0);
    }

    public void ConveryorHighOn(double volts) {
        conveyorMotorController2.setVoltage(volts);  //LEFT UPPER
        conveyorMotorController4.setVoltage(volts);
    }

    public void ConveryorHighOff() {
        conveyorMotorController1.setVoltage(0);
        conveyorMotorController2.setVoltage(0);
    }

    public void ConveryorLowOn(double volts) {
        conveyorMotorController1.setVoltage(volts); //LEFT LOWER
        conveyorMotorController3.setVoltage(volts);
    }

    public void ConveryorLowoff() {
        conveyorMotorController3.setVoltage(0);
        conveyorMotorController4.setVoltage(0);
    }

    public void ConveyorMotorBrake() {
        conveyorMotorController1.setIdleMode(IdleMode.kBrake);
        conveyorMotorController2.setIdleMode(IdleMode.kBrake);
        conveyorMotorController3.setIdleMode(IdleMode.kBrake);
        conveyorMotorController4.setIdleMode(IdleMode.kBrake);
        shooterMotorController1.setNeutralMode(NeutralMode.Brake);
        shooterMotorController2.setNeutralMode(NeutralMode.Brake);
    }
        public void ConveyorMotorCoast() {
        conveyorMotorController1.setIdleMode(IdleMode.kCoast);
        conveyorMotorController2.setIdleMode(IdleMode.kCoast);
        conveyorMotorController3.setIdleMode(IdleMode.kCoast);
        conveyorMotorController4.setIdleMode(IdleMode.kCoast);
        shooterMotorController1.setNeutralMode(NeutralMode.Coast);
        shooterMotorController2.setNeutralMode(NeutralMode.Coast);
    }


    public boolean IsConveyorLoaded() {
        boolean lsState = loadedLs1.get();
        return lsState;

    }

    public void ConveyorUp() {
        solenoid1.set(true);
    }
    public void ConveyorDown() {
        solenoid1.set(false);

    }

}