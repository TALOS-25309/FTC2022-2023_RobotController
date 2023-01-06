package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Test extends Part
{
    private DMotor motor = new DMotor();
    private SMotor servo = new SMotor();

    public void init(HardwareMap hwm, Telemetry tel)
    {
        motor.init(hwm, tel, "motor", 1);
        servo.init(hwm, tel, "servo", 1, 0);
        DMotor[] dl = {motor};
        SMotor[] sl = {servo};
        Sensor[] snl = {};

        this.telemetry = tel;

        util.init(dl, sl, snl);
        this.step = 0;
    }
    public void start()
    {
        start_step("1");
    }
    protected void next_step()
    {
        switch (move_type)
        {
            case "1" :
                switch (step % 3)
                {
                    case 0 :
                        motor.move(0.1, 0.5);
                        break;
                    case 1 :
                        servo.move(0.01, 1);
                        break;
                    case 2 :
                        servo.move(0.01, -1);
                        break;
                }
            case "2" :
                switch (step)
                {

                }
        }
        step++;
    }
}
