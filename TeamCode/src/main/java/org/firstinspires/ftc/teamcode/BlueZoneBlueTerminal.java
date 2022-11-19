package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "BlueZone BlueTerminal")
public class BlueZoneBlueTerminal extends LinearOpMode {
    @Override
    public void runOpMode() {
//        Robot robot = new Robot(hardwareMap);

        RobotEncoded robotencoded = new RobotEncoded(hardwareMap);

        waitForStart();
//        telemetry.addData("idk", robotencoded.)

        robotencoded.Forward(6,700);
            //  sleep(250);
            //robot.Stop();

        robotencoded.turnRight(90, 700);
            //sleep(1000);

        robotencoded.Forward(12,700);
            // sleep(2000);

        robotencoded.backward(12, 700);
            //sleep(1000);

        robotencoded.turnRight(90, 700);
            //sleep(1000);

        robotencoded.Forward(6, 700);
            //sleep(2000);



        }
    }
