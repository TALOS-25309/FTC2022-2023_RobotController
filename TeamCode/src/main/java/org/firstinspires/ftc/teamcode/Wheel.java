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
            private double front_left_speed = 0.43;
            private double front_right_speed = 0.43;
            private double back_left_speed = 0.5;
            private double back_right_speed = 0.5;

            public double front_left, front_right, back_left, back_right;
            public DirectionData(double front_left, double front_right, double back_left, double back_right){
                this.front_left = front_left * front_left_speed;
                this.front_right = front_right * front_right_speed;
                this.back_left = back_left * back_left_speed;
                this.back_right = back_right * back_right_speed;
            }
        }
    };

    private DMotor front_left = new DMotor();
    private DMotor front_right = new DMotor();
    private DMotor back_left = new DMotor();
    private DMotor back_right = new DMotor();
    private Color color = new Color();

    public void init(HardwareMap hwm, Telemetry tel){
        this.front_left.init(hwm, tel, "front left", DMotor.Direction.Reverse);
        this.front_right.init(hwm, tel, "front right", DMotor.Direction.Direct);
        this.back_left.init(hwm, tel, "back left", DMotor.Direction.Reverse);
        this.back_right.init(hwm, tel, "back right", DMotor.Direction.Direct);
        this.color.init(hwm, tel, "color");

        DMotor[] dl = {this.front_left, this.front_right, this.back_left, this.back_right};
        this.util = new RobotUtility();
        Sensor[] sen = {};
        SMotor[] sm = {};
        Color[] clr = {this.color};
        Distance[] dsl = {};
        this.util.init(dl, sm, sen, clr, dsl);

        this.step = 0;
        this.telemetry = tel;
        this.move_type = "";
        this.finish_step();
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
            case "detect signal":
                switch (step){
                    case 0:
                        this.move(0.03, Direction.Forward);
                        this.color.detect_color();
                        break;
                    case 1:
                        this.move(0, Direction.Forward);
                        this.delay(2);
                        this.finish_step();
                        break;
                }
                break;

            case "go parking place":
                switch (step){
                    case 0:
                        switch (this.color.get_parking_position()){
                            case 0:
                                this.move(0.03, Direction.Backward);
                                telemetry.addData("Parking", "Fail");
                                break;
                            case 1:
                                this.move(0.03, Direction.Left);
                                telemetry.addData("Parking", "1");
                                break;
                            case 2:
                                this.move(0, Direction.Forward);
                                telemetry.addData("Parking", "2");
                                break;
                            case 3:
                                this.move(0.03, Direction.Right);
                                telemetry.addData("Parking", "3");
                                break;
                        }
                        break;
                    case 1:
                        this.delay(1);
                        this.move(0, Direction.Forward);
                        this.finish_step();
                        break;
                }
                break;
        }
        this.step++;
    }
}