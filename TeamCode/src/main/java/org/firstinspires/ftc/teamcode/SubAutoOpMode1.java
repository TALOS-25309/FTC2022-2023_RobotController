package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Sub Auto (Left)", group = "")
public class SubAutoOpMode1 extends LinearOpMode
{
    private Pincer pincer_part = new Pincer();
    private Linear linear_part = new Linear();
    private Wheel wheel_part = new Wheel();
    private int step = 0;
    private Gyro imu = new Gyro();
    private boolean run;

    @Override
    public void runOpMode(){
        //init
        imu.init(hardwareMap, telemetry, "imu");
        pincer_part.init(hardwareMap, telemetry);
        linear_part.init(hardwareMap, telemetry);
        wheel_part.init(hardwareMap, telemetry, this.imu);
        this.step = 0;

        waitForStart();

        //start
        pincer_part.start();
        linear_part.start();
        wheel_part.start();
        this.procedure_run();

        //do something
        run = true;
        while(run){
            //Update Mechanism Parts
            pincer_part.update();
            linear_part.update();
            wheel_part.update();
            imu.update();
            telemetry.update();

            //Perform the Procedure
            if(this.procedure_finish()){
                this.procedure_run();
            }
        }
    }

    private void procedure_run(){
        switch (this.step){
            case 0:
                this.wheel_part.start_step("sub go junction");
                break;
            case 1:
                this.wheel_part.start_step("sub rotate left");
                this.linear_part.start_step("middle junction");
                break;
            case 2:
                this.wheel_part.start_step("sub go to junction (accurate)");
                break;
            case 3:
                this.linear_part.start_step("low junction");
                break;
            case 4:
                this.wheel_part.start_step("sub go back junction (accurate)");
                break;
            case 5:
                this.linear_part.start_step("down");
                this.wheel_part.start_step("sub rotate right");
                break;
            case 6:
                this.wheel_part.start_step("sub go home");
                break;
            case 7:
                //this.wheel_part.start_step("sub rotate home left");
                break;
            case 8:
                this.wheel_part.start_step("sub go left parking");
                break;
        }
        this.step++;
    }

    /*
            case 0:
                this.wheel_part.start_step("signal detection");
                break;
            case 1:
                this.wheel_part.start_step("first rotation");
                this.linear_part.start_step("high junction");
                break;
            case 2:
                this.wheel_part.start_step("junction range");
                break;
            case 3:
                this.linear_part.start_step("stack");
                this.wheel_part.start_step("end junction");
                break;
            case 4:
                this.wheel_part.start_step("second rotation");
                break;
            case 5:
                this.wheel_part.start_step("parking");
                break;
            case 6:
                this.run = false;
                break;
             */

    private boolean procedure_finish(){
        return this.pincer_part.finish() && this.linear_part.finish() && this.wheel_part.finish();
    }
}