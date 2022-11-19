package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "red zone red terminal") //Jayla
public class RedZoneRedTerminal extends LinearOpMode {
    @Override
    public void runOpMode() {
//        Robot robot = new Robot(hardwareMap);

        RobotEncoded robotencoded = new RobotEncoded(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;

        robotencoded.Forward(6,700);
      //  sleep(250);
        //robot.Stop();

        robotencoded.turnLeft(90, 700);
        //sleep(1000);

        robotencoded.Forward(24,700);
       // sleep(2000);

        robotencoded.backward(6, 700);
        //sleep(1000);

        robotencoded.turnRight(90, 700);
        //sleep(1000);

        robotencoded.Forward(24, 700);
        //sleep(2000);
    }
}

