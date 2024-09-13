package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

//Checks that input is only clicked once
//Returns true once if input switches from false to true
public class eventClicker {
    boolean lockClick = false;

    public boolean isClicked(boolean input) {
        if (input && !lockClick) {
            lockClick = true;
            return true;
        }
        else if (!input) {
            lockClick = false;
        }
        return false;
    }
}
