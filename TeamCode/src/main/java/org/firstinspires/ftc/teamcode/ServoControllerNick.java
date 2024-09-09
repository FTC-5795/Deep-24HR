package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ServoControllerNick implements Subsystem {
    private CRServo intakeWheel;
    private Servo pivotServo;

    private final HardwareMap hardwareMap;
    private Gamepad gamepad1;
    private float pivotServoPosition = 0.5f; // Starting at midpoint (90 degrees)

    // Constructor to initialize with the HardwareMap and Gamepad
    public ServoControllerNick(HardwareMap hardwareMap, Gamepad gamepad1) {
        this.hardwareMap = hardwareMap;
        this.gamepad1 = gamepad1;
    }
    @Override
    public void init() {
        intakeWheel = hardwareMap.get(CRServo.class, "intakeWheel");
        pivotServo = hardwareMap.get(Servo.class, "pivotServo");
        intakeWheel.setDirection(CRServo.Direction.FORWARD);
    }

    @Override
    public void run() {
        float intakeWheelPower = gamepad1.left_trigger - gamepad1.right_trigger;
        intakeWheel.setPower(intakeWheelPower);

        if (gamepad1.x) {
            pivotServoPosition += 0.05;  // Increment position safely
        } else if (gamepad1.y) {
            pivotServoPosition -= 0.05;  // Decrement position safely
        }

        pivotServoPosition = Math.max(0.0f, Math.min(1.0f, pivotServoPosition));
        pivotServo.setPosition(pivotServoPosition);
    }
}
