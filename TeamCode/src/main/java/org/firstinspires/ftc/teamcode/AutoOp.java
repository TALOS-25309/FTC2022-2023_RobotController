package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Auto_Op", group = "")
public class AutoOp extends OpMode
{
    private Wheel wheel;

    @Override
    public void init()
    {
        wheel.init(hardwareMap);
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
    }
}