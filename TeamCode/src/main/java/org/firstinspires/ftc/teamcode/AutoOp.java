package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Auto_Op", group = "")
public class AutoOp extends OpMode
{
    private Wheel wheel = new Wheel();

    @Override
    public void init()
    {
        wheel.init(hardwareMap, telemetry);
    }

    @Override
    public void start()
    {
        wheel.start();
    }

    @Override
    public void loop()
    {
        wheel.update();
        telemetry.addData("Step", wheel.step);
    }
}