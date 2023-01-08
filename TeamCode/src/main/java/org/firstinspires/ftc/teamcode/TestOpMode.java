package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Test_Op", group = "")
public class TestOpMode extends OpMode
{
    private Test test = new Test();

    @Override
    public void init()
    {
        test.init(hardwareMap, telemetry);
    }

    @Override
    public void start()
    {
        test.start();
    }

    @Override
    public void loop()
    {
        test.update();
    }
}