package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Pincer extends Part
{
    private SMotor pincer1 = new SMotor();
    private SMotor pincer2 = new SMotor();
    private DMotor axis1 = new DMotor();
    private DMotor axis2 = new DMotor();
    private Distance sensor = new Distance();

    public void init(HardwareMap hwm, Telemetry tel)
    {
        this.pincer1.init(hwm, tel, "pincer1", SMotor.Direction.Direct, 0.38);
        this.pincer2.init(hwm, tel, "pincer2", SMotor.Direction.Reverse, 0.38);
        this.axis1.init(hwm, tel, "axis1", DMotor.Direction.Direct);
        this.axis2.init(hwm, tel, "axis2", DMotor.Direction.Direct);
        this.sensor.init(hwm, tel, "sensor");

        DMotor[] dl = {axis1, axis2};
        SMotor[] sl = {this.pincer1, this.pincer2};
        Sensor[] snl = {};
        Color[] clr = {};
        Distance[] dsl = {this.sensor};

        this.util.init(dl, sl, snl, clr, dsl, null, this.telemetry);

        this.step = 0;
        this.telemetry = tel;
        this.move_type = "";
        this.finish_step();
    }

    //power = 1, -1, 0
    public void adjust_axis(double direction){
        this.axis1.move(direction * 0.2);
        this.axis2.move(direction * 0.2);
    }

    public void start()
    {

    }

    protected void next_step()
    {
        switch (move_type)
        {
            case "pincer" :
                switch (step)
                {
                    case 0 :
                        pincer1.move(0.12, 0.5);
                        pincer2.move(0.12, 0.5);
                        break;
                    case 1 :
                        axis1.move(0.6, 0.8);
                        axis2.move(0.6, 0.8);
                        break;
                    case 2:
                        pincer1.move(-0.12, 0.5);
                        pincer2.move(-0.12, 0.5);
                        this.change_move_type("release");
                        break;
                }
                break;

            case "release" :
                switch (step)
                {
                    case 0 :
                        axis1.move(-0.5);
                        axis2.move(-0.5);
                        this.sensor.activate(150.0, Distance.ActivateMode.Lower);
                        break;

                    case 1 :
                        axis1.move(0);
                        axis2.move(0);
                        break;

                    case 2:
                        this.finish_step();
                        break;
                }
                break;

            default:
                this.finish_step();
                break;
        }
        this.step++;
    }
}
