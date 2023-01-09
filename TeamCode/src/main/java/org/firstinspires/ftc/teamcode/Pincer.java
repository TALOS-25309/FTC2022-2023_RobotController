package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Pincer extends Part
{
    private SMotor pincer1 = new SMotor();
    private SMotor pincer2 = new SMotor();
    private DMotor axis1 = new DMotor();
    private DMotor axis2 = new DMotor();
    //private Sensor bottom = new Sensor();
    //private Sensor up = new Sensor();

    public void init(HardwareMap hwm, Telemetry tel)
    {
        this.pincer1.init(hwm, tel, "pincer1", SMotor.Direction.Direct, 0.2);
        this.pincer2.init(hwm, tel, "pincer2", SMotor.Direction.Reverse, 0.2);
        this.axis1.init(hwm, tel, "axis1", DMotor.Direction.Direct);
        this.axis2.init(hwm, tel, "axis2", DMotor.Direction.Direct);
        //this.bottom.init(hwm, tel, "bottom", true);
        //this.up.init(hwm, tel, "up", true);

        DMotor[] dl = {/*axis1, axis2*/};
        SMotor[] sl = {this.pincer1, this.pincer2};
        Sensor[] snl = {/*this.bottom, this.up*/};
        Color[] clr = {};
        Distance[] dst = {};

        this.util.init(dl, sl, snl, clr, dst);

        this.step = 0;
        this.telemetry = tel;
        this.move_finish = true;
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
                        pincer1.move(0.3, 0.5);
                        pincer2.move(0.3, 0.5);
                        break;
                    case 1 :
                        axis1.move(0.1);
                        axis2.move(0.1);
                        //up.activate();
                        break;
                    case 2 :
                        axis1.move(0.0);
                        axis2.move(0.0);
                        pincer1.move(-0.3, 0.5);
                        pincer2.move(-0.3, 0.5);
                        move_finish = true;
                        break;
                }
                break;

            case "release" :
                switch (step)
                {
                    case 0 :
                        axis1.move(0.1, -1);
                        axis2.move(0.1, -1);
                        //bottom.activate();
                        break;
                    case 1 :
                        axis1.move(0, 0);
                        axis2.move(0, 0);
                        move_finish = true;
                        break;
                }
                break;
        }
        this.step++;
        this.telemetry.addData("step", this.step);
    }
}
