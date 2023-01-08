package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.RobotEncoded.TICKS_PER_INCH_LS;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp (name="main")
public class TestTeleop extends OpMode {


    static double GJ = 0; //ground junction
    static double LJ = 14.5; //low junction
    static double MJ = 24.5; //medium junction
    static double HJ = 34.5; //high junction
    double lsHeight = 0;

    double slowVal = 0.4;
    double defaultVal = 0.9;

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotorEx linearSlide;

    Servo claw;

    @Override
    public void init() {   // only ran once

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        linearSlide = hardwareMap.get(DcMotorEx.class, "linearSlide");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        claw = hardwareMap.get(Servo.class, "claw");
        claw.scaleRange(0, 1);
    }

    @Override
    public void loop() {   // runs on multiple times
        
        //mecanum wheel motion
        double x = -gamepad1.left_stick_x; // stores data in gp
        double y = gamepad1.left_stick_y;
        double r = -gamepad1.right_stick_x;

        if (gamepad1.right_bumper) {
            frontLeft.setPower((y + x + r) * slowVal);
            frontRight.setPower((y - x - r) * slowVal);
            backLeft.setPower((y - x + r) * slowVal);
            backRight.setPower((y + x - r) * slowVal);
        } else {
            frontLeft.setPower((y + x + r) * defaultVal);
            frontRight.setPower((y - x - r) * defaultVal);
            backLeft.setPower((y - x + r) * defaultVal);
            backRight.setPower((y + x - r) * defaultVal);
        }


        //claw servo
        if (gamepad2.right_bumper) { // open claw
            claw.setPosition(0.5);
        }
        else if (gamepad2.left_bumper) { // close claw
             claw.setPosition(0.3);
        }


        // setting linear slide position
        if (gamepad2.a) {
            lsHeight = GJ;
        }
        else if (gamepad2.b) {
            lsHeight = LJ;
        }
        else if (gamepad2.x) {
            lsHeight = MJ;
        }
        else if (gamepad2.y) {
            lsHeight = HJ;
        }
        
        if (gamepad2.right_stick_y > 0) { //manually lifting the linear slide
            lsHeight += 0.5;
        }
        else if (gamepad2.right_stick_y < 0) { //manually lowering the linear alide
            lsHeight -= 0.5;
        }
        
        //running to linear slide position
        if (linearSlide.getCurrentPosition() != (int)(lsHeight * TICKS_PER_INCH_LS)) {
            linearSlide.setTargetPosition((int)(lsHeight * TICKS_PER_INCH_LS));
            linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            linearSlide.setVelocity(1500);
        } else linearSlide.setPower(0);

        
        //telemetry
        telemetry.addData("y:", y);
        telemetry.addData("x: ", x);
        telemetry.addData("r", r);
        telemetry.addData("claw position", claw.getPosition());
        telemetry.addData("linear slide velocity", linearSlide.getVelocity());
        telemetry.addData("cur pos", linearSlide.getCurrentPosition() / TICKS_PER_INCH_LS);
        telemetry.addData("target pos", lsHeight);
        telemetry.update();
//    }
    }
}
