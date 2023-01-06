package org.firstinspires.ftc.teamcode;

public abstract class Part {
    protected Utility util;
    protected int step;

    protected abstract void next_step();
    public void update(){
        util.update();
        if(util.finish()){
            this.next_step();
        }
    }
}
