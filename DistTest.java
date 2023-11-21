package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "DistTest")
public class DistTest extends LinearOpMode {

  private DcMotor frontRightMotor;
  private DcMotor backRightMotor;
  private DcMotor frontLeftMotor;
  private DcMotor backLeftMotor;
  private DcMotor intakeMotor;
  private Servo leftJoint;
  private Servo rightJoint;
  private CRServo outtake;
  private DcMotor rightArm;
  private DcMotor leftArm;
  private DistanceSensor distanceSensor;
  private int slidesCounter = 0;
  private int tick = 0;
  /**
   * This function is executed when this OpMode is selected from the 
Driver Station.
   */

  @Override
  public void runOpMode() {
    float front_left_pow;
    float front_right_pow;
    float back_right_pow;
    float back_left_pow;
    
    frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
    backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
    frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
    backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
    intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
    leftJoint = hardwareMap.get(Servo.class, "leftJoint");
    rightJoint = hardwareMap.get(Servo.class, "rightJoint");
    outtake = hardwareMap.get(CRServo.class, "outtake");
    rightArm = hardwareMap.dcMotor.get("rightArm");
    leftArm = hardwareMap.dcMotor.get("leftArm");
    distanceSensor = hardwareMap.get(DistanceSensor.class, "distanceSensor");
    waitForStart();
    // Put initialization blocks here.
    
    if (opModeIsActive()) {
      // Put run blocks here.
      
      rightJoint.setDirection(Servo.Direction.REVERSE);
      forward(1,180);
      //sleep(250);
      stop(1000);
      turn(false,100);
      //sleep(100);
      stop(0);
      boolean run = true;
      while(run){
        double thing = distanceSensor.getDistance(DistanceUnit.CM);
        telemetry.addData("Dist!", thing);
        turn(true,1);
        stop(10);
        if(thing<=30)
          run = false;
        telemetry.update();
      }
      
      
      //rightArm.ZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      //leftArm.ZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      
  /*
        if (gamepad1.left_trigger == 1) {
          intakeMotor.setPower(1);
          outtake.setPower(-1);
        } 
        if(tick==100){
        if(!(gamepad1.left_trigger == 1) || !(gamepad1.right_trigger == 1)){
          outtake.setPower(0);
        }
        tick=0;
        }
        if (gamepad1.right_trigger == 1) {
          intakeMotor.setPower(-1);
          outtake.setPower(1);
        } 
        if (gamepad1.a) {
          leftJoint.setPosition(-0.4);
          rightJoint.setPosition(-0.4);
          
        }
        if (gamepad1.b) {
          leftJoint.setPosition(0.41);
          rightJoint.setPosition(0.41);
        }
        if(gamepad1.right_bumper){
        while(gamepad1.right_bumper){
          leftArm.setPower(slidesCounter);
          rightArm.setPower(slidesCounter);
          slidesCounter++;
        }
        } else{
          slidesCounter = 0;
        }
        if(gamepad1.left_bumper){
        while(gamepad1.left_bumper){
          leftArm.setPower(-slidesCounter);
          rightArm.setPower(-slidesCounter);
          slidesCounter++;
        }
        }else{
          slidesCounter = 0;
        }
        */
        
        /*
        leftArm.setPower(0.01);
        rightArm.setPower(0.01);
        frontRightMotor.setPower(front_right_pow * 0.75);
        backRightMotor.setPower(back_right_pow * 0.75);
        frontLeftMotor.setPower(front_left_pow * 0.75);
        backLeftMotor.setPower(back_left_pow * 0.75);
        intakeMotor.setPower(0);
        tick++;
        */
        
    }
  }
  private void forward(int backwardMaybe, int t){
        frontRightMotor.setPower(1*backwardMaybe);
        backRightMotor.setPower(1*backwardMaybe);
        frontLeftMotor.setPower(-1*backwardMaybe);
        backLeftMotor.setPower(-1*backwardMaybe);
        sleep(t);
      }
  private void stop(int t){
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        sleep(t);
      }
  private void turn(boolean rightMaybe, int t){
        if(!rightMaybe){
        frontRightMotor.setPower(1);
        backRightMotor.setPower(1);
        frontLeftMotor.setPower(1);
        backLeftMotor.setPower(1);
        } else{
          frontRightMotor.setPower(-1);
          backRightMotor.setPower(-1);
          frontLeftMotor.setPower(-1);
          backLeftMotor.setPower(-1);
        }
        sleep(t);
      }
}
