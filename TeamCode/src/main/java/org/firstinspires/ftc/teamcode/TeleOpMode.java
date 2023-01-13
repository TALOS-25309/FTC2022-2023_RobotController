package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;

@TeleOp(name = "TeleOp", group = "")
public class TeleOpMode extends OpMode {
    private Wheel wheel_part = new Wheel();
    private Linear linear_part = new Linear();
    private Pincer pincer_part = new Pincer();
    private Gyro imu = new Gyro();

    private double slow_rate;
    private boolean adjusting = false;
    private boolean stop = false;

    @Override
    public void init()
    {
        imu.init(hardwareMap, telemetry, "imu");
        wheel_part.init(hardwareMap, telemetry, this.imu);
        linear_part.init(hardwareMap, telemetry);
        pincer_part.init(hardwareMap, telemetry);
    }

    @Override
    public void loop()
    {
        //Update
        wheel_part.update();
        linear_part.update();
        pincer_part.update();
        imu.update();

        //Wheel Part
        if(gamepad1.left_trigger > 0.7 || gamepad1.right_trigger > 0.7)
        {
            slow_rate = 0.3;
        }
        else
        {
            slow_rate = 1.0;
        }

        if(gamepad1.dpad_up) {
            wheel_part.move(0.5*slow_rate, Wheel.Direction.Forward);
        }
        else if(gamepad1.dpad_down) {
            wheel_part.move(0.5*slow_rate, Wheel.Direction.Backward);
        }
        else if(gamepad1.dpad_right) {
            wheel_part.move(0.5*slow_rate, Wheel.Direction.Right);
        }
        else if(gamepad1.dpad_left) {
            wheel_part.move(0.5*slow_rate, Wheel.Direction.Left);
        }
        else if(gamepad1.left_bumper){
            wheel_part.move(0.5*slow_rate, Wheel.Direction.TurnLeft);
        }
        else if(gamepad1.right_bumper){
            wheel_part.move(0.5*slow_rate, Wheel.Direction.TurnRight);
        }
        else {
            wheel_part.move(0.0,  Wheel.Direction.Forward);
        }

        if(!adjusting){
            //Linear
            if(linear_part.finish() && pincer_part.finish()) {
                if (gamepad2.x) {
                    linear_part.start_step("low junction");
                    //pincer_part.start_step("release");
                } else if (gamepad2.y) {
                    linear_part.start_step("middle junction");
                    //pincer_part.start_step("release");
                } else if (gamepad2.b) {
                    linear_part.start_step("high junction");
                    //pincer_part.start_step("release");
                }
            }

            //Pincer
            if(pincer_part.finish()) {
                if (gamepad2.a) {
                    pincer_part.start_step("pincer");
                }
            }
        }

        //Emergency Stop
        if((gamepad1.left_bumper && gamepad1.right_bumper && (gamepad1.left_trigger>0.9) && (gamepad1.right_trigger>0.9))
                || (gamepad2.left_bumper && gamepad2.right_bumper && (gamepad2.left_trigger>0.9) && (gamepad2.right_trigger>0.9))){
            this.stop = true;
            wheel_part.emergency_stop();
            linear_part.emergency_stop();
            pincer_part.emergency_stop();

            wheel_part.init(hardwareMap, telemetry, this.imu);
            linear_part.init(hardwareMap, telemetry);
            pincer_part.init(hardwareMap, telemetry);

            wheel_part.update();
            linear_part.update();
            pincer_part.update();

            gamepad1.rumble(500);
            gamepad2.rumble(500);
        }
        else if(this.stop){
            if(gamepad1.left_stick_button && gamepad1.right_stick_button){
                this.stop = false;
                gamepad1.rumble(200);
                gamepad2.rumble(200);
            }
        }

        //Adjusting
        if(gamepad2.right_bumper && gamepad2.left_bumper && !this.stop){
            if(this.pincer_part.finish() && this.linear_part.finish()){
                this.adjusting = true;
            }
        }
        else if(adjusting){
            this.pincer_part.adjust_axis(0);
            this.linear_part.adjust_rope(0);
            this.adjusting = false;
        }

        //adjusting pincer & linear (adjust mode or emergency stop mode)
        if(this.adjusting || this.stop) {
            if (pincer_part.finish()) {
                if (gamepad2.dpad_up) {
                    this.pincer_part.adjust_axis(1);
                } else if (gamepad2.dpad_down) {
                    this.pincer_part.adjust_axis(-1);
                } else {
                    this.pincer_part.adjust_axis(0);
                }
            }
            if (linear_part.finish()) {
                if (gamepad2.right_trigger > 0.1) {
                    this.linear_part.adjust_rope(gamepad2.right_trigger);
                } else if (gamepad2.left_trigger > 0.1) {
                    this.linear_part.adjust_rope(-gamepad2.left_trigger);
                } else {
                    this.linear_part.adjust_rope(0);
                }
            }
        }
    }
}
