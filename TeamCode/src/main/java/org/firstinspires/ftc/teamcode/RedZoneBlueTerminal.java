package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "red zone blue terminal", group = "basic auto")
public class RedZoneBlueTerminal extends LinearOpMode {
    @Override
    public void runOpMode() {
        //Robot robot = new Robot(hardwareMap);

        RobotEncoded robotencoded = new RobotEncoded(hardwareMap);

        waitForStart();
        //if (isStopRequested()) return;

        robotencoded.forward(3,800);

        robotencoded.turnRight(24,800);

        robotencoded.forward(24,800);

        robotencoded.backward(18,800);

        robotencoded.turnLeft(24,800);

        robotencoded.forward(24,800);

        if (opModeIsActive()) {
            telemetry.addData("front right", robotencoded.frontRight.getPower());
            telemetry.addData("front left", robotencoded.frontLeft.getPower());
            telemetry.addData("back right", robotencoded.backRight.getPower());
            telemetry.addData("back left", robotencoded.backLeft.getPower());
            telemetry.update();
        }

    }
}

