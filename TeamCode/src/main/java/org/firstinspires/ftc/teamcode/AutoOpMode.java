package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name = "AutoOp", group = "")
public class AutoOpMode extends OpMode
{
    //Debug Reason
    public static final boolean using_wheel = true;
    public static final boolean using_linear = true;
    public static final boolean using_pincer = true;
    public static final boolean using_IMU = true;

    private Pincer pincer_part = new Pincer();
    private Linear linear_part = new Linear();
    private Wheel wheel_part = new Wheel();
    private int step = 0;
    private Gyro imu = new Gyro();

    @Override
    public void init()
    {
        imu.init(hardwareMap, telemetry, "imu");
        pincer_part.init(hardwareMap, telemetry);
        linear_part.init(hardwareMap, telemetry);
        wheel_part.init(hardwareMap, telemetry, this.imu);
        this.step = 0;
    }

    @Override
    public void start()
    {
        if(using_pincer) pincer_part.start();
        if(using_linear) linear_part.start();
        if(using_wheel) wheel_part.start();
        if(using_IMU) this.procedure_run();
    }

    @Override
    public void loop()
    {
        //Update Mechanism Parts
        if(using_pincer) pincer_part.update();
        if(using_linear) linear_part.update();
        if(using_wheel) wheel_part.update();
        if(using_IMU) imu.update();

        //Perform the Procedure
        if(this.procedure_finish()){
            this.procedure_run();
        }
    }

    private void procedure_run(){
        String move_type = "";
        switch (this.step){
            case 0:
                move_type = "pincer";
                break;
            case 1:
                move_type = "signal detection";
                break;
            case 2:
                move_type = "first rotation";
                break;
            case 3:
                move_type = "high junction";
                break;
            case 4:
                move_type = "second rotation";
                break;
            case 5:
                move_type = "parking site";
                break;
            case 6:
                move_type = "parking";
                break;
        }
        this.step++;
        this.telemetry.addData("Auto Step " + String.valueOf(this.step), move_type);
        this.wheel_part.start_step(move_type);
        this.linear_part.start_step(move_type);
        this.pincer_part.start_step(move_type);
    }

    private boolean procedure_finish(){
        return this.pincer_part.finish() && this.linear_part.finish() && this.wheel_part.finish();
    }
}