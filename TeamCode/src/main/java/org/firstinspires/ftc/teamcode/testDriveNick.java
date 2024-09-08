package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class testDriveNick extends LinearOpMode{

    private DcMotorEx frontLeft, frontRight, backLeft, backRight;

    @Override
    public void runOpMode() throws InterruptedException {
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

        double y, x, rx;
        double fLPower, fRPower, bLPower, bRPower;
        double divisor;

        waitForStart();
        while (opModeIsActive()) {

            x = gamepad1.left_stick_x;
            y = -gamepad1.left_stick_y;
            rx = gamepad1.right_stick_x;

            fLPower = y + x - rx;
            fRPower = y - x - rx;
            bLPower = y - x + rx;
            bRPower = y + x - rx;

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
