package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class DMotor
{
        public enum Direction {
                Direct(1), Reverse(-1);
                private final int value;
                Direction(int i) {this.value = i;}
                int get_value() {return this.value;}
        };

        private DcMotor motor;
        private double dir;
        private double tpr;
        private double i_position;
        private double angle;
        private double target_degree;
        private Gyro imu;
        private Telemetry telemetry;
        private boolean finish = true;
        private String mode = "";

        public void init(HardwareMap hardwaremap, Telemetry telemetry, String name, Direction direction)
        {
                motor = hardwaremap.get(DcMotor.class, name);
                motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                tpr = motor.getMotorType().getTicksPerRev();
                this.dir = direction.get_value();
                this.telemetry = telemetry;
                this.finish = true;
        }
        public void update()
        {
                if(!this.finish()){
                        switch (mode){
                                case "distance":
                                        if((angle>0)&&(get_position()-(i_position+angle) > 0))
                                        {
                                                motor.setPower(0.0);
                                                this.finish = true;
                                        }
                                        else if((angle<0)&&(get_position()-(i_position+angle) < 0))
                                        {
                                                motor.setPower(0.0);
                                                this.finish = true;
                                        }
                                        break;
                                case "degree":
                                        if(Math.abs(this.target_degree - this.imu.get_rotation(AngleUnit.DEGREES)) < 1){
                                                motor.setPower(0.0);
                                                this.finish = true;
                                        }
                                        break;
                        }
                }
        }

        public double get_position()
        {
                return motor.getCurrentPosition()/tpr;
        }
        public void move(double speed, double angle)
        {
                if(speed < 0) speed *= -1;
                i_position = get_position();
                this.angle = angle*dir;
                this.finish = false;
                this.mode = "distance";
                if(this.angle > 0)
                {
                        motor.setPower(speed);
                }
                else
                {
                        motor.setPower(-speed);
                }
        }

        public void move(double speed)
        {
                this.finish = true;
                motor.setPower(speed*dir);
        }

        public boolean finish()
        {
                return this.finish;
        }
}