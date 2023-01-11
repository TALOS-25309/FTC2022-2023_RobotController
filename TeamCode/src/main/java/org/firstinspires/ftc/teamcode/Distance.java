package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


public class Distance
{
    private DistanceSensor distance;
    private Telemetry telemetry;
    private boolean finish;
    private ActivateMode activate_mode;
    private double target_distance;

    public enum ActivateMode {
        Equal, NotEqual, Upper, Lower
    };

    public void init(HardwareMap hardwaremap, Telemetry telemetry, String name)
    {
        distance = hardwaremap.get(DistanceSensor.class, name);
        this.telemetry = telemetry;
        finish = true;
    }

    public void activate(double target_distance, ActivateMode mode){
        this.target_distance = target_distance;
        this.activate_mode = mode;
        this.finish = false;
    }

    public void update()
    {
        if(!this.finish()){
            switch (this.activate_mode){
                case Equal:
                    if(Math.abs(this.get_distance(DistanceUnit.MM) - target_distance) < 1) {
                        this.finish = true;
                    }
                    break;
                case NotEqual:
                    if(Math.abs(this.get_distance(DistanceUnit.MM) - target_distance) > 1) {
                        this.finish = true;
                    }
                    break;
                case Upper:
                    if(this.get_distance(DistanceUnit.MM) > target_distance) {
                        this.finish = true;
                    }
                    break;
                case Lower:
                    if(this.get_distance(DistanceUnit.MM) < target_distance) {
                        this.finish = true;
                    }
                    break;
            }
        }
    }

    public double get_distance(DistanceUnit du)
    {
        return distance.getDistance(du);
    }

    public boolean finish()
    {
        return finish;
    }
}
