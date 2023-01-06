package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SMotor
{
        private Servo motor;
        private int dir; //1 or -1
        private boolean finish;
        private double d_angle;
        private double position;
        private double i_position;
        private double angle;
        private double unit = 0.001; //나중에 수정
        private Telemetry telemetry;

        public void init(HardwareMap hardwaremap, Telemetry telemetry, String name, int dir, double s_position)
        {
                motor = hardwaremap.get(Servo.class, name);
                finish = true;
                this.dir = dir;
                position = s_position*dir;
                motor.setPosition(position);
                this.telemetry = telemetry;
        }
        public void update()
        {
                if(!finish)
                {
                        position = position + d_angle;
                        if((angle>0)&&((position-(i_position+angle)) >= 0))
                        {
                                position = i_position + angle;
                        }
                        else if((angle<0)&&((position-(i_position+angle)) <= 0))
                        {
                                position = i_position + angle;
                        }
                        motor.setPosition(position);
                }
        }

        public void move(double speed, double angle)
        {
                finish = false;
                this.angle = angle*dir;
                i_position = position;
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