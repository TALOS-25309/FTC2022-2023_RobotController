package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Wheel extends Part {
    public enum Direction {
        Forward(new DirectionData(0.65,0.65,0.65,0.65)),
        Backward(new DirectionData(-0.65,-0.65,-0.65, -0.65)),
        Left(new DirectionData(-0.5,0.5,0.5,-0.5)),
        Right(new DirectionData(0.5,-0.5,-0.5,0.5)),
        TurnLeft(new DirectionData(-0.25,0.25,-0.25,0.25)),
        TurnRight(new DirectionData(0.25,-0.25,0.25,-0.25));

        private final DirectionData value;
        Direction(DirectionData i) {this.value = i;}
        DirectionData get_value() {return this.value;}

        public static class DirectionData{
            private double front_left_speed = 1.0;
            private double front_right_speed = 1.0;
            private double back_left_speed = 1.0;
            private double back_right_speed = 1.0;

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
    private Gyro imu;

    private double left_move = 0.16;
    private double right_move = 0.14;

    public void init(HardwareMap hwm, Telemetry tel, Gyro imu){
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
        this.util.init(dl, sm, sen, clr, dsl, imu, tel);

        this.step = 0;
        this.telemetry = tel;
        this.imu = imu;
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

    public void move_stop(){
        this.front_left.move(0);
        this.front_right.move(0);
        this.back_left.move(0);
        this.back_right.move(0);
    }

    protected void next_step(){
        switch (move_type)
        {
            case "signal detection":
                switch (step){
                    case 0:
                        this.move(0.15, 0.8, Direction.Forward);
                        this.color.detect_color(3);
                        break;
                    case 1:
                        this.delay(1);
                        break;
                    case 2:
                        this.finish_step();
                        break;
                }
                break;

            case "rotation":
                switch(this.color.get_parking_position()){
                    case 0:
                        telemetry.addData("Parking", "Fail");
                        switch (step){
                            case 0:
                                this.move(0.15, right_move, Direction.Backward);
                                break;
                            case 1:
                                this.delay(1);
                                break;
                            case 2:
                                this.move(0.1, Direction.TurnRight);
                                this.imu.activate(-90, Direction.TurnRight);
                                break;
                            case 3:
                                this.move_stop();
                                this.delay(0.3);
                                break;
                            case 4:
                                this.move(0.05, Direction.TurnLeft);
                                this.imu.correction();
                                break;
                            case 5:
                                this.move_stop();
                                this.delay(0.3);
                                break;
                            case 6:
                                this.finish_step();
                                break;
                        }
                        break;
                    case 1:
                        telemetry.addData("Parking", "1");
                        switch (step){
                            case 0:
                                this.move(0.15, left_move, Direction.Backward);
                                break;
                            case 1:
                                this.delay(1);
                                break;
                            case 2:
                                this.move(0.1, Direction.TurnLeft);
                                this.imu.activate(90, Direction.TurnLeft);
                                break;
                            case 3:
                                this.move_stop();
                                this.delay(0.3);
                                break;
                            case 4:
                                this.move(0.05, Direction.TurnRight);
                                this.imu.correction();
                                break;
                            case 5:
                                this.move_stop();
                                this.delay(0.3);
                                break;
                            case 6:
                                this.finish_step();
                                break;
                        }
                        break;
                    case 2:
                        telemetry.addData("Parking", "2");
                        switch (step){
                            case 0:
                                this.move(0.15, right_move, Direction.Backward);
                                break;
                            case 1:
                                this.delay(1);
                                break;
                            case 2:
                                this.move(0.1, Direction.TurnRight);
                                this.imu.activate(-90, Direction.TurnRight);
                                break;
                            case 3:
                                this.move_stop();
                                this.delay(0.3);
                                break;
                            case 4:
                                this.move(0.05, Direction.TurnLeft);
                                this.imu.correction();
                                break;
                            case 5:
                                this.move_stop();
                                this.delay(0.3);
                                break;
                            case 6:
                                this.finish_step();
                                break;
                        }
                        break;
                    case 3:
                        telemetry.addData("Parking", "3");
                        switch (step){
                            case 0:
                                this.move(0.15, right_move, Direction.Backward);
                                break;
                            case 1:
                                this.delay(1);
                                break;
                            case 2:
                                this.move(0.1, Direction.TurnRight);
                                this.imu.activate(-90, Direction.TurnRight);
                                break;
                            case 3:
                                this.move_stop();
                                this.delay(0.3);
                                break;
                            case 4:
                                this.move(0.05, Direction.TurnLeft);
                                this.imu.correction();
                                break;
                            case 5:
                                this.move_stop();
                                this.delay(0.3);
                                break;
                            case 6:
                                this.finish_step();
                                break;
                        }
                        break;
                }
                break;

            case "parking site":
                if(this.color.get_parking_position() != 2){
                    switch (step){
                        case 0:
                            this.move(0.1, 0.6, Direction.Forward);
                            break;
                        case 1:
                            this.move_stop();
                            this.delay(0.3);
                            break;
                        case 2:
                            this.finish_step();
                            break;
                    }
                }
                else{
                    switch(step){
                        case 0:
                            this.move_stop();
                            this.delay(0.3);
                            break;
                        case 1:
                            this.finish_step();
                            break;
                    }
                }
                break;

            case "rotation otherwise":
                switch(this.color.get_parking_position()){
                    case 0:
                        telemetry.addData("Parking", "Fail");
                        switch (step){
                            case 0:
                                this.move(0.15, left_move, Direction.Backward);
                                break;
                            case 1:
                                this.delay(1);
                                break;
                            case 2:
                                this.move(0.1, Direction.TurnLeft);
                                this.imu.activate(90, Direction.TurnLeft);
                                break;
                            case 3:
                                this.move_stop();
                                this.delay(0.3);
                                break;
                            case 4:
                                this.move(0.05, Direction.TurnRight);
                                this.imu.correction();
                                break;
                            case 5:
                                this.move_stop();
                                this.delay(0.3);
                                break;
                            case 6:
                                this.finish_step();
                                break;
                        }
                        break;
                    case 1:
                        telemetry.addData("Parking", "1");
                        switch (step){
                            case 0:
                                this.move(0.15, left_move, Direction.Backward);
                                break;
                            case 1:
                                this.delay(1);
                                break;
                            case 2:
                                this.move(0.1, Direction.TurnLeft);
                                this.imu.activate(90, Direction.TurnLeft);
                                break;
                            case 3:
                                this.move_stop();
                                this.delay(0.3);
                                break;
                            case 4:
                                this.move(0.05, Direction.TurnRight);
                                this.imu.correction();
                                break;
                            case 5:
                                this.move_stop();
                                this.delay(0.3);
                                break;
                            case 6:
                                this.finish_step();
                                break;
                        }
                        break;
                    case 2:
                        telemetry.addData("Parking", "2");
                        switch (step){
                            case 0:
                                this.move(0.15, left_move, Direction.Backward);
                                break;
                            case 1:
                                this.delay(1);
                                break;
                            case 2:
                                this.move(0.1, Direction.TurnLeft);
                                this.imu.activate(90, Direction.TurnLeft);
                                break;
                            case 3:
                                this.move_stop();
                                this.delay(0.3);
                                break;
                            case 4:
                                this.move(0.05, Direction.TurnRight);
                                this.imu.correction();
                                break;
                            case 5:
                                this.move_stop();
                                this.delay(0.3);
                                break;
                            case 6:
                                this.finish_step();
                                break;
                        }
                        break;
                    case 3:
                        telemetry.addData("Parking", "3");
                        switch (step){
                            case 0:
                                this.move(0.15, right_move, Direction.Backward);
                                break;
                            case 1:
                                this.delay(1);
                                break;
                            case 2:
                                this.move(0.1, Direction.TurnRight);
                                this.imu.activate(-90, Direction.TurnRight);
                                break;
                            case 3:
                                this.move_stop();
                                this.delay(0.3);
                                break;
                            case 4:
                                this.move(0.05, Direction.TurnLeft);
                                this.imu.correction();
                                break;
                            case 5:
                                this.move_stop();
                                this.delay(0.3);
                                break;
                            case 6:
                                this.finish_step();
                                break;
                        }
                        break;
                }
                break;

            case "sub go junction":
                switch (step){
                    case 0:
                        this.move(0.25, 0.62, Direction.Forward);
                        break;
                    case 1:
                        this.delay(1);
                        break;
                    case 2:
                        this.finish_step();
                        break;
                }
                break;

            case "sub go to junction (accurate)":
                switch (step){
                    case 0:
                        this.move(0.1, 0.1, Direction.Forward);
                        break;
                    case 1:
                        this.delay(0.1);
                        break;
                    case 2:
                        this.finish_step();
                        break;
                }
                break;

            case "sub go back junction (accurate)":
                switch (step){
                    case 0:
                        this.move(0.1, 0.1, Direction.Backward);
                        break;
                    case 1:
                        this.delay(0.1);
                        break;
                    case 2:
                        this.finish_step();
                        break;
                }
                break;

            case "sub rotate left":
                switch (step){
                    case 0:
                        this.move(0.15, Direction.TurnLeft);
                        this.imu.activate(45, Direction.TurnLeft);
                        break;
                    case 1:
                        this.move_stop();
                        this.delay(0.3);
                        break;
                    case 2:
                        this.move(0.1, Direction.TurnRight);
                        this.imu.correction();
                        break;
                    case 3:
                        this.move_stop();
                        this.delay(0.3);
                        break;
                    case 4:
                        this.finish_step();
                        break;
                }
                break;

            case "sub rotate right":
                switch (step){
                    case 0:
                        this.move(0.15, Direction.TurnRight);
                        this.imu.activate(-45, Direction.TurnRight);
                        break;
                    case 1:
                        this.move_stop();
                        this.delay(0.3);
                        break;
                    case 2:
                        this.move(0.1, Direction.TurnLeft);
                        this.imu.correction();
                        break;
                    case 3:
                        this.move_stop();
                        this.delay(0.3);
                        break;
                    case 4:
                        this.finish_step();
                        break;
                }
                break;

            case "sub go home":
                switch (step){
                    case 0:
                        this.move(0.25, 0.65, Direction.Backward);
                        break;
                    case 1:
                        this.delay(1);
                        break;
                    case 2:
                        this.finish_step();
                        break;
                }
                break;

            case "sub rotate home left":
                switch (step){
                    case 0:
                        this.move(0.15, Direction.TurnLeft);
                        this.imu.activate(90, Direction.TurnLeft);
                        break;
                    case 1:
                        this.move_stop();
                        this.delay(0.3);
                        break;
                    case 2:
                        this.move(0.1, Direction.TurnRight);
                        this.imu.correction();
                        break;
                    case 3:
                        this.move_stop();
                        this.delay(0.3);
                        break;
                    case 4:
                        this.finish_step();
                        break;
                }
                break;

            case "sub rotate home right":
                switch (step){
                    case 0:
                        this.move(0.15, Direction.TurnRight);
                        this.imu.activate(-90, Direction.TurnRight);
                        break;
                    case 1:
                        this.move_stop();
                        this.delay(0.3);
                        break;
                    case 2:
                        this.move(0.1, Direction.TurnLeft);
                        this.imu.correction();
                        break;
                    case 3:
                        this.move_stop();
                        this.delay(0.3);
                        break;
                    case 4:
                        this.finish_step();
                        break;
                }
                break;

            case "sub go parking":
                switch (step){
                    case 0:
                        this.move(0.25, 0.62, Direction.Forward);
                        break;
                    case 1:
                        this.delay(1);
                        break;
                    case 2:
                        this.finish_step();
                        break;
                }
                break;

            case "sub go left parking":
                switch (step){
                    case 0:
                        this.move(0.25, 0.65, Direction.Left);
                        break;
                    case 1:
                        this.delay(1);
                        break;
                    case 2:
                        this.finish_step();
                        break;
                }
                break;

            case "sub go right parking":
                switch (step){
                    case 0:
                        this.move(0.3, 0.7, Direction.Right);
                        break;
                    case 1:
                        this.delay(1);
                        break;
                    case 2:
                        this.finish_step();
                        break;
                }
                break;

            default:
                this.finish_step();
                break;
        }
        this.step++;
    }
}