package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ServoController implements Subsystem {
    private CRServo intakeWheel;
    private Servo pivotServo;
    private eventClicker eventClicker1;
    private final HardwareMap hardwareMap;
    private Gamepad gamepad1;
    private int modIncrement;
    private double pivotServoPosition; // Starting at midpoint (90 degrees)

    // Constructor to initialize with the HardwareMap and Gamepad
    public ServoController(HardwareMap hardwareMap, Gamepad gamepad1) {
        this.hardwareMap = hardwareMap;
        this.gamepad1 = gamepad1;
    }
    @Override
    public void init() {
        intakeWheel = hardwareMap.get(CRServo.class, "intakeWheel");
        pivotServo = hardwareMap.get(Servo.class, "pivotServo");
        intakeWheel.setDirection(CRServo.Direction.FORWARD);
        eventClicker1 = new eventClicker();
        modIncrement = 1;
    }

    @Override
    public void run() {
        if (eventClicker1.isClicked(gamepad1.x)) {
            modIncrement += 1;  // Increment position safely
            if (modIncrement % 2 == 0) {
                pivotServoPosition = 0.67;
            } else {
                pivotServoPosition = 0.25;
            }
            pivotServo.setPosition(pivotServoPosition);
        }

        float intakeWheelPower = gamepad1.left_trigger - gamepad1.right_trigger;
        intakeWheel.setPower(intakeWheelPower);
    }
}
