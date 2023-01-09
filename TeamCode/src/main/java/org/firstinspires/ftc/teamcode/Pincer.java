package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Pincer extends Part
{
    private SMotor pincer1 = new SMotor();
    private SMotor pincer2 = new SMotor();
    private DMotor axis = new DMotor();
    private Sensor bottom = new Sensor();
    private Sensor up = new Sensor();

    public void init(HardwareMap hwm, Telemetry tel)
    {
        this.pincer1.init(hwm, tel, "pincer1", SMotor.Direction.Direct, 0);
        this.pincer2.init(hwm, tel, "pincer2", SMotor.Direction.Reverse, 0);
        this.axis.init(hwm, tel, "axis", DMotor.Direction.Direct);
        this.bottom.init(hwm, tel, "bottom", true);
        this.up.init(hwm, tel, "up", true);

        DMotor[] dl = {this.axis};
        SMotor[] sl = {this.pincer1, this.pincer2};
        Sensor[] snl = {this.bottom, this.up};
        Color[] clr = {};

        this.util.init(dl, sl, snl, clr);

        this.step = 0;
        this.move_finish = true;
        this.telemetry = tel;
    }

    public void start()
    {

    }

    protected void next_step()
    {
        switch (move_type)
        {
            case "pick_up" :
                switch (step)
                {
                    case 0 :
                        pincer1.move(0.3, 0.1);
                        pincer2.move(0.3, 0.1);
                        break;
                    case 1 :
                        axis.move(0.1);
                        up.activate();
                        break;
                    case 2 :
                        axis.move(0.0);
                        pincer1.move(-0.3, 0.1);
                        pincer2.move(-0.3, 0.1);
                        move_finish = true;
                        break;
                }
                break;

            case "release" :
                switch (step)
                {
                    case 0 :
                        axis.move(-0.1);
                        bottom.activate();
                        break;
                    case 1 :
                        axis.move(0.0);
                        move_finish = true;
                        break;
                }
                break;
        }
        this.step++;
    }
}