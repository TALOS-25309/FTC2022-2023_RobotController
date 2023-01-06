package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Linear extends Part
{
    private DMotor rope = new DMotor();
    private SMotor ring = new SMotor();
    private Sensor low = new Sensor();
    private Sensor middle = new Sensor();
    private Sensor high = new Sensor();

    public void init(HardwareMap hwm)
    {
        this.rope.init(hwm, "rope", 1);
        this.ring.init(hwm, "ring", 1, 0);
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

    }
}
