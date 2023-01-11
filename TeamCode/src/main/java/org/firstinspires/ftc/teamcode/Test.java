package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Test extends Part
{
    private SMotor pincer1 = new SMotor();
    private Sensor magnet = new Sensor();

    public void init(HardwareMap hwm, Telemetry tel)
    {
        //touch.init(hwm, tel, "touch", true);
        //motor.init(hwm, tel, "motor", DMotor.Direction.Direct);
        magnet.init(hwm, tel, "low", true);
        //color.init(hwm, tel, "color");
        //distance.init(hwm, tel, "distance");
        //pincer1.init(hwm, tel, "pincer1", SMotor.Direction.Direct, 0);

        DMotor[] dl = {};
        SMotor[] sl = {};
        Sensor[] snl = {magnet};
        Color[] clr = {/*color*/};
        Distance[] dsl = {};

        this.telemetry = tel;

        util.init(dl, sl, snl, clr, dsl);
        this.step = 0;
    }
    public void start()
    {
        start_step("test");
    }
    protected void next_step()
    {
        switch (move_type)
        {
            /*
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
                */
             
            case "test" :
                switch (step%2)
                {
                    case 0 :
                        magnet.activate();
                    case 1:
                        telemetry.addData("Magnet", "Detected!!");
                }
        }
        //this.telemetry.addData("Step", step);
        step++;
    }
}
