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

    public void init(HardwareMap hardwaremap, Telemetry telemetry, String name)
    {
        this.telemetry = telemetry;
        imu = hardwaremap.get(BNO055IMU.class, name);
        BNO055IMU.Parameters params = new BNO055IMU.Parameters();
        imu.initialize(params);
    }

    public void update()
    {

    }

    public double get_rotation(AngleUnit angleunit)
    {
        Orientation rotation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleunit);
        return rotation.firstAngle;
    }
}
