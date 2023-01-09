package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Test extends Part
{
    Sensor touch = new Sensor();
    Sensor magnet = new Sensor();
    DMotor motor = new DMotor();
    Color color = new Color();

    public void init(HardwareMap hwm, Telemetry tel)
    {
        //touch.init(hwm, tel, "touch", true);
        //motor.init(hwm, tel, "motor", DMotor.Direction.Reverse);
        //magnet.init(hwm, tel, "magnet", true);
        color.init(hwm, tel, "color");
        DMotor[] dl = {};
        SMotor[] sl = {};
        Sensor[] snl = {};
        Color[] clr = {color};

        this.telemetry = tel;

        util.init(dl, sl, snl, clr);
        this.step = 0;
    }
    public void start()
    {
        start_step("color");
    }
    protected void next_step()
    {
        switch (move_type)
        {
            case "color" :
                switch (step % 2)
                {
                    case 0:
                        color.detect_color();
                        break;
                    case 1:
                        telemetry.addData("Parking Place", color.get_parking_position());
                        break;
                }
        }
        this.telemetry.addData("Step", step);
        step++;
    }
}
