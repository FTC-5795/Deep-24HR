package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDController {
    public double kp, ki, kd; //Proportional, Integral, and Derivative Coefficients

    private final ElapsedTime timer = new ElapsedTime(); // Delta time

    private double previousError;
    private double integralSum;

    public PIDController(double kp, double ki, double kd) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        this.previousError = 0;
        this.integralSum = 0;
        timer.reset();
    }

    public double calculatePower(double currentState, double targetState) {
        double error = targetState - currentState;

        // Proportional term
        double proportional = kp * error;

        // Integral term
        integralSum += ki * error * timer.seconds();

        // Derivative term
        double derivative = kd * (error - previousError) / timer.seconds();

        // Calculate the control signal
        double controlSignal = proportional + integralSum + derivative;

        // Update previous error for the next iteration
        previousError = error;

        // Reset timer delta
        timer.reset();

        return controlSignal;
    }

    //Setter methods
    public void setKp(double kp) {
        this.kp = kp;
    }

    public void setKi(double ki) {
        this.ki = ki;
    }

    public void setKd(double kd) {
        this.kd = kd;
    }

}
