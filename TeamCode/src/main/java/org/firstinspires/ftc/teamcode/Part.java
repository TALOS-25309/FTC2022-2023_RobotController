package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Part {
    protected RobotUtility util = new RobotUtility();
    protected int step;
    protected String move_type;
    protected Telemetry telemetry;
    protected boolean move_finish;
    protected int m_step;

    protected void change_move_type(String move_type, int m_step){
        this.move_type = move_type;
        this.step = -1;
        this.m_step = m_step;
        this.move_finish = false;

    }

    protected void delay(double delay){
        long time = (long)(delay * 1000);
        try{
            Thread.sleep(time);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void start_step(String move_type, int m_step){
        this.move_type = move_type;
        this.step = 0;
        this.m_step = m_step;
        this.move_finish = false;
        this.next_step();
    }

    protected abstract void next_step();

    public void update(){
        util.update();
        if(util.finish()){
            if(m_step >= this.step)
            {
                this.next_step();
            }
            else
            {
                move_finish = true;
                this.next_step();
            }
        }
    }
}
