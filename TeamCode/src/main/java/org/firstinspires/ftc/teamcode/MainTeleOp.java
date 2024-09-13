package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class MainTeleOp extends LinearOpMode {
    private ServoController servoController;
    private DriveTrain driveChain;
    private ArmController armController;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize subsystems with hardwareMap
        servoController = new ServoController(hardwareMap, gamepad1);
        driveChain = new DriveTrain(hardwareMap, gamepad1);
        armController = new ArmController(hardwareMap, gamepad1);

        // Initialize subsystems
        servoController.init();
        driveChain.init();
        armController.init();

        waitForStart();
        // Run subsystems in the main loop
        while (opModeIsActive()) {
            servoController.run();
            driveChain.run();
            armController.tempRun();
        }
    }
}

