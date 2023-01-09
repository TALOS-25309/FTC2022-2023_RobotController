package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Auto_Op", group = "")
public class AutoOpMode extends OpMode
{
    private Linear linear_part = new Linear();

    @Override
    public void init()
    {
        linear_part.init(hardwareMap, telemetry);
    }

    @Override
    public void start()
    {
        linear_part.start();
        linear_part.start_step("stack_cup");
    }

    @Override
    public void loop()
    {
        linear_part.update();
    }
}