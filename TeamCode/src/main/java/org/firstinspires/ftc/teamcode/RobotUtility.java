package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RobotUtility {
    private DMotor[] dm_list;
    private SMotor[] sm_list;
    private Sensor[] sen_list;
    private Color[] clr_list;
    private Distance[] dis_list;
    private Gyro imu = null;
    private Telemetry telemetry;

    public void init(DMotor[] dm_list, SMotor[] sm_list, Sensor[] sen_list, Color[] clr_list, Distance[] dis_list, Gyro imu, Telemetry tel){
        this.dm_list = dm_list;
        this.sm_list = sm_list;
        this.sen_list = sen_list;
        this.clr_list = clr_list;
        this.dis_list = dis_list;
        this.imu = imu;
        this.telemetry = tel;
    }

    public void update(){
        for(int i=0; i<this.dm_list.length; i++){
            dm_list[i].update();
        }
        for(int i=0; i<this.sm_list.length; i++){
            sm_list[i].update();
        }
        for(int i=0; i<this.sen_list.length; i++){
            sen_list[i].update();
        }
        for(int i=0; i<this.clr_list.length; i++){
            clr_list[i].update();
        }
        for(int i=0; i<this.dis_list.length; i++){
            dis_list[i].update();
        }
    }

    public boolean finish(){
        for(int i=0; i<this.dm_list.length; i++){
            if(!dm_list[i].finish()){
                return false;
            }
        }
        for(int i=0; i<this.sm_list.length; i++){
            if(!sm_list[i].finish()){
                return false;
            }
        }
        for(int i=0; i<this.sen_list.length; i++){
            if(!sen_list[i].finish()){
                return false;
            }
        }
        for(int i=0; i<this.clr_list.length; i++){
            if(!clr_list[i].finish()){
                return false;
            }
        }
        for(int i=0; i<this.dis_list.length; i++){
            if(!dis_list[i].finish()){
                return false;
            }
        }
        if(this.imu != null){
            if(!this.imu.finish())
                return false;
        }
        return true;
    }

    public void emergency_stop(){
        for(int i=0; i<this.dm_list.length; i++){
            this.dm_list[i].move(0);
        }
    }
}
