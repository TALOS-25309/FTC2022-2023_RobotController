package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Test extends Part
{
    Sensor touch = new Sensor();
    Sensor magnet = new Sensor();
    DMotor motor = new DMotor();
    Color color = new Color();
    Distance distance = new Distance();

    public void init(HardwareMap hwm, Telemetry tel)
    {
        //touch.init(hwm, tel, "touch", true);
        motor.init(hwm, tel, "motor", DMotor.Direction.Direct);
        //magnet.init(hwm, tel, "magnet", true);
        //color.init(hwm, tel, "color");
        //distance.init(hwm, tel, "distance");

        DMotor[] dl = {motor};
        SMotor[] sl = {};
        Sensor[] snl = {};
        Color[] clr = {/*color*/};
        Distance[] dsl = {};

        this.telemetry = tel;

        util.init(dl, sl, snl, clr, dsl);
        this.step = 0;
        move_finish = true;
    }
    public void start()
    {
        start_step("color");
    }
    protected void next_step()
    {
        switch (move_type)
        {
            case "color" :
                switch (step % 2)
                {
                    case 0:
                        color.detect_color();
                        break;
                    case 1:
                        telemetry.addData("Parking Place", color.get_parking_position());
                        move_finish = true;
                        break;
                }
                break;
            case "test" :
                motor.move(1.0);
        }
        this.telemetry.addData("Step", step);
        step++;
    }
}
