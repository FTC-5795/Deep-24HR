package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class TestDriveMichael extends LinearOpMode {

    private DcMotorEx frontLeft, frontRight, backLeft, backRight;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        frontLeft.setDirection(DcMotorEx.Direction.FORWARD);
        frontRight.setDirection(DcMotorEx.Direction.FORWARD);
        backLeft.setDirection(DcMotorEx.Direction.FORWARD);
        backRight.setDirection(DcMotorEx.Direction.FORWARD);

        double y, x, rx;
        double fLPower, fRPower, bLPower, bRPower;
        double divisor;

        waitForStart();
        while (opModeIsActive()) {

            x = gamepad1.left_stick_x;
            y = -gamepad1.left_stick_y;
            rx = gamepad1.right_stick_x;

            fLPower = y + rx + x;
            fRPower = y - rx + x;
            bLPower = y + rx - x;
            bRPower = y - rx - x;

            divisor = Math.max(Math.max(Math.abs(fLPower), Math.abs(fRPower)), Math.max(Math.abs(bLPower), Math.abs(bRPower)));
            divisor = Math.max(divisor, 1);

            fLPower /= divisor;
            fRPower /= divisor;
            bLPower /= divisor;
            bRPower /= divisor;

            frontLeft.setPower(fLPower);
            frontRight.setPower(fRPower);
            backLeft.setPower(bLPower);
            backRight.setPower(bRPower);
            
        }




    }
}
