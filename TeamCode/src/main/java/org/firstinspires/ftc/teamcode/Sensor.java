package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Sensor
{
        private DigitalChannel sensor;
        public boolean finish;
        private boolean reverse;
        private boolean active;
        private Telemetry telemetry;
        private boolean emergency;

        public void init(HardwareMap hardwaremap, Telemetry telemetry, String name, boolean reverse)
        {
                sensor = hardwaremap.get(DigitalChannel.class, name);
                sensor.setMode(DigitalChannel.Mode.INPUT);
                this.reverse = reverse;
                active = false;
                finish = true;
                this.telemetry = telemetry;
                this.emergency = false;
        }
        public void update()
        {
                if(active)
                {
                        if(reverse ^ sensor.getState())
                        {
                                finish = true;
                                active = false;
                        }
                }
                else if(emergency){
                        if(reverse ^ sensor.getState())
                        {
                        }
                }
        }
        public void activate()
        {
                active = true;
                finish = false;
        }

        public void set_reverse(boolean reverse){
                this.reverse = reverse;
        }
        public boolean finish()
        {
                return finish;
        }

        public void set_emergency_sensor(){
                this.emergency = true;
        }
}