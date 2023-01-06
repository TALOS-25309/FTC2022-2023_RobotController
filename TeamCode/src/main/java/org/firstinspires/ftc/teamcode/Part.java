package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Part {
    protected RobotUtility util = new RobotUtility();
    protected int step;
    protected String move_type;
    protected Telemetry telemetry;

    protected abstract void next_step();
    public void update(){
        util.update();
        this.telemetry.addData("finish", util.finish());
        if(util.finish()){
            this.next_step();
        }
    }
}
