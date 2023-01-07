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
        private double target;
        private long finish_time = 0;
        private long prev_time = 0;
        private double speed;
        Telemetry telemetry;

        public void init(HardwareMap hardwaremap, Telemetry telemetry, String name, Direction direction, double initial_position)
        {
                motor = hardwaremap.get(Servo.class, name);
                this.dir = direction.get_value();
                motor.setPosition(initial_position * dir % 1);
                this.telemetry = telemetry;
                this.finish_time = System.currentTimeMillis();
        }

        public void update()
        {
                if(this.target != this.motor.getPosition()){
                        long delta_t = System.currentTimeMillis() - this.prev_time;
                        double delta_pos = this.speed * delta_t;
                        double prev_pos = this.motor.getPosition();
                        double pos = delta_pos + this.motor.getPosition();
                        if((prev_pos <= this.target && this.target <= pos) || (prev_pos >= this.target && this.target >= pos)) {
                                pos = this.target;
                        }
                        this.motor.setPosition(pos);
                }
        }

        public void move(double position, long delay)
        {
                double pos = this.motor.getPosition() + position * this.dir;
                if(pos < 0) pos = 0;
                else if(pos > 1) pos = 1;
                this.motor.setPosition(pos);
                this.target = pos;
                this.finish_time = System.currentTimeMillis() + delay;
        }

        public void move(double position, long delay, double speed)
        {
                double pos = this.motor.getPosition() + position * this.dir;
                if(speed < 0) speed *= -1;
                if(pos < this.motor.getPosition()) speed *= -1;
                if(pos < 0) pos = 0;
                else if(pos > 1) pos = 1;
                this.target = pos;
                this.finish_time = System.currentTimeMillis() + delay;
                this.speed = speed;
                this.prev_time = System.currentTimeMillis();
        }

        public boolean finish()
        {
                return System.currentTimeMillis() >= this.finish_time && this.target == this.motor.getPosition();
        }
}