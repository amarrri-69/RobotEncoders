package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class Robot {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    BNO055IMU imu;

    public Orientation lastAngles = new Orientation();
    public double currAngle = 0.0;


    // constructor
    public Robot(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu.initialize(parameters);

    }

    public void resetAngle() {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);
        currAngle = 0;
    }

    public double getAngle() {
        Orientation orientation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);

        double deltaAngle = orientation.firstAngle - lastAngles.firstAngle;


        currAngle += normalizeAngle(deltaAngle);
        lastAngles = orientation;
        //telemetry.addData("RobotEncoded", orientation.firstAngle);
        return currAngle;
    }

    public void turn(double degrees, double power, Telemetry t) {
        resetAngle();

        double error = degrees;

        while(Math.abs(error) > 2) {
            double motorPower = (error < 0 ? -Math.abs(power) : Math.abs(power));
            TurnR(motorPower);
            error = degrees - getAngle();

            try {
                t.addData("error", error);
                t.update();
            } catch (Exception err) {}

        }

        Stop();
    }

//    public void turnTo(double degrees, double power) {
//        Orientation orientation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);
//
//        double error = degrees - orientation.firstAngle;
//        turn(normalizeAngle(error), power);
//    }

    public double normalizeAngle(double angle) {
        if (angle > 180) {
            angle -= 360;
        } else if (angle <= -180) {
            angle += 360;
        }

        return angle;
    }


    public void Stop() {
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }

    public void Forward(double Power){
        frontRight.setPower(-Power);
        frontLeft.setPower(-Power);
        backRight.setPower(-Power);
        backLeft.setPower(-Power);
    }

    public void Backward(double Power) {
        frontRight.setPower(Power);
        frontLeft.setPower(Power);
        backRight.setPower(Power);
        backLeft.setPower(Power);
    }

    //strafing to the left
    public void StrafeR(double Power) {
        frontRight.setPower(Power);
        frontLeft.setPower(-Power);
        backRight.setPower(-Power);
        backLeft.setPower(Power);
    }

    // strafing to the right
    public void StrafeL(double Power) {
        frontRight.setPower(-Power);
        frontLeft.setPower(Power);
        backRight.setPower(Power);
        backLeft.setPower(-Power);
    }

    //turning left
    public void TurnR(double Power) {
        frontRight.setPower(Power);
        frontLeft.setPower(-Power);
        backRight.setPower(Power);
        backLeft.setPower(-Power);
    }

    //turning right
    public void TurnL(double Power) {
        frontRight.setPower(-Power);
        frontLeft.setPower(Power);
        backRight.setPower(-Power);
        backLeft.setPower(Power);
    }

    //no carousel

    public void ForwardRight(double Power) {
        frontRight.setPower(0);
        frontLeft.setPower(Power);
        backRight.setPower(Power);
        backLeft.setPower(0);
    }

    public void ForwardLeft(double Power) {
        frontRight.setPower(Power);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(Power);
    }

    public void BackwardRight(double Power) {
        frontRight.setPower(-Power);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(-Power);
    }

    public void BackwardLeft(double Power) {
        frontRight.setPower(0);
        frontLeft.setPower(-Power);
        backRight.setPower(-Power);
        backLeft.setPower(0);
    }
}
