package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp(name = "Tele_Op", group = "")
public class TeleOpMode extends OpMode {
    private Wheel wheel_part = new Wheel();
    private Linear linear_part = new Linear();
    private Pincer pincer_part = new Pincer();

    private double slow_rate;
    private boolean pincer_up;

    @Override
    public void init()
    {
        wheel_part.init(hardwareMap, telemetry);
        linear_part.init(hardwareMap, telemetry);
        pincer_part.init(hardwareMap, telemetry);
    }

    @Override
    public void start()
    {
        wheel_part.start();
        linear_part.start();
        pincer_part.start();
    }

    @Override
    public void loop()
    {
        wheel_part.update();
        linear_part.update();
        pincer_part.update();
        //*
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
        //*/

        //Linear
        //*
        if(linear_part.finish() && pincer_part.finish()) {
<<<<<<< HEAD
            if (gamepad2.y) {
=======
            if (gamepad2.x) {
>>>>>>> 53cc0cf72eca86a4ae98111eb5b9260fdd2d962a
                pincer_part.start_step("release");
                linear_part.start_step("simple_stack_cup");
            } else if (gamepad2.y) {
                pincer_part.start_step("release");
                linear_part.start_step("go_low");
            } else if (gamepad2.b) {
                pincer_part.start_step("release");
                linear_part.start_step("go_high");
            }
        }
        //*/

        if(pincer_part.finish()) {
            if (gamepad2.a) {
                pincer_part.start_step("pick_up");
            }
        }

        //adjust
        if(gamepad2.right_bumper && gamepad2.left_bumper){
            //*
            if(pincer_part.finish()){
                if(gamepad2.dpad_up){
                    this.pincer_part.adjust_axis(1);
                }
                else if(gamepad2.dpad_down){
                    this.pincer_part.adjust_axis(-1);
                }
                else{
                    this.pincer_part.adjust_axis(0);
                }
            }
            //*/
            //*
            if(linear_part.finish()){
                if(gamepad2.right_trigger > 0.1) {
                    this.linear_part.adjust_rope(gamepad2.right_trigger);
                }
                else if(gamepad2.left_trigger > 0.1) {
                    this.linear_part.adjust_rope(-gamepad2.left_trigger);
                }
                else {
                    this.linear_part.adjust_rope(0);
                }
            }
            //*/
        }
    }
}
