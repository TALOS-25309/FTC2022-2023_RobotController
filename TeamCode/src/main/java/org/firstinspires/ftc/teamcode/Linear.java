package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Linear extends Part
{
    private DMotor rope = new DMotor();
    private SMotor ring = new SMotor();
    private Sensor bottom = new Sensor();
    private Sensor low_point = new Sensor();
    private Sensor high_point = new Sensor();
    private int cnt = 0;

    public void init(HardwareMap hwm, Telemetry tel)
    {
        this.rope.init(hwm, tel, "rope", DMotor.Direction.Direct);
        this.ring.init(hwm, tel, "ring", SMotor.Direction.Reverse, 0);
        this.bottom.init(hwm, tel, "bottom", true);
        this.low_point.init(hwm, tel, "low", true);
        this.high_point.init(hwm, tel, "high", true);

        DMotor[] dl = {this.rope};
        SMotor[] sl = {this.ring};
        Sensor[] snl = {this.bottom, this.low_point, this.high_point};
        Color[] clr = {};
        Distance[] dsl = {};

        this.util.init(dl, sl, snl, clr, dsl);

        this.step = 0;
        this.telemetry = tel;
        this.move_finish = true;
    }

    public void start()
    {
    }

    //direction : -1, 0, 1
    public void adjust_rope(double direction)
    {
        rope.move(direction * 0.2);
    }

    protected void next_step()
    {
        switch (move_type)
        {
            case "go_low" :
                switch (step)
                {
                    case 0:
                        rope.move(0.6);
                        low_point.activate();
                        break;
                    case 1:
                        rope.move(0.0);
                        this.change_move_type("stack_cup");
                        break;
                }
                break;

            case "go_high" :
                switch (step)
                {
                    case 0 :
                        rope.move(0.6);
                        high_point.activate();
                        break;
                    case 1 :
                        rope.move(0.0);
                        this.change_move_type("stack_cup");
                        break;
                }
                break;

            case "stack_cup" :
                switch (step)
                {
                    case 0 :
                        ring.move(1, 1.0);
                        //rope.move(0.00041);
                        break;
                    case 1:
                        //rope.move(0);
                        this.change_move_type("down");
                        break;
                }
                break;

            case "simple_stack_cup" :
                switch (step)
                {
                    case 0 :
                        ring.move(1, 1.0);
                        break;
                    case 1:
                        ring.move(-1,1.0);
                        //this.change_move_type("redefine");
                        move_finish = true;
                        break;
                }
                break;

            case "down" :
                switch (step)
                {
                    case 0 :
                        ring.move(-1, 0.5);
                        this.delay(0.1);
                        break;
                    case 1:
                        rope.move(-0.5);
                        bottom.activate();
                        break;
                    case 2 :
                        this.change_move_type("reset");
                        break;
                }
                break;

            case "reset":
                switch(step){
                    case 0:
                        bottom.set_reverse(false);
                        bottom.activate();
                        rope.move(0.3);
                        break;
                    case 1:
                        rope.move(0.0);
                        bottom.set_reverse(true);
                        //this.change_move_type("redefine");
                        move_finish = true;
                        break;
                }
                break;

            case "redefine":
                switch (step){
                    case 0:
                        //String mode[] = {"go_high", "go_low", "simple_stack_cup"};
                        //this.change_move_type(mode[(int)(Math.random() * 100000) % 3]);
                        this.change_move_type("go_high");
                        this.delay(0.1);
                        this.cnt++;
                        this.telemetry.addData("Cnt", this.cnt);
                        //move_finish = true;
                        break;
                }
        }
        step++;
    }
}
