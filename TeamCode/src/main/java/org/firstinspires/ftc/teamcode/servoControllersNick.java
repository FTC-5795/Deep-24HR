package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class servoControllersNick extends LinearOpMode{
    private CRServo intakeWheel;
    private Servo pivotServo;
    @Override
    public void runOpMode() throws InterruptedException {
        intakeWheel = hardwareMap.get(CRServo.class, "intakeWheel");
        pivotServo =    hardwareMap.get(Servo.class, "pivotServo");
        intakeWheel.setDirection(CRServo.Direction.FORWARD);

        float intakeWheelPower;

        while (opModeIsActive()){
            intakeWheelPower = gamepad1.left_trigger - gamepad1.right_trigger;
            intakeWheelPower /= 2;

            intakeWheel.setPower(intakeWheelPower);
        }
    }
}
