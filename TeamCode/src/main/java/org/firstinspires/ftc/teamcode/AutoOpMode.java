package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Auto_Op", group = "")
public class AutoOpMode extends OpMode
{
    private Pincer pincer_part = new Pincer();

    @Override
    public void init()
    {
        pincer_part.init(hardwareMap, telemetry);
    }

    @Override
    public void start()
    {
        pincer_part.start();
        pincer_part.start_step("release");
    }

    @Override
    public void loop()
    {
        pincer_part.update();
    }
}