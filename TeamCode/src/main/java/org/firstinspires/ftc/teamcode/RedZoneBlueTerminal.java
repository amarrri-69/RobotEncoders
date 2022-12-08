package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "red zone blue terminal", group = "basic auto") //Abi
public class RedZoneBlueTerminal extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap);

        RobotEncoded robotencoded = new RobotEncoded(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;

        robotencoded.Forward(12,700);

        robotencoded.turnRight(12,700);

        robotencoded.Forward(24,700);

        robotencoded.turnLeft(12,700);

        robotencoded.Forward(24,700);

        robotencoded.turnLeft(12,700);

        robotencoded.Forward(24,700);

    }
}

