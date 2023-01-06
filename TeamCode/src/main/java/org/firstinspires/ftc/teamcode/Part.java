package org.firstinspires.ftc.teamcode;

public abstract class Part {
    protected Utility util = new Utility();
    protected int step;
    protected String move_type;

    protected abstract void next_step();
    public void update(){
        util.update();
        if(util.finish()){
            this.next_step();
        }
    }
}
