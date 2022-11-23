package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (group = "test")
public class TestIMU extends LinearOpMode {
    @Override
    public void runOpMode() {

        Robot robot = new Robot(hardwareMap);

        waitForStart();

        //FOR THE FUTURE CHILDREN: find axes orientation experimentally with the following - Jason Yang

//            while (opModeIsActive()) {
//              Orientation orientation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);
//              telemetry.addData("first angle", orientation.firstAngle);
//              telemetry.addData("second angle", orientation.secondAngle);
//              telemetry.addData("third angle", orientation.thirdAngle);
//              telemetry.update();
//            }
//    }

        robot.turn(90, 0.2, telemetry);
        //sleep(3000);

        //robot.turnTo(-90, 0.5);
        if (isStopRequested()) return;

    }
}
