package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Wheel extends Part {
    public enum Direction {
        Forward(new DirectionData(1,1,1,1)),
        Backward(new DirectionData(-1,-1,-1, -1)),
        Left(new DirectionData(-1,1,1,-1)),
        Right(new DirectionData(1,-1,-1,1)),
        TurnLeft(new DirectionData(-1,1,-1,1)),
        TurnRight(new DirectionData(1,-1,1,-1));

        private final DirectionData value;
        Direction(DirectionData i) {this.value = i;}
        DirectionData get_value() {return this.value;}

        public static class DirectionData{
            public double front_left, front_right, back_left, back_right;
            public DirectionData(double front_left, double front_right, double back_left, double back_right){
                this.front_left = front_left;
                this.front_right = front_right;
                this.back_left = back_left;
                this.back_right = back_right;
            }
        }
    };

    private DMotor front_left = new DMotor();
    private DMotor front_right = new DMotor();
    private DMotor back_left = new DMotor();
    private DMotor back_right = new DMotor();

    public void init(HardwareMap hwm, Telemetry tel){
        this.front_left.init(hwm, tel, "front_left", DMotor.Direction.Direct);
        this.front_right.init(hwm, tel, "front_right", DMotor.Direction.Reverse);
        this.back_left.init(hwm, tel, "back_left", DMotor.Direction.Direct);
        this.back_right.init(hwm, tel, "back_right", DMotor.Direction.Reverse);

        DMotor[] dl = {this.front_left, this.front_right, this.back_left, this.back_right};
        this.util = new RobotUtility();
        Sensor[] sen = {};
        SMotor[] sm = {};
        Color[] clr = {};
        this.util.init(dl, sm, sen, clr);

        this.step = 0;
        this.telemetry = tel;
        this.move_finish = true;
    }

    public void start(){

    }

    public void move(double speed, double angle, Direction dir){
        this.front_left.move(speed, angle * dir.get_value().front_left);
        this.front_right.move(speed, angle * dir.get_value().front_right);
        this.back_left.move(speed, angle * dir.get_value().back_left);
        this.back_right.move(speed, angle * dir.get_value().back_right);
    }

    public void move(double speed, Direction dir){
        this.front_left.move(speed * dir.get_value().front_left);
        this.front_right.move(speed * dir.get_value().front_right);
        this.back_left.move(speed * dir.get_value().back_left);
        this.back_right.move(speed * dir.get_value().back_right);
    }

    protected void next_step(){
        switch (move_type)
        {

        }
        this.step++;
    }
}