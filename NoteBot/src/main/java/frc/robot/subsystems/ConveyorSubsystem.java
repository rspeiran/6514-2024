package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

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

public class ConveyorSubsystem extends SubsystemBase {
    private Compressor compressor;
    private Solenoid solenoid1;
    private Relay solenoidRelay1;
    private DoubleSolenoid solenoidDouble1;
    private Spark intakeTopMotorController;
    private Spark intakeBottomMotorController;
    private Spark shooterMotorController1;
    private Spark shooterMotorController2;
    private Spark conveyorMotorController1;
    private Spark conveyorMotorController2;
    private Spark conveyorMotorController3;
    private Spark liftLeftMotorController;
    private Spark liftRightMotorController;
    private Ultrasonic ultrasonic1;
    private Ultrasonic ultrasonic2;
    private DigitalInput limitSwitch1;
    private DigitalInput limitSwitch2;
    private DigitalInput limitSwitch3;
    private PowerDistribution powerDistribution;
    private Servo servo1;
    private Servo servo2;

    public ConveyorSubsystem() {
        //compressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
        //addChild("Compressor",compressor);

        //solenoid1 = new Solenoid(0, PneumaticsModuleType.CTREPCM, 0);
        //addChild("Solenoid1", solenoid1);

        //solenoidRelay1 = new Relay(0);
        //addChild("SolenoidRelay1", solenoidRelay1);

        //solenoidDouble1 = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, 1, 0);
        //addChild("SolenoidDouble1", solenoidDouble1);
 
        //intakeTopMotorController = new Spark(4);
        //addChild("IntakeTopMotorController",intakeTopMotorController);
        //intakeTopMotorController.setInverted(false);

        //intakeBottomMotorController = new Spark(5);
        //addChild("IntakeBottomMotorController",intakeBottomMotorController);
        //intakeBottomMotorController.setInverted(false);

        //shooterMotorController1 = new Spark(6);
        //addChild("ShooterMotorController1",shooterMotorController1);
        //shooterMotorController1.setInverted(false);

        //shooterMotorController2 = new Spark(7);
        //addChild("ShooterMotorController2",shooterMotorController2);
        //shooterMotorController2.setInverted(false);

        //conveyorMotorController1 = new Spark(8);
        //addChild("ConveyorMotorController1",conveyorMotorController1);
        //conveyorMotorController1.setInverted(false);

        //conveyorMotorController2 = new Spark(9);
        //addChild("ConveyorMotorController2",conveyorMotorController2);
        //conveyorMotorController2.setInverted(false);

        //conveyorMotorController3 = new Spark(10);
        //addChild("ConveyorMotorController3",conveyorMotorController3);
        //conveyorMotorController3.setInverted(false);

        //liftLeftMotorController = new Spark(11);
        //addChild("LiftLeftMotorController",liftLeftMotorController);
        //liftLeftMotorController.setInverted(false);

        //liftRightMotorController = new Spark(12);
        //addChild("LiftRightMotorController",liftRightMotorController);
        //liftRightMotorController.setInverted(false);

        //ultrasonic1 = new Ultrasonic(4, 5);
        //addChild("Ultrasonic1", ultrasonic1);

        //ultrasonic2 = new Ultrasonic(6, 7);
        //addChild("Ultrasonic2", ultrasonic2);

        //limitSwitch1 = new DigitalInput(8);
        //addChild("LimitSwitch1", limitSwitch1);

        //limitSwitch2 = new DigitalInput(9);
        //addChild("LimitSwitch2", limitSwitch2);

        //limitSwitch3 = new DigitalInput(10);
        //addChild("LimitSwitch3", limitSwitch3);

        powerDistribution = new PowerDistribution();
        addChild("PowerDistribution",powerDistribution);

        //servo1 = new Servo(13);
        //addChild("Servo1", servo1);

        //servo2 = new Servo(14);
        //addChild("Servo2", servo2);

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }


}

