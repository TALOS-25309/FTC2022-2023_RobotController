package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Linear extends Part
{
    private DMotor rope = new DMotor();
    private SMotor ring = new SMotor();
    private Sensor bottom = new Sensor();
    private Sensor low = new Sensor();
    private Sensor middle = new Sensor();
    private Sensor high = new Sensor();

    public void init(HardwareMap hwm)
    {
        this.rope.init(hwm, "rope", 1);
        this.ring.init(hwm, "ring", 1, 0);
        this.bottom.init(hwm, "bottom", true);
        this.low.init(hwm, "low", true);
        this.middle.init(hwm, "middle", true);
        this.high.init(hwm, "high", true);


        DMotor[] dl = {this.rope};
        SMotor[] sl = {this.ring};
        Sensor[] snl = {this.low, this.middle, this.high};

        this.util.init(dl/*, sl, snl*/);

        this.step = 0;
    }

    public void start(){

    }

    protected void next_step()
    {
        switch (move_type)
        {
            case "low" :
                switch (step)
                {
                    case 0 :
                        rope.move(0.1);
                        low.activate();
                        break;
                    case 1 :
                        rope.move(0.0);
                        ring.move(0.1, 0.4);
                        break;
                    case 2 :
                        ring.move(0.1, -0.4);
                        break;
                    case 3 :
                        rope.move(-0.1);
                        bottom.activate();
                        break;
                    case 4 :
                        rope.move(0.0);
                        break;
                }
        }
        step++;
    }
}
