package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Part {
    protected RobotUtility util = new RobotUtility();
    protected int step;
    protected String move_type = "";
    protected Telemetry telemetry;
    private boolean move_finish = true;
    private long delay_time = 0;

    protected void change_move_type(String move_type){
        this.move_type = move_type;
        this.step = -1;
        this.move_finish = false;
        this.delay_time = 0;
    }

    protected void delay(double delay){
        /*
        long time = (long)(delay * 1000);
        try{
            Thread.sleep(time);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        */
        delay_time = System.currentTimeMillis() + (long)(1000 * delay);
    }

    public void start_step(String move_type){
        this.move_type = move_type;
        this.step = 0;
        this.move_finish = false;
        this.next_step();
    }

    protected abstract void next_step();

    public void update(){
        util.update();
        if(util.finish() && System.currentTimeMillis() > this.delay_time){
            this.next_step();
        }
    }

    public void emergency_stop(){
        util.emergency_stop();
    }

    protected void finish_step(){
        this.move_type = "";
        this.move_finish = true;
    }

    public boolean finish(){
        return this.move_finish;
    }
}
