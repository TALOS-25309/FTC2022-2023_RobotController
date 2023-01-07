package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Test extends Part
{
    Sensor touch = new Sensor();
    Sensor magnet = new Sensor();
    DMotor motor = new DMotor();

    public void init(HardwareMap hwm, Telemetry tel)
    {
        //touch.init(hwm, tel, "touch", true);
        motor.init(hwm, tel, "motor", DMotor.Direction.Reverse);
        magnet.init(hwm, tel, "magnet", true);
        DMotor[] dl = {motor};
        SMotor[] sl = {};
        Sensor[] snl = {magnet};

        this.telemetry = tel;

        util.init(dl, sl, snl);
        this.step = 0;
    }
    public void start()
    {
        start_step("magnet");
    }
    protected void next_step()
    {
        switch (move_type)
        {
            case "magnet" :
                switch (step % 2)
                {
                    case 0:
                        magnet.activate();
                        break;
                    case 1:
                        motor.move(0.5, 0.5);
                        break;
                }
        }
        this.telemetry.addData("Step", step);
        step++;
    }
}
