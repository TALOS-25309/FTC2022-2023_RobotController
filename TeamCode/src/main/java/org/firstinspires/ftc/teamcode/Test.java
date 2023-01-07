package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Test extends Part
{
    private SMotor s1 = new SMotor(), s2 = new SMotor();

    public void init(HardwareMap hwm, Telemetry tel)
    {
        s1.init(hwm, tel, "servo1", SMotor.Direction.Reverse, 0);
        s2.init(hwm, tel, "servo2", SMotor.Direction.Direct, 0);
        DMotor[] dl = {};
        SMotor[] sl = {s1, s2};
        Sensor[] snl = {};

        this.telemetry = tel;

        util.init(dl, sl, snl);
        this.step = 0;
    }
    public void start()
    {
        start_step("Test");
    }
    protected void next_step()
    {
        switch (move_type)
        {
            case "Test" :
                switch (step % 3)
                {
                    case 0:
                        this.s1.move(1.0, 1, 1.0);
                        break;
                    case 1:
                        break;
                    case 2:
                        this.s1.move(-1.0, 1, 0.5);
                        break;
                }
        }
        this.telemetry.addData("Step", step);
        step++;
    }
}
