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
    public boolean finish;

    public void init(HardwareMap hardwaremap, Telemetry telemetry, String name)
    {
        distance = hardwaremap.get(DistanceSensor.class, name);
        this.telemetry = telemetry;
        finish = true;
    }

    public void update()
    {

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
