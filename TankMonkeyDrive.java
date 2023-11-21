package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "TankMoneyDrive")
public class TankMonkeyDrive extends LinearOpMode {

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
        
    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      rightJoint.setDirection(Servo.Direction.REVERSE);
      //rightArm.ZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      //leftArm.ZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      while (opModeIsActive()) {
        
        float strafecalc = gamepad1.right_stick_x;
        
        front_right_pow = gamepad1.right_stick_y;
        front_right_pow +=strafecalc;
        
        back_right_pow = gamepad1.right_stick_y;
        back_right_pow -=strafecalc;
        
        front_left_pow = -gamepad1.left_stick_y;
        front_left_pow += strafecalc;
        
        back_left_pow = -gamepad1.left_stick_y;
        back_left_pow -= strafecalc;
        
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
        
        leftArm.setPower(0.01);
        rightArm.setPower(0.01);
        frontRightMotor.setPower(front_right_pow * 0.75);
        backRightMotor.setPower(back_right_pow * 0.75);
        frontLeftMotor.setPower(front_left_pow * 0.75);
        backLeftMotor.setPower(back_left_pow * 0.75);
        intakeMotor.setPower(0);
        tick++;
        telemetry.update();
      }
    }
  }
}
