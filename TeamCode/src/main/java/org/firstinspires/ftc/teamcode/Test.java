package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Test extends Part
{
    private DMotor motor = new DMotor();
    private DMotor smotor = new DMotor();
    private SMotor servo = new SMotor();

    public void init(HardwareMap hwm, Telemetry tel)
    {
        motor.init(hwm, tel, "motor", 1);
        smotor.init(hwm, tel, "smotor", 1);
        servo.init(hwm, tel, "servo", 1, 0);
        DMotor[] dl = {motor, smotor};
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
                switch (step % 2)
                {
                    case 0 :
                        smotor.move(0.1, 0.5);
                        break;
                    case 1 :
                        smotor.move(0.02, 0.1);
                        break;
                }
                /*
            case "3" :
                switch (step%2)
                {
                    case 0 :
                        servo1.move(0.003, 0.3);
                        servo2.move(0.003, 0.3);
                        break;
                    case 1 :
                        servo1.move(0.003, 0.3);
                        servo2.move(0.003, 0.3);
                        break;
                }
                 */
        }
        step++;
    }
}
