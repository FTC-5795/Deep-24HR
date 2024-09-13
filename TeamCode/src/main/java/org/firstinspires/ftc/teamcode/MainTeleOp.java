package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class MainTeleOp extends LinearOpMode {
    private ServoController servoController;
    private DriveChain driveChain;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize subsystems with hardwareMap
        servoController = new ServoController(hardwareMap, gamepad1);
        driveChain = new DriveChain(hardwareMap, gamepad1);

        // Initialize subsystems
        servoController.init();
        driveChain.init();

        waitForStart();
        // Run subsystems in the main loop
        while (opModeIsActive()) {
            servoController.run();
            driveChain.run();
        }
    }
}

