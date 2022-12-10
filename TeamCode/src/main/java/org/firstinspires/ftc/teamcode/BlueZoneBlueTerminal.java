package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "BlueZone BlueTerminal", group = "basic auto")
public class BlueZoneBlueTerminal extends LinearOpMode {
    @Override
    public void runOpMode() {
//        Robot robot = new Robot(hardwareMap);

        RobotEncoded robotencoded = new RobotEncoded(hardwareMap);

        waitForStart();
//        telemetry.addData("idk", robotencoded.)

        robotencoded.forward(3,700);
            //  sleep(250);
            //robot.Stop();

        robotencoded.turnRight(12, 700);
            //sleep(1000);

        robotencoded.forward(12,700);
            // sleep(2000);

        robotencoded.backward(6, 700);
            //sleep(1000);

        robotencoded.turnLeft(12, 700);
            //sleep(1000);

        robotencoded.forward(24, 700);
            //sleep(2000);



        }
    }
