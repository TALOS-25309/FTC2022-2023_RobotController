package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SMotor
{
        public enum Direction {
                Direct(1), Reverse(-1);
                private final int value;
                Direction(int i) {this.value = i;}
                int get_value() {return this.value;}
        };

        private Servo motor;
        private int dir; //1 or -1
        private boolean finish;
        private double position;
        private long finish_time = 0;
        private double i_pos;
        Telemetry telemetry;

        public void init(HardwareMap hardwaremap, Telemetry telemetry, String name, Direction direction, double initial_position)
        {
                motor = hardwaremap.get(Servo.class, name);
                finish = true;
                this.dir = direction.get_value();
                position = initial_position * dir % 1;
                motor.setPosition(position);
                this.telemetry = telemetry;
                this.finish_time = System.currentTimeMillis();
        }

        public void update()
        {

        }

        public void move(double position, long delay){
                double pos = this.position + position * this.dir;
                if(pos < 0) pos = 0;
                else if(pos > 1) pos = 1;
                this.motor.setPosition(pos);
                this.finish_time = System.currentTimeMillis() + delay;
        }

        public void move(double position, long delay, double speed){

        }

        public boolean finish()
        {
                return System.currentTimeMillis() >= this.finish_time;
        }
}