package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class MainTeleOp extends LinearOpMode {
    private ServoControllerNick servoControllerNick;
    private DriveTestNick driveTestNick;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize subsystems with hardwareMap
        servoControllerNick = new ServoControllerNick(hardwareMap, gamepad1);
        driveTestNick = new DriveTestNick(hardwareMap, gamepad1);

        // Initialize subsystems
        servoControllerNick.init();
        driveTestNick.init();

        waitForStart();
        // Run subsystems in the main loop
        while (opModeIsActive()) {
            servoControllerNick.run();
            driveTestNick.run();
        }
    }
}

