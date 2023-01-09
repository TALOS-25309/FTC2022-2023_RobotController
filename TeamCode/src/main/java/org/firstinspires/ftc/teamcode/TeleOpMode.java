package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp(name = "Tele_Op", group = "")
public class TeleOpMode extends OpMode {


    private Wheel wheel_part = new Wheel();
    private Linear linear_part = new Linear();
    private Pincer pincer_part = new Pincer();

    private boolean pincer_up;

    @Override
    public void init()
    {
        wheel_part.init(hardwareMap, telemetry);
        linear_part.init(hardwareMap, telemetry);
        pincer_part.init(hardwareMap, telemetry);

        pincer_up = false;
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

        if(gamepad1.dpad_up) {
            wheel_part.move(0.5, Wheel.Direction.Forward);
        }
        else if(gamepad1.dpad_down) {
            wheel_part.move(0.5, Wheel.Direction.Backward);
        }
        else if(gamepad1.dpad_right) {
            wheel_part.move(0.5, Wheel.Direction.Right);
        }
        else if(gamepad1.dpad_left) {
            wheel_part.move(0.5, Wheel.Direction.Left);
        }
        else if(gamepad1.left_bumper){
            wheel_part.move(0.5, Wheel.Direction.TurnLeft);
        }
        else if(gamepad1.right_bumper){
            wheel_part.move(0.5, Wheel.Direction.TurnRight);
        }
        else {
            wheel_part.move(0.0,  Wheel.Direction.Forward);
        }

        if(linear_part.util.finish())
        {
            if(gamepad1.y) {
                linear_part.start_step("simple_stack_cup");
            }
            else if(gamepad1.x) {
                linear_part.start_step("go_low");
            }
            else if(gamepad1.b){
                linear_part.start_step("go_high");
            }
        }

        if(pincer_part.util.finish())
        {
            if(gamepad1.b) {
                if(!pincer_up) {
                    pincer_part.start_step("pick_up");
                }
                else {
                    pincer_part.start_step("release");
                }
            }
        }
    }
}
