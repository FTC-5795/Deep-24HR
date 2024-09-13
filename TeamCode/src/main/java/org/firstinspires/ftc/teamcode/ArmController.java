package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArmController implements Subsystem{

    private eventClicker eventClicker;
    private DcMotorEx armMotor;
    private PIDController PIDController1 = new PIDController(0.001, 0, 0);
    private final HardwareMap hardwareMap;
    private Gamepad gamepad1;
    private static double PPR = 2786.2;

    double armPositionDegree1 = 0.0;
    double armPositionDegree2 = 90.0;
    double armPositionDegree3 = 180.0;
    int armPosition = 1;

    public ArmController(HardwareMap hardwareMap, Gamepad gamepad1) {
        this.hardwareMap = hardwareMap;
        this.gamepad1 = gamepad1;

    }
    @Override
    public void init(){
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
    }

    @Override
    public void run() {
        if (eventClicker.isClicked(gamepad1.x)){
            armPosition += 1;
            if (armPosition > 3) {
                armPosition = 1;
            }
        }
        
        if(armPosition == 1){
            setMotorPower(armPositionDegree1);
        } else if (armPosition == 2){
            setMotorPower(armPositionDegree2);
        } else if (armPosition == 3) {
            setMotorPower(armPositionDegree3);
        }
    }

    public void tempRun() {
        if (gamepad1.left_bumper) {
            armMotor.setPower(1);
        } else if (gamepad1.right_bumper) {
            armMotor.setPower(-1);
        } else {
            armMotor.setPower(0);
        }
    }

    public static double degreesToTicks(double degrees) {
        double ticksPerDegree = PPR / 360.0;
        double ticks = degrees * ticksPerDegree;
        return Math.round(ticks);
    }

    public void setMotorPower(double degreeVal){
        double tickNum = degreesToTicks(degreeVal);
        double motorPower = PIDController1.calculatePower(armMotor.getCurrentPosition(), tickNum);
        armMotor.setPower(motorPower);
    }
}
