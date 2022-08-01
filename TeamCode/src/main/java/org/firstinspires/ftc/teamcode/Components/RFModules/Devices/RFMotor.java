package org.firstinspires.ftc.teamcode.Components.RFModules.Devices;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Components.Logger;

public class RFMotor extends Motor {
    private DcMotorEx rfMotor;
    Logger logger;

    /*Initializes the motor
        Inputs:
        motorName: the name of the device | Ex:'motorRightFront'
        motorDirection: the direction of the motor | 0 for Reverse, 1 for Forward | Ex: 0
     */
    public RFMotor(String motorName, DcMotorSimple.Direction motorDirection, LinearOpMode op, DcMotor.RunMode runMode, boolean resetPos, DcMotor.ZeroPowerBehavior zeroBehavior, Logger log) {
        logger = log;
        rfMotor = (DcMotorEx) op.hardwareMap.dcMotor.get(motorName);
        rfMotor.setDirection(motorDirection);
        if (resetPos) {
            rfMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        rfMotor.setMode(runMode);
        rfMotor.setZeroPowerBehavior(zeroBehavior);

        logger.createFile("RFMotorLog", "Action,Value");
    }
    public void setPower(double power){
        rfMotor.setPower(power);
        logger.log("RFMotorLog", "Setting Power," + power);
    }
    public void setVelocity(double velocity) {
        rfMotor.setVelocity(velocity);
        logger.log("RFMotorLog", "Setting Velocity," + velocity);
    }

    public int getCurrentPosition() {
        logger.log("RFMotorLog", "Current Tick Count," + rfMotor.getCurrentPosition());
        return rfMotor.getCurrentPosition();
    }
    public void setMode(DcMotor.RunMode runMode) {
        rfMotor.setMode(runMode);
        logger.log("RFMotorLog", "Setting Mode," + runMode);
    }

    public double getVelocity() {
        return rfMotor.getVelocity();
    }
}