package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Drive (Blocks to Java)")
public class Drive extends LinearOpMode {

  private DcMotor frontright;
  private DcMotor backright;
  private DcMotor frontleft;
  private DcMotor backleft;

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

    frontright = hardwareMap.get(DcMotor.class, "front right");
    backright = hardwareMap.get(DcMotor.class, "back right");
    frontleft = hardwareMap.get(DcMotor.class, "front left");
    backleft = hardwareMap.get(DcMotor.class, "back left");
    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        float strafecalc = gamepad1.right_stick_x;
        
        front_right_pow = gamepad1.right_stick_y;
        front_right_pow -=strafecalc;
        
        back_right_pow = gamepad1.right_stick_y;
        front_right_pow +=strafecalc;
        
        front_left_pow = -gamepad1.left_stick_y;
        front_left_pow += strafecalc;
        back_left_pow = -gamepad1.left_stick_y;
        back_left_pow -= strafecalc;
        
        
        
        frontright.setPower(front_right_pow);
        backright.setPower(back_right_pow);
        frontleft.setPower(front_left_pow);
        backleft.setPower(back_left_pow);
        telemetry.update();
      }
    }
  }
}
