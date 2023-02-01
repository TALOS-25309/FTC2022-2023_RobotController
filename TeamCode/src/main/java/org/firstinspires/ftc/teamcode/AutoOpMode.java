package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name = "AutoOp", group = "")
public class AutoOpMode extends LinearOpMode
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
                this.wheel_part.start_step("signal detection");
                break;
            case 1:
                this.wheel_part.start_step("rotation");
                break;
            case 2:
                this.wheel_part.start_step("parking site");
                break;
            case 3:
                this.run = false;
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