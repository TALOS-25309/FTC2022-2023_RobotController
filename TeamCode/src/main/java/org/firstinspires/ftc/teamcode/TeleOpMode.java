package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name = "Tele_Op", group = "")
public class TeleOpMode extends OpMode {

    private Linear linear_part = new Linear();
    private Wheel wheel_part = new Wheel();

    @Override
    public void init()
    {
        linear_part.init(hardwareMap, telemetry);
        wheel_part.init(hardwareMap, telemetry);
    }

    @Override
    public void loop()
    {
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

        if(gamepad1.y) {
            linear_part.start_step("simple_stack_cup");
        }
        else if(gamepad1.x) {
            linear_part.start_step("go_low");
        }
        else if(gamepad1.b){
            linear_part.start_step("go_high");
        }

        wheel_part.update();
        linear_part.update();
    }
}
