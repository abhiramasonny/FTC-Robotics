package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous(name = "LastAttempt")
public class LastAttempt extends LinearOpMode {

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
  private Servo distanceServo;
  double turnDistance;
  double ticksPerInch;
  double turnRadius;
  int velocity;
  double rotationAdjust;
  
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
    int wheelDiameter;
    int motorTicksPerRev;
    int gearRatio;
    double W;
    double L;

    double servoPos = 1;
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
    distanceServo = hardwareMap.get(Servo.class, "distanceServo");
    frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
    backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
    rightJoint.setDirection(Servo.Direction.REVERSE);
    Stop_and_Reset_Motor_Encoders();
    wheelDiameter = 96;
    // Enter the desired speed of the robot in in/sec
    velocity = 50;
    // Enter the motor encoder ticks per rev
    motorTicksPerRev = 28;
    // Enter the gear ratio for your drive motors
    gearRatio = 20;
    // Enter the width of your robots wheels in inches
    W = 17.25;
    // Enter the length of your robots wheels in inches
    L = 17.75;
    // Create a variable to adjust rotation
    rotationAdjust = 1.35;
    // Calculate the Ticks per Inch of your robot
    ticksPerInch = motorTicksPerRev * gearRatio * (1 / (2 * Math.PI * (wheelDiameter / 2))) * (25.4 / 1);
    turnRadius = Math.sqrt((L / 2) * (L / 2) + (W / 2) * (W / 2));
    waitForStart();
    // Put initialization blocks here.
    
    if (opModeIsActive()) {
      Drive_Robot_in__Y_Direction(15);
      sleep(1000);
      for(double j =0.5; j<=1;j+=0.025){
      for(int i=0; i<=12; i++){
      distanceServo.setPosition(j);
      sleep(75);
      }

      //0.625 buffer for first/2nd
      double thing = distanceSensor.getDistance(DistanceUnit.CM);
      telemetry.addData("Dist!", thing);
      telemetry.addData("getPos: ", distanceServo.getPosition());
      if(thing<=65){
      telemetry.addData("Dist!", thing);
      telemetry.addData("getPos: ", distanceServo.getPosition());
      servoPos = distanceServo.getPosition();
      telemetry.update();
      break;
      }
      telemetry.update();
      }
      int proppos = 1;
      telemetry.addData("servoPos: ", servoPos);
      if(servoPos>0.63 && servoPos<0.76){
        proppos = 2;
      }else if(distanceServo.getPosition()>0.76){
        proppos = 3;
      }
      telemetry.addData("POS: ", proppos);
      telemetry.update();
      sleep(1000);
      
      if(proppos==1){
        Drive_Robot_in__X_Direction(12);
        for(int i=0; i<=3; i++){
        intakeMotor.setPower(-0.3);
        sleep(1000);
        intakeMotor.setPower(0);
        sleep(100);
        }
      } else if(proppos==2){
        Drive_Robot_in__X_Direction(3);
        Drive_Robot_in__Y_Direction(12);
        for(int i=0; i<=3; i++){
        intakeMotor.setPower(-0.3);
        sleep(1000);
        intakeMotor.setPower(0);
        sleep(100);
        }
      }
      }
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
  private void strafe(int leftMaybe, int t){
        frontRightMotor.setPower(0.6*leftMaybe);
        backRightMotor.setPower(-0.6*leftMaybe);
        frontLeftMotor.setPower(0.6*leftMaybe);
        backLeftMotor.setPower(-0.6*leftMaybe);
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

private void Change_RunMode_to_Run_to_Position() {
    frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
  }

  /**
   * Describe this function...
   */
  private void Drive_Robot_in__Y_Direction(int yPos) {
    Stop_and_Reset_Motor_Encoders();
    // Set the Motors target position
    frontLeftMotor.setTargetPosition((int) (yPos * ticksPerInch));
    backLeftMotor.setTargetPosition((int) (yPos * ticksPerInch));
    frontRightMotor.setTargetPosition((int) (yPos * ticksPerInch));
    backRightMotor.setTargetPosition((int) (yPos * ticksPerInch));
    // Switch to RUN_TO_POSITION mode
    Change_RunMode_to_Run_to_Position();
    // Start the motor moving by setting the max velocity
    ((DcMotorEx) frontLeftMotor).setVelocity(velocity * ticksPerInch);
    ((DcMotorEx) backLeftMotor).setVelocity(velocity * ticksPerInch);
    ((DcMotorEx) frontRightMotor).setVelocity(velocity * ticksPerInch);
    ((DcMotorEx) backRightMotor).setVelocity(velocity * ticksPerInch);
    while (backLeftMotor.isBusy() && !isStopRequested()) {
      //Report_Current_Position_of_Motors();
      telemetry.update();
    }
  }

  /**
   * Describe this function...
   */
  private void Report_Current_Position_of_Motors() {
  }

  /**
   * Describe this function...
   */
  private void Drive_Robot_in__X_Direction(int xPos) {
    Stop_and_Reset_Motor_Encoders();
    // Set the Motors target position
    frontLeftMotor.setTargetPosition((int) (xPos * ticksPerInch));
    backLeftMotor.setTargetPosition((int) (-xPos * ticksPerInch));
    frontRightMotor.setTargetPosition((int) (-xPos * ticksPerInch));
    backRightMotor.setTargetPosition((int) (xPos * ticksPerInch));
    // Switch to RUN_TO_POSITION mode
    Change_RunMode_to_Run_to_Position();
    // Start the motor moving by setting the max velocity
    ((DcMotorEx) frontLeftMotor).setVelocity(velocity * ticksPerInch);
    ((DcMotorEx) backLeftMotor).setVelocity(velocity * ticksPerInch);
    ((DcMotorEx) frontRightMotor).setVelocity(velocity * ticksPerInch);
    ((DcMotorEx) backRightMotor).setVelocity(velocity * ticksPerInch);
    while (backLeftMotor.isBusy() && !isStopRequested()) {
      Report_Current_Position_of_Motors();
      telemetry.update();
    }
  }

  /**
   * Describe this function...
   */
  private void Rotate_robot__rz_direction2(double _rz) {
    Stop_and_Reset_Motor_Encoders();
    // Calculate the rotation distance
    turnDistance = 2 * Math.PI * turnRadius * (_rz / 360);
    //telemetry.addData("turnDistance", turnDistance);
    telemetry.update();
    // Set the Motors target position
    frontLeftMotor.setTargetPosition((int) (-rotationAdjust * turnDistance * ticksPerInch));
    backLeftMotor.setTargetPosition((int) (-rotationAdjust * turnDistance * ticksPerInch));
    frontRightMotor.setTargetPosition((int) (rotationAdjust * turnDistance * ticksPerInch));
    backRightMotor.setTargetPosition((int) (rotationAdjust * turnDistance * ticksPerInch));
    // Switch to RUN_TO_POSITION mode
    Change_RunMode_to_Run_to_Position();
    // Start the motor moving by setting the max velocity
    ((DcMotorEx) frontLeftMotor).setVelocity(velocity * ticksPerInch);
    ((DcMotorEx) backLeftMotor).setVelocity(velocity * ticksPerInch);
    ((DcMotorEx) frontRightMotor).setVelocity(velocity * ticksPerInch);
    ((DcMotorEx) backRightMotor).setVelocity(velocity * ticksPerInch);
    while (backLeftMotor.isBusy() && !isStopRequested()) {
      //telemetry.addData("Status:", "Rotating the robot in +rz, Waiting for the robot to reach its target position");
      Report_Current_Position_of_Motors();
      telemetry.update();
    }
  }

  /**
   * Describe this function...
   */
  private void Stop_and_Reset_Motor_Encoders() {
    frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
  }

  /**
   * Describe this function...
   */
  private void Drive_Robot_in__Y_Direction2(double yPos) {
    Stop_and_Reset_Motor_Encoders();
    // Set the Motors target position
    frontLeftMotor.setTargetPosition((int) (-yPos * ticksPerInch));
    backLeftMotor.setTargetPosition((int) (-yPos * ticksPerInch));
    frontRightMotor.setTargetPosition((int) (-yPos * ticksPerInch));
    backRightMotor.setTargetPosition((int) (-yPos * ticksPerInch));
    // Switch to RUN_TO_POSITION mode
    Change_RunMode_to_Run_to_Position();
    // Start the motor moving by setting the max velocity
    ((DcMotorEx) frontLeftMotor).setVelocity(velocity * ticksPerInch);
    ((DcMotorEx) backLeftMotor).setVelocity(velocity * ticksPerInch);
    ((DcMotorEx) frontRightMotor).setVelocity(velocity * ticksPerInch);
    ((DcMotorEx) backRightMotor).setVelocity(velocity * ticksPerInch);
    while (backLeftMotor.isBusy() && !isStopRequested()) {
      Report_Current_Position_of_Motors();
      telemetry.update();
    }
  }

  /**
   * Describe this function...
   */
  private void Drive_Robot_in__X_Direction2(double xPos) {
    Stop_and_Reset_Motor_Encoders();
    // Set the Motors target position
    frontLeftMotor.setTargetPosition((int) (-xPos * ticksPerInch));
    backLeftMotor.setTargetPosition((int) (xPos * ticksPerInch));
    frontRightMotor.setTargetPosition((int) (xPos * ticksPerInch));
    backRightMotor.setTargetPosition((int) (-xPos * ticksPerInch));
    // Switch to RUN_TO_POSITION mode
    Change_RunMode_to_Run_to_Position();
    // Start the motor moving by setting the max velocity
    ((DcMotorEx) frontLeftMotor).setVelocity(velocity * ticksPerInch);
    ((DcMotorEx) backLeftMotor).setVelocity(velocity * ticksPerInch);
    ((DcMotorEx) frontRightMotor).setVelocity(velocity * ticksPerInch);
    ((DcMotorEx) backRightMotor).setVelocity(velocity * ticksPerInch);
    while (backLeftMotor.isBusy() && !isStopRequested()) {
      Report_Current_Position_of_Motors();
      telemetry.update();
    }
  }

}
