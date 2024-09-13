package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain implements Subsystem {
    private DcMotorEx frontLeft, frontRight, backLeft, backRight;

    private HardwareMap hardwareMap;
    private final Gamepad gamepad1;

    // Constructor to initialize with the HardwareMap and Gamepad
    public DriveTrain(HardwareMap hardwareMap, Gamepad gamepad1) {
        this.hardwareMap = hardwareMap;
        this.gamepad1 = gamepad1;
    }

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        frontLeft.setDirection(DcMotorEx.Direction.FORWARD);
        frontRight.setDirection(DcMotorEx.Direction.REVERSE);
        backLeft.setDirection(DcMotorEx.Direction.FORWARD);
        backRight.setDirection(DcMotorEx.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void run() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        double fLPower = y + x - rx;
        double fRPower = y - x - rx;
        double bLPower = y - x + rx;
        double bRPower = y + x - rx;

        double divisor = Math.max(Math.max(Math.abs(fLPower), Math.abs(fRPower)), Math.max(Math.abs(bLPower), Math.abs(bRPower)));
        divisor = Math.max(divisor, 1);

        frontLeft.setPower(fLPower / divisor);
        frontRight.setPower(fRPower / divisor);
        backLeft.setPower(bLPower / divisor);
        backRight.setPower(bRPower / divisor);
    }
}
