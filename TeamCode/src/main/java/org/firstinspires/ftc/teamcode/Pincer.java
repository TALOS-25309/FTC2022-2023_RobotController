package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Pincer extends Part
{
    private SMotor pincer1 = new SMotor();
    private SMotor pincer2 = new SMotor();
    private DMotor axis1 = new DMotor();
    private DMotor axis2 = new DMotor();
    private Distance sensor = new Distance();
    private boolean open;

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
        this.open = true;
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

    private double distance = 150;

    private boolean ground(){
        return this.sensor.get_distance(DistanceUnit.MM) <= distance;
    }

    protected void next_step()
    {
        switch (move_type)
        {
            case "ground junction":
                if(this.ground()){
                    this.change_move_type("ground up");
                }
                else {
                    this.change_move_type("ground down");
                }
                break;

            case "ground up":
                switch (step)
                {
                    case 0 :
                        pincer1.move(0.12, 0.5);
                        pincer2.move(0.12, 0.5);
                        break;
                    case 1 :
                        this.open = false;
                        axis1.move(0.6);
                        axis2.move(0.6);
                        this.sensor.activate(distance, Distance.ActivateMode.Upper);
                        break;
                    case 2:
                        axis1.move(0.0);
                        axis2.move(0.0);
                        this.finish_step();
                        break;
                }
                break;

            case "ground down":
                switch (step)
                {
                    case 0 :
                        pincer1.move(-0.12, 0.5);
                        pincer2.move(-0.12, 0.5);
                        break;
                    case 1 :
                        this.open = true;
                        this.change_move_type("pincer down");
                        break;
                }
                break;

            case "grab":
                switch(step){
                    case 0:
                        if(this.open){
                            pincer1.move(0.12, 0.5);
                            pincer2.move(0.12, 0.5);
                            this.open = false;
                        }
                        else{
                            pincer1.move(-0.12, 0.5);
                            pincer2.move(-0.12, 0.5);
                            this.open = true;
                        }
                        break;
                    case 1:
                        this.finish_step();
                        break;
                }
                break;

            case "pincer" :
                if(this.ground()){
                    this.change_move_type("pincer up");
                }
                else{
                    this.change_move_type("pincer down");
                }
                break;

            case "pincer up":
                switch (step)
                {
                    case 0 :
                        pincer1.move(0.12, 0.5);
                        pincer2.move(0.12, 0.5);
                        break;
                    case 1 :
                        axis1.move(1, 0.7);
                        axis2.move(1, 0.7);
                        this.open = false;
                        break;
                    case 2:
                        pincer1.move(-0.12, 0.5);
                        pincer2.move(-0.12, 0.5);
                    case 3:
                        this.open = true;
                        this.finish_step();
                        break;
                }
                break;

            case "pincer down" :
                switch (step)
                {
                    case 0 :
                        axis1.move(-0.4);
                        axis2.move(-0.4);
                        this.sensor.activate(distance, Distance.ActivateMode.Lower);
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

    public double get_distance(){
        return this.sensor.get_distance(DistanceUnit.MM);
    }
}
