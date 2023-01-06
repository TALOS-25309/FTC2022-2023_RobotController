package org.firstinspires.ftc.teamcode;

public class RobotUtility {
    private DMotor[] dm_list;
    private SMotor[] sm_list;
    private Sensor[] sen_list;

    public void init(DMotor[] dm_list, SMotor[] sm_list, Sensor[] sen_list){
        this.dm_list = dm_list;
        this.sm_list = sm_list;
        this.sen_list = sen_list;
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
        return true;
    }
}
