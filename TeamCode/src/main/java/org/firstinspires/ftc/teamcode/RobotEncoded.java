package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotEncoded {

    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;

    DcMotorEx linearSlide;

    final double TICKS_PER_MOTOR_ROTATION = 537.7;
    final double GEAR_REDUCTION = 1;
    final double WHEEL_DIAMETER_INCHES = 3.77953;
    final double TICKS_PER_INCH = (TICKS_PER_MOTOR_ROTATION * GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);

    final double LS_DIAMETER_INCHES = 1.404;
    final double TICKS_PER_INCH_LS =  (TICKS_PER_MOTOR_ROTATION * GEAR_REDUCTION) / (LS_DIAMETER_INCHES * Math.PI);;

    final double MAX_TICKS_LS = 30;
    final double MIN_TICKS_LS = 10;

    //final double degreesPerInch = 360;

//    public Orientation lastAngles = new Orientation();
//    public double currAngle = 0.0;


    public RobotEncoded(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        linearSlide = hardwareMap.get(DcMotorEx.class, "linearSlide");

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

//        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // set default ticks back to 0
    }


    public void turnR(double Power) {
        frontRight.setPower(Power);
        frontLeft.setPower(-Power);
        backRight.setPower(Power);
        backLeft.setPower(-Power);
    }

    public double normalizeAngle(double angle) {
        if (angle > 180) {
            angle -= 180;
        } else if (angle <= -180) {
            angle += 180;
        }
        return angle;
    }


    public void forward(int distanceInches, double velocity) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setVelocity(velocity);
        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
        }

        frontLeft.setVelocity(0);
        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
        backRight.setVelocity(0);
    }

    public void backward(int distanceInches, double velocity) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setVelocity(velocity);
        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
        }

        frontLeft.setVelocity(0);
        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
        backRight.setVelocity(0);
    }

    public void strafeRight(int distanceInches, double velocity) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setVelocity(velocity);
        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
        }

        frontLeft.setVelocity(0);
        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
        backRight.setVelocity(0);
    }

    public void strafeLeft(int distanceInches, double velocity) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setVelocity(velocity);
        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
        }

        frontLeft.setVelocity(0);
        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
        backRight.setVelocity(0);
    }

    public void turnLeft(int distanceInches, double velocity) {

        //int distanceInches = (int) (degrees / degreesPerInch);
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setVelocity(velocity);
        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
        }

        frontLeft.setVelocity(0);
        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
        backRight.setVelocity(0);
    }

    public void turnRight(int distanceInches, double velocity) {
        //int distanceInches = (int) (degrees / degreesPerInch);
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setVelocity(velocity);
        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
        }

        frontLeft.setVelocity(0);
        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
        backRight.setVelocity(0);

    }

    public void diagonalUpLeft(int distanceInches, double velocity) {
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));

        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);

        while (frontRight.isBusy() && backLeft.isBusy()) {
        }

        frontRight.setVelocity(0);
        backLeft.setVelocity(0);

    }

    public void upRight(int distanceInches, double velocity) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) (distanceInches * TICKS_PER_INCH));

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);

        while (frontLeft.isBusy() && backRight.isBusy()) {
        }

        frontLeft.setVelocity(0);
        backRight.setVelocity(0);
    }
    public void downLeft ( int distanceInches, double velocity){
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setVelocity(velocity);
        backRight.setVelocity(velocity);

        while (frontLeft.isBusy() && backRight.isBusy()) {
        }

        frontLeft.setVelocity(0);
        backRight.setVelocity(0);
    }

    public void downRight ( int distanceInches, double velocity){
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) (-distanceInches * TICKS_PER_INCH));

        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontRight.setVelocity(velocity);
        backLeft.setVelocity(velocity);

        while (frontRight.isBusy() && backLeft.isBusy()) {
        }

        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
    }

    public void stop_bot () {
        frontLeft.setVelocity(0);
        frontRight.setVelocity(0);
        backLeft.setVelocity(0);
        backRight.setVelocity(0);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void raiseSlide(double velocity, int distanceInches) {

        if ((int) (distanceInches * TICKS_PER_INCH_LS) >= MAX_TICKS_LS || (int) (distanceInches * TICKS_PER_INCH_LS) < 0) return;

       linearSlide.setTargetPosition((int)(distanceInches * TICKS_PER_INCH_LS));
       linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       linearSlide.setVelocity(velocity);

        //while(linearSlide.isBusy()) { }
    }

     /*
    public void resetAngle() {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        currAngle = 0;
    }

    public double getAngle() {
        Orientation orientation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double deltaAngle = orientation.firstAngle - lastAngles.firstAngle;

        currAngle += normalizeAngle(deltaAngle);
        lastAngles = orientation;
        telemetry.addData("RobotEncoded", orientation.firstAngle);
        return currAngle;
    }

    public void turn(double degrees, double power) {
        resetAngle();

        double error = degrees;

        while(Math.abs(error) > 2) {
            double motorPower = (error < 0 ? -Math.abs(power) : Math.abs(power));
            TurnR(motorPower);
            error = degrees - getAngle();

        }
    }

     */

}

