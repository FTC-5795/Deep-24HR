package org.firstinspires.ftc.teamcode.src.drivePrograms.teleop.worlds;

import static com.qualcomm.hardware.rev.RevBlinkinLedDriver.BlinkinPattern;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.src.robotAttachments.sensors.TripWireDistanceSensor;
import org.firstinspires.ftc.teamcode.src.utills.enums.FreightFrenzyGameObject;
import org.firstinspires.ftc.teamcode.src.utills.opModeTemplate.TeleOpTemplate;

@TeleOp(name = "🟦Blue Worlds Drive Program🟦")
public class BlueWorldsDriveProgram extends TeleOpTemplate {
    final BlinkinPattern defaultColor = BlinkinPattern.BLUE;
    BlinkinPattern currentColor = defaultColor;
    TripWireDistanceSensor distanceSensor;

    public void opModeMain() throws InterruptedException {
        this.initAll();

        distanceSensor = new TripWireDistanceSensor(hardwareMap, "distance_sensor", 8, () -> {
            this.leds.setPattern(BlinkinPattern.BLACK);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            this.leds.setPattern(currentColor);
            return null;

        }, this::opModeIsActive, this::isStopRequested);

        distanceSensor.start();
        leds.setPattern(defaultColor);

        slide.autoMode();

        telemetry.addData("Initialization", "finished");
        telemetry.update();
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            //Declan's controls
            {
                driveTrain.gamepadControl(gamepad1, null);
            }

            //Eli's controls
            {
                //Handles Linear Slide Control
                slide.gamepadControl(null, gamepad2);

                //Intake Controls
                FreightFrenzyGameObject currentObj = (FreightFrenzyGameObject) outtake.gamepadControl(null, gamepad2);
                RevBlinkinLedDriver.BlinkinPattern colorToChangeTo;
                if (currentObj == null) {
                    colorToChangeTo = defaultColor;
                } else {
                    colorToChangeTo = FreightFrenzyGameObject.getLEDColorFromItem(currentObj);
                }

                if (colorToChangeTo != currentColor) {
                    leds.setPattern(colorToChangeTo);
                    currentColor = colorToChangeTo;
                }


                //Carousel Spinner
                spinner.gamepadControl(null, gamepad2);
            }


        }
    }
}


