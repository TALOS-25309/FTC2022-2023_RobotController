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
    private double accuracy = 0.2; // accuracy >= 0
    private double distance_limit = 30; // 100 > distance_limit > 10, unit = mm

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

    private boolean maximum(int target, int other1, int other2){
        return target > other1 * (accuracy + 1) && target > other2 * (accuracy + 1);
    }

    public void update()
    {
        if(!this.finish){
            /*
            //LOG
            telemetry.addData("Red", this.color_sensor.red());
            telemetry.addData("Green", this.color_sensor.green());
            telemetry.addData("Blue", this.color_sensor.blue());
            telemetry.addData("Distance", this.distance_sensor.getDistance(DistanceUnit.METER));
             */
            if(this.distance_sensor.getDistance(DistanceUnit.MM) < 55){
                int r = this.color_sensor.red();
                int g = this.color_sensor.green();
                int b = this.color_sensor.blue();
                if(this.maximum(r, g, b)){
                    this.parking_pos = 1;
                }
                else if(this.maximum(g, r, b)){
                    this.parking_pos = 2;
                }
                else if(this.maximum(b, r, g)){
                    this.parking_pos = 3;
                }
                this.finish = true;
            }
        }
    }

    //색을 식별할 수 있는 위치가 되면 finish = true & 그때의 색을 판단
    public void detect_color() {
        this.finish = false;
    }

    //판단한 색에 따라 숫자 출력
    public int get_parking_position() {
        return this.parking_pos; //0 = 식별 실패, 1 = 1번 위치, 2 = 2번 위치, 3 = 3번 위치
    }

    public boolean finish()
    {
        return this.finish;
    }
}
