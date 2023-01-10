package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name = "Auto_Op", group = "")
public class AutoOpMode extends OpMode
{
    private Pincer pincer_part = new Pincer();
    private Linear linear_part = new Linear();
    private boolean linear_run = true;

    @Override
    public void init()
    {
        pincer_part.init(hardwareMap, telemetry);
        linear_part.init(hardwareMap, telemetry);
    }

    @Override
    public void start()
    {
        pincer_part.start();
        pincer_part.start_step("pick_up");
    }

    @Override
    public void loop()
    {
        pincer_part.update();
        linear_part.update();
        if(pincer_part.move_finish && linear_run){
            linear_run = false;
            linear_part.start_step("go_high");
        }
    }
}