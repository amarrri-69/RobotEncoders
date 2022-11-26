package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp (name="TestTeleop")
public class TestTeleop extends OpMode {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor linearSlide;

    Servo claw;

    double slowVal = 0.4;
    double defaultVal = 0.9;

    @Override
    public void init() {   // only ran once
        // set values for the motors
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        linearSlide = hardwareMap.get(DcMotor.class, "linearSlide");

        claw = hardwareMap.get(Servo.class, "claw");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {   // runs on multiple times
        double x = -gamepad1.left_stick_x; // stores data in gp
        double y = gamepad1.left_stick_y;
        double r = gamepad1.right_stick_x;

        if (gamepad1.right_bumper) {
            frontLeft.setPower((y + x + r) * slowVal);
            frontRight.setPower((y - x - r) * slowVal);
            backLeft.setPower((y - x + r) * slowVal);
            backRight.setPower((y + x - r) * slowVal);
        }
        else {
            frontLeft.setPower((y + x + r) * defaultVal);
            frontRight.setPower((y - x - r) * defaultVal);
            backLeft.setPower((y - x + r) * defaultVal);
            backRight.setPower((y + x - r) * defaultVal);

        if (gamepad2.a) { // a = raise linear slide
             linearSlide.setPower(0.3);
            }

        else if (gamepad2.b) { // b = lowering linear slide
             linearSlide.setPower(-0.3);
        }

        if (gamepad2.y) { // move to 0 degrees. close claw
             claw.setPosition(0);
        }

        else if (gamepad2.x) { // move to 90 degrees. open claw
             claw.setPosition(0.5);
        }

        telemetry.addData("frontLeft: ", y - x + r);
        telemetry.addData("frontRight:", y + x - r);
        telemetry.addData("backLeft:", y + x + r);
        telemetry.addData("backRight", y - x - r);
        telemetry.addData("y:", y);
        telemetry.addData("x: ", x);
        telemetry.addData("r", r);
        telemetry.update();
        }
    }
}