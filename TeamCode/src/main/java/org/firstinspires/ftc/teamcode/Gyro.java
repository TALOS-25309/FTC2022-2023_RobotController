package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Gyro {

    private BNO055IMU imu;
    private Telemetry telemetry;
    public boolean finish;
    public int dir = 1;

    private double target_degree = 0;
    private double i_degree = 0;

    public void init(HardwareMap hardwaremap, Telemetry telemetry, String name)
    {
        this.telemetry = telemetry;
        imu = hardwaremap.get(BNO055IMU.class, name);
        BNO055IMU.Parameters params = new BNO055IMU.Parameters();
        this.target_degree = 0;
        this.finish = true;
        imu.initialize(params);
    }

    public void update()
    {
        if(!this.finish()){
            double f_degree = this.get_rotation(AngleUnit.DEGREES);
            double f_i = f_degree - i_degree;
            double f_t = f_degree - target_degree;
            double t_i = target_degree - i_degree;
            f_i = (double)Math.floor(f_i * 10) / 10.0;
            f_t = (double)Math.floor(f_t * 10) / 10.0;
            t_i = (double)Math.floor(t_i * 10) / 10.0;
            telemetry.addData("Degree", String.valueOf(f_i) + " " + String.valueOf(f_t) + " " + String.valueOf(t_i));
            if(f_i * this.dir < 0) f_i += 360 * this.dir;
            if(f_t * this.dir < 0) f_t += 360 * this.dir;
            if(t_i * this.dir < 0) t_i += 360 * this.dir;
            telemetry.addData("Degree", String.valueOf(f_i) + " " + String.valueOf(f_t) + " " + String.valueOf(t_i));
            telemetry.addData("Degree", String.valueOf(i_degree) + " " + String.valueOf(f_degree) +  " " + String.valueOf(target_degree));

            if(Math.abs(f_i - (f_t + t_i)) < 1){
                this.finish = true;
            }
        }
    }

    public double get_rotation(AngleUnit angleunit)
    {
        Orientation rotation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleunit);
        return rotation.firstAngle;
    }

    private double set_degree(double degree){
        while(degree <= -180){
            degree +=  360;
        }
        while(degree > 180){
            degree -= 360;
        }
        return degree;
    }

    public void activate(double target_degree, Wheel.Direction dir){
        this.i_degree = this.get_rotation(AngleUnit.DEGREES);
        this.target_degree = this.set_degree(target_degree + this.i_degree);
        if(dir == Wheel.Direction.TurnLeft){
            this.dir = 1;
        }
        else{
            this.dir = -1;
        }
        this.finish = false;
    }

    public boolean finish(){
        this.telemetry.addData("???","");
        return this.finish;
    }
}
