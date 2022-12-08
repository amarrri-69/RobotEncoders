package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "BlueZoneRedTerminal", group = "basic auto")
public class BlueZoneRedTerminal extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap);

        RobotEncoded robotencoded = new RobotEncoded(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;

        robotencoded.Forward(12,700);

        robotencoded.turnLeft(12,700);

        robotencoded.Forward(24,700);

        robotencoded.turnRight(12,700);

        robotencoded.Forward(24,700);

        robotencoded.turnLeft(90,700);

        robotencoded.Forward(12,700);

        robotencoded.backward(12,700);

        if (opModeIsActive()) {
            telemetry.addData("front right", robotencoded.frontRight.getPower());
            telemetry.addData("front left", robotencoded.frontLeft.getPower());
            telemetry.addData("back right", robotencoded.backRight.getPower());
            telemetry.addData("back left", robotencoded.backLeft.getPower());
            telemetry.update();
        }
    }
}
