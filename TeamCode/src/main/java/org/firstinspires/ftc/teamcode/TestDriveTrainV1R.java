package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class TestDriveTrainV1R extends LinearOpMode {

    private DcMotorEx fL, fR, bL, bR;

    @Override
    public void runOpMode() throws InterruptedException {
        fL = hardwareMap.get(DcMotorEx.class, "frontLeft");
        fR = hardwareMap.get(DcMotorEx.class, "frontRight");
        bL = hardwareMap.get(DcMotorEx.class, "backLeft");
        bR = hardwareMap.get(DcMotorEx.class, "backRight");

        fL.setDirection(DcMotorEx.Direction.FORWARD);
        fR.setDirection(DcMotorEx.Direction.FORWARD);
        bL.setDirection(DcMotorEx.Direction.FORWARD);
        bR.setDirection(DcMotorEx.Direction.FORWARD);

        double y, x, rx;
        double fLPower, fRPower, bRPower, bLPower;
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

            divisor = (Math.max(Math.max(Math.abs(fLPower), Math.abs(fRPower)), Math.max(Math.abs(bLPower), (Math.abs(bRPower)))));
            divisor = Math.max(divisor, 1);

            fLPower = fLPower/divisor;
            fRPower = fRPower/divisor;
            bLPower = bLPower/divisor;
            bRPower = bRPower/divisor;

            fL.setPower(fLPower);
            fR.setPower(fRPower);
            bL.setPower(bLPower);
            bR.setPower(bRPower);


        }
    }

}
