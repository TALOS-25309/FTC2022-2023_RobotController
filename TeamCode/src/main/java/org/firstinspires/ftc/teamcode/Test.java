package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Test extends Part
{
    private Color color = new Color();
    public void init(HardwareMap hwm, Telemetry tel)
    {
        //touch.init(hwm, tel, "touch", true);
        //motor.init(hwm, tel, "motor", DMotor.Direction.Direct);
        //magnet.init(hwm, tel, "low", true);
        color.init(hwm, tel, "color");

        //distance.init(hwm, tel, "distance");
        //pincer1.init(hwm, tel, "pincer1", SMotor.Direction.Direct, 0);

        DMotor[] dl = {};
        SMotor[] sl = {};
        Sensor[] snl = {};
        Color[] clr = {color};
        Distance[] dsl = {};

        this.telemetry = tel;

        util.init(dl, sl, snl, clr, dsl, null, this.telemetry);
        this.step = 0;
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
                        color.detect_color(10);
                        break;
                    case 1:
                        telemetry.addData("Parking Place", color.get_parking_position());
                        this.finish_step();
                        break;
                }
                break;
        }
        //this.telemetry.addData("Step", step);
        step++;
    }
}
