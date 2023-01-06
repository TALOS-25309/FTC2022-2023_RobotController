package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Wheel extends Part {
    public static class Direction{
        public double front_left, front_right, back_left, back_right;
        public Direction(double front_left, double front_right, double back_left, double back_right){
            this.front_left = front_left;
            this.front_right = front_right;
            this.back_left = back_left;
            this.back_right = back_right;
        }
    }

    public final Direction forward = new Direction(1,1,1,1);
    public final Direction backward = new Direction(-1,-1,-1, -1);
    public final Direction left = new Direction(-1,1,1,-1);
    public final Direction right = new Direction(1,-1,-1,1);

    private DMotor front_left = new DMotor();
    private DMotor front_right = new DMotor();
    private DMotor back_left = new DMotor();
    private DMotor back_right = new DMotor();

    public void init(HardwareMap hwm){
        this.front_left.init(hwm, "wheel0", 1);
        this.front_right.init(hwm, "wheel1", 1);
        this.back_left.init(hwm, "wheel2", -1);
        this.back_right.init(hwm, "wheel3", -1);

        DMotor[] dl = {this.front_left, this.front_right, this.back_left, this.back_right};
        this.util = new Utility();
        this.util.init(dl);
        //this.util.init(dl, null, null);

        this.step = 0;
    }

    public void start(){
        move_type = "1";
        this.next_step();
    }

    public void move(double speed, double angle, Direction dir){
        this.front_left.move(speed, angle * dir.front_left);
        this.front_right.move(speed, angle * dir.front_right);
        this.back_left.move(speed, angle * dir.back_left);
        this.back_right.move(speed, angle * dir.back_right);
    }

    public void move(double speed, Direction dir){
        this.front_left.move(speed * dir.front_left);
        this.front_right.move(speed * dir.front_right);
        this.back_left.move(speed * dir.back_left);
        this.back_right.move(speed * dir.back_right);
    }

    protected void next_step(){
        switch (move_type)
        {
            case "1" :
                switch(this.step % 4){
                    case 0:
                        this.move(0.3, 1.0, this.forward);
                        break;
                    case 1:
                        this.move(0.2, 1.0, this.left);
                    case 2:
                        this.move(0.2, 1.0, this.backward);
                    case 3:
                        this.move(0.3, 1.0, this.right);
                }
        }
        this.step++;
    }
}
