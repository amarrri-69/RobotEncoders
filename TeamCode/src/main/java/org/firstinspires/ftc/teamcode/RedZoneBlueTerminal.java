package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "red zone blue terminal") //Abi
public class RedZoneBlueTerminal extends LinearOpMode {
    @Override
    public void runOpMode() {
//        Robot robot = new Robot(hardwareMap);

        RobotEncoded robotencoded = new RobotEncoded(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;

        robotencoded.Forward(6,0.4);

        //robotencoded.turnLeft(90, 0.4);

        //robotencoded.Forward(24,0.4);

        //robotencoded.backward(6, 0.4);

        //robotencoded.turnRight(90, 0.4);

        //robotencoded.Forward(24, 0.4);
    }
}

