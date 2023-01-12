package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name = "Test_Op", group = "")
public class TestOpMode extends OpMode
{
    private Test test = new Test();
    private Gyro imu = new Gyro();

    @Override
    public void init()
    {
        test.init(hardwareMap, telemetry);
        imu.init(hardwareMap, telemetry, "gyro");

        //imu = hardwareMap.get(BNO055IMU.class, "imu");
        //BNO055IMU.Parameters params = new BNO055IMU.Parameters();
        //imu.initialize(params);
    }

    @Override
    public void start()
    {
        test.start();
    }

    @Override
    public void loop()
    {
        test.update();
        telemetry.update();
        this.imu.update();
    }
}