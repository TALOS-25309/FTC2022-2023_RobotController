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
        this.low.init(hwm, "low");
        this.middle.init(hwm, "middle");
        this.high.init(hwm, "high");


        DMotor[] dl = {this.rope};
        SMotor[] sl = {this.ring};
        Sensor[] snl = {this.low, this.middle, this.high};


        this.util = new Utility();
        this.util.init(dl, sl, snl);

        this.step = 0;
    }

    public void start(){
        this.next_step();
    }

    public void move(double speed, double angle, Direction dir)
    {
        this.front_left.move(speed * dir.front_left, angle);
        //this.front_right.move(speed * dir.front_right, angle);
        //this.back_left.move(speed * dir.back_left, angle);
        //this.back_right.move(speed * dir.back_right, angle);
    }

    public void move(double speed, Direction dir)
    {
        this.front_left.move(speed * dir.front_left);
        //this.front_right.move(speed * dir.front_right);
        //this.back_left.move(speed * dir.back_left);
        //this.back_right.move(speed * dir.back_right);
    }

    protected void next_step()
    {
        switch(this.step % 4)
        {
            case 0:
                this.move(0.3, 1.0, this.forward);
                break;
            case 1:
                this.move(0.2, 1.0, this.left);
            case 2:
                this.move(0.2, 1.0, this.backward);
            case 3:
                this.move(0.3, 1.0, this.right);
        }
        this.step++;
    }
}
