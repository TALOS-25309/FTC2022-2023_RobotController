package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Color {
    private ColorSensor color_sensor;
    private DistanceSensor distance_sensor;
    private Telemetry telemetry;
    private boolean finish;
    private int parking_pos;
    private double accuracy = 0.3; // accuracy >= 0
    private double distance_limit = 10; // 100 > distance_limit > 1, unit = mm
    private long finish_time;

    public void init(HardwareMap hardwaremap, Telemetry telemetry, String name)
    {
        if(this.color_sensor == null){
            color_sensor = hardwaremap.get(ColorSensor.class, name);
        }
        distance_sensor = hardwaremap.get(DistanceSensor.class, name);
        this.telemetry = telemetry;
        this.finish = true;
        this.parking_pos = 0;
    }

    private boolean maximum(double target, double other1, double other2){
        return target > other1 * (accuracy + 1) && target > other2 * (accuracy + 1);
    }

    public void update()
    {
        if(!this.finish){
            if(this.distance_sensor.getDistance(DistanceUnit.MM) <= distance_limit){
                double r = this.color_sensor.red() * 1.73;
                double g = this.color_sensor.green();
                double b = this.color_sensor.blue() * 1.23;
                if(this.maximum(r, g, b)){
                    this.parking_pos = 1;
                }
                else if(this.maximum(g, r, b)){
                    this.parking_pos = 2;
                }
                else if(this.maximum(b, r, g)){
                    this.parking_pos = 3;
                }
                else {
                    this.parking_pos = 0;
                }

                if(this.parking_pos != 0){
                    /*
                    //LOG
                    telemetry.addData("Red", r);
                    telemetry.addData("Green", g);
                    telemetry.addData("Blue", b);
                    telemetry.addData("Distance", this.distance_sensor.getDistance(DistanceUnit.MM));
                    //*/
                    telemetry.addData("Parking Point", this.parking_pos);
                    this.finish = true;
                }
                else{
                    telemetry.addData("Parking Point", "Not Found");
                }
            }
            else if(System.currentTimeMillis() > this.finish_time){
                telemetry.addData("Parking Point", "Not Found");
                this.finish = true;
            }
        }
    }

    //색을 식별할 수 있는 위치가 되면 finish = true & 그때의 색을 판단
    public void detect_color(double duration) {
        this.finish = false;
        this.finish_time = System.currentTimeMillis() + (long)(duration * 1000);
    }

    //판단한 색에 따라 숫자 출력
    public int get_parking_position() {
        return this.parking_pos; //0 = 식별 실패, 1 = 1번 위치, 2 = 2번 위치, 3 = 3번 위치
    }

    public double get_distance(){
        return this.distance_sensor.getDistance(DistanceUnit.CM);
    }

    public boolean finish()
    {
        return this.finish;
    }
}
