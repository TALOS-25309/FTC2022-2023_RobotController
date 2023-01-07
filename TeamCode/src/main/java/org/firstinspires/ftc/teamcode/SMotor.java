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
        private double d_angle;
        private double position;
        private double i_position;
        private double angle;
        private Telemetry telemetry;

        public void init(HardwareMap hardwaremap, Telemetry telemetry, String name, Direction direction, double initial_position)
        {
                motor = hardwaremap.get(Servo.class, name);
                finish = true;
<<<<<<< HEAD
=======
                this.dir = direction.get_value();
                position = initial_position * dir %1;
>>>>>>> 1800d84bbe07b60df4fb88f5e4a30a65d36926a1
                motor.setPosition(position);
                this.telemetry = telemetry;
        }
        public void update()
        {
                if(!finish)
                {
                        position = position + d_angle;
                        if((angle>0)&&((position + d_angle - (i_position+angle)) >= 0))
                        {
                                position = i_position + angle;
                                finish = true;
                        }
                        else if((angle<0)&&((position + d_angle -(i_position+angle)) <= 0))
                        {
                                position = i_position + angle;
                                finish = true;
                        }
                        motor.setPosition(position);
                }
        }

        public void move(double speed, double angle)
        {
                finish = false;
                this.angle = angle*dir;
                i_position = position;

                if(speed > 0.045)
                {
                        speed = 0.045;
                }
                if(angle > 0)
                {
                        d_angle = speed;
                }
                else
                {
                        d_angle = -speed;
                }
        }
        public boolean finish()
        {
                return finish;
        }
}