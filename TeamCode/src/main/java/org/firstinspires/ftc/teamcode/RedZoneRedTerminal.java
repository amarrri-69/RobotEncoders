package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "red zone red terminal", group = "basic auto") //Jayla
public class RedZoneRedTerminal extends LinearOpMode {

    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap);

        RobotEncoded robotencoded = new RobotEncoded(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;

        robotencoded.forward(12,700);

        robotencoded.turnLeft(12, 700);

        robotencoded.forward(24,700);

        robotencoded.backward(6, 700);

        robotencoded.turnRight(90, 700);

        robotencoded.forward(24, 700);

    }

}

